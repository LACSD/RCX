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
import org.lacsd.rcx.values.CharityPaymentVO;
import org.lacsd.rcx.values.SiteCharityVO;
import org.lacsd.rcx.dao.ejb.CharityPaymentDAOBean;

public class CharityPaymentPO {
	@javax.ejb.EJB private CharityPaymentDAOBean CharityPaymentDAOBean;
	
	/**
	 * Default Constructor
	 */
	public CharityPaymentPO(){
		CharityPaymentDAOBean = new CharityPaymentDAOBean();

	}
	
	/**
	 * Return list of charity payments
	 * @return CharityPaymentVO 
	 * @throws LACSDException
	 */
	public CharityPaymentVO getCharityPaymentList() throws LACSDException{
		return CharityPaymentDAOBean.getCharityPaymentsList();
	}
	
	/**
	 * Create new payment
	 * @return CharityPaymentVO 
	 * @throws LACSDException
	 */
	public void addCharityPayment(CharityPaymentVO charityPaymentVO) throws LACSDException {
		CharityPaymentDAOBean.addCharity(charityPaymentVO);
	}
	
	/**
	 * Delete a charity Payments
	 * @return CharityPaymentVO 
	 * @throws LACSDException
	 */
	public void delCharityPayment(CharityPaymentVO charityPaymentVO) throws LACSDException {
		CharityPaymentDAOBean.delCharity(charityPaymentVO);
	}
	
	/**
	 * Get list of sites
	 * @return CharityPaymentVO 
	 * @throws LACSDException
	 */
	public SiteCharityVO getSitesList() throws LACSDException {
		return CharityPaymentDAOBean.getSitesList();
	}
	
}
