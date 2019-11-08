package org.lacsd.rcx.action;

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

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.application.RCXStoredProcedures;
import org.lacsd.rcx.common.action.RCXGenericAction;
import org.lacsd.rcx.process.MaterialPO;
import org.lacsd.rcx.values.MaterialVO;


public class MaterialAction extends RCXGenericAction {

	private static final long serialVersionUID = -1999512615203170321L;
	
	public static final String SET_UP = "setup";
	
	public static final String ADD = "add";
	
	public static final String DEL = "del";
	
	private static final String MSG_NAME_EXISTS = "mat.name.exists";

	
	private MaterialVO matVO;
	
	public MaterialAction (){
		matVO = new MaterialVO();
	}
	
	public void initErrControl(){
		super.regRecoverableErr("300", RCXStoredProcedures.RCXMATL001SP, MSG_NAME_EXISTS, ADD);

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
		return null;
	}
	
	private String doSetup() throws LACSDException, Throwable {
		this.matVO = new MaterialPO().getMaterialsList();
		return SET_UP;
	}
	
	private String doAdd() throws LACSDException, Throwable {
		new MaterialPO().addMaterial(matVO);


		return doSetup();
	}

	
	private String doDelete() throws LACSDException, Throwable {

		new MaterialPO().delMaterial(matVO);


		return doSetup();
	}
	
	public MaterialVO getMatVO() {
		return matVO;
	}

	public void setMatVO(MaterialVO matVO) {
		this.matVO = matVO;
	}
	
}
