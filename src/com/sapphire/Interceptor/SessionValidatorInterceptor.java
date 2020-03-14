package com.sapphire.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sapphire.dao.SessionDao;
import com.sapphire.entity.SessionEntry;

public class SessionValidatorInterceptor extends HandlerInterceptorAdapter {
	
	
	
	@Autowired
	SessionDao sessionDao;
	// before the actual handler will be executed

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		boolean isSessionvalid = false;
		// Get Seesion_Id from HTTPSession or Cookie
		HttpSession session = request.getSession();

		String sessionId = String.valueOf(session.getAttribute("sessionId"));
		String userName = (String) session.getAttribute("userName");

		// Call sessionDao to check if the sesssion exists in DB or not
		SessionEntry sessionEntry = new SessionEntry();
		sessionEntry.setSessionId(sessionId);
		sessionEntry.setUserName(userName);

		isSessionvalid = sessionDao.isSessionIdPresent(sessionEntry);
		if (isSessionvalid) {
			//response.sendRedirect("home");
			isSessionvalid = true;
		} else {
			response.sendRedirect("login");
			isSessionvalid = false;
		}
		return isSessionvalid;

	}
}