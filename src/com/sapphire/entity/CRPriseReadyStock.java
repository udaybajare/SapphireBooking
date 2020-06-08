package com.sapphire.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CRPriseReadyStock implements Comparable<CRPriseReadyStock>{

	@Id
	Integer rowIndex;
	
	String typeVal;
	String tint;
	String indexVal;
	String coating;
	String dia;
	String sphUpto;
	String cylUpto;
	String axis;
	String uc;
	String hc;
	String arc;

	public CRPriseReadyStock() {

	}

	public CRPriseReadyStock(Integer rowIndex, String typeVal, String tint, String index, String coating, String dia,
			String sphUpto, String cylUpto, String axis, String uc, String hc, String arc) {
		super();
		this.rowIndex = rowIndex;
		this.typeVal = typeVal;
		this.tint = tint;
		this.indexVal = index;
		this.coating = coating;
		this.dia = dia;
		this.sphUpto = sphUpto;
		this.cylUpto = cylUpto;
		this.axis = axis;
		this.uc = uc;
		this.hc = hc;
		this.arc = arc;
	}

	public Integer getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}

	public String getTypeVal() {
		return typeVal;
	}

	public void setTypeVal(String typeVal) {
		this.typeVal = typeVal;
	}

	public String getTint() {
		return tint;
	}

	public void setTint(String tint) {
		this.tint = tint;
	}

	public String getIndex() {
		return indexVal;
	}

	public void setIndex(String index) {
		this.indexVal = index;
	}

	public String getCoating() {
		return coating;
	}

	public void setCoating(String coating) {
		this.coating = coating;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getSphUpto() {
		return sphUpto;
	}

	public void setSphUpto(String sphUpto) {
		this.sphUpto = sphUpto;
	}

	public String getCylUpto() {
		return cylUpto;
	}

	public void setCylUpto(String cylUpto) {
		this.cylUpto = cylUpto;
	}

	public String getAxis() {
		return axis;
	}

	public void setAxis(String axis) {
		this.axis = axis;
	}

	public String getUc() {
		return uc;
	}

	public void setUc(String uc) {
		this.uc = uc;
	}

	public String getHc() {
		return hc;
	}

	public void setHc(String hc) {
		this.hc = hc;
	}

	public String getArc() {
		return arc;
	}

	public void setArc(String arc) {
		this.arc = arc;
	}

	@Override
	public int compareTo(CRPriseReadyStock arg0) {
		
		return this.getSphUpto().compareTo(arg0.getSphUpto());
	}

}
