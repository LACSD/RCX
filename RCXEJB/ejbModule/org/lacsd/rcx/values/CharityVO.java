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

public class CharityVO implements Serializable{




	/**
	 * 
	 */
	private static final long serialVersionUID = 7574133226220394518L;
	private short charityID;
	private String name;
	private String inUse;
	private ArrayList<CharityVO> charityVOs;
	
	public CharityVO(short id, String name){
		this.charityID = id;
		this.name = name;
	}
	
	public CharityVO() {
		// TODO Auto-generated constructor stub
	}

	public String getInUse() {
		return inUse;
	}

	public void setInUse(String inUse) {
		this.inUse = inUse;
	}

	
	public ArrayList<CharityVO> getCharityVOs() {
		return charityVOs;
	}

	public void setCharityVOs(ArrayList<CharityVO> charityVOs) {
		this.charityVOs = charityVOs;
	}

	public short getCharityID() {
		return charityID;
	}
	
	public void setCharityID(short charityID) {
		this.charityID = charityID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CharityVO [charityID=" + charityID + ", name=" + name + ", inUse=" + inUse + "]";
	}
	
	
	
}
