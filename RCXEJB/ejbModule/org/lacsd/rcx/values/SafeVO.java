package org.lacsd.rcx.values;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		SafeVO.java
//* Revision: 		1.0
//* Author:			christophersimmons@lacsd.org
//* Created On: 	Aug 21, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

import java.io.Serializable;

public class SafeVO implements Serializable{


	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4692081090422141503L;
	
	private String date;
	private short siteID;
	private float openingBalance;
	private float currentBalance;
	private float closeCount;
	private float bankDrop;
	
	
	
	
	public float getBankDrop() {
		return bankDrop;
	}
	public void setBankDrop(float bankDrop) {
		this.bankDrop = bankDrop;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}


	public short getSiteID() {
		return siteID;
	}
	public void setSiteID(short siteID) {
		this.siteID = siteID;
	}

	public float getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(float openingBalance) {
		this.openingBalance = openingBalance;
	}

	public float getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(float currentBalance) {
		this.currentBalance = currentBalance;
	}

	public float getCloseCount() {
		return closeCount;
	}
	public void setCloseCount(float closeCount) {
		this.closeCount = closeCount;
	}

	public SafeVO() {
		//empty constructor
	}
	@Override
	public String toString() {
		return "SafeVO [date=" + date + ", siteID=" + siteID + ", openingBalance=" + openingBalance
				+ ", currentBalance=" + currentBalance + ", closeCount=" + closeCount + ", bankDrop=" + bankDrop + "]";
	}
	
	
}
