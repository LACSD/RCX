package org.lacsd.rcx.dao;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		VendorDAO.java
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Sept 16, 2019
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
import org.lacsd.rcx.application.RCXStoredProcedures;
import org.lacsd.rcx.values.VendorVO;

public class VendorDAO extends LACSDSqlServerProcDAO{

	/**
	-- C : Create record
	-- R : Retrieve all records
	-- U : Update record
	-- D : Delete record
	*/
	//private static String CREATE_VEND = "C";
	private static String RETRIEVE_VEND = "R";
	private static String UPDATE_VEND = "U";
	private static String DELETE_VEND = "D";
	
	
	private String action;
	private VendorVO vendVO;
	
	/**
	 * Calls Stored Procedure
	 */
	public VendorDAO(){
		super(RCXStoredProcedures.RCXVEND001SP);
	}
	/*
	public void createVend(VendorVO vendVO) throws LACSDException{
		super.setQueryType(EXECUTE_UPDATE);
		this.action = CREATE_VEND;
		this.vendVO = vendVO;
		execute();
	}
	*/
	
	/**
	 * Retrieves all records from Vendor database
	 * @return
	 * @throws LACSDException
	 */
	public VendorVO getVendList() throws LACSDException{
		super.setQueryType(EXECUTE_QUERY);
		this.action = RETRIEVE_VEND;
		this.vendVO = new VendorVO();
		execute();
		return this.vendVO;
	}
	
	/**
	 * Update or Add vendor information to Vendor database
	 * @param vendVO
	 * @throws LACSDException
	 */
	public void updateVend(VendorVO vendVO) throws LACSDException{
		super.setQueryType(EXECUTE_UPDATE);
		this.action = UPDATE_VEND;
		this.vendVO = vendVO;
		System.out.println(vendVO);
		execute();
	}
	
	/**
	 * Deletes the parameter record from the Vendor database
	 * @param vendVO
	 * @throws LACSDException
	 */
	public void deleteVend(VendorVO vendVO) throws LACSDException{
		super.setQueryType(EXECUTE_UPDATE);
		this.action = DELETE_VEND;
		this.vendVO = vendVO;
		execute();
	}

	@Override
	/**
	 * REGISTER INPUT PARAMETERS - (SUBCLASS STEP #1)
	 * -----------------------------------------------
	 * Subclass must implement this method so that 
	 * stored procedure I/O handshake matches what
	 * is expected by stored procedure input signature
	 * -----------------------------------------------
	 * Usage: >>	super.setInputParam(1, Integer(12345))
	 * 				super.setInputParam(2,"MyInput");
	*/
	protected void registerInputs() throws LACSDException {
		// TODO Auto-generated method stub
		setInputParam(1,this.action);
		if(action.equals(UPDATE_VEND)){
			setInputParam(2, this.vendVO.getVendID());
			setInputParam(3, this.vendVO.getName());
			setInputParam(4, this.vendVO.getAddress());
			setInputParam(5, this.vendVO.getCity());
			setInputParam(6, this.vendVO.getState());
			setInputParam(7, this.vendVO.getZip());
			setInputParam(8, this.vendVO.getPhone());
			setInputParam(9, this.vendVO.getExt());
			setInputParam(10, this.vendVO.getFax());
			setInputParam(11, this.vendVO.getContact());
			setInputParam(12, this.vendVO.getRefNum());
		}
		else if(action.equals(DELETE_VEND)){
			setInputParam(2, this.vendVO.getVendID());
		}
	}

	@Override
	/**
	 * REGISTER OUTPUT PARAMETERS - (SUBCLASS STEP #2)
	 * -----------------------------------------------
	 * Subclass must implement this method so that 
	 * stored procedure I/O handshake matches what
	 * is expected by stored procedure output parameter
	 * -----------------------------------------------
	 * Usage: >>	super.setOutputAsString(1);
	 * 				super.setOutputAsInt(2); 
	*/
	protected void registerOutputs() throws LACSDException {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * PARSE RESULTSET - (SUBCLASS CONDITIONAL STEP #3)
	 * -----------------------------------------------
	 * Subclass must implement this method so that 
	 * resultset (cursors) from a stored procedure can
	 * be properly parsed.   If the procedure does not
	 * return a resultset, the implementation of this
	 * method should throw a new LACSDException(), 
	 * with the message "Parse Resultset not implemented"
	 * -----------------------------------------------
	*/
	protected void getResultsFromResultSet(ResultSet rs) throws SQLException, LACSDException {
		// TODO Auto-generated method stub
		if(this.action.equals(RETRIEVE_VEND))
			getResultsFromVendorSet(rs);
	}
	
	/**
	 * Iterates through result set (rs) to populate an array of Vendor VOs
	 * Sets the created array to the array property of vendVO
	 * @param rs
	 * @throws SQLException
	 * @throws LACSDException
	 */
	protected void getResultsFromVendorSet(ResultSet rs) throws SQLException, LACSDException{
		ArrayList<VendorVO> vendVOs = new ArrayList<>();
		VendorVO vendVO;
		while(rs.next()){
			vendVO = new VendorVO();
			vendVO.setVendID(rs.getShort("VendorID"));
			vendVO.setName(rs.getString("Name"));
			vendVO.setAddress(rs.getString("Address"));
			vendVO.setCity(rs.getString("City"));
			vendVO.setState(rs.getString("State"));
			vendVO.setZip(rs.getString("Zip"));
			vendVO.setPhone(rs.getString("Phone"));
			vendVO.setExt(rs.getString("Extension"));
			vendVO.setFax(rs.getString("Fax"));
			vendVO.setContact(rs.getString("Contact"));
			vendVO.setRefNum(rs.getString("ReferenceNumber"));
			vendVO.setInUse(rs.getString("inUse"));
			vendVOs.add(vendVO);
		}
		this.vendVO.setVendVOs(vendVOs);
	}

	/**
	 * PARSE RESULTSET - (SUBCLASS CONDITIONAL STEP #4)
	 * -----------------------------------------------
	 * Subclass must implement this method so that 
	 * String array outputs (parameter #3 and higher)
	 * can be properly parsed.  If the procedure does not
	 * return a String array, the implementation of this
	 * method should throw a new LACSDException(), 
	 * with the message "Parse String not implemented"
	 * -----------------------------------------------
	*/
	protected void getResultsFromString(String[] output) throws LACSDException {
		// TODO Auto-generated method stub
		
	}

}
