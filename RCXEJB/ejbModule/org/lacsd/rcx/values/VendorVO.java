package org.lacsd.rcx.values;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		VendorDAO.java
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Sept 16, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Private variables for all fields of Vendor Database + inUse field, and an array list of vendVOs
 * Getters/Setters for all these variables contained here
 * @author carlosarojas
 *
 */
public class VendorVO implements Serializable{

	private static final long serialVersionUID = -6032020804002919172L;
	
		
	private short vendID;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String ext;
	private String fax;
	private String contact;
	private String refNum;
	
	private ArrayList<VendorVO> vendVOs;
	
	private String inUse;
	
	
	public String getInUse() {
		return inUse;
	}
	public void setInUse(String inUse) {
		this.inUse = inUse;
	}
	public ArrayList<VendorVO> getVendVOs() {
		return vendVOs;
	}
	public void setVendVOs(ArrayList<VendorVO> vendVOs) {
		this.vendVOs = vendVOs;
	}
	public short getVendID() {
		return vendID;
	}
	public void setVendID(short vendID) {
		this.vendID = vendID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getRefNum() {
		return refNum;
	}
	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}
	
	/*
	@Override
	public String toString() {
		return "VendorVO [vendID=" + vendID + ", name=" + name + ", address=" + address + ", city=" + city + ", state="
				+ state + ", zip=" + zip + ", phone=" + phone + ", ext=" + ext + ", fax=" + fax + ", contact=" + contact
				+ ", refNum=" + refNum + ", inUse=" + inUse + "]";
	}
	*/
	
	

}
