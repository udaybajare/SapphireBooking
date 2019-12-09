package com.sapphire.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Uday
 *
 */
@Entity
@Table(name = "UserDetails", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserDetails {
    private String customerName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String postalCode;
    private String userName;
    private String contactNumber;
    private String email;
    private String password;
    private String gstNo;
    private String orgName;
    
    /**
     * Auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long serialNumberCustomer;

    public UserDetails() {

    }

    public UserDetails(String customerName, String addressLine1, String addressLine2, String addressLine3,
	    String postalCode, String userName, String contactNumber, String email, String password, String gSTNo, String orgName)
    {
	super();
	this.customerName = customerName;
	this.addressLine1 = addressLine1;
	this.addressLine2 = addressLine2;
	this.addressLine3 = addressLine3;
	this.postalCode = postalCode;
	this.userName = userName;
	this.contactNumber = contactNumber;
	this.email = email;
	this.password = password;
	this.gstNo = gSTNo;
	this.orgName = orgName;
    }

    public String getCustomerName() {
	return customerName;
    }

    public void setCustomerName(String customerName) {
	this.customerName = customerName;
    }

    public String getAddressLine1() {
	return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
	this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
	return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
	this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
	return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
	this.addressLine3 = addressLine3;
    }

    public String getPostalCode() {
	return postalCode;
    }

    public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getContactNumber() {
	return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getGstNo() {
	return gstNo;
    }

    public void setGstNo(String gSTNo) {
	this.gstNo = gSTNo;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getSerialNumberCustomer() {
	return srNoPrefix + serialNumberCustomer;
    }

    public void setSerialNumberCustomer(Long serialNumberCustomer) {
	this.serialNumberCustomer = serialNumberCustomer;
    }
    
    public String getOrgName() {
	return orgName;
    }

    public void setOrgName(String orgName) {
	this.orgName = orgName;
    }

    public static final String srNoPrefix = "SAPPHIRE";
}
