package org.lacsd.rcx.action;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		RateAction.java
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Oct 4, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.common.action.RCXGenericAction;
import org.lacsd.rcx.process.RatePO;
import org.lacsd.rcx.process.SiteMaterialCommonPO;
import org.lacsd.rcx.values.RateVO;
import org.lacsd.rcx.values.SiteVO;


public class RateAction extends RCXGenericAction{

	private static final long serialVersionUID = -5002524887485391690L;
	
	public static final String UPDATE = "update";
	
	public static final String DELETE = "delete";
	
	public static final String RETRIEVE = "retrieve";
	
	public static final String CREATE = "create";
	
	
	private RateVO rateVO;
	private SiteVO siteVO;
	
	/**
	 * Creates new instance of RateVO with the private local variable
	 */
	public RateAction(){
		setRateVO(new RateVO());
	}
	
	public void initErrControl() {
		//		Custom Error Exceptions Not Implemented! 
	}

	/**
	 * Based on the value of actionName, this function decides which function (set up, update/add, delete) to do
	 */
	protected String executeWithResult() throws LACSDException, Throwable {
		this.siteVO = new SiteMaterialCommonPO().getSites(); //populates Sites dropdown

		if(actionName.equals(RETRIEVE))
			return doRetrieve();
		if(actionName.equals(CREATE)){
			return doCreate();
		}
		if(actionName.equals(DELETE))
			return doDelete();
		if(actionName.equals(UPDATE))
			return doUpdate();
		return null;
	}
	

	
	/**
	 * Function updates database by creating new record if it is unique by calling createRate(rateVO) through PO
	 * Sets rateVO.materialID to 0 if creation was successful; allows table to display all materials on refresh rather than only show the material created
	 * @return
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doCreate() throws LACSDException, Throwable {
		this.rateVO = new RatePO().createRate(rateVO);
		if(this.rateVO.getMaterialID()!= (short)-9999)
			this.rateVO.setMaterialID((short)0);
		doRetrieve();
		return RETRIEVE;
	}
	
	/**
	 * Function updates database by updating a single records optional values by calling updateRate(rateVO) through PO
	 * @return
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doUpdate() throws LACSDException, Throwable {
		new RatePO().updateRate(rateVO);
		doRetrieve();
		return RETRIEVE;
	}
	
	/**
	 * Function deletes single record from database by calling deleteRate(rateVO) through PO
	 * @return
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doDelete() throws LACSDException, Throwable {
		new RatePO().deleteRate(rateVO);
		doRetrieve();//doSetup();
		return RETRIEVE;
	}
	
	/**
	 * Function fetches data from database to display based on search parameters by calling getRateList(rateVO) through PO
	 * If Site ID = 0, will default it to 1
	 * If Date == null, will default it to "" (null when opening page for the first time)
	 * @return
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doRetrieve() throws LACSDException, Throwable {
		if(rateVO.getSiteID() == 0)
			rateVO.setSiteID((short)1);
		if(rateVO.getDate() == null)
			rateVO.setDate("");
		this.rateVO = new RatePO().getRateList(rateVO);
		return RETRIEVE;
	}

	
	/**
	 * Getter and setter for rateVO
	 * @return
	 */
	public RateVO getRateVO() {
		return rateVO;
	}
	public void setRateVO(RateVO rateVO) {
		this.rateVO = rateVO;
	}

	public SiteVO getSiteVO() {
		return siteVO;
	}

	public void setSiteVO(SiteVO siteVO) {
		this.siteVO = siteVO;
	}

	

}
