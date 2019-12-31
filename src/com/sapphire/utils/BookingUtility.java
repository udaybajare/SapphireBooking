package com.sapphire.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.dao.CRPriceDao;
import com.sapphire.dao.GlassPriceDao;
import com.sapphire.dao.OrderDao;
import com.sapphire.entity.CRPrise;
import com.sapphire.entity.EntryDetails;
import com.sapphire.entity.OrderDetails;

@ManagedBean
public class BookingUtility {

	@Autowired
	OrderDao orderDao;

	@Autowired
	GlassPriceDao glassPriceDao;

	@Autowired
	CRPriceDao crPriceDao;

	public String getOrganizationList(ArrayList<ArrayList<String>> registeredOrgs) {
		StringBuilder registeredOrgsStr = new StringBuilder();

		for (ArrayList<String> name : registeredOrgs) {
			
			registeredOrgsStr.append(optionsHTMLOpen);
			registeredOrgsStr.append(" value='" + (String)name.get(1) + "'>" + (String)name.get(0)+" - "+(String)name.get(1));
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
			sphInt = (int) Math.ceil(sph);
		}

		if (cyl != null) {
			cylInt = (int) Math.ceil(cyl);
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
		
		switch(type)
		{
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

		if (index != -1)
		{
			unitPrice = glassPriceDao.getUnitPrice(index, columnToSelect);

			if (isExeOrder) {
				if (columnToSelect.equals("W_KT"))
				{
					unitPrice = unitPrice + 50;
		}
				else 
				{
					unitPrice = unitPrice + 200;
				}
			}
		}

		unitPrice = unitPrice/2;
		

		System.out.println("unitPrice is " + unitPrice);

		return unitPrice;
	}

	public int getCRLensePrice(String type, String tint, String index, String sph, String cyl, String coating) {
		boolean isSphNtv = sph.startsWith("-");

		Double sphInt = Math.ceil(Float.parseFloat(isSphNtv ? sph.substring(1) : sph));
		Double cylInt = Math.ceil(Float.parseFloat(cyl.startsWith("-") ? cyl.substring(1) : cyl));

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

		ArrayList<CRPrise> crPriceList = (ArrayList<CRPrise>) crPriceDao.getUnitPrice(type, tint, index);

		Comparator<CRPrise> compareById = (CRPrise o1, CRPrise o2) -> o1.getRowIndex().compareTo(o2.getRowIndex());

		Collections.sort(crPriceList, compareById);

		Integer unitPrise = 0;

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

			case "ARC":
				unitPrise = Integer.parseInt(crPriceList.get(i).getArcSrp());
				break;
			}

			if (shouldBreak) {
				System.out.println("unitPrise is : " + unitPrise);
				break;
			}
		}

		unitPrise = unitPrise/2;		
		return unitPrise;
	}

	public String getOrderRowHTML(int orderId) {
		String orderRow = templateHTML;
		String orderContent = "";
		double totalAmount = 0;

		ArrayList<OrderDetails> orderDetailsList = orderDao.getAllOrders(orderId);

		for (OrderDetails orderDetails : orderDetailsList) {

			orderContent = orderContent + orderDetailsContentHTML;

			EntryDetails entryDetails = orderDao.getEntryDetails(orderId, orderDetails.getId());

			orderContent = orderContent.replace("rSph", entryDetails.getrSph() == null ? "" : entryDetails.getrSph());
			orderContent = orderContent.replace("rCyl", entryDetails.getrCyl() == null ? "" : entryDetails.getrCyl());
			orderContent = orderContent.replace("rAxis", entryDetails.getrAxis());
			orderContent = orderContent.replace("rAdd", entryDetails.getrAdd());
			orderContent = orderContent.replace("rDia", entryDetails.getrDia());
			orderContent = orderContent.replace("lSph", entryDetails.getlSph() == null ? "" : entryDetails.getlSph());
			orderContent = orderContent.replace("lCyl", entryDetails.getlCyl() == null ? "" : entryDetails.getlCyl());
			orderContent = orderContent.replace("lAxis", entryDetails.getlAxis());
			orderContent = orderContent.replace("lAdd", entryDetails.getlAdd());
			orderContent = orderContent.replace("lDia", entryDetails.getlDia());
			orderContent = orderContent.replace("qtyNos", orderDetails.getQtyNos());
			orderContent = orderContent.replace("typeStr", orderDetails.getType());
			orderContent = orderContent.replace("index1", orderDetails.getIndex());
			orderContent = orderContent.replace("coatingStr", orderDetails.getCoating());
			orderContent = orderContent.replace("tintStr", orderDetails.getTint());
			orderContent = orderContent.replace("frameType", orderDetails.getFrameType());
			orderContent = orderContent.replace("sourcingStr", orderDetails.getSourcing());
			orderContent = orderContent.replace("material", orderDetails.getMaterial());
			orderContent = orderContent.replace("organizationName", orderDetails.getOrganizationName());
			orderContent = orderContent.replace("orderIdStr", "orderId" + orderDetails.getId());

			double lPrice = entryDetails.getlPrice() != null ? Double.valueOf(entryDetails.getlPrice()) : 0.0;
			double rPrice = entryDetails.getrPrice() != null ? Double.valueOf(entryDetails.getrPrice()) : 0.0;

			orderContent = orderContent.replace("lPrice", String.valueOf(lPrice));
			orderContent = orderContent.replace("rPrice", String.valueOf(rPrice));

			//totalAmount = totalAmount + lPrice + rPrice;

		}
		
		totalAmount = orderDetailsList.get(0).getTotalAmount();
		
		orderRow = orderRow.replace("orderNo", String.valueOf(orderDetailsList.get(0).getOrderId()));
		orderRow = orderRow.replace("organizationName", orderDetailsList.get(0).getOrganizationName());
		orderRow = orderRow.replace("fullName", String.valueOf(orderDetailsList.get(0).getUserName()));
		orderRow = orderRow.replace("orderDate", String.valueOf(orderDetailsList.get(0).getOrderDate()));
		orderRow = orderRow.replace("currentStatus", String.valueOf(orderDetailsList.get(0).getStatus()));
		orderRow = orderRow.replace("orderDetailsContent", orderContent);
		orderRow = orderRow.replace("myModalStr", "myModal" + String.valueOf(orderId));
		orderRow = orderRow.replace("totalAmountStr", String.valueOf(totalAmount));
		
		return orderRow;
	}

	public static final String templateHTML = "<div class=\"row\"><div class=\"main col-lg-12\">"
			+ "<div class=\"pv-30 ph-20 feature-box bordered shadow text-center\" "
			+ "data-animation-effect=\"fadeInDownSmall\" data-effect-delay=\"100\"><h4 name=\"orderDesc\">"
			+ "OrderId: orderNo Organization: organizationName Booked By: fullName on Date: orderDate</h4>"
			+ "<button type=\"button\" class=\"btn btn-default\" data-toggle=\"modal\" data-target=\"#myModalStr\">"
			+ "Order Status Details</button><div class=\"modal fade\" id=\"myModalStr\" tabindex=\"-1\" "
			+ "role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\"><div class=\"modal-dialog\""
			+ " role=\"document\"><div class=\"modal-content\"><div class=\"modal-header\"><h4 class=\"modal-title\""
			+ " id=\"myModalLabel\">OrderId is : orderNo</h4><button type=\"button\" class=\"close\" data-dismiss=\"modal\""
			+ " aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span>"
			+ "</button></div><div class=\"modal-body\">orderDetailsContent"
			+ "<p>Current Status : currentStatus</p><form action=\"updateTotal\" method=\"POST\"><p>Total Amount : <input type = \"text\" name =\"totalAmount\" value=\"totalAmountStr\"></p>"
			+ "<p>Comments : <input type=\"text\" name=\"comment\" value=\"\"></p><input type=\"hidden\" name=\"orderId\" value=\"orderNo\">"
			+ "<button type=\"button\" class=\"btn btn-sm btn-default updateOrder\" >Update Order</button></form></div><div class=\"modal-footer\"><h4>Mark As</h4><form id=\"accept\""
			+ " action=\"updateStatus\" method=\"POST\"><input type=\"hidden\" name=\"orderId\" value=\"orderNo\">"
			+ "<input type=\"hidden\" name=\"status\" value=\"accepted\"><button type=\"submit\""
			+ " class=\"btn btn-sm btn-default\" >Accepted</button></form>"
			+ "<form id=\"process\" action=\"updateStatus\" method=\"POST\"><input type=\"hidden\" name=\"orderId\""
			+ " value=\"orderNo\"><input type=\"hidden\" name=\"status\" value=\"processing\"><button type=\"submit\""
			+ " class=\"btn btn-sm btn-default\">Processing</button></form><form id=\"ready\""
			+ " action=\"updateStatus\" method=\"POST\"><input type=\"hidden\" name=\"orderId\" value=\"orderNo\">"
			+ "<input type=\"hidden\" name=\"status\" value=\"readyToDeliver\"><button type=\"submit\" class=\"btn btn-sm btn-default\""
			+ " >Ready to Deliver</button></form><form id=\"deliver\" action=\"updateStatus\""
			+ " method=\"POST\"><input type=\"hidden\" name=\"orderId\" value=\"orderNo\"><input type=\"hidden\" name=\"status\""
			+ " value=\"delivered\"><button type=\"submit\" class=\"btn btn-sm btn-default\" >"
			+ "Delivered</button></form></div></div></div></div></div></div></div>";

	public static final String orderDetailsContentHTML = "<p>Material : material Type : typeStr Quantity : qtyNos Index : index1 "
			+ "Coating : coatingStr Tint : tintStr Frame Type : frameType Sourcing : <select name= \"sourcing\" value=\"sourcingStr\"><option value=\"Factory Order\">Factory Order</option><option value=\"Ready Stock\">Ready Stock</option></select> </p>"
			+ "<table class=\"table table-striped\"><thead>  <tr>    <th>LENS SIDE</th>    <th>SPH</th>"
			+ "    <th>CYL</th>    <th>AXIS</th>    <th>ADD</th>    <th>DIA</th>  </tr></thead><tbody>  "
			+ "<tr>    <td>R</td>    <td>rSph</td>    <td>rCyl</td>    <td>rAxis</td>    <td>rAdd</td>    "
			+ "<td>rDia</td>  </tr>  <tr>    <td>L</td>    <td>lSph</td>    <td>lCyl</td>    <td>lAxis</td>"
			+ "    <td>lAdd</td>    <td>lDia</td>  </tr></tbody></table> <button type=\"button\" class=\"btn btn-sm btn-default\" onClick=\"printItem('orderIdStr');\">Print</button>"
			+ "<div class=\"separator clearfix\"></div>"
			+ "<div style=\"display:none;\" id=\"orderIdStr\" ><div style=\"height : 33mm; width : 70mm;\" >			<p>organizationName	UON:99999	Rate:rPrice</p>			<table border=\"1\">			<thead>  <tr>    <th>LENS SIDE</th>    <th>SPH</th>			    <th>CYL</th>    <th>AXIS</th>    <th>ADD</th>    <th>DIA</th>  </tr>			</thead>			<tbody>  						<tr>    <td>R</td>    <td>rSph</td>    <td>rCyl</td>    <td>rAxis</td>    <td>rAdd</td>    <td>rDia</td>  			</tr>  			<tr>  <td colspan=\"6\" >material, typeStr, index1, coatingStr, tintStr</td>      			</tr>			</tbody>			</table></div><div style=\"height : 33mm; width : 70mm;\" >			<p>organizationName	UON:99999	Rate:lPrice</p>			<table border=\"1\">			<thead>  <tr>    <th>LENS SIDE</th>    <th>SPH</th>			    <th>CYL</th>    <th>AXIS</th>    <th>ADD</th>    <th>DIA</th>  </tr>			</thead>			<tbody>  						<tr>    <td>L</td>    <td>lSph</td>    <td>lCyl</td>    <td>lAxis</td>    <td>lAdd</td>    <td>lDia</td>  			</tr>  			<tr>  <td colspan=\"6\" >material, typeStr, index1, coatingStr, tintStr</td>      			</tr>			</tbody>			</table></div></div>";

	public static final String optionsHTMLOpen = "<option";
	public static final String optionsHTMLClose = "</option>";
}
