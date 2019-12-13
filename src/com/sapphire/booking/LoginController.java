package com.sapphire.booking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sapphire.dao.LoginDetailsDao;
import com.sapphire.dao.SessionDao;
import com.sapphire.entity.LoginDetails;
import com.sapphire.entity.SessionEntry;

@Controller
@EnableWebMvc
public class LoginController {
	final static String VIEW = "Login";

	@Autowired
	LoginDetailsDao loginDetailsDao;
	
	@Autowired
	SessionDao sessionDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView modelAndView = new ModelAndView(VIEW);

		return modelAndView;
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	protected ModelAndView login(LoginDetails loginDetails, HttpSession session) 
	{

		String view = "";
		boolean validaLogin = validateLogin(loginDetails);
		if (validaLogin) {

			double sessionId = 0;
			// Call session DAO and add the entry of the sessionId created above
			// for the current userName

			boolean sessionSaved = false;

			while (!sessionSaved)
			{
				sessionId = Math.random();
				sessionSaved = sessionDao.saveSession(new SessionEntry(String.valueOf(sessionId), loginDetails.getUserName()));
			}

			session.setAttribute("userName", loginDetails.getUserName());
			session.setAttribute("sessionId", sessionId);

			view = "Home";
		} else {
			view = "Login";

		}

		ModelAndView modelAndView = new ModelAndView(view);

		return modelAndView;

	}

	private boolean validateLogin(LoginDetails loginDetails) {
		boolean validLogin = false;

		String validPassword = loginDetailsDao.getPasswordToValidate(new LoginDetails(loginDetails.getUserName(), ""));

		if (validPassword.equalsIgnoreCase(loginDetails.getPassword())) {
			validLogin = true;
		}

		return validLogin;
	}
}