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

public class MaterialVO implements Serializable{





	/**
	 * 
	 */
	private static final long serialVersionUID = -5895409545525440350L;

	private short matID;
	private int siteID;
	private String name;
	private String isActive;
	private String hasCountValue;
	private String hasScrapValue;
	private String hasRedemptionValue;
	private String hasBonusValue;
	
	public String getInUse() {
		return inUse;
	}

	public void setInUse(String inUse) {
		this.inUse = inUse;
	}

	private String inUse;
	
	
	
	private ArrayList<MaterialVO> matVOs;
	
	public ArrayList<MaterialVO> getMatVOs() {
		return matVOs;
	}

	public void setMatVOs(ArrayList<MaterialVO> matVOs) {
		this.matVOs = matVOs;
	}

	public short getMatID() {
		return matID;
	}
	
	public void setMatID(short matID) {
		this.matID = matID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIsActive() {
		return isActive;
	}
	
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	public String getHasCountValue() {
		return hasCountValue;
	}
	
	public void setHasCountValue(String hasCountValue) {
		this.hasCountValue = hasCountValue;
	}
	
	public String getHasScrapValue() {
		return hasScrapValue;
	}
	
	public void setHasScrapValue(String hasScrapValue) {
		this.hasScrapValue = hasScrapValue;
	}
	
	public String getHasRedemptionValue() {
		return hasRedemptionValue;
	}
	
	public void setHasRedemptionValue(String hasRedemptionValue) {
		this.hasRedemptionValue = hasRedemptionValue;
	}
	
	public String getHasBonusValue() {
		return hasBonusValue;
	}
	
	public void setHasBonusValue(String hasBonusValue) {
		this.hasBonusValue = hasBonusValue;
	}

	public int getSiteID() {
		return siteID;
	}

	public void setSiteID(int siteID) {
		this.siteID = siteID;
	}
	
	

}
