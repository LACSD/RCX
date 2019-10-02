package org.lacsd.rcx.action;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		MaterialAction.java
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Sep 17, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.common.action.RCXGenericAction;
import org.lacsd.rcx.process.VendorPO;
import org.lacsd.rcx.values.VendorVO;
import org.lacsd.common.constants.LACSDWebConstants;
import org.lacsd.common.values.LookupVO;
import org.lacsd.rcx.application.RCXConstants;
import org.lacsd.rcx.application.RCXStoredProcedures;


public class VendorAction extends RCXGenericAction {
	
	private static final long serialVersionUID = 5905370382754146359L;
	
	public static final String SET_UP = "setup";
	
	public static final String UPDATE = "update";
	
	public static final String DELETE = "delete";
	
	
	private VendorVO vendVO;
	/**
	 * Creates new instance of VendorVO with the private local variable
	 */
	public VendorAction(){
		vendVO = new VendorVO();
	}
	
	public void initErrControl() {
		//		Custom Error Exceptions Not Implemented! 
		//super.regRecoverableErr("300", RCXStoredProcedures.RCXMATL001SP, MSG_NAME_EXISTS, ADD);
	}

	

	/**
	 * Based on the value of actionName, this function decides which function (set up, update/add, delete) to do
	 */
	public String executeWithResult() throws LACSDException, Throwable {
		if(actionName.equals(SET_UP))
			return doSetup();
		if(actionName.equals(UPDATE))
			return doUpdate();
		if(actionName.equals(DELETE))
			return doDelete();
		return null;
	}
	
	/**
	 * Function sets up data for table by retrieving vendor list with getVendList(); through PO
	 * @return
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doSetup() throws LACSDException, Throwable {
		this.vendVO = new VendorPO().getVendList();
		return SET_UP;
	}

	/**
	 * Function deletes record from database by calling deleteVend(vendVO); through PO
	 * @return
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doDelete() throws LACSDException, Throwable {
		new VendorPO().deleteVend(vendVO);
		return doSetup();
	}

	/**
	 * Function updates database by adding/updating records with updateVend(vendVO) through PO
	 * @return
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doUpdate() throws LACSDException, Throwable {
		System.out.println(vendVO);
		new VendorPO().updateVend(vendVO);
		return doSetup();
	}
	
	
	/**
	 * Getters and setters for vendVO
	 * @return
	 */
	public VendorVO getVendVO() {
		return vendVO;
	}
	public void setVendVO(VendorVO vendVO) {
		this.vendVO = vendVO;
	}

}
