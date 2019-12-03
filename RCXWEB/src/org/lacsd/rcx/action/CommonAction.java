package org.lacsd.rcx.action;

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.common.struts2.action.LACSDGenericAction;
import org.lacsd.rcx.process.MaterialPO;
import org.lacsd.rcx.process.SiteMaterialCommonPO;
import org.lacsd.rcx.values.MaterialVO;
import org.lacsd.rcx.values.SiteVO;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		CommonAction.java
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Oct 29, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/
public class CommonAction  extends LACSDGenericAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -471667965527468152L;
	public static final String GET_SITES	= "getSites";
	public static final String GET_MATERIAL = "getMaterial";
	private MaterialVO materialVO;
	private SiteVO siteVO;
	public void initErrControl() {
	}
	public String executeWithResult() throws LACSDException, Throwable {
		//Not implemented
		return null;
	}
	
	/**
     * Execute With Result - Primary Execution Block for LACSD Struts Frameworkz
     * @return String
     * @throws LACSDException
    */
	public String executeApplicationGlobal() throws LACSDException, Throwable {
		if (actionName.equals(GET_MATERIAL)) {
			this.materialVO = new MaterialPO().getMaterialsListBySite(materialVO);
		}
		else if (actionName.equals(GET_SITES)){
			this.siteVO = new SiteMaterialCommonPO().getSites();
		}
		return SUCCESS;
	}
	public MaterialVO getMaterialVO() {
		return materialVO;
	}
	public void setMaterialVO(MaterialVO materialVO) {
		this.materialVO = materialVO;
	}
	public SiteVO getSiteVO() {
		return siteVO;
	}
	public void setSiteVO(SiteVO siteVO) {
		this.siteVO = siteVO;
	}
	
	
}
