package org.lacsd.rcx.action;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		CharityPaymentAction.java
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
import org.lacsd.rcx.process.CharityPaymentPO;
import org.lacsd.rcx.values.CharityPaymentVO;
import org.lacsd.rcx.values.SiteCharityVO;



/**
 * @author christophersimmons
 *
 */
public class CharityPaymentAction extends RCXGenericAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8911199492503314362L;

	public static final String SET_UP = "setup";
	
	public static final String ADD = "add";
	
	public static final String DEL = "del";
	
	private int charityIdx = 0;
	private int siteIdx = 0;
	private SiteCharityVO siteVO;
	
	private CharityPaymentVO charityPaymentVO;
	
	
	/**
	 * Get siteVO
	 * @return SiteVO siteVO
	 */
	public SiteCharityVO getSiteVO() {
		return siteVO;
	}


	/**
	 * Set siteVO
	 * @param siteVO
	 */
	public void setSiteVO(SiteCharityVO siteVO) {
		this.siteVO = siteVO;
	}

	
	
	/**
	 * Default Constructor
	 * set siteID and charityID to an invalid ID
	 */
	public CharityPaymentAction (){
		charityPaymentVO = new CharityPaymentVO();
		charityPaymentVO.setSiteID((short)-1);
		charityPaymentVO.setCharityID((short)-1);
	}
	
	
	/* (non-Javadoc)
	 * @see org.lacsd.common.struts2.action.LACSDGenericAction#executeWithResult()
	 */
	public String executeWithResult() throws LACSDException, Throwable {

		if (actionName.equals(SET_UP)) {
			return doSetup();
		}
		if(actionName.equals(ADD)){
			
			return doAdd();
		}
		if(actionName.equals(DEL)){
			System.out.println("del called: " + charityPaymentVO);
			return doDelete();
		}

		
		return null;
	}
	
	/**
	 * @return String 
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doSetup() throws LACSDException, Throwable {
		System.out.println("CharityPayment doSetUp() Called");
		CharityPaymentPO charityPO = new CharityPaymentPO();
		this.charityPaymentVO.setCharityPaymentVOs( charityPO.getCharityPaymentList().getCharityPaymentVOs());
		this.siteVO = charityPO.getSitesList(); 
		if(this.charityPaymentVO.getCharityID() == -1 && this.charityPaymentVO.getSiteID() == -1){
			this.charityPaymentVO.setSiteID(this.siteVO.getSiteVOs().get(0).getSiteID());
			this.charityPaymentVO.setCharityID(this.siteVO.getSiteVOs().get(0).getCharities().get(0).getCharityID());
		}
		
		System.out.println("CharityPaymentAction doSetup()");
//		for(CharityPaymentVO c : this.charityPaymentVO.getCharityPaymentVOs()){
//			System.out.println(c);
//		}
//		for(SiteCharityVO c : this.siteVO.getSiteVOs()){
//			System.out.println(c);
//		}
		
		System.out.println(charityPaymentVO);
		return SET_UP;
	}
	
	
	/**
	 * @return String 
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doAdd() throws LACSDException, Throwable {
		new CharityPaymentPO().addCharityPayment(charityPaymentVO);


		return doSetup();
	}

	/**
	 * @return String 
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doDelete() throws LACSDException, Throwable {
		new CharityPaymentPO().delCharityPayment(charityPaymentVO);

		return doSetup();
	}
	
	/**
	 * @return CharityPaymentVO 
	 * @throws LACSDException
	 */
	public CharityPaymentVO getCharityPaymentVO() {
		return charityPaymentVO;
	}

	/**
	 *
	 */
	public void setCharityPaymentVO(CharityPaymentVO charityPaymentVO) {
		this.charityPaymentVO = charityPaymentVO;
	}
	
}
