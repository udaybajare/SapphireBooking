package com.sapphire.booking;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.sapphire.dao.InvoiceDao;
import com.sapphire.dao.LoginDetailsDao;
import com.sapphire.dao.OrderDao;
import com.sapphire.dao.OrganizationDao;
import com.sapphire.dao.RegistrationDao;
import com.sapphire.entity.EntryDetails;
import com.sapphire.entity.InvoiceDetails;
import com.sapphire.entity.LensPrices;
import com.sapphire.entity.OrderDetails;
import com.sapphire.entity.OrganizationDetails;
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

	@Autowired
	InvoiceDao invoiceDao;

	final static String ORDER_BOOKING_FORM = "OrderBooking";
	final static String ORDER_SUBMITTED = "OrderSubmitted";
	final static String ADD_ORGANIZATION_FORM = "addOrganization";
	final static String ORDER_LIST = "OrderList";
	final static String ADD_Invoice = "NonInvoice";

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

		if (userRole.equalsIgnoreCase("Admin")) {
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

		String custNo = orgDetails.getCustNumber();
		if (custNo == null || custNo.equals("")) {
			custNo = String.valueOf(organizationDao.getMaxCustNumber() + 1);
			orgDetails.setCustNumber(custNo);
		}

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
		if (userRole.equalsIgnoreCase("Admin")) {
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
			HttpSession session, String updatedItemPrice) throws Exception {
		String[] updatedItemPrices = updatedItemPrice.split(",");
		String[] lSourcingValues = lSourcing.split(",");
		String[] rSourcingValues = rSourcing.split(",");

		String userName = (String) session.getAttribute("userName");
		String orgName = "";
		String userRole = "";
		if (userName != null && !(userName.equals(""))) {
			userRole = loginDetailsDao.getUserRole(userName);
		}
		if (userRole.equalsIgnoreCase("Admin")) {
			orderDao.updateOrder(orderId, totalAmount, lSourcingValues, rSourcingValues, comment, updatedItemPrices);
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

		if (!(userRole.equalsIgnoreCase("Admin"))) {
			orgName = registrationDao.getRelatedOrganiztion(userName);

		}

		orderIdList = orderDao.getAllOrdersIds(orgName, userRole.equalsIgnoreCase("Admin"));

		StringBuilder orderListHTML = new StringBuilder();

		for (int orderId : orderIdList) {
			orderListHTML.append(bookingUtility.getOrderRowHTML(orderId, userRole.equalsIgnoreCase("Admin")));
		}

		if (!(orderListHTML.toString().trim().equals(""))) {
			modelAndView.addObject("orderList", orderListHTML.toString());
		} else {
			modelAndView.addObject("orderList", "No Orders to Show");
		}

		ArrayList<ArrayList<String>> registeredOrg = null;

		if (userRole.equalsIgnoreCase("Admin")) {
			registeredOrg = organizationDao.getRegisteredOrganization(null);
		} else {

			registeredOrg = organizationDao.getRegisteredOrganization(orgName);
		}

		String registeredOrgStr = bookingUtility.getOrganizationList(registeredOrg);

		modelAndView.addObject("organizationOptions", registeredOrgStr);

		if (userRole.equalsIgnoreCase("Admin")) {
			modelAndView.addObject("generateButton", generateButton);
		} else {
			modelAndView.addObject("generateButton", "");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/listOrdersHTML", method = RequestMethod.POST)
	protected  @ResponseBody  String listOrders(String criteria, String criteriaValue, String fromDate, String toDate,
			HttpSession session) throws Exception {

		ModelAndView modelAndView = new ModelAndView(ORDER_LIST);
		
		String userRole = "";
		String orgName = null;

		ArrayList<Integer> orderIdList = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String userName = (String) session.getAttribute("userName");
		if (userName != null && !(userName.equals(""))) {
			userRole = loginDetailsDao.getUserRole(userName);
		}
		if (userRole.equalsIgnoreCase("User")) {
			orgName = registrationDao.getRelatedOrganiztion(userName);
		}

		Date dateFromDate = null;

		if (fromDate != null && !(fromDate.equals(""))) {
			dateFromDate = dateFormat.parse(fromDate);
		} else {
			dateFromDate = dateFormat.parse("01/01/2020");
		}

		Date dateToDate = null;

		if (toDate != null && !(toDate.equals(""))) {
			dateToDate = dateFormat.parse(toDate);
		} else {
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
			orderListHTML.append(bookingUtility.getOrderRowHTML(orderId, userRole.equalsIgnoreCase("Admin")));
		}

		if (!(orderListHTML.toString().trim().equals(""))) {
			
			System.out.println("orderListHTML is : "+orderListHTML.toString());
			
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

				OrderDetails orderDetail = new OrderDetails(material.length > 0 ? material[i] : "",
						type.length > 0 ? type[i] : "", index.length > 0 ? index[i] : "",
						coating.length > 0 ? coating[i] : "", tint.length > 0 ? tint[i] : "", qtyNos[i],
						frameType.length > 0 ? frameType[i] : "", orgName, userName, mobileNo, orderDate, status, "",
						0.0, fitting.length > 0 ? fitting[i] : "",
						custOrderNumber.length > 0 ? custOrderNumber[i] : "");

				// get right and left lens price

				LensPrices lensPrice = bookingUtility.getPrise(material[i], 
						rSph.length>=i+1?rSph[i]:"", 
								rCyl.length>=i+1?rCyl[i]:"", 
								rAxis.length>=i+1?rAxis[i]:"", 
								rDia.length>=i+1?rDia[i]:"",
								rSourcing.length>=i+1?rSourcing[i]:"", 
								tint[i], type[i], 
								lSph.length>=i+1?lSph[i]:"", 
								lCyl.length>=i+1?lCyl[i]:"", 
								lAxis.length>=i+1?lAxis[i]:"", 
								lDia.length>=i+1?lDia[i]:"", 
								lSourcing.length>=i+1?lSourcing[i]:"", 
								coating[i],
								index[i], qtyNos[i], fitting[i]);

				// Add fitting price as well
				// totalPrice = totalPrice + FittingPrice

				entryDetails.add(
						new EntryDetails(rSph.length > 0 && !(rSph[i] == null || rSph[i].equals("")) ? rSph[i] : null,

								rCyl.length > 0 && !(rCyl[i] == null || rCyl[i].equals("")) ? rCyl[i] : null,
								rAxis.length > 0 && !(rAxis[i] == null || rAxis[i].equals("")) ? rAxis[i] : null,
								rAdd.length > 0 && !(rAdd[i] == null || rAdd[i].equals("")) ? rAdd[i] : null,
								rDia.length > 0 && !(rDia[i] == null || rDia[i].equals("")) ? rDia[i] : null,
								lSph.length > 0 && !(lSph[i] == null || lSph[i].equals("")) ? lSph[i] : null,
								lCyl.length > 0 && !(lCyl[i] == null || lCyl[i].equals("")) ? lCyl[i] : null,
								lAxis.length > 0 && !(lAxis[i] == null || lAxis[i].equals("")) ? lAxis[i] : null,
								lAdd.length > 0 && !(lAdd[i] == null || lAdd[i].equals("")) ? lAdd[i] : null,
								lDia.length > 0 && !(lDia[i] == null || lDia[i].equals("")) ? lDia[i] : null,
								String.valueOf(lensPrice.getlPrice() * Integer.parseInt(qtyNos[i])),
								String.valueOf(lensPrice.getrPrice() * Integer.parseInt(qtyNos[i])),
								lSourcing.length > 0 && !(lSourcing[i] == null || lSourcing[i].equals(""))
										? lSourcing[i] : null,
								rSourcing.length > 0 && !(rSourcing[i] == null || rSourcing[i].equals(""))
										? rSourcing[i] : null));

				orderDetails.add(orderDetail);

				totalAmount = totalAmount + lensPrice.getTotalPrice();

				for (int j = 0; j < orderDetails.size(); j++) {

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
			String[] tint, String[] qtyNos, String[] frameType, String[] lSourcing, String[] rSourcing, String[] rSph, String[] rCyl,
			String[] rAxis, String[] rAdd, String[] rDia, String[] lSph, String[] lCyl, String[] lAxis, String[] lAdd,
			String[] lDia, String[] fitting) {
		StringBuilder priseList = new StringBuilder();

		for (int i = 0; i < material.length; i++) {

			LensPrices lensPrice = bookingUtility.getPrise(material[i], 
					rSph.length>=i+1?rSph[i]:"", 
					rCyl.length>=i+1?rCyl[i]:"", 
					rAxis.length>=i+1?rAxis[i]:"", 
					rDia.length>=i+1?rDia[i]:"",
					rSourcing.length>=i+1?rSourcing[i]:"", 
					tint[i], type[i], 
					lSph.length>=i+1?lSph[i]:"", 
					lCyl.length>=i+1?lCyl[i]:"", 
					lAxis.length>=i+1?lAxis[i]:"", 
					lDia.length>=i+1?lDia[i]:"", 
					lSourcing.length>=i+1?lSourcing[i]:"", 
					coating[i],
					index[i], qtyNos[i], fitting[i]);

			priseList.append(String.valueOf(lensPrice.getTotalPrice()) + ",");

		}

		if (priseList.toString().split("0,").length == 0) {
			return "0";
		}

		return priseList.toString();
	}

	@RequestMapping(value = "/generateInvoice", method = RequestMethod.GET)
	protected ModelAndView generateInvoice() throws Exception {

		ModelAndView modelAndView = new ModelAndView(ADD_Invoice);

		ArrayList<ArrayList<String>> registeredOrg = organizationDao.getRegisteredOrganization(null);

		String registeredOrgStr = bookingUtility.getOrganizationList(registeredOrg);

		modelAndView.addObject("message", " ");

		modelAndView.addObject("organizationOptions", registeredOrgStr);
		modelAndView.addObject("invoiceDownload", "");

		return modelAndView;
	}

	@RequestMapping(value = "/generatenon", method = RequestMethod.POST)
	protected void generateNonInvoice(String orgName, String fromDate, String toDate, HttpServletResponse response)
			throws Exception {
		ArrayList<OrderDetails> orderDetailsList = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Date dateFromDate = dateFormat.parse(fromDate);
		Date dateToDate = dateFormat.parse(toDate);

		dateToDate.setHours(23);
		dateToDate.setMinutes(59);
		dateToDate.setSeconds(59);

		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		orderDetailsList = orderDao.getOrderDetailsFromCriteriaAndDate("organizationName", orgName,
				sDateFormat.format(dateFromDate), sDateFormat.format(dateToDate));

		byte[] excelByts = reportCreator.invoiceExcel(orderDetailsList);

		response.setHeader("Content-disposition", "attachment; filename=Invoice.xlsx");

		OutputStream out = response.getOutputStream();

		byte[] buffer = excelByts; // use bigger if you want
		int length = buffer.length;

		System.out.println("Buffer length is : " + length);
		out.write(buffer, 0, length);
		out.close();

	}

	@RequestMapping(value = "/generateGST", method = RequestMethod.POST)
	protected void generateGstInvoice(String invoiceNo, String orgName, String fromDate, String toDate, double discount,
			boolean gstNon, Double totalAmount, Double discountTotalAmount, String terms, String invoiceGenerateDate,
			HttpServletResponse response) throws Exception {

		ArrayList<OrderDetails> orderDetailsList = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateFromDate = dateFormat.parse(fromDate);
		Date dateToDate = dateFormat.parse(toDate);

		dateToDate.setHours(23);
		dateToDate.setMinutes(59);
		dateToDate.setSeconds(59);

		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		orderDetailsList = orderDao.getOrderDetailsFromCriteriaAndDate("organizationName", orgName,
				sDateFormat.format(dateFromDate), sDateFormat.format(dateToDate));

		ArrayList<Integer> orderIdList = new ArrayList<Integer>();
		totalAmount = 0.0;
		for (OrderDetails orderDetail : orderDetailsList) {
			if (!(orderDetailsList.contains(orderDetail.getOrderId()))) {

				orderIdList.add(orderDetail.getOrderId());
				double total = orderDetail.getTotalAmount();
				totalAmount += total;
			}
		}
		// totalAmount = orderDetail.getTotalAmount();

		discountTotalAmount = totalAmount - totalAmount * discount / 100;

		// year generation yy-yy
		Calendar c = Calendar.getInstance();
		int digyear = c.get(Calendar.YEAR);
		String yrStr = Integer.toString(digyear);
		String yrStr1 = Integer.toString(digyear - 1);
		String yrStrEnd = yrStr.substring(yrStr.length() - 2);
		String yrStrEnd1 = yrStr1.substring(yrStr1.length() - 2);
		int year = Integer.parseInt(yrStrEnd);
		int year1 = Integer.parseInt(yrStrEnd1);
		String financialYear = (year1 + "-" + year);

		// month generation in number
		int month = dateFromDate.getMonth();
		String mon = Integer.toString(month + 1);

		String lasttaxInvoiceNo = invoiceDao.getInvoiceNo();
		int lastNo = 0;
		if (lasttaxInvoiceNo.length() > 0) {
			lastNo = Integer.parseInt(lasttaxInvoiceNo.substring(lasttaxInvoiceNo.lastIndexOf("/") + 1));
		}

		invoiceGenerateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		invoiceNo = "SL/RV/" + financialYear + "/" + mon + "/" + String.valueOf(lastNo + 1);
		// Create Invoice Object and save using DAO
		InvoiceDetails invoiceDetail = new InvoiceDetails(invoiceNo, orgName, fromDate, toDate, gstNon, discount,
				totalAmount, discountTotalAmount, terms, invoiceGenerateDate);

		invoiceDao.saveInvoice(invoiceDetail);

		byte[] excelByts = reportCreator.gstExcel(orderDetailsList, discount, terms, invoiceNo);

		response.setHeader("Content-disposition", "attachment; filename=InvoiceGST.xlsx");

		OutputStream out = response.getOutputStream();
		byte[] buffer = excelByts; // use bigger if you want
		int length = buffer.length;

		System.out.println("Buffer length is : " + length);
		out.write(buffer, 0, length);
		out.close();

	}

	@RequestMapping(value = "/download", method = RequestMethod.POST)
	protected void downloadInvoice(String invoiceNo, String orgName, String fromDate, String toDate,
			HttpServletResponse response) {
		ArrayList<OrderDetails> orderDetailsList = null;

		InvoiceDetails downloadInvoiceDetail = invoiceDao.getInvoiceDetails(invoiceNo);

		Double discount = downloadInvoiceDetail.getDiscount();
		String terms = downloadInvoiceDetail.getTerms();

		// orgName
		// fromDate
		// toDate

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dateFromDate = dateFormat.parse(fromDate);
			Date dateToDate = dateFormat.parse(toDate);

			dateToDate.setHours(23);
			dateToDate.setMinutes(59);
			dateToDate.setSeconds(59);

			SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			orderDetailsList = orderDao.getOrderDetailsFromCriteriaAndDate("organizationName", orgName,
					sDateFormat.format(dateFromDate), sDateFormat.format(dateToDate));

			// orderDetailsList =
			// orderDao.getOrderDetailsFromCriteriaAndDate(orgaName,fromDate,toDate);

			byte[] excelByts = reportCreator.gstExcel(orderDetailsList, discount, terms, invoiceNo);

			response.setHeader("Content-disposition", "attachment; filename=InvoiceGST.xlsx");

			OutputStream out = response.getOutputStream();
			byte[] buffer = excelByts; // use bigger if you want
			int length = buffer.length;

			System.out.println("Buffer length is : " + length);
			out.write(buffer, 0, length);
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

	public static final String generateButton = "<button type=\"button\" class=\"btn btn-default\" style=\"margin-left: 3%;\" onClick=\"generateReport()\">Generate Report</button>";
	// public static final String invoiceButton = "<button type=\"button\"
	// class=\"btn btn-default\" onClick=\"generateInvoiceNonGst()\">NonGST
	// Invoice</button>";
}
