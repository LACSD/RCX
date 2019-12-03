package org.lacsd.rcx.values;

import java.io.Serializable;
import java.util.ArrayList;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		SiteVO.java
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Oct 30, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

public class SiteVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 291774221265376773L;

	
	private short siteID;
	private String siteName;
	private String abbreviation;
	private String certNumber;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	private ArrayList<SiteVO> siteVOs;
	
	
	public short getSiteID() {
		return siteID;
	}
	public void setSiteID(short siteID) {
		this.siteID = siteID;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getCertNumber() {
		return certNumber;
	}
	public void setCertNumber(String certNumber) {
		this.certNumber = certNumber;
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
	public ArrayList<SiteVO> getSiteVOs() {
		return siteVOs;
	}
	public void setSiteVOs(ArrayList<SiteVO> siteVOs) {
		this.siteVOs = siteVOs;
	}
	
}
