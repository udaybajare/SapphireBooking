package com.sapphire.booking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sapphire.dao.CRPriceDao;
import com.sapphire.dao.GlassPriceDao;
import com.sapphire.entity.CRPrise;
import com.sapphire.entity.GlassPrice;

@Controller
@EnableWebMvc
public class PirceController {

	@Autowired
	CRPriceDao crPriceDao;

	@Autowired
	GlassPriceDao glassPriceDao;

	@RequestMapping(value = "showPriceList", method = RequestMethod.GET)
	public ModelAndView showPriceList() {
		ModelAndView modelAndView = new ModelAndView();

		ArrayList<GlassPrice> glassPriceList = (ArrayList<GlassPrice>) glassPriceDao.getPriceList();

		StringBuffer glassPriceDetails = new StringBuffer();

		double cylendrical = 1.0;
		double sperical = 1.0;
		for (GlassPrice glassPrice : glassPriceList) {
			if (sperical > 5.0) {
				sperical = 1.0;
				cylendrical += 1.0;
			}

			glassPriceDetails.append("<tr>");
			glassPriceDetails.append("<td>" + String.valueOf(cylendrical) + "/" + String.valueOf(sperical) + "</td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getPB_KT() + "' name='PB_KT' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getPB_SV() + "' name='PB_SV' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getPG4_KT() + "' name='PG4_KT' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getPG4_SV() + "' name='PG4_SV' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getPG6_KT() + "' name='PG6_KT' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getPG6_SV() + "' name='PG6_SV' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getSP2_KT() + "' name='SP2_KT' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getSP2_SV() + "' name='SP2_SV' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getW_KT() + "' name='W_KT' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getW_SV() + "' name='W_SV' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getPG_DB() + "' name='PG_DB' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getPG_DB_ARC() + "' name='PG_DB_ARC' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getPG_PR() + "' name='PG_PR' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getPG_PR_ARC() + "' name='PG_PR_ARC' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getW_DB() + "' name='W_DB' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getW_DB_ARC() + "' name='W_DB_ARC' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getW_PR() + "' name='W_PR' ></td>");
			glassPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ glassPrice.getW_PR_ARC() + "' name='W_PR_ARC' ></td>");
			glassPriceDetails.append("</tr>");

			sperical++;
		}

		ArrayList<CRPrise> crPriceList = (ArrayList<CRPrise>) crPriceDao.getPriceList();

		StringBuffer crPriceDetails = new StringBuffer();
		int index = 1;

		for (CRPrise crPrice : crPriceList) {
			crPriceDetails.append("<tr>");
			crPriceDetails.append("<td>" + index + "</td>");
			crPriceDetails.append("<td><input type='hidden' name='product' value='" + crPrice.getProduct() + "'>"
					+ crPrice.getProduct() + "</td>");
			crPriceDetails.append("<td><input type='hidden' name='type' value='" + crPrice.getType() + "'>"
					+ crPrice.getType() + "</td>");
			crPriceDetails.append("<td><input type='hidden' name='indexVal' value='" + crPrice.getIndexVal() + "'>"
					+ crPrice.getIndexVal() + "</td>");
			crPriceDetails.append("<td><input type='hidden' name='negativeNbr' value='" + crPrice.getNegativeNbr()
					+ "'>" + crPrice.getNegativeNbr() + "</td>");
			crPriceDetails.append("<td><input type='hidden' name='positiveNbr' value='" + crPrice.getPositiveNbr()
					+ "'>" + crPrice.getPositiveNbr() + "</td>");
			crPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ crPrice.getUcSrp() + "' name='ucSrp' ></td>");
			crPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ crPrice.getHcSrp() + "' name='hcSrp' ></td>");
			crPriceDetails.append("<td><input type='text' style='width:45px;' class='form-control1' value='"
					+ crPrice.getArcSrp() + "' name='arcSrp' ></td>");
			crPriceDetails.append("</tr>");

			index++;
		}

		modelAndView.setViewName("showPriceList");

		modelAndView.addObject("glassPriceDetails", glassPriceDetails.toString());
		modelAndView.addObject("crPriceDetails", crPriceDetails.toString());
		return modelAndView;
	}

	@RequestMapping(value = "updateGlassPriceList", method = RequestMethod.POST)
	public @ResponseBody String updateGlassPriceList(String[] PB_KT, String[] PB_SV, String[] PG4_KT, String[] PG4_SV,
			String[] PG6_KT, String[] PG6_SV, String[] SP2_KT, String[] SP2_SV, String[] W_KT, String[] W_SV,
			String[] PG_DB, String[] PG_DB_ARC, String[] PG_PR, String[] PG_PR_ARC, String[] W_DB, String[] W_DB_ARC,
			String[] W_PR, String[] W_PR_ARC) {

		for (int i = 0; i < PB_KT.length; i++) {

			GlassPrice glassPrice = new GlassPrice(W_KT[i], W_SV[i], PG4_KT[i], PG4_SV[i], PG6_KT[i], PG6_SV[i],
					SP2_KT[i], SP2_SV[i], PB_KT[i], PB_SV[i], W_PR[i], W_DB[i], PG_PR[i], PG_DB[i], W_PR_ARC[i],
					W_DB_ARC[i], PG_PR_ARC[i], PG_DB_ARC[i]);
			glassPrice.setRowIndex(i + 5);

			glassPriceDao.saveOrUpdate(glassPrice);
		}

		return "true";
	}

	@RequestMapping(value = "updateCRPriceList", method = RequestMethod.POST)
	public @ResponseBody String updateCRPriceList(String[] product, String[] type, String[] indexVal,
			String[] negativeNbr, String[] positiveNbr, String[] ucSrp, String[] hcSrp, String[] arcSrp) {

		for (int i = 0; i < product.length; i++) {

			CRPrise crPrice = new CRPrise(i + 1, product[i], type[i], indexVal[i], negativeNbr[i], positiveNbr[i],
					ucSrp[i], hcSrp[i], arcSrp[i]);

			crPriceDao.saveOrUpdate(crPrice);

		}

		return "true";
	}

}
