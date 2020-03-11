package org.lacsd.rcx.dao;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		CharityAction.java
//* Revision: 		1.0
//* Author:			christophersimmons@lacsd.org
//* Created On: 	Aug 21, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.lacsd.common.dao.LACSDSqlServerProcDAO;
import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.common.service.DateConvert;
import org.lacsd.rcx.application.RCXStoredProcedures;
import org.lacsd.rcx.values.CashBoxVO;
import org.lacsd.rcx.values.SafeVO;
import org.lacsd.common.values.EmployeeVO;
import org.lacsd.rcx.common.MockLACSDUser;

public class SiteOperationsDAO extends LACSDSqlServerProcDAO{
	private static String GET_CASHBOX = "A";
	private static String GET_SAFE = "B";
	private static String CREATE_CASHBOX = "C";
	private static String CREATE_SAFE = "D";
	private static String GET_TICKET_INFO = "E";
	private static String UPDATE_BANKDROP = "F";
	private static String ADD_CASH = "G";
	private static String CLOSE_SAFE = "H";
	
	private String action;
	private CashBoxVO cashBoxVO;
	private SafeVO safeVO;
	private EmployeeVO employeeVO;
	private ArrayList<Integer> ticketInfo;
	
	public SiteOperationsDAO(){
		super(RCXStoredProcedures.RCXTRAN001SP);
		this.employeeVO = new MockLACSDUser().getEmployeeVO();
	}
	
	/**
	 * Get list of payments
	 * @return CharityPaymentVO
	 * @throws LACSDException
	 */
	public CashBoxVO getCashBox() throws LACSDException{
		super.setQueryType(EXECUTE_QUERY);
		this.action = GET_CASHBOX;
		this.cashBoxVO = new CashBoxVO();
		execute();
		return this.cashBoxVO;
	}
	
	public SafeVO getSafe() throws LACSDException{
		super.setQueryType(EXECUTE_QUERY);
		this.action = GET_SAFE;
		this.safeVO= new SafeVO();
		execute();
		return this.safeVO;
	}
	
	public ArrayList<Integer> getTicketInfo() throws LACSDException{
		super.setQueryType(EXECUTE_QUERY);
		this.action = GET_TICKET_INFO;
		this.ticketInfo= new ArrayList<Integer>();
		execute();
		return this.ticketInfo;
	}
	
	public void createCashBox (CashBoxVO cashBoxVO) throws LACSDException{
		super.setQueryType(EXECUTE_UPDATE);
		this.action = CREATE_CASHBOX;
		this.cashBoxVO = cashBoxVO;
		execute();
	}
	
	public void createSafe (SafeVO safeVO) throws LACSDException{
		super.setQueryType(EXECUTE_UPDATE);
		this.action = CREATE_SAFE;
		this.safeVO = safeVO;
		execute();
	}
	
	public void updateSafe (SafeVO safeVO) throws LACSDException{
		super.setQueryType(EXECUTE_UPDATE);
		this.action = UPDATE_BANKDROP;
		this.safeVO = safeVO;
		execute();
	}
	
	public void addCash (CashBoxVO cashBoxVO) throws LACSDException{
		super.setQueryType(EXECUTE_UPDATE);
		this.action = ADD_CASH;
		this.cashBoxVO = cashBoxVO;
		execute();
	}
	
	public void closeSafe (SafeVO safeVO) throws LACSDException{
		super.setQueryType(EXECUTE_UPDATE);
		this.action = CLOSE_SAFE;
		this.safeVO = safeVO;
		execute();
	}
	
	/**
	 * deletes a charity payment
	 * @return void
	 * @throws LACSDException
	 */
//	public void delCharity(CharityPaymentVO charityPaymentVO) throws LACSDException {
//		super.setQueryType(EXECUTE_UPDATE);
//		this.action = Del_Charity_Payment;
//		this.charityPaymentVO = charityPaymentVO;
//		execute();
//	}
	
	
	
	@Override
	protected void registerInputs() throws LACSDException {
		// TODO Auto-generated method stub
		setInputParam(1,this.action);
		if(action.equals(GET_CASHBOX)){
			setInputParam(2, this.employeeVO.getEmployeeID());
		}
		if(action.equals(GET_SAFE)){
			setInputParam(2, this.employeeVO.getEmployeeID());
		}
		if(action.equals(CREATE_CASHBOX)){
			setInputParam(2, this.employeeVO.getEmployeeID());
			setInputParam(3, this.cashBoxVO.getOpeningBalance());
		}
		if(action.equals(CREATE_SAFE)){
			System.out.println("CreateSafe Called");
			System.out.println("SiteOperationsDAO: CreateSafe(): safeVO: " + safeVO);
			setInputParam(2, this.employeeVO.getEmployeeID());
			setInputParam(3, this.safeVO.getCurrentBalance());
		}
		if(action.equals(GET_TICKET_INFO)){
			setInputParam(2, this.employeeVO.getEmployeeID());
		}
		if(action.equals(UPDATE_BANKDROP)){
			System.out.println("Update_BankDrop Called");
			setInputParam(2, this.employeeVO.getEmployeeID());
			setInputParam(3,null);
			setInputParam(4,this.safeVO.getBankDrop());
		}
		if(action.equals(ADD_CASH)){
			setInputParam(2, this.employeeVO.getEmployeeID());
			setInputParam(3,null);
			setInputParam(4,null);
			//CurrentBalance in this case is actually the amount to be added 
			//not the current balance
			setInputParam(5,this.cashBoxVO.getCurrentBalance());

		}
		if(action.equals(CLOSE_SAFE)){
			setInputParam(2, this.employeeVO.getEmployeeID());
			setInputParam(3,null);
			setInputParam(4,null);
			//CurrentBalance in this case is actually the amount to be added 
			//not the current balance
			setInputParam(5,null);
			setInputParam(6,this.safeVO.getCloseCount());

		}

	}

	@Override
	protected void registerOutputs() throws LACSDException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getResultsFromResultSet(ResultSet rs) throws SQLException, LACSDException {
		if(action.equalsIgnoreCase(GET_CASHBOX)) {
			getResultFromCashBox(rs);
		}
		if(action.equalsIgnoreCase(GET_SAFE)) {
			getResultFromSafe(rs);
		}
		if(action.equalsIgnoreCase(GET_TICKET_INFO)){
			getResultFromTicket(rs);
		}
		
	}
	
	
	/**
	 * get cashBox Data
	 * @return void
	 * @throws LACSDException
	 */
	private void getResultFromCashBox (ResultSet rs) throws SQLException, LACSDException{
		cashBoxVO = new CashBoxVO();
		DateConvert dc = DateConvert.getInstance();
		if(rs.next()){
			cashBoxVO.setDate(dc.getStringOfSQLTimestamp(rs.getTimestamp("CashBoxDate"),"MM/dd/yyyy"));
			cashBoxVO.setEmployeeID((rs.getString("EmployeeID")));
			cashBoxVO.setOpeningBalance(rs.getFloat("OpeningBalance"));
			cashBoxVO.setCurrentBalance(rs.getFloat("CurrentBalance"));
			cashBoxVO.setPhysicalCount(rs.getFloat("PhysicalCount"));
		} 
	}
	
	/**
	 * get ticket process info
	 * @return void
	 * @throws LACSDException
	 */
	private void getResultFromTicket (ResultSet rs) throws SQLException, LACSDException{
		ticketInfo = new ArrayList<Integer>();
		if(rs.next()){
			ticketInfo.add(rs.getInt(1));
			ticketInfo.add(rs.getInt(2));
		} 
	}
	
	/**
	 * get Safe Data
	 * @return void
	 * @throws LACSDException
	 */
	private void getResultFromSafe (ResultSet rs) throws SQLException, LACSDException{
		safeVO = new SafeVO();
		DateConvert dc = DateConvert.getInstance();
		if(rs.next()){
			safeVO.setDate(dc.getStringOfSQLTimestamp(rs.getTimestamp("SafeDate"),"MM/dd/yyyy"));
			safeVO.setSiteID((rs.getShort("siteID")));
			safeVO.setOpeningBalance(rs.getFloat("OpeningBalance"));
			safeVO.setCurrentBalance(rs.getFloat("CurrentBalance"));
			safeVO.setCloseCount(rs.getFloat("ClosingCount"));
			safeVO.setBankDrop(rs.getFloat("BankDrop"));
		} 
	}

	@Override
	protected void getResultsFromString(String[] output) throws LACSDException {
		// TODO Auto-generated method stub
		
	}

}
