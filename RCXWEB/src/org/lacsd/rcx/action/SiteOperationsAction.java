package org.lacsd.rcx.action;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		SiteOperationAction.java
//* Revision: 		1.0
//* Author:			christophersimmons@lacsd.org
//* Created On: 	Aug 21, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.common.values.EmployeeVO;
import org.lacsd.rcx.common.MockLACSDUser;
import org.lacsd.rcx.common.action.RCXGenericAction;
import org.lacsd.rcx.process.SiteOperationsPO;
import org.lacsd.rcx.values.CashBoxVO;
import org.lacsd.rcx.values.SafeVO;



/**
 * @author christophersimmons
 *
 */
public class SiteOperationsAction extends RCXGenericAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4173713853602617226L;

	public static final String SET_UP = "setup";
	
	public static final String NEW_CASHBOX = "newCashBox";
	
	public static final String UPDATE = "update";
	
	public static final String ADD_CASH = "add";
	
	public static final String CLOSE_SAFE = "close";

	private EmployeeVO employeeVO;
	
	private CashBoxVO cashBoxVO;
	
	private SafeVO safeVO;
		
	private List<String> reports;

	private int numTicketsVoided;
	
	private int numTicketsProcessed;
	
	
	
	
	public int getNumTicketsVoided() {
		return numTicketsVoided;
	}


	public void setNumTicketsVoided(int numTicketsVoided) {
		this.numTicketsVoided = numTicketsVoided;
	}


	public int getNumTicketsProcessed() {
		return numTicketsProcessed;
	}


	public void setNumTicketsProcessed(int numTicketsProcessed) {
		this.numTicketsProcessed = numTicketsProcessed;
	}


	public List<String> getReports() {
		return reports;
	}


	public void setReports(List<String> reports) {
		this.reports = reports;
	}


	public SafeVO getSafeVO() {
		return safeVO;
	}


	public void setSafeVO(SafeVO safeVO) {
		this.safeVO = safeVO;
	}


	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}


	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}


	public CashBoxVO getCashBoxVO() {
		return cashBoxVO;
	}


	public void setCashBoxVO(CashBoxVO cashBoxVO) {
		this.cashBoxVO = cashBoxVO;
	}


	/**
	 * Default Constructor
	 * create report array for struts radio button list
	 */
	public SiteOperationsAction (){
		reports = new ArrayList<String>();
		reports.add("Cashbox Summary");
		reports.add("Charity Summary");
		reports.add("Daily Tonnage");
		reports.add("Rate List");
	}
	
	
	/* (non-Javadoc)
	 * @see org.lacsd.common.struts2.action.LACSDGenericAction#executeWithResult()
	 */
	public String executeWithResult() throws LACSDException, Throwable {

		if (actionName.equals(SET_UP)) {
			return doSetup();
		}
		if(actionName.equals(NEW_CASHBOX)){
			
			return doAdd();
		}
		if(actionName.equals(UPDATE)){
			return doSafeUpdate();
		}
		if(actionName.equals(ADD_CASH)){
			return doAddCash();
		}
		if(actionName.equals(CLOSE_SAFE)){
			return doCloseSafe();
		}

		
		return null;
	}
	
	/**
	 * Retrieve's employee information and attempts to retrieve
	 * today's Cashbox and Safe records
	 * @return String 
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doSetup() throws LACSDException, Throwable {
		System.out.println("SiteOperationAction doSetUp() Called");
		SiteOperationsPO siteOperationsPO = new SiteOperationsPO();
		employeeVO = new MockLACSDUser().getEmployeeVO();
		
		this.cashBoxVO = siteOperationsPO.getCashBox();
		if(this.cashBoxVO.getEmployeeID() == null){
			this.cashBoxVO.setEmployeeID("-1");
		} else {
			ArrayList<Integer> ticketInfo = siteOperationsPO.getTicketInfo();
			for (Integer e : ticketInfo){
				System.out.println("SiteOperationAction doSetup()"+e);
			}
			numTicketsVoided = ticketInfo.get(0);
			
			numTicketsProcessed = ticketInfo.get(1);
		}
		this.safeVO = siteOperationsPO.getSafe();
		if(this.safeVO.getDate() == null){
			this.safeVO.setSiteID((short)-1);
		}
		
	
		System.out.println("SiteOperationAction doSetUp(): cashBoxVO: " + cashBoxVO);
		System.out.println("SiteOperationAction doSetUp(): safeVO: " + safeVO);
		System.out.println("SiteOperationAction doSetUp(): employeVO: " + employeeVO);
		System.out.println("SiteOperationAction doSetUp(): numTicketsVoided: " + numTicketsVoided);
		System.out.println("SiteOperationAction doSetUp(): numTicketsProcessed: " + numTicketsProcessed);

		return SET_UP;
	}
	
	
	/**
	 * @return String 
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doAdd() throws LACSDException, Throwable {
		SiteOperationsPO siteOpPO = new SiteOperationsPO();
		System.out.println("SiteOperationsAction doAdd(): cashBoxVO: " + cashBoxVO);
		System.out.println("SiteOperationsAction doAdd(): safeVO: " + safeVO);

		if(this.safeVO.getSiteID() == -1){
			siteOpPO.createSafe(safeVO);
		}
		siteOpPO.createCashBox(cashBoxVO);

		return doSetup();
	}
	
	/**
	 * @return String 
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doSafeUpdate() throws LACSDException, Throwable {
		SiteOperationsPO siteOpPO = new SiteOperationsPO();
		System.out.println("SiteOperationsAction doUpdate(): safeVO: " + safeVO);

		siteOpPO.updateSafe(safeVO);

		return doSetup();
	}
	
	/**
	 * @return String 
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doAddCash() throws LACSDException, Throwable {
		SiteOperationsPO siteOpPO = new SiteOperationsPO();
		System.out.println("SiteOperationsAction doAddCash(): cashBoxVO: " + cashBoxVO);

		siteOpPO.addCash(cashBoxVO);

		return doSetup();
	}

	/**
	 * @return String 
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doCloseSafe() throws LACSDException, Throwable {
		SiteOperationsPO siteOpPO = new SiteOperationsPO();
		System.out.println("SiteOperationsAction doCloseSafe(): safeVO: " + safeVO);

		siteOpPO.closeSafe(safeVO);

		return doSetup();
	}
	

}
