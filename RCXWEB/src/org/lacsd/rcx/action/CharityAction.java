package org.lacsd.rcx.action;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		CharityAction.java
//* Revision: 		1.0
//* Author:			christophersimmons@lacsd.org
//* Created On: 	Aug 21, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.common.action.RCXGenericAction;
import org.lacsd.rcx.process.CharityPO;
import org.lacsd.rcx.values.CharityVO;
import org.lacsd.rcx.values.SiteCharityVO;


public class CharityAction extends RCXGenericAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8911199492503314362L;

	public static final String SET_UP = "setup";
	
	public static final String ADD = "add";
	
	public static final String DEL = "del";
	
	public static final String ASSIGN = "assign";
	
	private SiteCharityVO siteVO;
	
	private CharityVO charityVO;
	
	public SiteCharityVO getSiteVO() {
		return siteVO;
	}


	public void setSiteVO(SiteCharityVO siteVO) {
		this.siteVO = siteVO;
	}

	
	
	public CharityAction (){
		charityVO = new CharityVO();
	}
	
	
	public String executeWithResult() throws LACSDException, Throwable {

		if (actionName.equals(SET_UP)) {
			return doSetup();
		}
		if(actionName.equals(ADD)){

			return doAdd();
		}
		if(actionName.equals(DEL)){

			return doDelete();
		}
		if(actionName.equals(ASSIGN)){
			return doAssign();
		}
		
		return null;
	}
	
	private String doSetup() throws LACSDException, Throwable {
		CharityPO charityPO = new CharityPO();
		this.charityVO = charityPO.getCharitiesList();
		this.siteVO = charityPO.getSitesList(); 
		
		
		return SET_UP;
	}
	
	private String doAssign() throws LACSDException, Throwable {
		new CharityPO().assignCharities(siteVO);
		
		return doSetup();
	}
	
	private String doAdd() throws LACSDException, Throwable {
		new CharityPO().addCharity(charityVO);

		return doSetup();
	}

	
	private String doDelete() throws LACSDException, Throwable {
		new CharityPO().delCharity(charityVO);

		return doSetup();
	}
	
	public CharityVO getCharityVO() {
		return charityVO;
	}

	public void setCharityVO(CharityVO charityVO) {
		this.charityVO = charityVO;
	}
	
}
