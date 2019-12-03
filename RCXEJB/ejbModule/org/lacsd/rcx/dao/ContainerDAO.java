package org.lacsd.rcx.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.lacsd.common.dao.LACSDSqlServerProcDAO;
import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.application.RCXStoredProcedures;
import org.lacsd.rcx.values.ContainerVO;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		ContainerDAO.java
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Nov 27, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/
public class ContainerDAO extends LACSDSqlServerProcDAO{

	/**
	-- C : Create new record
	-- R : Retrieve all records
	-- U : Update existing record
	-- D : Delete record
	-- S : Search specific record
	 */
	
	private static String CREATE_CONTAINER = "C";
	private static String RETRIEVE_CONTAINER = "R";
	private static String UPDATE_CONTAINER = "U";
	private static String DELETE_CONTAINER = "D";
	
	private String action;
	private ContainerVO containerVO;
	
	/**
	 * Calls Stored Procedure
	 */
	public ContainerDAO(){
		super(RCXStoredProcedures.RCXCONT001SP);
	}
	
	
	
	/**
	 * Creates Container record, but sets ContainerID to -9999 if record already exists
	 * @return 
	 */
	public ContainerVO createContainer(ContainerVO containerVO) throws LACSDException{
		super.setQueryType(EXECUTE);
		this.action = CREATE_CONTAINER;
		this.containerVO = containerVO;
		execute();
		return this.containerVO;
	}
	
	/**
	 * Retrieves all records from Container dbo
	 * @param containerVO
	 * @return
	 * @throws LACSDException
	 */
	public ContainerVO getContainerList(ContainerVO containerVO) throws LACSDException{
		super.setQueryType(EXECUTE_QUERY);
		this.action = RETRIEVE_CONTAINER;
		this.containerVO = containerVO;
		execute();
		return this.containerVO;
	}
	
	
	/**
	 * Updates single record based on Container ID
	 * @param containerVO
	 * @throws LACSDException
	 */
	public void updateContainer(ContainerVO containerVO) throws LACSDException{
		super.setQueryType(EXECUTE_UPDATE);
		this.action = UPDATE_CONTAINER;
		this.containerVO = containerVO;
		execute();
	}
	
	/**
	 * Deletes selected Container record
	 * @param containerVO
	 * @throws LACSDException
	 */
	public void deleteContainer(ContainerVO containerVO) throws LACSDException{
		super.setQueryType(EXECUTE_UPDATE);
		this.action = DELETE_CONTAINER;
		this.containerVO = containerVO;
		execute();
	}
	
	
	
	@Override
	protected void registerInputs() throws LACSDException {
		setInputParam(1,this.action);
		if(action.equals(CREATE_CONTAINER)){
			setInputParam(2, null);
			setInputParam(3, this.containerVO.getName());
		}
		else if(action.equals(UPDATE_CONTAINER)){
			setInputParam(2, this.containerVO.getContainerID());
			setInputParam(3, this.containerVO.getName());
		}
		else if(action.equals(DELETE_CONTAINER)){
			setInputParam(2, this.containerVO.getContainerID());
		}
		
	}

	@Override
	protected void registerOutputs() throws LACSDException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getResultsFromResultSet(ResultSet rs) throws SQLException, LACSDException {
		if(this.action.equals(RETRIEVE_CONTAINER)){
			getResultsFromContainerSet(rs);
		}
		if(this.action.equals(CREATE_CONTAINER)){
			getResultsFromCreateSet(rs);
		}
	}
	
	/**
	 * Iterates through result set (rs) to populate an array of Container VOs
	 * Sets the created array to the array property of ContainerVO
	 * @param rs
	 * @throws SQLException
	 * @throws LACSDException
	 */
	protected void getResultsFromContainerSet(ResultSet rs) throws SQLException, LACSDException{
		ArrayList<ContainerVO> containerVOs = new ArrayList<>();
		ContainerVO containerVO;
		while(rs.next()){
			containerVO = new ContainerVO();
			containerVO.setContainerID(rs.getShort("ContainerID"));
			containerVO.setName(rs.getString("Name"));
			containerVOs.add(containerVO);
		}
		this.containerVO.setContainerVOs(containerVOs);
	}
	
	/**
	 * Retrieves ContainerID when calling Create;
	 * Fetches ID = -9999 if creation failed due to record with same ID already existing in dbo
	 * @param rs
	 * @throws SQLException
	 * @throws LACSDException
	 */
	protected void getResultsFromCreateSet(ResultSet rs) throws SQLException, LACSDException{
		rs.next();
		this.containerVO.setContainerID(rs.getShort("ContainerID"));
	}

	@Override
	protected void getResultsFromString(String[] output) throws LACSDException {
		// TODO Auto-generated method stub
		
	}

}
