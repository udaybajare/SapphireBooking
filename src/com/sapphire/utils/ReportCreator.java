package com.sapphire.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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
			
			cuetomerDetailsMap.put((String)nameList[1],(String)nameList[0]);
		}
		
		Sheet sheet = workbook.getSheetAt(0);

		int index = 0;
		int nextRow = 4;
		int i = 1;
		for (OrderDetails orerDetail : orerDetailList) {

			int orderId = orerDetail.getOrderId();
			int orderDetailsId = orerDetail.getId();
			String lenseSide = "";

			EntryDetails entry = orderDao.getEntryDetails(orderId, orderDetailsId);

			if ((entry.getrAdd() != null || entry.getrAxis() != null || entry.getrCyl() != null
					|| entry.getrDia() != null || entry.getrSph() != null)
					&& (entry.getlAdd() != null || entry.getlAxis() != null || entry.getlCyl() != null
							|| entry.getlDia() != null || entry.getlSph() != null)) {
				lenseSide = "B";
			} else if ((entry.getrAdd() != null || entry.getrAxis() != null || entry.getrCyl() != null
					|| entry.getrDia() != null || entry.getrSph() != null)) {
				lenseSide = "R";
			} else if ((entry.getlAdd() != null || entry.getlAxis() != null || entry.getlCyl() != null
					|| entry.getlDia() != null || entry.getlSph() != null)) {
				lenseSide = "L";
			}

			int sph = 0;
			int cyl = 0;

			int row = nextRow + i;

			Cell cellToUpdate0 = sheet.getRow(row).getCell(0);
			
			String customerNo = cuetomerDetailsMap.get(orerDetail.getOrganizationName());
			cellToUpdate0.setCellValue(customerNo+"/"+orerDetail.getCustOrderNumber());
			Cell cellToUpdate1 = sheet.getRow(row).getCell(1);
			cellToUpdate1.setCellValue(orerDetail.getOrganizationName());
			Cell cellToUpdate2 = sheet.getRow(row).getCell(2);
			cellToUpdate2.setCellValue(orerDetail.getOrderDate());

			Cell cellToUpdate3 = sheet.getRow(row).getCell(3);
			cellToUpdate3.setCellValue(lenseSide.equals("B") ? "R" : lenseSide);

			if (lenseSide.equalsIgnoreCase("R")) {
				Cell cellToUpdate4 = sheet.getRow(row).getCell(4);
				cellToUpdate4.setCellValue(entry.getrSph());
				Cell cellToUpdate5 = sheet.getRow(row).getCell(5);
				cellToUpdate5.setCellValue(entry.getrCyl());
				Cell cellToUpdate6 = sheet.getRow(row).getCell(6);
				cellToUpdate6.setCellValue(entry.getrAxis());
				Cell cellToUpdate7 = sheet.getRow(row).getCell(7);
				cellToUpdate7.setCellValue(entry.getrAdd());

				Cell cellToUpdate9 = sheet.getRow(row).getCell(9);
				cellToUpdate9.setCellValue(entry.getrPrice());

				if (entry.getrSph() != null) {
					sph = 1;
				}
				if (entry.getrCyl() != null) {
					cyl = 1;
				}

			}

			if (lenseSide.equalsIgnoreCase("L")) {
				Cell cellToUpdate4 = sheet.getRow(row).getCell(4);
				cellToUpdate4.setCellValue(entry.getlSph());
				Cell cellToUpdate5 = sheet.getRow(row).getCell(5);
				cellToUpdate5.setCellValue(entry.getlCyl());
				Cell cellToUpdate6 = sheet.getRow(row).getCell(6);
				cellToUpdate6.setCellValue(entry.getlAxis());
				Cell cellToUpdate7 = sheet.getRow(row).getCell(7);
				cellToUpdate7.setCellValue(entry.getlAdd());

				Cell cellToUpdate9 = sheet.getRow(row).getCell(9);
				cellToUpdate9.setCellValue(entry.getlPrice());

				if (entry.getlSph() != null) {
					sph = 1;
				}
				if (entry.getlCyl() != null) {
					cyl = 1;
				}
			}

			Cell cellToUpdate8 = sheet.getRow(row).getCell(8);
			cellToUpdate8.setCellValue(orerDetail.getCoating() + " " + orerDetail.getTint());

			Cell cellToUpdate10 = sheet.getRow(row).getCell(10);
			cellToUpdate10.setCellValue(orerDetail.getComment());

			// Nothing to set in 12
			/*
			 * Cell cellToUpdate0 = sheet.getRow(row).getCell(12);
			 * cellToUpdate0.setCellValue();
			 */

			Cell cellToUpdate11 = sheet.getRow(row).getCell(12);
			cellToUpdate11.setCellValue(sph);
			Cell cellToUpdate12 = sheet.getRow(row).getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cellToUpdate12.setCellValue(cyl);
			Cell cellToUpdate13 = sheet.getRow(row).getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cellToUpdate13.setCellValue(Integer.sum(sph, cyl));
			Cell cellToUpdate14 = sheet.getRow(row).getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cellToUpdate14.setCellValue(1);

			if (lenseSide == "B") {
				// Add R lense details
				Cell cellToUpdate4 = sheet.getRow(row).getCell(4);
				cellToUpdate4.setCellValue(entry.getrSph());
				Cell cellToUpdate5 = sheet.getRow(row).getCell(5);
				cellToUpdate5.setCellValue(entry.getrCyl());
				Cell cellToUpdate6 = sheet.getRow(row).getCell(6);
				cellToUpdate6.setCellValue(entry.getrAxis());
				Cell cellToUpdate7 = sheet.getRow(row).getCell(7);
				cellToUpdate7.setCellValue(entry.getrAdd());

				Cell cellToUpdate9 = sheet.getRow(row).getCell(9);
				cellToUpdate9.setCellValue(entry.getrPrice());

				if (entry.getrSph() != null) {
					sph = 1;
				}
				if (entry.getrCyl() != null) {
					cyl = 1;
				}

				Cell cellToUpdateR11 = sheet.getRow(row).getCell(12);
				cellToUpdateR11.setCellValue(sph);
				Cell cellToUpdateR12 = sheet.getRow(row).getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdateR12.setCellValue(cyl);
				Cell cellToUpdateR13 = sheet.getRow(row).getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdateR13.setCellValue(Integer.sum(sph, cyl));
				Cell cellToUpdateR14 = sheet.getRow(row).getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdateR14.setCellValue(1);

				row = row + 1;
				Cell cellToUpdateR0 = sheet.getRow(row).getCell(0);
				cellToUpdateR0.setCellValue(1);
				Cell cellToUpdateR1 = sheet.getRow(row).getCell(1);
				cellToUpdateR1.setCellValue(orerDetail.getOrganizationName());
				Cell cellToUpdateR2 = sheet.getRow(row).getCell(2);
				cellToUpdateR2.setCellValue(orerDetail.getOrderDate());
				Cell cellToUpdateR3 = sheet.getRow(row).getCell(3);
				cellToUpdateR3.setCellValue("L");

				// Add L lense details
				Cell cellToUpdateL4 = sheet.getRow(row).getCell(4);
				cellToUpdateL4.setCellValue(entry.getlSph());
				Cell cellToUpdateL5 = sheet.getRow(row).getCell(5);
				cellToUpdateL5.setCellValue(entry.getlCyl());
				Cell cellToUpdateL6 = sheet.getRow(row).getCell(6);
				cellToUpdateL6.setCellValue(entry.getlAxis());
				Cell cellToUpdateL7 = sheet.getRow(row).getCell(7);
				cellToUpdateL7.setCellValue(entry.getlAdd());

				Cell cellToUpdateR9 = sheet.getRow(row).getCell(9);
				cellToUpdateR9.setCellValue(entry.getlPrice());

				if (entry.getlSph() != null) {
					sph = 1;
				}
				if (entry.getlCyl() != null) {
					cyl = 1;
				}

				Cell cellToUpdateL11 = sheet.getRow(row).getCell(12);
				cellToUpdateL11.setCellValue(sph);
				Cell cellToUpdateL12 = sheet.getRow(row).getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdateL12.setCellValue(cyl);
				Cell cellToUpdateL13 = sheet.getRow(row).getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdateL13.setCellValue(Integer.sum(sph, cyl));
				Cell cellToUpdateL14 = sheet.getRow(row).getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cellToUpdateL14.setCellValue(1);

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
}
