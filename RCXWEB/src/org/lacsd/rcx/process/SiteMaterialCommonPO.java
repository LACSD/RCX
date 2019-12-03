package org.lacsd.rcx.process;

import java.util.ArrayList;

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.common.process.LACSDGenericPO;
import org.lacsd.common.values.LookupVO;
import org.lacsd.rcx.dao.SiteMaterialCommonDAO;
import org.lacsd.rcx.values.SiteVO;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		SiteMaterialCommonPO.java
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Oct 25, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/
/**
 * @author carlosarojas
 *
 */
public class SiteMaterialCommonPO extends LACSDGenericPO{
	
	@javax.ejb.EJB private SiteMaterialCommonDAO siteMaterialCommonDAO;
	
	public SiteMaterialCommonPO() {
		siteMaterialCommonDAO = new SiteMaterialCommonDAO();
	}
	
	
	/**
	 * Get sites
	 * @return
	 * @throws LACSDException
	 */
	public SiteVO getSites() throws LACSDException{
		return siteMaterialCommonDAO.getSites();
	}
	
}
