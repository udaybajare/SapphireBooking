package com.sapphire.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EntryDetails")
public class EntryDetails implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id1;
        
    private int orderDetailsId;
    private int orderId;
    private String rSph;
    private String rCyl;
    private String rAxis;
    private String rAdd;
    private String rDia;

    private String lSph;
    private String lCyl;
    private String lAxis;
    private String lAdd;
    private String lDia;
    
    private String lPrice;
    private String rPrice;

    public EntryDetails(){	
    }
    
    public EntryDetails(String rSph, String rCyl, String rAxis, String rAdd, String rDia,
	    String lSph, String lCyl, String lAxis, String lAdd, String lDia, String lPrice, String rPrice) {
	super();
	this.rSph = rSph;
	this.rCyl = rCyl;
	this.rAxis = rAxis;
	this.rAdd = rAdd;
	this.rDia = rDia;
	this.lSph = lSph;
	this.lCyl = lCyl;
	this.lAxis = lAxis;
	this.lAdd = lAdd;
	this.lDia = lDia;
	this.lPrice = lPrice;
	this.rPrice = rPrice;
    }

    public int getOrderId() {
	return orderId;
    }

    public void setOrderId(int orderId) {
	this.orderId = orderId;
    }

    public String getrSph() {
	return rSph;
    }

    public void setrSph(String rSph) {
	this.rSph = rSph;
    }

    public String getrCyl() {
	return rCyl;
    }

    public void setrCyl(String rCyl) {
	this.rCyl = rCyl;
    }

    public String getrAxis() {
	return rAxis;
    }

    public void setrAxis(String rAxis) {
	this.rAxis = rAxis;
    }

    public String getrAdd() {
	return rAdd;
    }

    public void setrAdd(String rAdd) {
	this.rAdd = rAdd;
    }

    public String getrDia() {
	return rDia;
    }

    public void setrDia(String rDia) {
	this.rDia = rDia;
    }

    public String getlSph() {
	return lSph;
    }

    public void setlSph(String lSph) {
	this.lSph = lSph;
    }

    public String getlCyl() {
	return lCyl;
    }

    public void setlCyl(String lCyl) {
	this.lCyl = lCyl;
    }

    public String getlAxis() {
	return lAxis;
    }

    public void setlAxis(String lAxis) {
	this.lAxis = lAxis;
    }

    public String getlAdd() {
	return lAdd;
    }

    public void setlAdd(String lAdd) {
	this.lAdd = lAdd;
    }

    public String getlDia() {
	return lDia;
    }

    public void setlDia(String lDia) {
	this.lDia = lDia;
    }

    public int getlOrderDetailsId() {
	return orderDetailsId;
    }

    public void setOrderDetailsId(int orderDetailsId) {
	this.orderDetailsId = orderDetailsId;
    }

	public String getlPrice() {
		return lPrice;
	}

	public void setlPrice(String lPrice) {
		this.lPrice = lPrice;
	}

	public String getrPrice() {
		return rPrice;
	}

	public void setrPrice(String rPrice) {
		this.rPrice = rPrice;
	}

	@Override
	public String toString() {
		return "EntryDetails [id1=" + id1 + ", orderDetailsId=" + orderDetailsId + ", orderId=" + orderId + ", rSph="
				+ rSph + ", rCyl=" + rCyl + ", rAxis=" + rAxis + ", rAdd=" + rAdd + ", rDia=" + rDia + ", lSph=" + lSph
				+ ", lCyl=" + lCyl + ", lAxis=" + lAxis + ", lAdd=" + lAdd + ", lDia=" + lDia + ", lPrice=" + lPrice
				+ ", rPrice=" + rPrice + "]";
	}
    
    
}
