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

public class SiteCharityVO implements Serializable{





	/**
	 * 
	 */
	private static final long serialVersionUID = -2944582244807029758L;
	/**
	 * 
	 */


	private short siteID;
	private String name;
	private ArrayList<SiteCharityVO> siteVOs;
	private ArrayList<CharityVO> charities = new ArrayList<CharityVO>();
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addToCharities(CharityVO charity) {
		this.charities.add(charity);
	}

	public ArrayList<CharityVO> getCharities() {
		return charities;
	}

	public void setCharities(ArrayList<CharityVO> charities) {
		this.charities = charities;
	}

	public ArrayList<SiteCharityVO> getSiteVOs() {
		return siteVOs;
	}

	public void setSiteVOs(ArrayList<SiteCharityVO> siteVOs) {
		this.siteVOs = siteVOs;
	}

	public short getSiteID() {
		return siteID;
	}
	
	public void setSiteID(short siteID) {
		this.siteID = siteID;
	}

	@Override
	public String toString() {
		return "SiteVO [siteID=" + siteID + ", name=" + name + ", charities=" + charities + "]";
	}
	
	

}
