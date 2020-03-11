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

public class CashBoxVO implements Serializable{


	private static final long serialVersionUID = 1782676457779472655L;

	private String date;
	private String employeeID;
	private float openingBalance;
	private float currentBalance;
	private float physicalCount;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}


	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
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

	public float getPhysicalCount() {
		return physicalCount;
	}
	public void setPhysicalCount(float physicalCount) {
		this.physicalCount = physicalCount;
	}

	@Override
	public String toString() {
		return "CashBoxVO [date=" + date + ", employeeID=" + employeeID + ", openingBalance=" + openingBalance
				+ ", currentBalance=" + currentBalance + ", physicalCount=" + physicalCount + "]";
	}

	public CashBoxVO() {
		//empty constructor
	}
	
}
