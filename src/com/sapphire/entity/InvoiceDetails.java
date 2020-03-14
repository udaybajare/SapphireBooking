package com.sapphire.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InvoiceDetails {
	
@Id
//@GeneratedValue(strategy=GenerationType.AUTO)
private String invoiceNo;
private String orgName;
private String fromDate;
private String toDate;
private boolean gstNon;
private Double discount;
private Double totalAmount;
private Double discountTotalAmount;
private String terms;
private String invoiceGenerateDate;





public InvoiceDetails(String invoiceNo, String orgName, String fromDate, String toDate, boolean gstNon, Double discount,
		Double totalAmount, Double discountTotalAmount, String terms, String invoiceGenerateDate) {
	super();
	this.invoiceGenerateDate = invoiceGenerateDate;
	this.invoiceNo = invoiceNo;
	this.orgName = orgName;
	this.fromDate = fromDate;
	this.toDate = toDate;
	this.gstNon = gstNon;
	this.discount = discount;
	this.totalAmount = totalAmount;
	this.discountTotalAmount = discountTotalAmount;
	this.terms = terms;
}
public String getInvoiceNo() {
	return invoiceNo;
}
public void setInvoiceNo(String invoiceNo) {
	this.invoiceNo = invoiceNo;
}
public String getOrgName() {
	return orgName;
}
public void setOrgName(String orgName) {
	this.orgName = orgName;
}
public String getFromDate() {
	return fromDate;
}
public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}
public String getToDate() {
	return toDate;
}
public void setToDate(String toDate) {
	this.toDate = toDate;
}
public boolean isGstNon() {
	return gstNon;
}
public void setGst_non(boolean gstNon) {
	this.gstNon = gstNon;
}
public Double getDiscount() {
	return discount;
}
public void setDiscount(Double discount) {
	this.discount = discount;
}
public Double getTotalAmount() {
	return totalAmount;
}
public void setTotalAmount(Double totalAmount) {
	this.totalAmount = totalAmount;
}
public Double getDiscountTotalAmount() {
	return discountTotalAmount;
}
public void setDiscountTotalAmount(Double discountTotalAmount) {
	this.discountTotalAmount = discountTotalAmount;
}
public String getTerms() {
	return terms;
}
public void setTerms(String terms) {
	this.terms = terms;
}

@Override
public String toString() {
	return "InvoiceDetails [invoiceNo=" + invoiceNo + ", orgName=" + orgName + ", fromDate=" + fromDate + ", toDate="
			+ toDate + ", gstNon=" + gstNon + ", discount=" + discount + ", totalAmount=" + totalAmount
			+ ", discountTotalAmount=" + discountTotalAmount + ", terms=" + terms + "]";
}



}
