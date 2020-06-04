package com.sapphire.booking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
	protected ModelAndView login(LoginDetails loginDetails, HttpSession session) {

		String view = "";
		boolean validaLogin = validateLogin(loginDetails);
		if (validaLogin) {

			double sessionId = 0;
			// Call session DAO and add the entry of the sessionId created above
			// for the current userName

			boolean sessionSaved = false;

			while (!sessionSaved) {
				sessionId = Math.random();
				sessionSaved = sessionDao
						.saveSession(new SessionEntry(String.valueOf(sessionId), loginDetails.getUserName()));
			}

			session.setAttribute("userName", loginDetails.getUserName());
			session.setAttribute("sessionId", sessionId);

			view = "home";
		} else {
			view = "Login";

		}
		String userName = (String) session.getAttribute("userName");
		String orgName = "";
		String userRole = "";
		if (userName != null && !(userName.equals(""))) {
			userRole = loginDetailsDao.getUserRole(userName);
		}
		ModelAndView modelAndView = new ModelAndView(view);

		if (userRole.equalsIgnoreCase("Admin")) {
			modelAndView.addObject("invoiceButton", invoiceButton);
			modelAndView.addObject("userListButton", userListButton);
			modelAndView.addObject("addOrgOption", addOrgOption);
		} else {
			modelAndView.addObject("invoiceButton", "");
			modelAndView.addObject("userListButton", "");
			modelAndView.addObject("addOrgOption", "");
		}

		return modelAndView;

	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
		String userName = (String) session.getAttribute("userName");
		String orgName = "";
		String userRole = "";
		if (userName != null && !(userName.equals(""))) {
			userRole = loginDetailsDao.getUserRole(userName);
		}
		ModelAndView modelAndView = new ModelAndView("home");

		if (userRole.equalsIgnoreCase("Admin")) {
			modelAndView.addObject("invoiceButton", invoiceButton);
			modelAndView.addObject("userListButton", userListButton);
			modelAndView.addObject("addOrgOption", addOrgOption);
		} else {
			modelAndView.addObject("invoiceButton", "");
			modelAndView.addObject("userListButton", "");
			modelAndView.addObject("addOrgOption", "");
		}

		return modelAndView;
	}

	private boolean validateLogin(LoginDetails loginDetails) {
		boolean validLogin = false;

		String validPassword = loginDetailsDao
				.getPasswordToValidate(new LoginDetails(loginDetails.getUserName(), "", ""));

		if (validPassword.equalsIgnoreCase(loginDetails.getPassword())) {
			validLogin = true;
		}

		return validLogin;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	protected ModelAndView logout(HttpSession session, HttpServletRequest request) {
		double sessionId = (double) session.getAttribute("sessionId");
		boolean sessionDelete = true;

		// TODO Delete all the sessions for current userID

		String userName = (String) session.getAttribute("userName");

		request.getSession().invalidate();

		sessionDelete = sessionDao.deleteSession(String.valueOf(sessionId));

		return new ModelAndView("redirect:login");
	}

	public static final String invoiceButton = "<form id=\"addInvoice\" action=\"generateInvoice\" method=\"GET\"><img src=\"images/img/invoice.jpg\" alt=\"\" width=\"200\" height=\"200\" onClick=\"$('#addInvoice').submit();\"><button type=\"button\" class=\"btn btn-sm btn-default\" onClick=\"$('#addInvoice').submit();\" style=\"margin-top: 14%;margin-left: 12%\">Invoice</button></form>";
	public static final String userListButton = "<form id=\"userList\" action=\"listUser\" method=\"GET\"><img src=\"images/img/userlist1.png\" alt=\"\" width=\"170\" height=\"170\" onClick=\"$('#userList').submit();\"> <button type=\"button\" class=\"btn btn-sm btn-default\" onClick=\"$('#userList').submit();\" style=\"margin-top: 14%;margin-left: 12%\">User List</button></form>";
	public static final String addOrgOption = "<div class='col-md-3'><div class='form-group has-feedback'><form id='addOrg' action='addOrgForm' method='GET'> <img src='images/img/addOrg1.jpg' alt='' width='200' height='200' style='margin-top: 10%;' onClick=\"$('#addOrg').submit();\"><button type='button' class='btn btn-sm btn-default' onClick=\"$('#addOrg').submit();\" style='margin-top: 14%;margin-left: 12%'>Add Organization</button></form></div></div>";
}