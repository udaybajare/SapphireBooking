package com.sapphire.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.ManagedBean;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import com.sapphire.dao.OrderDao;
import com.sapphire.dao.OrganizationDao;
import com.sapphire.entity.EntryDetails;
import com.sapphire.entity.OrderDetails;
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

		ArrayList<ArrayList<String>> registeredOrgs = organizationDao.getRegisteredOrganization();

		for (Object name : registeredOrgs) {

			Object[] nameList = (Object[]) name;

			cuetomerDetailsMap.put((String) nameList[1], (String) nameList[0]);
		}

		Sheet sheet = workbook.getSheetAt(0);

		sheet.getRow(0).getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(new SimpleDateFormat("dd.MM.yy").format(new Date()));

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
			if ((entry.getrAdd() != null || entry.getrAxis() != null || entry.getrCyl() != null
					|| entry.getrDia() != null || entry.getrSph() != null)) {
				lenseSide = "R";
				isRPresent = true;
			} 
			if ((entry.getlAdd() != null || entry.getlAxis() != null || entry.getlCyl() != null
					|| entry.getlDia() != null || entry.getlSph() != null)) {
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
				cellToUpdate10.setCellValue(orerDetail.getCoating() + " " + orerDetail.getTint());
				
				Double rPrise = Double.valueOf(entry.getrPrice());
				
				if(!isLPresent)
				{
					double remainder = 10 - (rPrise)%10;
					rPrise = ((((rPrise)+remainder)/10)*10);
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

				Cell cellToUpdate14 = sheet.getRow(row).getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate14.setCellValue(sph);
				Cell cellToUpdate15 = sheet.getRow(row).getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate15.setCellValue(cyl);
				Cell cellToUpdate16 = sheet.getRow(row).getCell(16, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate16.setCellValue(Integer.sum(sph, cyl));
				Cell cellToUpdate17 = sheet.getRow(row).getCell(17, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate17.setCellValue(1);
			}

			if (isLPresent) {

				// Go to next row
				row++;
				nextRow++;

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
				cellToUpdate10.setCellValue(orerDetail.getCoating() + " " + orerDetail.getTint());

				Double lPrise = Double.valueOf(entry.getlPrice());
				
				if(!isRPresent)
				{
					double remainder = 10 - (lPrise)%10;
					lPrise = ((((lPrise)+remainder)/10)*10);
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

				Cell cellToUpdate14 = sheet.getRow(row).getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate14.setCellValue(sph);
				Cell cellToUpdate15 = sheet.getRow(row).getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate15.setCellValue(cyl);
				Cell cellToUpdate16 = sheet.getRow(row).getCell(16, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate16.setCellValue(Integer.sum(sph, cyl));
				Cell cellToUpdate17 = sheet.getRow(row).getCell(17, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdate17.setCellValue(1);
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
	
	public String get4DigitNumber(int number)
	{
		String orderIdStr = String.valueOf(number);
		
		while(orderIdStr.length()<4)
		{
			orderIdStr = "0"+orderIdStr;
		}
		
		return orderIdStr;
	}
}
