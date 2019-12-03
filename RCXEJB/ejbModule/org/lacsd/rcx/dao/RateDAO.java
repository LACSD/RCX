package org.lacsd.rcx.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.lacsd.common.dao.LACSDSqlServerProcDAO;
import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.common.service.DateConvert;
import org.lacsd.rcx.application.RCXStoredProcedures;
import org.lacsd.rcx.values.RateVO;


/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		RateDAO
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Oct 4, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/


public class RateDAO extends LACSDSqlServerProcDAO{
	
	/**
	-- C : Create new record
	-- R : Retrieve all records (based on SiteID, MaterialID, and/or Date)
	-- U : Update existing record (based on SiteID, Material Name, and Date); 
			Uses Material Name over ID because textfield contains matName when edit modal launched, else a dropdown is used
	-- D : Delete record
	*/
	private static String CREATE_RATE = "C";
	private static String RETRIEVE_RATE = "R";
	private static String UPDATE_RATE = "U";
	private static String DELETE_RATE = "D";
	
	private String action;
	private RateVO rateVO;
	
	/**
	 * Calls Stored Procedure
	 */
	public RateDAO(){
		super(RCXStoredProcedures.RCXRATE001SP);
	}
		
	/**
	 * Retrieves all records from Rate database based on given SiteName(REQ), MaterialName(OPT), Date(OPT)
	 * @return
	 * @throws LACSDException
	 */
	public RateVO getRateList(RateVO rateVO) throws LACSDException{
		super.setQueryType(EXECUTE_QUERY);
		this.action = RETRIEVE_RATE;
		this.rateVO = rateVO;
		execute();
		return this.rateVO;
	}
	
	/**
	 * Updates single record based on Site ID, Material Name, and Date
	 * @param rateVO
	 * @throws LACSDException
	 */
	public void updateRate(RateVO rateVO) throws LACSDException{
		super.setQueryType(EXECUTE_UPDATE);
		this.action = UPDATE_RATE;
		this.rateVO = rateVO;
		execute();
	}
	
	/**
	 * Creates rate, but sets Site ID to -9999 if record already exists
	 * @param rateVO
	 * @return
	 * @throws LACSDException
	 */
	public RateVO createRate(RateVO rateVO) throws LACSDException{
		super.setQueryType(EXECUTE);
		this.action = CREATE_RATE;
		this.rateVO = rateVO;
		execute();
		return this.rateVO;
	}
	
	/**
	 * Deletes selected rate
	 * @param rateVO
	 * @throws LACSDException
	 */
	public void deleteRate(RateVO rateVO) throws LACSDException{
		super.setQueryType(EXECUTE_UPDATE);
		this.action = DELETE_RATE;
		this.rateVO = rateVO;
		execute();
	}
	

	@Override
	protected void registerInputs() throws LACSDException {
		setInputParam(1,this.action);
		if(action.equals(RETRIEVE_RATE)){
			setInputParam(2, this.rateVO.getSiteID());
			setInputParam(3, this.rateVO.getMaterialID());
			setInputParam(4, this.rateVO.getDate());
		}
		else if(action.equals(UPDATE_RATE)){
			setInputParam(2, this.rateVO.getSiteID());
			setInputParam(3, this.rateVO.getMaterialID());
			setInputParam(4, this.rateVO.getDate());
			
			setInputParam(5, this.rateVO.getSiteName());
			setInputParam(6, this.rateVO.getMatName());
			
			setInputParam(7, this.rateVO.getContPerLb());
			setInputParam(8, this.rateVO.getScrapRate());
			setInputParam(9, this.rateVO.getRedemRate());
			setInputParam(10, this.rateVO.getBonusRate());
			setInputParam(11, this.rateVO.getSmallCountRate());
			setInputParam(12, this.rateVO.getLargeCountRate());
			
		}
		
		else if(action.equals(CREATE_RATE)){
			setInputParam(2, this.rateVO.getSiteID());
			setInputParam(3, this.rateVO.getMaterialID());
			setInputParam(4, this.rateVO.getDate());
			
			setInputParam(5, null);
			setInputParam(6, null);
			
			setInputParam(7, this.rateVO.getContPerLb());
			setInputParam(8, this.rateVO.getScrapRate());
			setInputParam(9, this.rateVO.getRedemRate());
			setInputParam(10, this.rateVO.getBonusRate());
			setInputParam(11, this.rateVO.getSmallCountRate());
			setInputParam(12, this.rateVO.getLargeCountRate());

			
		}
		
		else if(action.equals(DELETE_RATE)){
			setInputParam(2, this.rateVO.getSiteID());
			setInputParam(3, this.rateVO.getMaterialID());
			setInputParam(4, this.rateVO.getDate());
			
			setInputParam(5, this.rateVO.getSiteName());
			setInputParam(6, this.rateVO.getMatName());
		}
		
				
		
	}

	@Override
	protected void registerOutputs() throws LACSDException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getResultsFromResultSet(ResultSet rs) throws SQLException, LACSDException {
		if(this.action.equals(RETRIEVE_RATE)){
			getResultsFromRateSet(rs);
		}
		if(this.action.equals(CREATE_RATE)){
			getResultsFromCreateSet(rs);
		}
	}
	

	/**
	 * Iterates through result set (rs) to populate an array of Rate VOs
	 * Sets the created array to the array property of rateVO
	 * @param rs
	 * @throws SQLException
	 * @throws LACSDException
	 */
	protected void getResultsFromRateSet(ResultSet rs) throws SQLException, LACSDException{
		ArrayList<RateVO> rateVOs = new ArrayList<>();
		DateConvert dc = DateConvert.getInstance();
		RateVO rateVO;
		while(rs.next()){
			rateVO = new RateVO();
			rateVO.setSiteName(rs.getString("SiteName"));
			rateVO.setMatName(rs.getString("MaterialName"));
			rateVO.setDate(dc.getStringOfSQLTimestamp(rs.getTimestamp("Date"),"MM/dd/yyyy"));
			rateVO.setContPerLb(rs.getBigDecimal("ContainersPerPound"));
			rateVO.setScrapRate(rs.getBigDecimal("ScrapRate"));
			rateVO.setRedemRate(rs.getBigDecimal("RedemptionRate"));
			rateVO.setBonusRate(rs.getBigDecimal("BonusRate"));
			rateVO.setSmallCountRate(rs.getBigDecimal("SmallItemCountRate"));
			rateVO.setLargeCountRate(rs.getBigDecimal("LargeItemCountRate"));
			
			rateVO.setIsActive(rs.getString("IsActive"));
			rateVO.setHasScrap(rs.getString("HasScrapValue"));
			rateVO.setHasRedemption(rs.getString("HasRedemptionValue"));
			rateVO.setHasBonus(rs.getString("HasBonusValue"));
			rateVO.setHasCount(rs.getString("HasCountValue"));
			
			rateVOs.add(rateVO);
		}
		this.rateVO.setRateVOs(rateVOs);
	}
	
	/**
	 * Retrieves Material ID when calling Create;
	 * Fetches ID = -9999 if creation failed due to record with same date, material, and site combination already existing in dbo
	 * @param rs
	 * @throws SQLException
	 * @throws LACSDException
	 */
	protected void getResultsFromCreateSet(ResultSet rs) throws SQLException, LACSDException{
		rs.next();
		this.rateVO.setMaterialID(rs.getShort("MaterialID"));
	}

	@Override
	protected void getResultsFromString(String[] output) throws LACSDException {
		// TODO Auto-generated method stub
		
	}

}
