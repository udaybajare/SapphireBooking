package com.sapphire.booking;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sapphire.dao.LoginDetailsDao;
import com.sapphire.dao.OrderDao;
import com.sapphire.dao.OrganizationDao;
import com.sapphire.dao.RegistrationDao;
import com.sapphire.entity.EntryDetails;
import com.sapphire.entity.OrderDetails;
import com.sapphire.entity.OrganizationDetails;

import com.sapphire.entity.UserDetails;
import com.sapphire.utils.BookingUtility;
import com.sapphire.utils.ReportCreator;

@Controller
@EnableWebMvc
public class OrderController {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrganizationDao organizationDao;

	@Autowired
	private BookingUtility bookingUtility;

	@Autowired
	ReportCreator reportCreator;

	@Autowired
	LoginDetailsDao loginDetailsDao;

	@Autowired
	RegistrationDao registrationDao;

	final static String ORDER_BOOKING_FORM = "OrderBooking";
	final static String ORDER_SUBMITTED = "OrderSubmitted";
	final static String ADD_ORGANIZATION_FORM = "addOrganization";
	final static String ORDER_LIST = "OrderList";

	@RequestMapping(value = "/bookorder", method = RequestMethod.GET)
	protected ModelAndView bookOrderForm(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {

		ModelAndView modelAndView = new ModelAndView(ORDER_BOOKING_FORM);

		String userName = (String) session.getAttribute("userName");

		String userRole = "";
		String orgName = "";
		if (userName != null && !(userName.equals(""))) {
			userRole = loginDetailsDao.getUserRole(userName);
		}

		ArrayList<ArrayList<String>> registeredOrg = organizationDao.getRegisteredOrganization(null);

		if (userRole.equals("Admin")) {
			registeredOrg = organizationDao.getRegisteredOrganization(null);

			modelAndView.addObject("message", "");

		} else {
			orgName = registrationDao.getRelatedOrganiztion(userName);

			registeredOrg = organizationDao.getRegisteredOrganization(orgName);

			modelAndView.addObject("message", "");
		}

		String registeredOrgStr = bookingUtility.getOrganizationList(registeredOrg);
		modelAndView.addObject("organizationOptions", registeredOrgStr);

		return modelAndView;
	}

	@RequestMapping(value = "/addOrgForm", method = RequestMethod.GET)
	protected ModelAndView addOrganizationForm() throws Exception {

		ModelAndView modelAndView = new ModelAndView(ADD_ORGANIZATION_FORM);

		int nextCustNumber = organizationDao.getMaxCustNumber() + 1;

		String nextCustNumberStr = String.valueOf(nextCustNumber);

		int custNolength = nextCustNumberStr.length();
		int zerosToAdd = 4 - custNolength;

		nextCustNumberStr = String.valueOf(nextCustNumber);

		for (int i = 0; i < zerosToAdd; i++) {
			nextCustNumberStr = "0" + nextCustNumberStr;
		}

		modelAndView.addObject("message", " ");
		modelAndView.addObject("nextCustNumber", nextCustNumberStr);

		return modelAndView;
	}

	@RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
	protected ModelAndView addOrganization(OrganizationDetails orgDetails, HttpSession session) throws Exception {

		ModelAndView modelAndView = new ModelAndView(ORDER_BOOKING_FORM);

		// boolean searchOrganization = true;

		boolean orgExist = organizationDao.searchOrganization(orgDetails.getOrgName());

		// Check if Org already exixts

		if (orgExist) {
			modelAndView.addObject("message", "Org already exists. you can proceed with your bookings");
		} else {
			organizationDao.addOrganization(orgDetails);
			modelAndView.addObject("message", "Org Successfully Registred");
		}

		// if user is Admin => all organization list
		// else only one organization
		String userName = (String) session.getAttribute("userName");
		String orgName = "";
		String userRole = "";
		if (userName != null && !(userName.equals(""))) {
			userRole = loginDetailsDao.getUserRole(userName);
		}

		ArrayList<ArrayList<String>> registeredOrg = organizationDao.getRegisteredOrganization(null);

		String registeredOrgStr = bookingUtility.getOrganizationList(registeredOrg);
		if (userRole.equals("Admin")) {
			modelAndView.addObject("organizationOptions", registeredOrgStr);
		} else {
			modelAndView.addObject("organizationOptions", "");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	protected String updateStatus(String orderId, String status) throws Exception {
		orderDao.updateStatus(orderId, status);
		return "redirect:/listOrders";
	}

	@RequestMapping(value = "/updateTotal", method = RequestMethod.POST)

	protected String updateTotal(String orderId, Double totalAmount, String lSourcing, String rSourcing, String comment,
			HttpSession session) throws Exception {

		String[] lSourcingValues = lSourcing.split(",");
		String[] rSourcingValues = rSourcing.split(",");

		String userName = (String) session.getAttribute("userName");
		String orgName = "";
		String userRole = "";
		if (userName != null && !(userName.equals(""))) {
			userRole = loginDetailsDao.getUserRole(userName);
		}
		if (userRole.equals("Admin")) {
			orderDao.updateOrder(orderId, totalAmount, lSourcingValues, rSourcingValues, comment);
		} else {

		}

		return "redirect:/listOrders";
	}

	@RequestMapping(value = "/listOrders", method = RequestMethod.GET)
	protected ModelAndView listOrders(HttpSession session) throws Exception {

		ModelAndView modelAndView = new ModelAndView(ORDER_LIST);

		ArrayList<Integer> orderIdList = null;

		String userName = (String) session.getAttribute("userName");

		String orgName = "";
		String userRole = "";

		if (userName != null && !(userName.equals(""))) {
			userRole = loginDetailsDao.getUserRole(userName);
		}

		if (!(userRole.equals("Admin"))) {
			orgName = registrationDao.getRelatedOrganiztion(userName);

		}

		orderIdList = orderDao.getAllOrdersIds(orgName);

		StringBuilder orderListHTML = new StringBuilder();

		for (int orderId : orderIdList) {
			orderListHTML.append(bookingUtility.getOrderRowHTML(orderId, userRole.equals("Admin")));
		}

		if (!(orderListHTML.toString().trim().equals(""))) {
			modelAndView.addObject("orderList", orderListHTML.toString());
		} else {
			modelAndView.addObject("orderList", "No Orders to Show");
		}

		ArrayList<ArrayList<String>> registeredOrg = null;

		if (userRole.equals("Admin")) {
			registeredOrg = organizationDao.getRegisteredOrganization(null);
		} else {

			registeredOrg = organizationDao.getRegisteredOrganization(orgName);
		}

		String registeredOrgStr = bookingUtility.getOrganizationList(registeredOrg);

		modelAndView.addObject("organizationOptions", registeredOrgStr);

		if (userRole.equals("Admin")) {
			modelAndView.addObject("generateButton", generateButton);
		} else {
			modelAndView.addObject("generateButton", "");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/listOrdersHTML", method = RequestMethod.POST)
	protected @ResponseBody String listOrders(String criteria, String criteriaValue, String fromDate, String toDate,
			HttpSession session) throws Exception {
		String userRole = "";
		String orgName = null;
		ArrayList<Integer> orderIdList = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String userName = (String) session.getAttribute("userName");		
		if (userName != null && !(userName.equals(""))) {
			 userRole = loginDetailsDao.getUserRole(userName);
		}
		if(userRole.equalsIgnoreCase("User")){
			orgName = registrationDao.getRelatedOrganiztion(userName);
		}
		
		Date dateFromDate = null;
		
		if(fromDate!=null && !(fromDate.equals("")))
		{
			dateFromDate = dateFormat.parse(fromDate);
		}
		else
		{
			dateFromDate = dateFormat.parse("01/01/2020");
		}
		
		Date dateToDate = null;
		
		if(toDate!=null && !(toDate.equals("")))
		{
			dateToDate = dateFormat.parse(toDate);
		}
		else
		{
			dateToDate = new Date();
		}
		
		
		dateToDate.setHours(23);
		dateToDate.setMinutes(59);
		dateToDate.setSeconds(59);
		
		

		
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		orderIdList = orderDao.getOrderDetailsFromCriteriaAndDate(criteria, criteriaValue,
		sDateFormat.format(dateFromDate), sDateFormat.format(dateToDate), orgName);

		if (userName != null && !(userName.equals(""))) {
			userRole = loginDetailsDao.getUserRole(userName);
		}

		StringBuilder orderListHTML = new StringBuilder();

		for (int orderId : orderIdList) {
			orderListHTML.append(bookingUtility.getOrderRowHTML(orderId, userRole.equals("Admin")));
		}

		if (!(orderListHTML.toString().trim().equals(""))) {
			return orderListHTML.toString();
		} else {
			return "No matching orders found.";
		}
	}

	@RequestMapping(value = "/generateReport", method = RequestMethod.POST)
	protected void generateReport(String criteria, String criteriaValue, HttpServletResponse response)
			throws Exception {

		ArrayList<Integer> orderIdList = null;

		ArrayList<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
		orderIdList = orderDao.getAllOrdersIds(criteria, criteriaValue);

		for (int orderId : orderIdList) {
			ArrayList<OrderDetails> orderDetailsSubList = orderDao.getAllOrders(orderId);

			for (OrderDetails orderDetail : orderDetailsSubList) {
				orderDetailsList.add(orderDetail);
			}
		}

		byte[] excelByts = reportCreator.writeExcel(orderDetailsList);

		response.setHeader("Content-disposition", "attachment; filename=OrderList.xlsx");

		OutputStream out = response.getOutputStream();

		byte[] buffer = excelByts; // use bigger if you want
		int length = buffer.length;

		System.out.println("Buffer length is : " + length);
		out.write(buffer, 0, length);
		out.close();
	}

	@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
	protected ModelAndView confirmOrder(String orgName, String userName, String mobileNo, String[] material,
			String[] type, String[] index, String[] coating, String[] tint, String[] qtyNos, String[] frameType,
			String[] fitting, String[] custOrderNumber, String[] lSourcing, String[] rSourcing, String[] rSph,
			String[] rCyl, String[] rAxis, String[] rAdd, String[] rDia, String[] lSph, String[] lCyl, String[] lAxis,
			String[] lAdd, String[] lDia) throws Exception {

		ModelAndView modelAndView = new ModelAndView(ORDER_SUBMITTED);

		List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
		List<EntryDetails> entryDetails = new ArrayList<EntryDetails>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Date orderDate = new Date();

		/* Date orderDateStr = dateFormat.format(orderDate); */

		String status = "Pending";

		Double totalAmount = 0.0;

		if (material != null) {
			for (int i = 0; i < material.length; i++) {

				
				OrderDetails orderDetail = new OrderDetails(material.length>0?material[i]:"", type.length>0?type[i]:"",index.length>0?index[i]:"", coating.length>0?coating[i]:"", tint.length>0?tint[i]:"", qtyNos[i], frameType.length>0?frameType[i]:"", orgName, userName, mobileNo, orderDate, status, "", 0.0, fitting.length>0?fitting[i]:"",	custOrderNumber.length>0?custOrderNumber[i]:"");


				// get right and left lens price

				int rPrise = 0;
				int lPrise = 0;

				int totalPrice = 0;

				if (material[i].equalsIgnoreCase("Glass")) {

					rPrise = bookingUtility.getGlassLensePrice(
							rSph.length > 0 && rSph[i] != null && !(rSph[i].trim().equals("")) ? Float.parseFloat(rSph[i])
									: null,
							rCyl.length > 0 && rCyl[i] != null && !(rCyl[i].trim().equals("")) ? Float.parseFloat(rCyl[i])
									: null,
							tint[i], type[i]);

					lPrise = bookingUtility.getGlassLensePrice(
							lSph.length > 0 && lSph[i] != null && !(lSph[i].trim().equals("")) ? Float.parseFloat(lSph[i])
									: null,
							lCyl.length > 0 && lCyl[i] != null && !(lCyl[i].trim().equals("")) ? Float.parseFloat(lCyl[i])
									: null,
							tint[i], type[i]);

					System.out.println(rPrise);
					System.out.println(lPrise);

				} else if (material[i].equalsIgnoreCase("CR")) {
					rPrise = bookingUtility.getCRLensePrice(type[i], tint[i], index[i],

							rSph.length > 0 && rSph[i] != null && !(rSph[i].trim().equals("")) ? rSph[i] : null,
							rCyl.length > 0 && rCyl[i] != null && !(rCyl[i].trim().equals("")) ? rCyl[i] : null, coating[i]);
					

					lPrise = bookingUtility.getCRLensePrice(type[i], tint[i], index[i],

							lSph.length > 0 && lSph[i] != null && !(lSph[i].trim().equals("")) ? lSph[i] : null,
							lCyl.length > 0 && lCyl[i] != null && !(lCyl[i].trim().equals("")) ? lCyl[i] : null, coating[i]);


					if (Math.ceil(Float.parseFloat(
							lCyl.length > 0 && lCyl[i] != null && !(lCyl[i].trim().equals("")) ? lCyl[i] : "0")) > 4) {
						rPrise = rPrise != 0 ? Math.addExact(rPrise, 25) : rPrise;
						lPrise = lPrise != 0 ? Math.addExact(lPrise, 25) : lPrise;
					}

				}


				int remainder = 10 - (lPrise+rPrise)%10;
				if (remainder == 10)
				{
					remainder = 0;
				}
				totalPrice = ((((lPrise+rPrise)+remainder)/10)*10) * Integer.parseInt(qtyNos[i]);
				
				//Add fitting price as well 
				// totalPrice = totalPrice + FittingPrice
				
				entryDetails.add(new EntryDetails(
						rSph.length > 0 && !(rSph[i] == null || rSph[i].equals("")) ? rSph[i] : null,

						rCyl.length > 0 && !(rCyl[i] == null || rCyl[i].equals("")) ? rCyl[i] : null, 
						rAxis.length > 0 && !(rAxis[i] == null || rAxis[i].equals("")) ? rAxis[i] : null, 
						rAdd.length > 0 && !(rAdd[i] == null || rAdd[i].equals("")) ? rAdd[i] : null,
						rDia.length > 0 && !(rDia[i] == null || rDia[i].equals("")) ? rDia[i] : null, 
						lSph.length > 0 && !(lSph[i] == null || lSph[i].equals("")) ? lSph[i] : null,
						lCyl.length > 0 && !(lCyl[i] == null || lCyl[i].equals("")) ? lCyl[i] : null,
						lAxis.length > 0 && !(lAxis[i] == null || lAxis[i].equals("")) ? lAxis[i] : null, 
						lAdd.length > 0 && !(lAdd[i] == null || lAdd[i].equals("")) ? lAdd[i] : null,
						lDia.length > 0 && !(lDia[i] == null || lDia[i].equals("")) ? lDia[i] : null, 
						String.valueOf(lPrise * Integer.parseInt(qtyNos[i])),
						String.valueOf(rPrise * Integer.parseInt(qtyNos[i])),
						lSourcing.length > 0 && !(lSourcing[i] == null || lSourcing[i].equals("")) ? lSourcing[i] : null,
						rSourcing.length > 0 && !(rSourcing[i] == null || rSourcing[i].equals("")) ? rSourcing[i] : null));
				
							
				orderDetails.add(orderDetail);

				totalAmount = totalAmount + totalPrice;	
				
				for(int j=0; j<orderDetails.size(); j++)
				{
					orderDetails.get(j).setTotalAmount(totalAmount);
				}
			}

			orderDao.saveInventory(orderDetails, entryDetails);
		} else {
			// Add Logic to return error message
		}
		return modelAndView;
	}

	@RequestMapping(value = "/getItemWisePrice", method = RequestMethod.POST)
	protected @ResponseBody String getItemWisePrice(String[] material, String[] type, String[] index, String[] coating,
			String[] tint, String[] qtyNos, String[] frameType, String[] sourcing, String[] rSph, String[] rCyl,
			String[] rAxis, String[] rAdd, String[] rDia, String[] lSph, String[] lCyl, String[] lAxis, String[] lAdd,
			String[] lDia) {
		StringBuilder priseList = new StringBuilder();

		for (int i = 0; i < material.length; i++) {
			int itemTotal = 0;
			int totalPrice = 0;
			int rPrise = 0;
			int lPrise = 0;

			if (material[i].equalsIgnoreCase("Glass")) {


				 rPrise = bookingUtility.getGlassLensePrice(
						rSph.length > 0 && rSph[i] != null && !(rSph[i].trim().equals("")) ? Float.parseFloat(rSph[i]) : null,
						rCyl.length > 0 && rCyl[i] != null && !(rCyl[i].trim().equals("")) ? Float.parseFloat(rCyl[i]) : null,
						tint[i], type[i]);


				 lPrise = bookingUtility.getGlassLensePrice(
						lSph.length > 0 && lSph[i] != null && !(lSph[i].trim().equals("")) ? Float.parseFloat(lSph[i]) : null,
						lCyl.length > 0 && lCyl[i] != null && !(lCyl[i].trim().equals("")) ? Float.parseFloat(lCyl[i]) : null,
						tint[i], type[i]);
				
				itemTotal = Math.addExact(rPrise, lPrise);

			} else if (material[i].equalsIgnoreCase("CR")) {

				 rPrise = bookingUtility.getCRLensePrice(type[i], tint[i], index[i],
						rSph.length > 0 && rSph[i] != null && !(rSph[i].trim().equals("")) ? rSph[i] : null,
						rCyl.length > 0 && rCyl[i] != null && !(rCyl[i].trim().equals("")) ? rCyl[i] : null, coating[i]);
				 lPrise = bookingUtility.getCRLensePrice(type[i], tint[i], index[i],
						lSph.length > 0 && lSph[i] != null && !(lSph[i].trim().equals("")) ? lSph[i] : null,
						lCyl.length > 0 && lCyl[i] != null && !(lCyl[i].trim().equals("")) ? lCyl[i] : null, coating[i]);

				if (Math.ceil((lCyl.length > 0 && !(lCyl[i].trim().equals("")))? Float.parseFloat(lCyl[i]) : 0) > 4) {
					rPrise = rPrise != 0 ? Math.addExact(rPrise, 50) : rPrise;
					lPrise = lPrise != 0 ? Math.addExact(lPrise, 50) : lPrise;
				}

			}

			int remainder  = 10 - (lPrise+rPrise)%10;
			
			if (remainder == 10)
			{
				remainder = 0;
			}
			totalPrice = ((((lPrise+rPrise)+remainder)/10)*10) * Integer.parseInt(qtyNos[i]);
			
			priseList.append(String.valueOf(totalPrice) + ",");
		}

		if (priseList.toString().split("0,").length == 0) {
			return "0";
		}

		return priseList.toString();
	}

	@RequestMapping(value = "/generateInvoiceByOrdId", method = RequestMethod.GET)
	protected void generateInvoice(int orderId) throws Exception {

		ArrayList<OrderDetails> orderDetailsList = orderDao.getAllOrders(orderId);

		ArrayList<EntryDetails> entryDetails = new ArrayList<>();

		for (int i = 0; i < orderDetailsList.size(); i++) {
			EntryDetails ed = orderDao.getEntryDetails(orderDetailsList.get(i).getOrderId(),
					orderDetailsList.get(i).getId());
			entryDetails.add(ed);
		}

		for (OrderDetails od : orderDetailsList) {
			System.out.println(od.toString());
		}
		for (EntryDetails de : entryDetails) {
			System.out.println(de.toString());
		}
		return;
	}

	public static final String generateButton = "<button type=\"button\" class=\"btn btn-default\" onClick=\"generateReport()\">Generate Report</button>";
}
