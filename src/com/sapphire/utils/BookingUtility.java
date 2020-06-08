package com.sapphire.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.dao.CRPriceDao;
import com.sapphire.dao.CRPriceReadyStockDao;
import com.sapphire.dao.GlassPriceDao;
import com.sapphire.dao.OrderDao;
import com.sapphire.dao.RegistrationDao;
import com.sapphire.entity.CRPrise;
import com.sapphire.entity.CRPriseReadyStock;
import com.sapphire.entity.EntryDetails;
import com.sapphire.entity.LensPrices;
import com.sapphire.entity.OrderDetails;
import com.sapphire.entity.UserDetails;

@ManagedBean
public class BookingUtility {

	@Autowired
	OrderDao orderDao;

	@Autowired
	GlassPriceDao glassPriceDao;

	@Autowired
	CRPriceDao crPriceDao;

	@Autowired
	CRPriceReadyStockDao crPriseReadyStockDao;

	@Autowired
	RegistrationDao registrationDao;

	public String getOrganizationList(ArrayList<ArrayList<String>> registeredOrgs) {
		StringBuilder registeredOrgsStr = new StringBuilder();

		for (Object name : registeredOrgs) {

			Object[] nameList = (Object[]) name;

			registeredOrgsStr.append(optionsHTMLOpen);
			registeredOrgsStr.append(
					" value='" + (String) nameList[1] + "'>" + (String) nameList[1] + " - " + (String) nameList[0]);
			registeredOrgsStr.append(optionsHTMLClose);
		}
		return registeredOrgsStr.toString();
	}

	public int getGlassLensePrice(Float sph, Float cyl, String tint, String type) {

		boolean onlyCylOrSph = false;

		if (null == sph && cyl != null || cyl == null && sph != null) {
			onlyCylOrSph = true;
		}

		int sphInt = 0;
		int cylInt = 0;

		if (sph != null) {
			sphInt = (int) Math.ceil(Math.abs(sph));
		}

		if (cyl != null) {
			cylInt = (int) Math.ceil(Math.abs(cyl));
		}

		int index = -1;

		if (onlyCylOrSph) {
			index = sphInt == 0 ? cylInt : sphInt;
		} else {
			index = Math.addExact(Math.addExact(5 * (cylInt - 1), 4), sphInt);
		}

		System.out.println("index is " + index);

		String typeToSelect = "";
		boolean isExeOrder = false;

		switch (type) {
		case "Single Vision":
			typeToSelect = "_SV";
			break;
		case "Kt Bifocal":
			typeToSelect = "_KT";
			break;
		case "Progressive":
			typeToSelect = "_PR";
			break;
		case "D Bifocal":
			typeToSelect = "_DB";
			break;
		case "Executive":
			// If type is Executive calculate the price based on Kryptop Bifocal
			// price
			isExeOrder = true;
			typeToSelect = "_KT";
			break;
		}

		String columnToSelect = "";

		switch (tint) {
		case "Clear":
			columnToSelect = "W" + typeToSelect;
			break;

		case "PG4":
			columnToSelect = "PG4" + typeToSelect;
			break;

		case "PG6":
			columnToSelect = "PG6" + typeToSelect;
			break;

		case "SP2":
			columnToSelect = "SP2" + typeToSelect;
			break;

		case "PB":
			columnToSelect = "PB" + typeToSelect;
			break;

		case "PR":
			columnToSelect = "PR" + typeToSelect;
			break;

		}

		int unitPrice = 0;
		try {
			if (index != -1) {
				unitPrice = glassPriceDao.getUnitPrice(index, columnToSelect);

				if (isExeOrder) {
					if (columnToSelect.equals("W_KT")) {
						unitPrice = unitPrice + 50;
					} else {
						unitPrice = unitPrice + 100;
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("Glass Combination not found in DB. Price will be decided manually.");
		}

		unitPrice = unitPrice / 2;

		System.out.println("unitPrice is " + unitPrice);

		return unitPrice;
	}

	public int getCRLensePrice(String type, String tint, String index, String sph, String cyl, String coating) {

		if (sph == null && cyl == null) {

			return 0;
		}

		Double sphInt = 0.0;
		Double cylInt = 0.0;
		boolean isSphNtv = false;

		if (sph != null) {
			isSphNtv = sph.startsWith("-");
			sphInt = Math.ceil(Float.parseFloat(isSphNtv ? sph.substring(1) : sph));
		}

		if (cyl != null) {
			cylInt = Math.ceil(Float.parseFloat(cyl.startsWith("-") ? cyl.substring(1) : cyl));
		}

		switch (type) {
		case "Single Vision":
			type = "SINGLE_VISION";
			break;

		case "Kt Bifocal":
			type = "KT_BIFOCAL";
			break;

		case "Progressive":
			type = "PROGREESSIVE";
			break;
		case "D Bifocal":
			type = "D_BIFOCAL";
			break;
		}
		Integer unitPrise = 0;
		try {
			ArrayList<CRPrise> crPriceList = (ArrayList<CRPrise>) crPriceDao.getUnitPrice(type, tint, index);

			Comparator<CRPrise> compareById = (CRPrise o1, CRPrise o2) -> o1.getRowIndex().compareTo(o2.getRowIndex());

			Collections.sort(crPriceList, compareById);

			for (int i = 0; i < crPriceList.size(); i++) {
				System.out.println("Begin : " + crPriceList.get(i).getNegativeNbr());

				boolean shouldBreak = false;
				if (isSphNtv) {
					if (sphInt <= Double.parseDouble(crPriceList.get(i).getNegativeNbr())) {

						System.out.println("sphInt is : " + sphInt);
						System.out.println("crPrise.getNegativeNbr() id : " + crPriceList.get(i).getNegativeNbr());
						shouldBreak = true;
					}

				} else {
					if (sphInt <= Double.parseDouble(crPriceList.get(i).getPositiveNbr())) {
						System.out.println("sphInt is : " + sphInt);
						System.out.println("crPrise.getPositiveNbr() id : " + crPriceList.get(i).getPositiveNbr());
						shouldBreak = true;
					}
				}

				switch (coating) {
				case "UC":
					unitPrise = Integer.parseInt(crPriceList.get(i).getUcSrp());
					break;

				case "HC":
					unitPrise = Integer.parseInt(crPriceList.get(i).getHcSrp());
					break;

				case "ARC GREEN COAT":
				case "BLUE PROTECT GREEN COAT":
					unitPrise = Integer.parseInt(crPriceList.get(i).getArcSrp());
					break;
				}

				if (shouldBreak) {
					System.out.println("unitPrise is : " + unitPrise);
					break;
				}
			}
		} catch (Exception ex) {
			System.out.println("Glass Combination not found in DB. Price will be decided manually.");
		}

		unitPrise = unitPrise / 2;
		return unitPrise;
	}

	public int getReadyStockCRPrice(String type, String tint, String index, String sph, String cyl, String coating,
			String dia, String axis) {

		if (sph == null && cyl == null) {

			return 0;
		}

		Double sphInt = Double.parseDouble(sph);
		Double cylInt = Double.parseDouble(cyl);
		boolean isSphNtv = false;

		/*if (sph != null) {
			isSphNtv = sph.startsWith("-");
			sphInt = Math.ceil(Float.parseFloat(isSphNtv ? sph.substring(1) : sph));
		}

		if (cyl != null) {
			cylInt = Math.ceil(Float.parseFloat(cyl.startsWith("-") ? cyl.substring(1) : cyl));
		}*/

		switch (type) {
		case "Single Vision":
			type = "SINGLE_VISION";
			break;

		case "Kt Bifocal":
			type = "KT_BIFOCAL";
			break;

		case "Progressive":
			type = "PROGREESSIVE";
			break;
		case "D Bifocal":
			type = "D_BIFOCAL";
			break;
		}
		Integer unitPrise = 0;
		try {
			ArrayList<CRPriseReadyStock> crPriceList = (ArrayList<CRPriseReadyStock>) crPriseReadyStockDao
					.getUnitPrice(type, tint, index, coating, dia, axis);

			Comparator<CRPriseReadyStock> compareById = (CRPriseReadyStock o1, CRPriseReadyStock o2) -> Double.valueOf(o1.getSphUpto())
					.compareTo(Double.valueOf(o2.getSphUpto()));

			Collections.sort(crPriceList, compareById);

			for (int i = 0; i < crPriceList.size(); i++) {

				boolean shouldBreak = false;

				System.out.println(sphInt + " : " + Double.parseDouble(crPriceList.get(i).getSphUpto()) + "###########"
						+ cylInt + " : " + Double.parseDouble(crPriceList.get(i).getCylUpto()));
				System.out.println();
				
				if (sphInt <= Double.parseDouble(crPriceList.get(i).getSphUpto())
						&& cylInt <= Double.parseDouble(crPriceList.get(i).getCylUpto())) {

					System.out.println("sphInt is : " + sphInt);
					System.out.println("crPrise.getSphUpto() is : " + crPriceList.get(i).getSphUpto());
					shouldBreak = true;
				}

				switch (coating) {
				case "UC":
					unitPrise = Integer.parseInt(crPriceList.get(i).getUc());
					break;

				case "HC":
					unitPrise = Integer.parseInt(crPriceList.get(i).getHc());
					break;

				case "ARC GREEN COAT":
				case "BLUE PROTECT GREEN COAT":
					unitPrise = Integer.parseInt(crPriceList.get(i).getArc());
					break;
				}

				if (shouldBreak) {
					System.out.println("unitPrise is : " + unitPrise);
					break;
				}
			}
		} catch (Exception ex) {
			System.out.println("Glass Combination not found in DB. Price will be decided manually.");
		}

		unitPrise = unitPrise / 2;
		return unitPrise;
	}

	public String getOrderRowHTML(int orderId, boolean isAdmin) {
		String orderRow = templateHTML;

		String orderContent = "";
		double totalAmount = 0;

		// A + x + x + B

		// A if(Admin) +l if(Admin)+k + n(x+if(Admin)s+if(Admin)p)

		ArrayList<OrderDetails> orderDetailsList = orderDao.getAllOrders(orderId);

		for (OrderDetails orderDetails : orderDetailsList) {

			orderContent = orderContent + orderDetailsContentHTMLStart;

			EntryDetails entryDetails = orderDao.getEntryDetails(orderId, orderDetails.getId());

			boolean isRightPresent = false;
			boolean isLeftPresent = false;

			if ((entryDetails.getrSph() != null && !(entryDetails.getrSph().trim().equals("")))
					|| (entryDetails.getrCyl() != null && !(entryDetails.getrCyl().trim().equals("")))) {
				isRightPresent = true;
			}

			if ((entryDetails.getlSph() != null && !(entryDetails.getlSph().trim().equals("")))
					|| (entryDetails.getlCyl() != null && entryDetails.getlCyl().trim().equals(""))) {
				isLeftPresent = true;
			}

			if (isRightPresent) {
				orderContent = orderContent + orderDetailsContentHTMLRight;

			}

			if (isLeftPresent) {
				orderContent = orderContent + orderDetailsContentHTMLLeft;
			}

			orderContent = orderContent + orderDetailsContentHTMLEnd;

			String sourcingRight = sourcingSection;
			String sourcingLeft = sourcingSection;

			if (isRightPresent && null != entryDetails.getrSourcing()
					&& entryDetails.getrSourcing().equalsIgnoreCase("Factory Order")) {
				sourcingRight = sourcingRight.replace("selectedFactory", "Selected");
				sourcingRight = sourcingRight.replace("selectedReady", "");
			} else {
				sourcingRight = sourcingRight.replace("selectedFactory", "");
				sourcingRight = sourcingRight.replace("selectedReady", "Selected");
			}

			if (isLeftPresent && null != entryDetails.getlSourcing()
					&& entryDetails.getlSourcing().equalsIgnoreCase("Factory Order")) {
				sourcingLeft = sourcingLeft.replace("selectedFactory", "Selected");
				sourcingLeft = sourcingLeft.replace("selectedReady", "");
			} else {
				sourcingLeft = sourcingLeft.replace("selectedFactory", "");
				sourcingLeft = sourcingLeft.replace("selectedReady", "Selected");
			}

			if (isAdmin) {

				orderContent = orderContent.replace("sourcingSectionRight", sourcingRight);
				orderContent = orderContent.replace("sourcingSectionLeft", sourcingLeft);
				orderContent = orderContent.replace("printButton", printButton);
			} else {
				orderContent = orderContent.replace("sourcingSectionRight", sourcingRight);
				orderContent = orderContent.replace("sourcingSectionLeft", sourcingLeft);
				orderContent = orderContent.replace("printButton", "");
			}

			if (isAdmin) {

				orderContent = orderContent + orderDetailsContentPrintSTART;
				if (isRightPresent) {
					orderContent = orderContent + orderDetailsContentPrintRight;
				}

				if (isLeftPresent) {
					orderContent = orderContent + orderDetailsContentPrintLeft;
				}

				orderContent = orderContent + orderDetailsContentPrintEND;
			}

			orderContent = orderContent.replace("rSph", entryDetails.getrSph() == null ? "" : entryDetails.getrSph());
			orderContent = orderContent.replace("rCyl", entryDetails.getrCyl() == null ? "" : entryDetails.getrCyl());
			orderContent = orderContent.replace("rAxis",
					entryDetails.getrAxis() == null ? "" : entryDetails.getrAxis());
			orderContent = orderContent.replace("rAdd", entryDetails.getrAdd() == null ? "" : entryDetails.getrAdd());
			orderContent = orderContent.replace("rDia", entryDetails.getrDia() == null ? "" : entryDetails.getrDia());
			orderContent = orderContent.replace("rSourcingStr",
					entryDetails.getrSourcing() == null ? "" : entryDetails.getrSourcing());

			if (entryDetails.getrSourcing() != null && entryDetails.getrSourcing().equalsIgnoreCase("Factory Order")) {
				orderContent = orderContent.replace("rFoSelectedStr", "selected");
				orderContent = orderContent.replace("rRsSelectedStr", "");
			} else {
				orderContent = orderContent.replace("rFoSelectedStr", "");
				orderContent = orderContent.replace("rRsSelectedStr", "selected");
			}

			orderContent = orderContent.replace("lSph", entryDetails.getlSph() == null ? "" : entryDetails.getlSph());
			orderContent = orderContent.replace("lCyl", entryDetails.getlCyl() == null ? "" : entryDetails.getlCyl());
			orderContent = orderContent.replace("lAxis",
					entryDetails.getlAxis() == null ? "" : entryDetails.getlAxis());
			orderContent = orderContent.replace("lAdd", entryDetails.getlAdd() == null ? "" : entryDetails.getlAdd());
			orderContent = orderContent.replace("lDia", entryDetails.getlDia() == null ? "" : entryDetails.getlDia());
			boolean lSourcingNull = entryDetails.getlSourcing() == null;
			orderContent = orderContent.replace("lSourcingStr", lSourcingNull ? "" : entryDetails.getlSourcing());

			if (entryDetails.getlSourcing() != null && entryDetails.getlSourcing().equalsIgnoreCase("Factory Order")) {
				orderContent = orderContent.replace("lFoSelectedStr", "selected");
				orderContent = orderContent.replace("lRsSelectedStr", "");
			} else {
				orderContent = orderContent.replace("lFoSelectedStr", "");
				orderContent = orderContent.replace("lRsSelectedStr", "selected");
			}

			orderContent = orderContent.replace("qtyNos", orderDetails.getQtyNos());
			orderContent = orderContent.replace("subOrderId", String.valueOf(entryDetails.getlOrderDetailsId()));
			orderContent = orderContent.replace("orderNo", String.valueOf(orderDetailsList.get(0).getOrderId()));
			orderContent = orderContent.replace("typeStr",
					orderDetails.getType() != null ? orderDetails.getType().toUpperCase() : "");
			orderContent = orderContent.replace("index1",
					orderDetails.getIndex() != null ? orderDetails.getIndex().toUpperCase() : "");
			orderContent = orderContent.replace("coatingStr",
					orderDetails.getCoating() != null ? orderDetails.getCoating().toUpperCase() : "");
			orderContent = orderContent.replace("tintStr",
					orderDetails.getTint() != null ? orderDetails.getTint().toUpperCase() : "");
			orderContent = orderContent.replace("frameType", orderDetails.getFrameType());

			orderContent = orderContent.replace("material",
					orderDetails.getMaterial() != null ? orderDetails.getMaterial().toUpperCase() : "");
			orderContent = orderContent.replace("organizationName", orderDetails.getOrganizationName());
			orderContent = orderContent.replace("orderIdStr", "orderId" + orderDetails.getId());

			double lPrice = entryDetails.getlPrice() != null ? Double.valueOf(entryDetails.getlPrice()) : 0.0;
			double rPrice = entryDetails.getrPrice() != null ? Double.valueOf(entryDetails.getrPrice()) : 0.0;

			double remainder = 10 - (lPrice + rPrice) % 10;
			if (remainder == 10) {
				remainder = 0;
			}
			double itemPrice = (lPrice + rPrice) * Integer.parseInt(orderDetails.getQtyNos());

			orderContent = orderContent.replace("lPrice", String.valueOf(itemPrice));
			orderContent = orderContent.replace("rPrice", String.valueOf(itemPrice));

			orderContent = orderContent.replace("custOrdNo", orderDetails.getCustOrderNumber());
			orderContent = orderContent.replace("itemPriceStr", String.valueOf(itemPrice));
		}

		totalAmount = orderDetailsList.get(0).getTotalAmount();
		if (isAdmin) {
			orderRow = orderRow.replace("updateOrderSection", updateOrderSection);
			orderRow = orderRow.replace("statusUpdateSection", statusUpdateSection);
		} else {
			orderRow = orderRow.replace("updateOrderSection", "<p>Total Amount : totalAmountStr");
			orderRow = orderRow.replace("statusUpdateSection", "");
		}

		String orderIdStr = String.valueOf(orderDetailsList.get(0).getOrderId());

		while (orderIdStr.length() < 4) {
			orderIdStr = "0" + orderIdStr;
		}

		orderRow = orderRow.replace("orderNo", orderIdStr);
		orderRow = orderRow.replace("organizationName", orderDetailsList.get(0).getOrganizationName());
		orderRow = orderRow.replace("fullName", String.valueOf(orderDetailsList.get(0).getUserName()));
		orderRow = orderRow.replace("orderDate", String.valueOf(orderDetailsList.get(0).getOrderDate()));
		orderRow = orderRow.replace("currentStatus", String.valueOf(orderDetailsList.get(0).getStatus()));
		orderRow = orderRow.replace("orderDetailsContent", orderContent);
		orderRow = orderRow.replace("myModalStr", "myModal" + String.valueOf(orderId));
		orderRow = orderRow.replace("totalAmountStr", String.valueOf(totalAmount));

		return orderRow;
	}

	public static boolean notNullOrBlank(String input) {
		return null != input && !("".equals(input));
	}

	public String getUserList(ArrayList<UserDetails> userList) {

		String list = "";
		// ArrayList<UserDetails> userList1 = registrationDao.getUserDetails();
		// for(UserDetails uList : userList1)
		for (int i = 0; i < userList.size(); i++) {
			String dummy = userListTemp;

			dummy = dummy.replace("serialNumberStr", String.valueOf(userList.get(i).getSerialNumberCustomer()));

			dummy = dummy.replace("orgName", userList.get(i).getOrgName());

			dummy = dummy.replace("addressLine3", userList.get(i).getAddressLine3());

			dummy = dummy.replace("customerName", userList.get(i).getCustomerName());

			list = list + dummy;

		}

		return list;
	}

	public LensPrices getPrise(String material, String rSph, String rCyl, String rAxis, String rDia, String rSourcing, String tint,
			String type, String lSph, String lCyl, String lAxis, String lDia, String lSourcing, String coating, String index,
			String qtyNos) {

		int totalPrice = 0;
		int rPrise = 0;
		int lPrise = 0;

		try {

				if (material.equalsIgnoreCase("Glass")) {

					rPrise = getGlassLensePrice(
							rSph != null && !(rSph.trim().equals("")) ? Float.parseFloat(rSph) : null,
							rCyl != null && !(rCyl.trim().equals("")) ? Float.parseFloat(rCyl) : null, tint, type);

					lPrise = getGlassLensePrice(
							lSph != null && !(lSph.trim().equals("")) ? Float.parseFloat(lSph) : null,
							lCyl != null && !(lCyl.trim().equals("")) ? Float.parseFloat(lCyl) : null, tint, type);

			} else if (material.equalsIgnoreCase("CR")) {

				if (rSourcing.equalsIgnoreCase("Factory Order")) 
				{
					rPrise = getCRLensePrice(type, tint, index, rSph != null && !(rSph.trim().equals("")) ? rSph : null,
							rCyl != null && !(rCyl.trim().equals("")) ? rCyl : null, coating);
				} 
				else if (rSourcing.equalsIgnoreCase("Ready Stock"))
				{
					rPrise = getReadyStockCRPrice(type, tint, index, 
							rSph != null && !(rSph.trim().equals("")) ? rSph : null,
							rCyl != null && !(rCyl.trim().equals("")) ? rCyl : null, 
							coating, 
							rDia != null && !(rDia.trim().equals("")) ? rDia : null, 
							rAxis != null && !(rAxis.trim().equals("")) ? rAxis : null);
				}

				if (lSourcing.equalsIgnoreCase("Factory Order"))
				{
					lPrise = getCRLensePrice(type, tint, index, lSph != null && !(lSph.trim().equals("")) ? lSph : null,
							lCyl != null && !(lCyl.trim().equals("")) ? lCyl : null, coating);
					
					
				} 
				else if (lSourcing.equalsIgnoreCase("Ready Stock")) 
				{
					rPrise = getReadyStockCRPrice(type, tint, index, 
							lSph != null && !(lSph.trim().equals("")) ? lSph : null,
							lCyl != null && !(lCyl.trim().equals("")) ? lCyl : null, 
							coating, 
							lDia != null && !(lDia.trim().equals("")) ? lDia : null, 
							lAxis != null && !(lAxis.trim().equals("")) ? lAxis : null);
				}

				if (Math.ceil(Float.parseFloat(lCyl != null && !(lCyl.trim().equals("")) ? lCyl : "0")) > 4) {

					rPrise = rPrise != 0 ? Math.addExact(rPrise, 25) : rPrise;
					lPrise = lPrise != 0 ? Math.addExact(lPrise, 25) : lPrise;
				}

			}
			

			int remainder = 10 - (lPrise + rPrise) % 10;

			if (remainder == 10) {
				remainder = 0;
			}
			totalPrice = ((((lPrise + rPrise) + remainder) / 10) * 10) * Integer.parseInt(qtyNos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new LensPrices(lPrise, rPrise, totalPrice);
	}

	public static final String templateHTML = "	<div class=\"row\"><div class=\"main col-lg-12\">"
			+ "<div class=\"pv-30 ph-20 feature-box bordered shadow text-center\" "
			+ "data-animation-effect=\"fadeInDownSmall\" data-effect-delay=\"100\"><h4 name=\"orderDesc\">"
			+ "OrderId: orderNo Organization: organizationName Booked By: fullName on Date: orderDate</h4>"
			+ "<div class=\"row\"><div class=\"col-md-4\"></div><div class=\"col-md-4\">"
			+ "<button type=\"button\" class=\"btn btn-default\" data-toggle=\"modal\" data-target=\"#myModalStr\">Order Status Details</button></div>"
			+ "<div class=\"col-md-4\"><br><h5>currentStatus</h5></div></div>"
			+ "<div class=\"modal fade\" id=\"myModalStr\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">"
			+ "<div class=\"modal-dialog\" role=\"document\"><div class=\"modal-content\">"
			+ "<div class=\"modal-header\"><h4 class=\"modal-title\" id=\"myModalLabel\">OrderId is : orderNo</h4><button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">"
			+ "<span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>"
			+ "</div><div class=\"modal-body\">orderDetailsContent<p>Current Status : currentStatus</p>updateOrderSection</div>statusUpdateSection"
			+ "</div></div></div></div></div></div>";

	public static final String updateOrderSection = "<form action=\"updateTotal\" method=\"POST\"><p>	Total Amount : <input type =\"text\" name =\"totalAmount\" readonly=\"true\" value=\"totalAmountStr\">"
			+ "</p><p>Comments : <input type=\"text\" name=\"comment\" value=\"\"></p>"
			+ "<input type=\"hidden\" name=\"orderId\" value=\"orderNo\">"
			+ "<button type=\"button\" class=\"btn btn-sm btn-default updateOrder\">"
			+ "Update Order</button>	</form>";

	public static final String statusUpdateSection = "<div class=\"modal-footer\"><h4>Mark As</h4>"
			+ "<form id=\"reject\" action=\"updateStatus\" method=\"POST\"><input type=\"hidden\" name=\"orderId\" value=\"orderNo\">"
			+ "<input type=\"hidden\" name=\"status\" value=\"rejected\"><button type=\"submit\" class=\"btn btn-sm btn-default\" >Reject</button></form>"
			+ "<form id=\"accept\" action=\"updateStatus\" method=\"POST\"><input type=\"hidden\" name=\"orderId\" value=\"orderNo\">"
			+ "<input type=\"hidden\" name=\"status\" value=\"accepted\">"
			+ "<button type=\"submit\" class=\"btn btn-sm btn-default\" >Accept</button></form>"
			+ "<form id=\"process\" action=\"updateStatus\" method=\"POST\">"
			+ "<input type=\"hidden\" name=\"orderId\" value=\"orderNo\"><input type=\"hidden\" name=\"status\" value=\"processing\">"
			+ "<button type=\"submit\" class=\"btn btn-sm btn-default\">Processing</button></form>"
			+ "<form id=\"ready\" action=\"updateStatus\" method=\"POST\"><input type=\"hidden\" name=\"orderId\" value=\"orderNo\"><input type=\"hidden\" name=\"status\" value=\"readyToDeliver\">"
			+ "<button type=\"submit\" class=\"btn btn-sm btn-default\" >Ready to Deliver</button></form>"
			+ "<form id=\"deliver\" action=\"updateStatus\" method=\"POST\"><input type=\"hidden\" name=\"orderId\" value=\"orderNo\">"
			+ "<input type=\"hidden\" name=\"status\" value=\"delivered\"><button type=\"submit\" class=\"btn btn-sm btn-default\" >Delivered</button></form></div>";

	public static final String orderDetailsContentHTMLStart = "<p><b>SubOrder NO</b>: subOrderId <b>Material</b>: material <b>Type</b> : typeStr <b>Quantity</b> : qtyNos <b>Index</b> : index1 <b>Coating</b> : coatingStr <b>Tint</b> : tintStr <b>Frame Type</b> : frameType</p>"
			+ "		<table class='table table-striped'>" + "			<thead>" + "				<tr>"
			+ "					<th>LENS SIDE</th>" + "					<th>SPH</th> "
			+ "					<th>CYL</th>" + "					<th>AXIS</th>    "
			+ "					<th>ADD</th>    " + "					<th>DIA</th>"
			+ "					<th>Sourcing</th>" + "			</thead>" + "			<tbody>  ";

	public static final String orderDetailsContentHTMLRight = "					<tr>    "
			+ "						<td>R</td>    " + "						<td>rSph</td>    "
			+ "						<td>rCyl</td>    " + "						<td>rAxis</td>    "
			+ "						<td>rAdd</td>    " + "						<td>rDia</td>"
			+ "						<td>" + "				sourcingSectionRight" + "			</td>    "
			+ "					</tr>  ";

	public static final String orderDetailsContentHTMLLeft = "					<tr>    "
			+ "						<td>L</td>    " + "						<td>lSph</td>    "
			+ "						<td>lCyl</td>    " + "						<td>lAxis</td>"
			+ "						<td>lAdd</td>    " + "						<td>lDia</td>" + "			<td>"
			+ "				sourcingSectionLeft" + "			</td>" + "					</tr>";

	public static final String orderDetailsContentHTMLEnd = "				</tbody>" + "			</table>"
			+ "			<h5>Item Price : </h5><br><input type='text' name='itemPrice' onchange='updatePrice($(this));' value='itemPriceStr'> printButton <div class='separator clearfix'></div>";
	public static final String sourcingSection = "<select name=\"sourcing\" value=\"sourcingStr\">"
			+ "<option selectedFactory value=\"Factory Order\">Factory Order</option>"
			+ "<option selectedReady value=\"Ready Stock\">Ready Stock</option>" + "</select>";

	public static final String printButton = "<button type='button' class='btn btn-sm btn-default' style=\"margin-left:50%;\" onClick='printItem(\"orderIdStr\");'>Print</button>";

	public static final String orderDetailsContentPrintSTART = "			<div style='display:none;margin-left:35%;' id='orderIdStr' >";

	public static final String orderDetailsContentPrintRight = ""
			+ "			<div style='height : 37mm; width : 69mm;'>"
			+ "			organizationName UON: orderNo/subOrderId Qty:qtyNos <span class=\"orderIdStr\">Cust Order #: custOrdNo</span>"
			+ "			<table border='1' style='border-collapse:collapse;'>" + "				<thead>"
			+ "					<tr>" + "						<th>SIDE</th>" + "						<th>SPH</th>"
			+ "						<th>CYL</th>" + "						<th>AXIS</th>"
			+ "						<th>ADD</th>    "
			+ "						<th>DIA</th>									"
			+ "					</tr>			" + "				</thead>			"
			+ "				<tbody>  						" + "					<tr>    "
			+ "						<td>R</td>    " + "						<td>rSph</td>    "
			+ "						<td>rCyl</td>    " + "						<td>rAxis</td>    "
			+ "						<td>rAdd</td>    " + "						<td>rDia</td>  			"
			+ "					</tr>  			" + "					<tr>  "
			+ "						<td colspan='6'>material, typeStr, index1, coatingStr, tintStr</td>"
			+ "					</tr>			" + "					</tbody>			"
			+ "				</table>" + "			</div>";
	public static final String orderDetailsContentPrintLeft = ""
			+ "			<div style='height : 37mm; width : 69mm;' >"
			+ "			organizationName UON: orderNo/subOrderId Qty:qtyNos <span class=\"orderIdStr\">Cust Order #: custOrdNo</span>"
			+ "			<table border='1' style='border-collapse:collapse;'>" + "				<thead>"
			+ "					<tr>" + "						<th>SIDE</th>" + "						<th>SPH</th>"
			+ "						<th>CYL</th>" + "						<th>AXIS</th>"
			+ "						<th>ADD</th>    "
			+ "						<th>DIA</th>									"
			+ "					</tr>			" + "				</thead>			"
			+ "				<tbody>  						" + "					<tr>    "
			+ "						<td>L</td>    " + "						<td>lSph</td>    "
			+ "						<td>lCyl</td>    " + "						<td>lAxis</td>    "
			+ "						<td>lAdd</td>    " + "						<td>lDia</td>  			"
			+ "					</tr>  			" + "					<tr>  "
			+ "						<td colspan='6'>material, typeStr, index1, coatingStr, tintStr</td>"
			+ "					</tr>			" + "					</tbody>			"
			+ "				</table>" + "			</div>";
	public static final String orderDetailsContentPrintEND = "		</div>";
	public static final String optionsHTMLOpen = "<option";
	public static final String optionsHTMLClose = "</option>";

	public static final String userListTemp = "<tr>" + "		<td>serialNumberStr</td>" + "		<td>orgName</td>"
			+ "		<td>addressLine3</td>" + "		<td>customerName</td>"
			+ "		<form id=\"accept\"  action=\"updateUserStatus\" method=\"POST\">"
			+ "		<input type=\"hidden\" name=\"status\" value=\"accept\"><input type=\"hidden\" name=\"serialNumberCustomer\" value=\"serialNumberStr\">"
			+ "		<td><button type=\"submit\" class=\"btn btn-sm btn-default\" >Accept</button></td> </form>"
			+ "		<form id=\"decline\"  action=\"deleteUserStatus\" method=\"POST\">"
			+ "		<input type=\"hidden\" name=\"status\" value=\"decline\"><input type=\"hidden\" name=\"serialNumberCustomer\" value=\"serialNumberStr\">"
			+ "		<td><button type=\"submit\" class=\"btn btn-sm btn-default\" >Decline</button></td>		</form>"
			+ "		</tr>";

}
