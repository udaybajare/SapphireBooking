package com.sapphire.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CRPrise implements Comparable<CRPrise> {

	@Id
	private Integer rowIndex;

	private String product;
	private String type;
	private String indexVal;
	private String negativeNbr;
	private String positiveNbr;
	private String ucSrp;
	private String hcSrp;
	private String arcSrp;

	public CRPrise()
	{
		
	}
	
	public CRPrise(int rowIndex, String product, String type, String indexVal, String negativeNbr, String positiveNbr,
			String ucSrp, String hcSrp, String arcSrp) {
		super();
		this.rowIndex = rowIndex;
		this.product = product;
		this.type = type;
		this.indexVal = indexVal;
		this.negativeNbr = negativeNbr;
		this.positiveNbr = positiveNbr;
		this.ucSrp = ucSrp;
		this.hcSrp = hcSrp;
		this.arcSrp = arcSrp;
	}

	public Integer getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIndexVal() {
		return indexVal;
	}

	public void setIndexVal(String indexVal) {
		this.indexVal = indexVal;
	}

	public String getNegativeNbr() {
		return negativeNbr;
	}

	public void setNegativeNbr(String negativeNbr) {
		this.negativeNbr = negativeNbr;
	}

	public String getPositiveNbr() {
		return positiveNbr;
	}

	public void setPositiveNbr(String positiveNbr) {
		this.positiveNbr = positiveNbr;
	}

	public String getUcSrp() {
		return ucSrp;
	}

	public void setUcSrp(String ucSrp) {
		this.ucSrp = ucSrp;
	}

	public String getHcSrp() {
		return hcSrp;
	}

	public void setHcSrp(String hcSrp) {
		this.hcSrp = hcSrp;
	}

	public String getArcSrp() {
		return arcSrp;
	}

	public void setArcSrp(String arcSrp) {
		this.arcSrp = arcSrp;
	}

	@Override
	public int compareTo(CRPrise o) 
	{
		return this.getNegativeNbr().compareTo(o.getNegativeNbr());
	}

}
