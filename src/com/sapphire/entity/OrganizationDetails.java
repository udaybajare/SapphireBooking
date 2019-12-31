package com.sapphire.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class OrganizationDetails {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int orgID;
    
    @Id
    private String custNumber;
   

	private String orgName;
    
    private String orgAddress;
    private String orgContact;

    public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
    public String getOrgName() {
	return orgName;
    }

    public void setOrgName(String orgName) {
	this.orgName = orgName;
    }

    public String getOrgAddress() {
	return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
	this.orgAddress = orgAddress;
    }

    public String getOrgContact() {
	return orgContact;
    }

    public void setOrgContact(String orgContact) {
	this.orgContact = orgContact;
    }
}
