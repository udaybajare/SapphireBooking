package com.sapphire.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.ManagedBean;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.sapphire.dao.OrderDao;
import com.sapphire.dao.OrganizationDao;
import com.sapphire.entity.EntryDetails;
import com.sapphire.entity.InvoiceDetails;
import com.sapphire.entity.OrderDetails;
import com.sapphire.entity.OrganizationDetails;
import com.sapphire.entity.UserDetails;
import com.sun.media.sound.InvalidFormatException;

@ManagedBean
public class ReportCreator {

	@Autowired
	OrderDao orderDao;

	@Autowired
	OrganizationDao organizationDao;

	public byte[] writeExcel(ArrayList<OrderDetails> orerDetailList) throws IOException {

		Workbook workbook = null;
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(ResourceUtils.getFile("classpath:OrderDetails_template.xlsx"));
			workbook = WorkbookFactory.create(inputStream);
		} catch (EncryptedDocumentException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, String> cuetomerDetailsMap = new HashMap<String, String>();

		ArrayList<ArrayList<String>> registeredOrgs = organizationDao.getRegisteredOrganization(null);

		for (Object name : registeredOrgs) {

			Object[] nameList = (Object[]) name;

			cuetomerDetailsMap.put((String) nameList[1], (String) nameList[0]);
		}

		Sheet sheet = workbook.getSheetAt(0);

		sheet.getRow(0).getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
				.setCellValue(new SimpleDateFormat("dd.MM.yy").format(new Date()));

		int index = 0;
		int nextRow = 4;
		int i = 1;
		for (OrderDetails orerDetail : orerDetailList) {

			int orderId = orerDetail.getOrderId();
			int orderDetailsId = orerDetail.getId();
			String lenseSide = "";

			boolean isRPresent = false;
			boolean isLPresent = false;

			EntryDetails entry = orderDao.getEntryDetails(orderId, orderDetailsId);

			/*
			 * if ((entry.getrAdd() != null || entry.getrAxis() != null ||
			 * entry.getrCyl() != null || entry.getrDia() != null ||
			 * entry.getrSph() != null) && (entry.getlAdd() != null ||
			 * entry.getlAxis() != null || entry.getlCyl() != null ||
			 * entry.getlDia() != null || entry.getlSph() != null)) { lenseSide
			 * = "B"; } else
			 */
			if ((entry.getrAdd() != null && entry.getrAxis() != null && entry.getrCyl() != null
					&& entry.getrDia() != null && entry.getrSph() != null)) {
				lenseSide = "R";
				isRPresent = true;
			}
			if ((entry.getlAdd() != null && entry.getlAxis() != null && entry.getlCyl() != null
					&& entry.getlDia() != null && entry.getlSph() != null)) {
				lenseSide = "L";
				isLPresent = true;
			}

			int sph = 0;
			int cyl = 0;

			int row = nextRow + i;

			String customerNo = cuetomerDetailsMap.get(orerDetail.getOrganizationName());

			if (isRPresent) {
				Cell cellToUpdate0 = sheet.getRow(row).getCell(0);
				cellToUpdate0.setCellValue(get4DigitNumber(orerDetail.getOrderId()));

				Cell cellToUpdate1 = sheet.getRow(row).getCell(1);
				cellToUpdate1.setCellValue(customerNo);

				Cell cellToUpdate2 = sheet.getRow(row).getCell(2);
				cellToUpdate2.setCellValue(orerDetail.getCustOrderNumber());

				Cell cellToUpdate3 = sheet.getRow(row).getCell(3);
				cellToUpdate3.setCellValue(orerDetail.getOrganizationName());

				Cell cellToUpdate4 = sheet.getRow(row).getCell(4);
				cellToUpdate4.setCellValue(orerDetail.getOrderDate());

				Cell cellToUpdate5 = sheet.getRow(row).getCell(5);
				cellToUpdate5.setCellValue("R");
				Cell cellToUpdate6 = sheet.getRow(row).getCell(6);
				cellToUpdate6.setCellValue(entry.getrSph());
				Cell cellToUpdate7 = sheet.getRow(row).getCell(7);
				cellToUpdate7.setCellValue(entry.getrCyl());
				Cell cellToUpdate8 = sheet.getRow(row).getCell(8);
				cellToUpdate8.setCellValue(entry.getrAxis());
				Cell cellToUpdate9 = sheet.getRow(row).getCell(9);
				cellToUpdate9.setCellValue(entry.getrAdd());

				Cell cellToUpdate10 = sheet.getRow(row).getCell(10);
				cellToUpdate10.setCellValue(orerDetail.getMaterial() + " " + orerDetail.getType() + " "
						+ orerDetail.getCoating() + " " + orerDetail.getTint());

				Double rPrise = Double.valueOf(entry.getrPrice());

				if (!isLPresent) {
					double remainder = 10 - (rPrise) % 10;
					rPrise = ((((rPrise) + remainder) / 10) * 10);
				}

				Cell cellToUpdate11 = sheet.getRow(row).getCell(11);
				cellToUpdate11.setCellValue(rPrise);

				if (entry.getrSph() != null) {
					sph = 1;
				}
				if (entry.getrCyl() != null) {
					cyl = 1;
				}

				Cell cellToUpdate12 = sheet.getRow(row).getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			    cellToUpdate12.setCellValue(orerDetail.getComment());

				Cell cellToUpdate13 = sheet.getRow(row).getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate13.setCellValue(orerDetail.getStatus());

				
				Cell cellToUpdate15 = sheet.getRow(row).getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate15.setCellValue(sph);
				Cell cellToUpdate16 = sheet.getRow(row).getCell(16, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate16.setCellValue(cyl);
				Cell cellToUpdate17 = sheet.getRow(row).getCell(17, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate17.setCellValue(Integer.sum(sph, cyl));
				Cell cellToUpdate18 = sheet.getRow(row).getCell(18, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate18.setCellValue(1);
			}

			if (isLPresent) {

				// Go to next row
				if(isRPresent){
				row++;
				nextRow++;}

				Cell cellToUpdate0 = sheet.getRow(row).getCell(0);
				cellToUpdate0.setCellValue(get4DigitNumber(orerDetail.getOrderId()));

				Cell cellToUpdate1 = sheet.getRow(row).getCell(1);
				cellToUpdate1.setCellValue(customerNo);

				Cell cellToUpdate2 = sheet.getRow(row).getCell(2);
				cellToUpdate2.setCellValue(orerDetail.getCustOrderNumber());

				Cell cellToUpdate3 = sheet.getRow(row).getCell(3);
				cellToUpdate3.setCellValue(orerDetail.getOrganizationName());

				Cell cellToUpdate4 = sheet.getRow(row).getCell(4);
				cellToUpdate4.setCellValue(orerDetail.getOrderDate());

				Cell cellToUpdate5 = sheet.getRow(row).getCell(5);
				cellToUpdate5.setCellValue("L");
				Cell cellToUpdate6 = sheet.getRow(row).getCell(6);
				cellToUpdate6.setCellValue(entry.getlSph());
				Cell cellToUpdate7 = sheet.getRow(row).getCell(7);
				cellToUpdate7.setCellValue(entry.getlCyl());
				Cell cellToUpdate8 = sheet.getRow(row).getCell(8);
				cellToUpdate8.setCellValue(entry.getlAxis());
				Cell cellToUpdate9 = sheet.getRow(row).getCell(9);
				cellToUpdate9.setCellValue(entry.getlAdd());

				Cell cellToUpdate10 = sheet.getRow(row).getCell(10);
				cellToUpdate10.setCellValue(orerDetail.getMaterial() + " " + orerDetail.getType() + " "
						+ orerDetail.getCoating() + " " + orerDetail.getTint());

				Double lPrise = Double.valueOf(entry.getlPrice());

				if (!isRPresent) {
					double remainder = 10 - (lPrise) % 10;
					lPrise = ((((lPrise) + remainder) / 10) * 10);
				}

				Cell cellToUpdate11 = sheet.getRow(row).getCell(11);
				cellToUpdate11.setCellValue(lPrise);

				if (entry.getlSph() != null) {
					sph = 1;
				}
				if (entry.getlCyl() != null) {
					cyl = 1;
				}

				Cell cellToUpdate12 = sheet.getRow(row).getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate12.setCellValue(orerDetail.getComment());
				Cell cellToUpdate13 = sheet.getRow(row).getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate13.setCellValue(orerDetail.getStatus());

				Cell cellToUpdate15 = sheet.getRow(row).getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate15.setCellValue(sph);
				Cell cellToUpdate16 = sheet.getRow(row).getCell(16, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate16.setCellValue(cyl);
				Cell cellToUpdate17 = sheet.getRow(row).getCell(17, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate17.setCellValue(Integer.sum(sph, cyl));
				Cell cellToUpdate18 = sheet.getRow(row).getCell(18, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate18.setCellValue(1);
			}

			i++;
		}

		inputStream.close();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			workbook.write(bos);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			bos.close();
		}
		byte[] bytes = bos.toByteArray();

		// Closing the workbook
		workbook.close();

		return bytes;
	}

	public String get4DigitNumber(int number) {
		String orderIdStr = String.valueOf(number);

		while (orderIdStr.length() < 4) {
			orderIdStr = "0" + orderIdStr;
		}

		return orderIdStr;
	}

	public byte[] invoiceExcel(ArrayList<OrderDetails> orderDetailList) throws IOException {

		Workbook workbook = null;
		FileInputStream inputStream = null;
		double amount1 = 0;
		double amount2 = 0;

		try {
			inputStream = new FileInputStream(ResourceUtils.getFile("classpath:template.xlsx"));
			workbook = WorkbookFactory.create(inputStream);
		} catch (EncryptedDocumentException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Map<String, String> customerDetailsMap = new HashMap<String,
		 * String>();
		 * 
		 * ArrayList<ArrayList<String>> registeredOrgs =
		 * organizationDao.getRegisteredOrganization(null);
		 * 
		 * for (Object name : registeredOrgs) {
		 * 
		 * Object[] nameList = (Object[]) name;
		 * 
		 * customerDetailsMap.put((String) nameList[1], (String) nameList[0]); }
		 */

		Sheet sheet = workbook.getSheetAt(0);

		int index = 0;
		int nextRow = 5;
		for (OrderDetails orderDetail : orderDetailList) {

			int orderId = orderDetail.getOrderId();
			int orderDetailsId = orderDetail.getId();

			boolean isRPresent = false;
			boolean isLPresent = false;

			EntryDetails entry = orderDao.getEntryDetails(orderId, orderDetailsId);

			// Font calibri8Font = workbook.createFont();
			// calibri8Font.setFontName("Calibri");
			// calibri8Font.setFontHeightInPoints((short)10);

			CellStyle detailsCellStyle = workbook.createCellStyle();
			// detailsCellStyle.setFont(calibri8Font);
			detailsCellStyle.setWrapText(true);

			detailsCellStyle.setBorderBottom(BorderStyle.THIN);
			detailsCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			detailsCellStyle.setBorderLeft(BorderStyle.THIN);
			detailsCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			detailsCellStyle.setBorderRight(BorderStyle.THIN);
			detailsCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
			detailsCellStyle.setBorderTop(BorderStyle.THIN);
			detailsCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

			DateFormat df = new SimpleDateFormat("dd-MMM-YYYY");
			Date date = new Date();
			String currentDate = df.format(date);
			sheet.getRow(0).getCell(0).setCellValue(currentDate);
			sheet.getRow(1).getCell(0).setCellValue(orderDetail.getOrganizationName());

			if (BookingUtility.notNullOrBlank(entry.getrSph()) || BookingUtility.notNullOrBlank(entry.getrCyl())) {
				String orderDate = df.format(orderDetail.getOrderDate());

				Cell cellUpdate0 = sheet.createRow(nextRow).getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate0.setCellStyle(detailsCellStyle);
				cellUpdate0.setCellValue(orderDate);

				// eye lense side
				Cell cellUpdate1 = sheet.getRow(nextRow).getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate1.setCellStyle(detailsCellStyle);
				cellUpdate1.setCellValue("R");
				Cell cellUpdate2 = sheet.getRow(nextRow).getCell(2, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate2.setCellStyle(detailsCellStyle);
				cellUpdate2.setCellValue(entry.getrSph());
				Cell cellUpdate3 = sheet.getRow(nextRow).getCell(3, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate3.setCellStyle(detailsCellStyle);
				cellUpdate3.setCellValue(entry.getrCyl());
				Cell cellUpdate4 = sheet.getRow(nextRow).getCell(4, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate4.setCellStyle(detailsCellStyle);
				cellUpdate4.setCellValue(entry.getrAxis());
				Cell cellUpdate5 = sheet.getRow(nextRow).getCell(5, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate5.setCellStyle(detailsCellStyle);
				cellUpdate5.setCellValue(entry.getlAdd());
				// color orderDetails tint+type
				Cell cellUpdate6 = sheet.getRow(nextRow).getCell(6, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate6.setCellStyle(detailsCellStyle);
				cellUpdate6.setCellValue(orderDetail.getTint() + "-" + orderDetail.getType());
				Cell cellUpdate7 = sheet.getRow(nextRow).getCell(7, MissingCellPolicy.CREATE_NULL_AS_BLANK);

				cellUpdate7.setCellStyle(detailsCellStyle);
				cellUpdate7.setCellValue(orderDetail.getTotalAmount());

				isRPresent = true;

				nextRow++;
			}

			if (BookingUtility.notNullOrBlank(entry.getlSph()) || BookingUtility.notNullOrBlank(entry.getlCyl())) {
				String orderDate = df.format(orderDetail.getOrderDate());

				Cell cellUpdate0 = sheet.createRow(nextRow).getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate0.setCellStyle(detailsCellStyle);
				cellUpdate0.setCellValue(orderDate);

				// eye lense side
				Cell cellUpdate1 = sheet.getRow(nextRow).getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate1.setCellStyle(detailsCellStyle);
				cellUpdate1.setCellValue("L");
				Cell cellUpdate2 = sheet.getRow(nextRow).getCell(2, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate2.setCellStyle(detailsCellStyle);
				cellUpdate2.setCellValue(entry.getlSph());
				Cell cellUpdate3 = sheet.getRow(nextRow).getCell(3, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate3.setCellStyle(detailsCellStyle);
				cellUpdate3.setCellValue(entry.getlCyl());
				Cell cellUpdate4 = sheet.getRow(nextRow).getCell(4, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate4.setCellStyle(detailsCellStyle);
				cellUpdate4.setCellValue(entry.getlAxis());
				Cell cellUpdate5 = sheet.getRow(nextRow).getCell(5, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate5.setCellStyle(detailsCellStyle);
				cellUpdate5.setCellValue(entry.getlAdd());
				// color orderDetails tint+type
				Cell cellUpdate6 = sheet.getRow(nextRow).getCell(6, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate6.setCellStyle(detailsCellStyle);
				cellUpdate6.setCellValue(orderDetail.getTint() + "-" + orderDetail.getType());
				Cell cellUpdate7 = sheet.getRow(nextRow).getCell(7, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellUpdate7.setCellStyle(detailsCellStyle);
				// cellUpdate7.setCellValue(orderDetail.getTotalAmount());
				isLPresent = true;
				nextRow++;
			}

			if (isRPresent && isLPresent) {
				sheet.addMergedRegion(new CellRangeAddress(nextRow - 2, nextRow - 1, 7, 7));
			}
			amount1 += (double) Math.round(orderDetail.getTotalAmount());
			Cell cellUpdate06 = sheet.createRow(nextRow).getCell(6, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cellUpdate06.setCellStyle(detailsCellStyle);
			cellUpdate06.setCellValue("Total");
			Cell cellUpdate7 = sheet.getRow(nextRow).getCell(7, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cellUpdate7.setCellStyle(detailsCellStyle);
			cellUpdate7.setCellValue(amount1);

		}

		inputStream.close();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			workbook.write(bos);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			bos.close();
		}
		byte[] bytes = bos.toByteArray();

		// Closing the workbook
		workbook.close();

		return bytes;
	}

	public byte[] gstExcel(ArrayList<OrderDetails> orderDetailList, double discount, String terms, String invoiceNo)
			throws IOException {

		Workbook workbook = null;
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(ResourceUtils.getFile("classpath:GSTtemplate.xlsx"));
			workbook = WorkbookFactory.create(inputStream);
		} catch (EncryptedDocumentException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// InvoiceDetails invoiceDetail = (InvoiceDetails) get("invoiceDetail");

		Sheet sheet = workbook.getSheetAt(0);
		double totalAmount = 0;
		int quantityPieces = 0;
		int index = 0;
		// int nextRow = 5;
		for (OrderDetails orderDetail : orderDetailList) {
			String orgName = orderDetail.getOrganizationName();

			UserDetails user = orderDao.getUserDetails(orgName);

			OrganizationDetails orgDetails = (organizationDao.getOrganizationDetails(orgName)).get(0);

			String[] addressList = orgDetails.getOrgAddress().split(",");

			sheet.getRow(4).getCell(7).setCellValue(invoiceNo);
			DateFormat df = new SimpleDateFormat("dd-MMM-YYYY");
			Date date = new Date();
			System.out.printf("two digit no: %tm\n", date);
			String currentDate = df.format(date);
			sheet.getRow(5).getCell(7).setCellValue(currentDate);
			sheet.getRow(9).getCell(5).setCellValue(orderDetail.getOrganizationName());
			sheet.getRow(10).getCell(5, MissingCellPolicy.CREATE_NULL_AS_BLANK)
					.setCellValue(addressList.length > 0 ? addressList[0] : "");
			sheet.getRow(10).getCell(6, MissingCellPolicy.CREATE_NULL_AS_BLANK)
					.setCellValue(addressList.length > 1 ? addressList[1] : "");
			sheet.getRow(11).getCell(5, MissingCellPolicy.CREATE_NULL_AS_BLANK)
					.setCellValue(addressList.length > 2 ? addressList[2] : "");
			sheet.getRow(12).getCell(5).setCellValue("GST No:");
			sheet.getRow(12).getCell(6, MissingCellPolicy.CREATE_NULL_AS_BLANK)
					.setCellValue(user != null ? user.getGstNo() : "");
			sheet.getRow(13).getCell(5).setCellValue("Cell:");
			sheet.getRow(13).getCell(6).setCellValue(user != null ? user.getContactNumber()
					: (orgDetails.getOrgContact() == null ? "" : orgDetails.getOrgContact()));
			sheet.getRow(14).getCell(5).setCellValue("E-mail:");
			sheet.getRow(14).getCell(6, MissingCellPolicy.CREATE_NULL_AS_BLANK)
					.setCellValue(user != null ? user.getEmail() : "");
			String quantityNo = orderDetail.getQtyNos();
			quantityPieces += Integer.parseInt(quantityNo);
			sheet.getRow(17).getCell(6).setCellValue(quantityPieces);
			double total = orderDetail.getTotalAmount();
			// double total1 = orderDetail.getTotalAmount();
			// double total2 = orderDetail.getTotalAmount();
			totalAmount += total;
			sheet.getRow(17).getCell(7).setCellValue(totalAmount);
			// Double amount = totalAmount;
			sheet.getRow(21).getCell(0).setCellValue("Discounted @" + discount + "%");
			Double discountAmount = totalAmount * discount / 100;
			sheet.getRow(21).getCell(7).setCellValue(discountAmount);
			Double sgst = totalAmount * 6 / 100;
			sheet.getRow(22).getCell(7).setCellValue(sgst);
			Double cgst = totalAmount * 6 / 100;
			sheet.getRow(23).getCell(7).setCellValue(cgst);
			// totalAmount after disount and sgtst cgst added
			Double invoiceTotal = totalAmount + sgst + cgst - discountAmount;
			System.out.println(invoiceTotal);
			System.out.println(" Round of:" + Math.round(invoiceTotal));
			Double totalInvoiceAmount = (double) Math.round(invoiceTotal);

			sheet.getRow(24).getCell(7).setCellValue(totalInvoiceAmount);

			NumberWordConverter numberToWord = new NumberWordConverter();
			String totalInWord = numberToWord.convert(totalInvoiceAmount);
			sheet.getRow(26).getCell(1).setCellValue(totalInWord);
			// for loop
			// String[] terms = invoiceDetail.getTerms();

			int currentRow = 28;
			String[] termsArray = terms.split(",");
			for (int i = 0; i < termsArray.length; i++) {
				sheet.getRow(currentRow + i).getCell(0).setCellValue(termsArray[i]);
			}
			// nextRow++;
		}

		inputStream.close();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			workbook.write(bos);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			bos.close();
		}
		byte[] bytes = bos.toByteArray();

		// Closing the workbook
		workbook.close();

		return bytes;
	}

	private InvoiceDetails get(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
