package org.lacsd.rcx.process;

import java.util.ArrayList;

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
import org.lacsd.rcx.values.CashBoxVO;
import org.lacsd.rcx.values.SafeVO;
import org.lacsd.rcx.dao.ejb.SiteOperationsDAOBean;

public class SiteOperationsPO {
	@javax.ejb.EJB private SiteOperationsDAOBean SiteOperationsDAOBean;
	
	/**
	 * Default Constructor
	 */
	public SiteOperationsPO(){
		SiteOperationsDAOBean = new SiteOperationsDAOBean();

	}
	

	
	/**
	 * Get CashBox Data
	 * @return CashBoxVO 
	 * @throws LACSDException
	 */
	public CashBoxVO getCashBox() throws LACSDException {
		return SiteOperationsDAOBean.getCashBox();
	}
	
	/**
	 * Get CashBox Data
	 * @return CashBoxVO 
	 * @throws LACSDException
	 */
	public SafeVO getSafe() throws LACSDException {
		return SiteOperationsDAOBean.getSafe();
	}
	
	/**
	 * Get CashBox Data
	 * @return CashBoxVO 
	 * @throws LACSDException
	 */
	public ArrayList<Integer> getTicketInfo() throws LACSDException {
		return SiteOperationsDAOBean.getTicketInfo();
	}
	
	/**
	 * Create a new CashBox record
	 * @throws LACSDException
	 */
	public void createCashBox(CashBoxVO cashBoxVO) throws LACSDException{
		SiteOperationsDAOBean.createCashBox(cashBoxVO);
	}
	
	/**
	 * Create a new Safe Record
	 * @throws LACSDException
	 */
	public void createSafe(SafeVO safeVO) throws LACSDException{
		System.out.println("SiteOperationsPO: createSafe() called");
		SiteOperationsDAOBean.createSafe(safeVO);
	}
	
	/**
	 * Updates Bank drop and subtracts bank drop from current amount
	 * for safeVO
	 * @throws LACSDException
	 */
	public void updateSafe(SafeVO safeVO) throws LACSDException{
		System.out.println("SiteOperationsPO: updateSafe() called");
		SiteOperationsDAOBean.updateSafe(safeVO);
	}
	
	/**
	 * Adds cash to the current amount subtracts the cash from the safe's
	 * current amount and logs the action to CashboxLog
	 * @throws LACSDException
	 */
	public void addCash (CashBoxVO cashBoxVO) throws LACSDException {
		System.out.println("SiteOperationsPO: addCash() called");
		SiteOperationsDAOBean.addCash(cashBoxVO);
	}
	
	/**
	 * Updates the safe closing amount
	 * @throws LACSDException
	 */
	public void closeSafe(SafeVO safeVO) throws LACSDException{
		System.out.println("SiteOperationsPO: closeSafe() called");
		SiteOperationsDAOBean.closeSafe(safeVO);
	}
	

	
}
