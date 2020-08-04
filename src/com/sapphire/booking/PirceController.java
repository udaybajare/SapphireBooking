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
import com.sapphire.dao.CRPriceReadyStockDao;
import com.sapphire.dao.GlassPriceDao;
import com.sapphire.dao.GlassPriceReadyStockDao;
import com.sapphire.entity.CRPrise;
import com.sapphire.entity.CRPriseReadyStock;
import com.sapphire.entity.GlassPrice;
import com.sapphire.entity.GlassPriceReadyStock;

@Controller
@EnableWebMvc
public class PirceController {

	@Autowired
	CRPriceDao crPriceDao;

	@Autowired
	CRPriceReadyStockDao crPriceReadyStockDao;

	@Autowired
	GlassPriceDao glassPriceDao;
	
	@Autowired
	GlassPriceReadyStockDao glassPriceReadyStockDao;

	
	

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

		// CR Price for ready Stock Orders

		ArrayList<CRPriseReadyStock> crPriseReadyStockList = (ArrayList<CRPriseReadyStock>) crPriceReadyStockDao.getPriceList();

		StringBuffer crPriceReadyStockDetails = new StringBuffer();

		for (CRPriseReadyStock crPriseReadyStock : crPriseReadyStockList) {
			crPriceReadyStockDetails.append("<tr>");
			crPriceReadyStockDetails.append("<td>" + crPriseReadyStock.getRowIndex() + "</td>");
			crPriceReadyStockDetails.append("<td><input type='hidden' name='typeVal' value='" + crPriseReadyStock.getTypeVal() + "'>" + crPriseReadyStock.getTypeVal() + "</td>");
			crPriceReadyStockDetails.append("<td><input type='hidden' name='tint' value='" + crPriseReadyStock.getTint() + "'>" + crPriseReadyStock.getTint() + "</td>");
			crPriceReadyStockDetails.append("<td><input type='text'  					name='indexVal' value='"
					+ crPriseReadyStock.getIndex() + "'></td>");
			crPriceReadyStockDetails.append("<td><input type='text' 					name='coating' value='"
					+ crPriseReadyStock.getCoating() + "'></td>");
			crPriceReadyStockDetails.append("<td><input type='text' style='width:45px;' name='dia' value='"
					+ crPriseReadyStock.getDia() + "'></td>");
			crPriceReadyStockDetails.append("<td><input type='text' style='width:45px;' name='sphUpto' value='"
					+ crPriseReadyStock.getSphUpto() + "'></td>");
			crPriceReadyStockDetails.append("<td><input type='text' style='width:45px;' name='cylUpto' value='"
					+ crPriseReadyStock.getCylUpto() + "'></td>");
			crPriceReadyStockDetails.append("<td><input type='text' style='width:45px;' name='axis' value='"
					+ crPriseReadyStock.getAxis() + "'></td>");
			crPriceReadyStockDetails.append("<td><input type='text' style='width:45px;' name='uc' value='"
					+ crPriseReadyStock.getUc() + "'></td>");
			crPriceReadyStockDetails.append("<td><input type='text' style='width:45px;' name='hc' value='"
					+ crPriseReadyStock.getHc() + "'></td>");
			crPriceReadyStockDetails.append("<td><input type='text' style='width:45px;' name='arc' value='"
					+ crPriseReadyStock.getArc() + "'></td>");
			crPriceReadyStockDetails.append("</tr>");

		}
		
		//Glass Price for ready stock 
		//Take all data from db and insert into input
		//creaate stringBuffer for glassReadyStock
		ArrayList<GlassPriceReadyStock> glassPriceReadyStockList = (ArrayList<GlassPriceReadyStock>) glassPriceReadyStockDao.getPriceList();
		StringBuffer glassPriceReadyStockDetails = new StringBuffer();
		int index1 = 1;
		for(GlassPriceReadyStock glassPriceReadyStock:glassPriceReadyStockList){
			glassPriceReadyStockDetails.append("<tr>");
			
			glassPriceReadyStockDetails.append("<td><input type='text' name='Sph' value='"+ glassPriceReadyStock.getSph() +"' style='width: 76px;'></td>");
			glassPriceReadyStockDetails.append("<td><input type='text' name='Cyl' value='" + glassPriceReadyStock.getCyl() +"' style='width: 76px;'></td>");
			glassPriceReadyStockDetails.append("<td><input type='text' name='W_SV_NN' value='"+ glassPriceReadyStock.getW_SV_NN() +"' style='width: 76px;'></td>");
			glassPriceReadyStockDetails.append("<td><input type='text' name='W_SV_PP' value='"+ glassPriceReadyStock.getW_SV_PP() +"' style='width: 76px;'></td>");
			glassPriceReadyStockDetails.append("<td><input type='text' name='W_SV_PN' value='"+ glassPriceReadyStock.getW_SV_PN() +"' style='width: 76px;'></td>");
			glassPriceReadyStockDetails.append("<td><input type='text' name='PG_SV_NN' value='"+ glassPriceReadyStock.getPG_SV_NN() +"' style='width: 76px;'></td>");
			glassPriceReadyStockDetails.append("<td><input type='text' name='PG_SV_PP' value='"+ glassPriceReadyStock.getPG_SV_PP() +"' style='width: 76px;'></td>");
			glassPriceReadyStockDetails.append("<td><input type='text' name='PG_SV_PN' value='"+ glassPriceReadyStock.getPG_SV_PN() +"' style='width: 76px;'></td>");
			glassPriceReadyStockDetails.append("<td><input type='text' name='W_KT_P' value='"+ glassPriceReadyStock.getW_KT_P() +"' style='width: 76px;'></td>");
			glassPriceReadyStockDetails.append("<td><input type='text' name='W_KT_N' value='"+ glassPriceReadyStock.getW_KT_N() +"' style='width: 76px;'></td>");
			glassPriceReadyStockDetails.append("<td><input type='text' name='PG_KT_P' value='"+ glassPriceReadyStock.getPG_KT_P() +"' style='width: 76px;'></td>");
			glassPriceReadyStockDetails.append("<td><input type='text' name='PG_KT_N' value='"+ glassPriceReadyStock.getPG_KT_N() +"' style='width: 76px;'></td>");
			glassPriceReadyStockDetails.append("<td><input type='text' name='W_KT' value='"+ glassPriceReadyStock.getW_KT() +"' style='width: 76px;'></td>");
			glassPriceReadyStockDetails.append("<td><input type='text' name='PG_KT' value='"+ glassPriceReadyStock.getPG_KT() +"' style='width: 76px;'></td>");

			glassPriceReadyStockDetails.append("</tr>");
			
			index1++;
		}

		modelAndView.setViewName("showPriceList");

		modelAndView.addObject("glassPriceDetails", glassPriceDetails.toString());
		modelAndView.addObject("crPriceDetails", crPriceDetails.toString());
		modelAndView.addObject("crPriceReadyStockDetails", crPriceReadyStockDetails.toString());
		modelAndView.addObject("glassPriceReadyStockDetails", glassPriceReadyStockDetails.toString());
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

	@RequestMapping(value = "updateCRPriceReadyStockList", method = RequestMethod.POST)
	public @ResponseBody String updateCRPriceReadyStockList(String[] typeVal, String[] tint, String[] indexVal,
			String[] coating, String[] dia, String[] sphUpto, String[] cylUpto, String[] axis, String[] uc, String[] hc,
			String[] arc) {

		System.out.println("I m here in array "+typeVal.length);
		for (int i = 0; i < typeVal.length; i++) {

			CRPriseReadyStock crPrice = new CRPriseReadyStock(i + 1, typeVal[i], tint[i], indexVal[i], coating[i],
					dia[i], sphUpto[i], cylUpto[i], axis[i], uc[i], hc[i], arc[i]);
			crPriceReadyStockDao.saveOrUpdate(crPrice);

		}

		return "true";
	}
	
	

	@RequestMapping(value = "updateGlasspriceReadyStockList", method = RequestMethod.POST)
	public @ResponseBody String updateGlasspriceReadyStockList(String[] Sph, String[] Cyl, String[] W_SV_NN,
			String[] W_SV_PP, String[] W_SV_PN, String[] PG_SV_NN, String[] PG_SV_PP,
			String[] PG_SV_PN, String[] W_KT_P, String[] W_KT_N, String[] PG_KT_P,
			String[] PG_KT_N, String[] W_KT, String[] PG_KT) {
		
		System.out.println("I m here in array "+Sph.length);

		for (int i = 0; i < Sph.length ; i++) {

			GlassPriceReadyStock glassPrice = new GlassPriceReadyStock(Sph[i], Cyl[i], W_SV_NN[i], W_SV_PP[i], W_SV_PN[i], PG_SV_NN[i], PG_SV_PP[i], PG_SV_PN[i], W_KT_P[i], W_KT_N[i], PG_KT_P[i], PG_KT_N[i], W_KT[i], PG_KT[i] );
			glassPrice.setId(i+1);
			glassPriceReadyStockDao.saveOrUpdate(glassPrice);

		}

		return "true";
	}

}
