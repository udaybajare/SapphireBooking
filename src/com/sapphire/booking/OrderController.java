package com.sapphire.booking;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sapphire.dao.OrderDao;
import com.sapphire.dao.OrganizationDao;
import com.sapphire.entity.EntryDetails;
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

	final static String ORDER_BOOKING_FORM = "OrderBooking";
	final static String ORDER_SUBMITTED = "OrderSubmitted";
	final static String ADD_ORGANIZATION_FORM = "addOrganization";
	final static String ORDER_LIST = "OrderList";

	@RequestMapping(value = "/bookorder", method = RequestMethod.GET)
	protected ModelAndView bookOrderForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView(ORDER_BOOKING_FORM);

		ArrayList<String> registeredOrg = organizationDao.getRegisteredOrganization();

		String registeredOrgStr = bookingUtility.getOrganizationList(registeredOrg);

		modelAndView.addObject("organizationOptions", registeredOrgStr);

		return modelAndView;
	}

	@RequestMapping(value = "/addOrgForm", method = RequestMethod.GET)
	protected ModelAndView addOrganizationForm() throws Exception {

		ModelAndView modelAndView = new ModelAndView(ADD_ORGANIZATION_FORM);

		return modelAndView;
	}

	@RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
	protected ModelAndView addOrganization(OrganizationDetails orgDetails) throws Exception {

		ModelAndView modelAndView = new ModelAndView(ORDER_BOOKING_FORM);

		organizationDao.addOrganization(orgDetails);

		ArrayList<String> registeredOrg = organizationDao.getRegisteredOrganization();

		String registeredOrgStr = bookingUtility.getOrganizationList(registeredOrg);

		modelAndView.addObject("organizationOptions", registeredOrgStr);

		return modelAndView;
	}

	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	protected String updateStatus(String orderId, String status) throws Exception {
		orderDao.updateStatus(orderId, status);
		return "redirect:/listOrders";
	}

	@RequestMapping(value = "/listOrders", method = RequestMethod.GET)
	protected ModelAndView listOrders() throws Exception {

		ModelAndView modelAndView = new ModelAndView(ORDER_LIST);

		ArrayList<Integer> orderIdList = null;

		orderIdList = orderDao.getAllOrdersIds();

		StringBuilder orderListHTML = new StringBuilder();

		for (int orderId : orderIdList) {
			orderListHTML.append(bookingUtility.getOrderRowHTML(orderId));
		}

		ArrayList<String> registeredOrg = organizationDao.getRegisteredOrganization();
		String registeredOrgStr = bookingUtility.getOrganizationList(registeredOrg);

		if (!(orderListHTML.toString().trim().equals(""))) {
			modelAndView.addObject("orderList", orderListHTML.toString());
		} else {
			modelAndView.addObject("orderList", "No Orders to Show");
		}
		modelAndView.addObject("organizationOptions", registeredOrgStr);

		return modelAndView;
	}

	@RequestMapping(value = "/listOrdersHTML", method = RequestMethod.POST)
	protected @ResponseBody String listOrders(String criteria, String criteriaValue) throws Exception {

		ArrayList<Integer> orderIdList = null;

		orderIdList = orderDao.getAllOrdersIds(criteria, criteriaValue);
		StringBuilder orderListHTML = new StringBuilder();

		for (int orderId : orderIdList) {
			orderListHTML.append(bookingUtility.getOrderRowHTML(orderId));
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
			String[] sourcing, String[] rSph, String[] rCyl, String[] rAxis, String[] rAdd, String[] rDia,
			String[] lSph, String[] lCyl, String[] lAxis, String[] lAdd, String[] lDia) throws Exception {

		ModelAndView modelAndView = new ModelAndView(ORDER_SUBMITTED);

		List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
		List<EntryDetails> entryDetails = new ArrayList<EntryDetails>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Date orderDate = new Date();

		String orderDateStr = dateFormat.format(orderDate);
		String status = "Pending";
		if (material != null) {
			for (int i = 0; i < material.length; i++) {
				orderDetails.add(new OrderDetails(material[i], type[i], index[i], coating[i], tint[i], qtyNos[i],
						frameType[i], sourcing[i], orgName, userName, mobileNo, orderDateStr, status));

				// get right and left lence price

				int rPrise = 0;
				int lPrise = 0;

				if (material[i].equalsIgnoreCase("Glass")) {

					rPrise = bookingUtility.getGlassLensePrice(
							rSph.length > 0 && !(rSph[i] == null || rSph[i].equals("")) ? Float.parseFloat(rSph[i])
									: null,
							rCyl.length > 0 && !(rCyl[i] == null || rCyl[i].equals("")) ? Float.parseFloat(rCyl[i])
									: null,
							tint[i], type[i], Integer.parseInt(qtyNos[i]));

					lPrise = bookingUtility.getGlassLensePrice(
							lSph.length > 0 && !(lSph[i] == null || lSph[i].equals("")) ? Float.parseFloat(lSph[i])
									: null,
							lCyl.length > 0 && !(lCyl[i] == null || lCyl[i].equals("")) ? Float.parseFloat(lCyl[i])
									: null,
							tint[i], type[i], Integer.parseInt(qtyNos[i]));
					System.out.println(rPrise);
					System.out.println(lPrise);

				} else if (material[i].equalsIgnoreCase("CR")) {
					rPrise = bookingUtility.getCRLensePrice(type[i], tint[i], index[i],
							rSph.length > 0 && !(rSph[i] == null || rSph[i].equals("")) ? rSph[i] : null,
							rCyl.length > 0 && !(rCyl[i] == null || rCyl[i].equals("")) ? rCyl[i] : null, coating[i],
							Integer.parseInt(qtyNos[i]));
					lPrise = bookingUtility.getCRLensePrice(type[i], tint[i], index[i],
							lSph.length > 0 && !(lSph[i] == null || lSph[i].equals("")) ? lSph[i] : null,
							lCyl.length > 0 && !(lCyl[i] == null || lCyl[i].equals("")) ? lCyl[i] : null, coating[i],
							Integer.parseInt(qtyNos[i]));

					if (Math.ceil(Float.parseFloat(lCyl[i])) > 4) {
						rPrise = rPrise != 0 ? Math.addExact(rPrise, 50) : rPrise;
						lPrise = lPrise != 0 ? Math.addExact(lPrise, 50) : lPrise;
					}

				}

				entryDetails.add(new EntryDetails(
						rSph.length > 0 && !(rSph[i] == null || rSph[i].equals("")) ? rSph[i] : null,
						rCyl.length > 0 && !(rCyl[i] == null || rCyl[i].equals("")) ? rCyl[i] : null, rAxis[i], rAdd[i],
						rDia[i], lSph.length > 0 && !(lSph[i] == null || lSph[i].equals("")) ? lSph[i] : null,
						lCyl.length > 0 && !(lCyl[i] == null || lCyl[i].equals("")) ? lCyl[i] : null, lAxis[i], lAdd[i],
						lDia[i], String.valueOf(lPrise), String.valueOf(rPrise)));
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
			if (material[i].equalsIgnoreCase("Glass")) {

				int rPrise = bookingUtility.getGlassLensePrice(
						rSph.length > 0 && !(rSph[i] == null || rSph[i].equals("")) ? Float.parseFloat(rSph[i]) : null,
						rCyl.length > 0 && !(rCyl[i] == null || rCyl[i].equals("")) ? Float.parseFloat(rCyl[i]) : null,
						tint[i], type[i], Integer.parseInt(qtyNos[i]));

				int lPrise = bookingUtility.getGlassLensePrice(
						lSph.length > 0 && !(lSph[i] == null || lSph[i].equals("")) ? Float.parseFloat(lSph[i]) : null,
						lCyl.length > 0 && !(lCyl[i] == null || lCyl[i].equals("")) ? Float.parseFloat(lCyl[i]) : null,
						tint[i], type[i], Integer.parseInt(qtyNos[i]));
				System.out.println(rPrise);
				System.out.println(lPrise);
				itemTotal = Math.addExact(rPrise, lPrise);

			} else if (material[i].equalsIgnoreCase("CR")) {
				int rPrise = bookingUtility.getCRLensePrice(type[i], tint[i], index[i], 
						rSph.length > 0 && !(rSph[i] == null || rSph[i].equals("")) ? rSph[i] : null,
						rCyl.length > 0 && !(rCyl[i] == null || rCyl[i].equals("")) ? rCyl[i] : null, coating[i],
						Integer.parseInt(qtyNos[i]));
				int lPrise = bookingUtility.getCRLensePrice(type[i], tint[i], index[i], 
						lSph.length > 0 && !(lSph[i] == null || lSph[i].equals("")) ? lSph[i] : null,
						lCyl.length > 0 && !(lCyl[i] == null || lCyl[i].equals("")) ? lCyl[i] : null, coating[i],
						Integer.parseInt(qtyNos[i]));

				if (Math.ceil(lCyl.length > 0 ? Float.parseFloat(lCyl[i]) : 0) > 4) {
					rPrise = rPrise != 0 ? Math.addExact(rPrise, 50) : rPrise;
					lPrise = lPrise != 0 ? Math.addExact(lPrise, 50) : lPrise;
				}

				itemTotal = Math.addExact(rPrise, lPrise);
			}
			priseList.append(String.valueOf(itemTotal) + ",");
		}

		if (priseList.toString().split("0,").length == 0) {
			return "Please verify the details you selected and retry..!!!";
		}

		return priseList.toString();
	}
}
