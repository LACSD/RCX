package org.lacsd.rcx.process;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		CharityDetailPO.java
//* Revision: 		1.0
//* Author:			christophersimmons@lacsd.org
//* Created On: 	Aug 21, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.values.CharityVO;
import org.lacsd.rcx.values.SiteCharityVO;
import org.lacsd.rcx.dao.ejb.CharityDAOBean;

public class CharityPO {
	@javax.ejb.EJB private CharityDAOBean CharityDAOBean;
	
	public CharityPO(){
		CharityDAOBean = new CharityDAOBean();

	}
	
	public CharityVO getCharitiesList() throws LACSDException{
		return CharityDAOBean.getCharitysList();
	}
	
	public void addCharity(CharityVO charityVO) throws LACSDException {
		CharityDAOBean.addCharity(charityVO);
	}
	
	public void delCharity(CharityVO charityVO) throws LACSDException {
		CharityDAOBean.delCharity(charityVO);
	}
	
	public SiteCharityVO getSitesList() throws LACSDException {
		return CharityDAOBean.getSitesList();
	}
	
	public void assignCharities(SiteCharityVO siteVO) throws LACSDException {
	  CharityDAOBean.assignCharities(siteVO);
	}
}
