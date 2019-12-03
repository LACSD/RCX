/**
 * 
 */
package org.lacsd.rcx.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.lacsd.common.dao.LACSDSqlServerProcDAO;
import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.common.values.LookupVO;
import org.lacsd.rcx.application.RCXStoredProcedures;
import org.lacsd.rcx.values.SiteVO;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		SiteMaterialCommonDAO.java
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
public class SiteMaterialCommonDAO extends LACSDSqlServerProcDAO{

	private static String GET_SITES 		= "A";
	//private static String GET_MATERIALS 	= "B";
	private String action;
	//private ArrayList<LookupVO<String, Integer>> lookupVOs;
	private SiteVO siteVO;
	
	
	public SiteMaterialCommonDAO(){
		super(RCXStoredProcedures.RCXCOMM001SP);
	}
	
	
	public SiteVO getSites() throws LACSDException{
		super.setQueryType(EXECUTE_QUERY);
		this.action = GET_SITES;
		this.siteVO = new SiteVO();
		execute();
		return this.siteVO;
	}
	

	/**
	 * SETUP STEP 1:	Register Input Parameters
	 * @return void
	 * @throws LACSDException
	 */
	protected void registerInputs() throws LACSDException {
		setInputParam(1,this.action);
		//setInputParam(2, this.siteVO.getSiteID());
		//setInputParam(2, this.siteVO.getSiteName());

	}
	
	/**
	 * SETUP STEP 2:	Register Output Parameters
	 * @return void
	 * @throws LACSDException
	*/
	protected void registerOutputs() throws LACSDException {
		
	}
	
	
	/**
	 * HANDLE OUTPUT TYPE 1:	Stored Procedure Returns an Open Cursor
	 * @param ResultSet rs
	 * @return void
	 * @throws SQLException, LACSDException
	*/
	//@SuppressWarnings({"unchecked", "rawtypes"})
	protected void getResultsFromResultSet(ResultSet rs) throws SQLException, LACSDException {
		//lookupVOs = new ArrayList<LookupVO<String, Integer>>();
		//LookupVO<String, Integer> lVO = null;
		ArrayList<SiteVO> siteVOs = new ArrayList<SiteVO>();
		SiteVO siteVO;
		while(rs.next()){
			//lVO = new LookupVO(rs.getString("Name"), rs.getInt("ID"));
			//lookupVOs.add(lVO);
			siteVO = new SiteVO();
			siteVO.setSiteID(rs.getShort("SiteID"));
			siteVO.setSiteName(rs.getString("Name"));
			siteVO.setAbbreviation(rs.getString("Abbreviation"));
			siteVO.setCertNumber(rs.getString("CertificationNumber"));
			siteVO.setAddress(rs.getString("Address"));
			siteVO.setCity(rs.getString("City"));
			siteVO.setState(rs.getString("State"));
			siteVO.setZip(rs.getString("Zip"));
			siteVO.setPhone(rs.getString("Phone"));
			siteVOs.add(siteVO);
			//System.out.println(siteVO.getSiteName());
		}
		this.siteVO.setSiteVOs(siteVOs);
		
	}
	
	/**
	 * HANDLE OUTPUT TYPE 2:	Stored Procedure Returns 1 or more Strings
	 * @param String[] output
	 * @return void
	 * @throws LACSDException
	*/
	protected void getResultsFromString(String[] output) 
			throws LACSDException {
	}
}
