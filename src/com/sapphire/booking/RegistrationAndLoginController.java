package com.sapphire.booking;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sapphire.dao.OrganizationDao;
import com.sapphire.dao.RegistrationDao;
import com.sapphire.entity.UserDetails;
import com.sapphire.utils.BookingUtility;

@Controller
@EnableWebMvc
public class RegistrationAndLoginController {

    @Autowired
    private RegistrationDao registrationDao;
    @Autowired
    private OrganizationDao organizationDao;
    @Autowired
    private BookingUtility bookingUtility;

    final static String REGISTRATION_VIEW_NAME = "Registration";

    final static String REGISTRATION_COMPLETE = "RegistrationCompleted";
    final static String HOME_PAGE = "home";

    @RequestMapping(value = "/registrationForm", method = RequestMethod.GET)
    protected ModelAndView registrationForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

	ModelAndView modelAndView = new ModelAndView(REGISTRATION_VIEW_NAME);

	List<Object[]> registeredOrg = organizationDao.getRegisteredOrganization();
	String registeredOrgStr = bookingUtility.getOrganizationList(registeredOrg);

	modelAndView.addObject("organizationOptions", registeredOrgStr);
	return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    protected ModelAndView registerUser(UserDetails userDetails) throws Exception {
	ModelAndView modelAndView = new ModelAndView(REGISTRATION_COMPLETE);

	registrationDao.saveNewUserDetails(userDetails);
	return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    protected ModelAndView getHomePage() throws Exception {
	ModelAndView modelAndView = new ModelAndView(HOME_PAGE);
	return modelAndView;
    }

}
