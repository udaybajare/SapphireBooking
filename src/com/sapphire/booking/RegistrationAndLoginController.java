package com.sapphire.booking;

import java.util.ArrayList;
import java.util.List;

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
import com.sapphire.dao.OrganizationDao;
import com.sapphire.dao.RegistrationDao;
import com.sapphire.dao.SessionDao;
import com.sapphire.entity.LoginDetails;
import com.sapphire.entity.OrderDetails;
import com.sapphire.entity.SessionEntry;
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
	private LoginDetailsDao loginDetailsDao;
	@Autowired
	private BookingUtility bookingUtility;
	@Autowired
	private SessionDao sessionDao;

	final static String REGISTRATION_VIEW_NAME = "Registration";

	final static String REGISTRATION_COMPLETE = "RegistrationCompleted";
	final static String HOME_PAGE = "home";
	final static String USER_LIST = "UserList";

	@RequestMapping(value = "/registrationForm", method = RequestMethod.GET)
	protected ModelAndView registrationForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView(REGISTRATION_VIEW_NAME);
		ArrayList<ArrayList<String>> registeredOrg = organizationDao.getRegisteredOrganization(null);
		String registeredOrgStr = bookingUtility.getOrganizationList(registeredOrg);

		modelAndView.addObject("organizationOptions", registeredOrgStr);

		HttpSession session = request.getSession(false);

		if (session != null) {
			// update

			String userName = (String) session.getAttribute("userName");
			UserDetails currentUserDetails = null;
			currentUserDetails = registrationDao.getUserCurrentDetails(userName);
			
			if (currentUserDetails == null)
			{
				// currentUserDetails = new ArrayList<UserDetails>();
				currentUserDetails = new UserDetails("", "", "", "", "", "", "", "", "", "", "", "Admin");

				modelAndView.addObject("updateUserDetails", updateUserDetails);
				modelAndView.addObject("registerUserDetails", "");

			}

			modelAndView.addObject("customerName", currentUserDetails.getCustomerName());
			modelAndView.addObject("contactNumber", currentUserDetails.getContactNumber());

			modelAndView.addObject("userName", currentUserDetails.getUserName());
			modelAndView.addObject("password", currentUserDetails.getPassword());
			modelAndView.addObject("gstNo", currentUserDetails.getGstNo());
			modelAndView.addObject("email", currentUserDetails.getEmail());
			modelAndView.addObject("addressLine1", currentUserDetails.getAddressLine1());
			modelAndView.addObject("addressLine2", currentUserDetails.getAddressLine2());
			modelAndView.addObject("addressLine3", currentUserDetails.getAddressLine3());
			modelAndView.addObject("postalCode", currentUserDetails.getPostalCode());
			modelAndView.addObject("updateUserDetails", updateUserDetails);
			modelAndView.addObject("registerUserDetails", "");
			modelAndView.addObject("errorMessage", "");
		} 
		else 
		{
			modelAndView.addObject("customerName", "");
			modelAndView.addObject("contactNumber", "");
			modelAndView.addObject("userName", "");
			modelAndView.addObject("password", "");
			modelAndView.addObject("gstNo", "");
			modelAndView.addObject("email", "");
			modelAndView.addObject("addressLine1", "");
			modelAndView.addObject("addressLine2", "");
			modelAndView.addObject("addressLine3", "");
			modelAndView.addObject("postalCode", "");

			modelAndView.addObject("registerUserDetails", registerUserDetails);
			modelAndView.addObject("updateUserDetails", "");
			modelAndView.addObject("errorMessage", "");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	protected ModelAndView registerUser(String customerName, String addressLine1, String addressLine2,
			String addressLine3, String postalCode, String userName, String contactNumber, String email,
			String password, String gstNo, String orgName, HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String status = "Pending";
		boolean userPresent = false;
		UserDetails userDetails = registrationDao.getUserCurrentDetails(userName);

		if (userDetails == null) {
			userDetails = new UserDetails(customerName, addressLine1, addressLine2, addressLine3, postalCode, userName,
					contactNumber, email, password, gstNo, orgName, status);
		} else {
			userPresent = true;
			userDetails.setAddressLine1(addressLine1);
			userDetails.setAddressLine2(addressLine2);
			userDetails.setAddressLine3(addressLine3);
			userDetails.setContactNumber(contactNumber);
			userDetails.setCustomerName(customerName);
			userDetails.setEmail(email);
			userDetails.setGstNo(gstNo);
			userDetails.setPostalCode(postalCode);
			userDetails.setPassword(password);
		}
		boolean sessionPresent = sessionDao.isSessionIdPresent(new SessionEntry("",
				(String) (session.getAttribute("userName") != null ? session.getAttribute("userName") : "")));
		boolean success = false;
		
		try
		{
			success = registrationDao.saveNewUserDetails(userDetails, sessionPresent, userPresent);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		if (session.getAttribute("userName") == null)
		{
			session.invalidate();
		}

		if (success) 
		{
			if (sessionPresent)
			{
				modelAndView.setViewName(HOME_PAGE);
				modelAndView.addObject("invoiceButton", "");
				modelAndView.addObject("userListButton", "");
			}
			else
			{
				modelAndView.setViewName(REGISTRATION_COMPLETE);
			}
		} 
		else 
		{
			System.out.println("registration not completed");
			modelAndView.setViewName(REGISTRATION_VIEW_NAME);
			ArrayList<ArrayList<String>> registeredOrg = organizationDao.getRegisteredOrganization(null);
			String registeredOrgStr = bookingUtility.getOrganizationList(registeredOrg);

			modelAndView.addObject("organizationOptions", registeredOrgStr);

			modelAndView.addObject("customerName", "");
			modelAndView.addObject("contactNumber", "");
			modelAndView.addObject("userName", "");
			modelAndView.addObject("password", "");
			modelAndView.addObject("gstNo", "");
			modelAndView.addObject("email", "");
			modelAndView.addObject("addressLine1", "");
			modelAndView.addObject("addressLine2", "");
			modelAndView.addObject("addressLine3", "");
			modelAndView.addObject("postalCode", "");

			modelAndView.addObject("registerUserDetails", registerUserDetails);
			modelAndView.addObject("updateUserDetails", "");
		

			modelAndView.addObject("errorMessage", errorMessage);
		}
		return modelAndView;
	}

	/*
	 * @RequestMapping(value = "/home", method = RequestMethod.GET protected
	 * ModelAndView getHomePage() throws Exception { ModelAndView modelAndView =
	 * new ModelAndView(HOME_PAGE); return modelAndView; }
	 */

	@RequestMapping(value = "/listUser", method = RequestMethod.GET)
	protected ModelAndView listUser(HttpSession session) throws Exception {

		ModelAndView modelAndView = new ModelAndView(USER_LIST);
		ArrayList<UserDetails> userList = null;
		// Get all pending records from DB (userDetails)
		userList = registrationDao.getUserDetails();
		// for loop on Pending records
		String userString = bookingUtility.getUserList(userList);

		modelAndView.addObject("userList", userString);

		return modelAndView;
	}

	@RequestMapping(value = "/updateUserStatus", method = RequestMethod.POST)
	protected String updateUserStatus(String serialNumberCustomer, String status) throws Exception {
		registrationDao.updateUserStatus(serialNumberCustomer, status);

		UserDetails userDetails = registrationDao.userLoginDetails(serialNumberCustomer);

		if (userDetails != null) {
			loginDetailsDao
					.saveLoginDetails(new LoginDetails(userDetails.getUserName(), userDetails.getPassword(), "USER"));
		}
		return "redirect:/listUser";
	}

	@RequestMapping(value = "/deleteUserStatus", method = RequestMethod.POST)
	protected String deleteUserStatus(String serialNumberCustomer) throws Exception {
		registrationDao.deleteUserStatus(serialNumberCustomer);
		return "redirect:/listUser";
	}

	public static final String errorMessage = "<p class=\"lead\"><font size=\"2\" color=\"red\">Something went wrong. Please verify the information you entered and make sure the User Name/Email Address is not already registered.</font></p>";
	public static final String updateUserDetails = "<input type=\"submit\"  class=\"btn btn-default btn-lg\" value=\"update\" action=\"register\">";
	public static final String registerUserDetails = "<input type=\"submit\" href=\"#\" class=\"btn btn-default btn-lg\" value=\"Register\" action=\"register\">";
}
