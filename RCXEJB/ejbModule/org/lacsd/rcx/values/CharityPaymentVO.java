package org.lacsd.rcx.values;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		MaterialAction.java
//* Revision: 		1.0
//* Author:			christophersimmons@lacsd.org
//* Created On: 	Aug 21, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

import java.io.Serializable;
import java.util.ArrayList;

public class CharityPaymentVO implements Serializable{




	/**
	 * 
	 */
	private static final long serialVersionUID = -1363118027287581350L;
	/**
	 * 
	 */

	private short charityID;
	private short siteID;
	private String date;
	private float payment;
	private ArrayList<CharityPaymentVO> charityPaymentVOs;
	
	
	
	public short getCharityID() {
		return charityID;
	}
	public void setCharityID(short charityID) {
		this.charityID = charityID;
	}
	public short getSiteID() {
		return siteID;
	}
	public void setSiteID(short siteID) {
		this.siteID = siteID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getPayment() {
		return payment;
	}
	public void setPayment(float payment) {
		this.payment = payment;
	}
	public ArrayList<CharityPaymentVO> getCharityPaymentVOs() {
		return charityPaymentVOs;
	}
	public void setCharityPaymentVOs(ArrayList<CharityPaymentVO> charityVOs) {
		this.charityPaymentVOs = charityVOs;
	}
	
	public CharityPaymentVO() {
		//empty constructor
	}
	
	@Override
	public String toString() {
		return "CharityPaymentVO [charityID=" + charityID + ", siteID=" + siteID + ", date=" + date + ", payment="
				+ payment + "]";
	}
	
}
