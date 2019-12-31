package com.sapphire.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderDetails")
public class OrderDetails implements Serializable {

    public OrderDetails() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private int orderId;
    private  Date orderDate;
    private String material;
    private String type;
    private String indx;
    private String coating;
    private String tint;
    private String qtyNos;
    private String frameType;
    private String sourcing;
    private String status;
    private Double totalAmount;
    private String comment;
    private String organizationName;
    private String userName;
    private String contactNo;

    public OrderDetails(String material, String type, String index, String coating, String tint, String qtyNos,
	    String frameType, String sourcing, String organizatioName, String userName, String contactNo,
	    Date orderDate, String status, String comment, Double totalAmount) {
	super();
	this.material = material;
	this.type = type;
	this.indx = index;
	this.coating = coating;
	this.tint = tint;
	this.qtyNos = qtyNos;
	this.frameType = frameType;
	this.sourcing = sourcing;
	this.organizationName = organizatioName;
	this.userName = userName;
	this.contactNo = contactNo;
	this.orderDate = orderDate;
	this.status = status;
	this.comment = comment;
	this.totalAmount = totalAmount;
    }

    public int getId() {
	return id;
    }

    public void setId(int Id) {
	this.id = Id;
    }

    public int getOrderId() {
	return orderId;
    }

    public void setOrderId(int orderId) {
	this.orderId = orderId;
    }

    public String getMaterial() {
	return material;
    }

    public void setMaterial(String material) {
	this.material = material;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getIndex() {
	return indx;
    }

    public void setIndex(String index) {
	this.indx = index;
    }

    public String getCoating() {
	return coating;
    }

    public void setCoating(String coating) {
	this.coating = coating;
    }

    public String getTint() {
	return tint;
    }

    public void setTint(String tint) {
	this.tint = tint;
    }

    public String getQtyNos() {
	return qtyNos;
    }

    public void setQtyNos(String qtyNos) {
	this.qtyNos = qtyNos;
    }

    public String getFrameType() {
	return frameType;
    }

    public void setFrameType(String frameType) {
	this.frameType = frameType;
    }

    public String getSourcing() {
	return sourcing;
    }

    public void setSourcing(String sourcing) {
	this.sourcing = sourcing;
    }

    public String getOrganizationName() {
	return organizationName;
    }

    public void setOrganizationName(String organizationName) {
	this.organizationName = organizationName;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getContactNo() {
	return contactNo;
    }

    public void setContactNo(String contactNo) {
	this.contactNo = contactNo;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }
    public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

    public Date getOrderDate() {
	return orderDate;
    }

    public void setOrderDate(Date orderDate) {
	this.orderDate = orderDate;
    }

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		return "OrderDetails [id=" + id + ", orderId=" + orderId + ", orderDate=" + dateFormat.format(orderDate) + ", material=" + material
				+ ", type=" + type + ", indx=" + indx + ", coating=" + coating + ", tint=" + tint + ", qtyNos=" + qtyNos
				+ ", frameType=" + frameType + ", sourcing=" + sourcing + ", status=" + status + ", organizationName="
				+ organizationName + ", userName=" + userName + ", contactNo=" + contactNo + ", comment=" + comment + ", totalAmount=" + totalAmount + " ]";
	}
    
    
    
}
