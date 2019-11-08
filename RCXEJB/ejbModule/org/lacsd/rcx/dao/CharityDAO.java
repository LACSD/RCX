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
import org.lacsd.rcx.application.RCXStoredProcedures;
import org.lacsd.rcx.values.CharityVO;
import org.lacsd.rcx.values.SiteCharityVO;

public class CharityDAO extends LACSDSqlServerProcDAO{
	private static String GET_ALL_Charities = "A";
	private static String DEL_charity = "B";
	private static String ADD_charity = "D";
	private static String GET_SITE_DATA = "C";
	private static String ASSIGN_SITE_CHARITIES = "E";


	
	private String action;
	private CharityVO charityVO;
	private SiteCharityVO siteVO;
	
	public CharityDAO(){
		super(RCXStoredProcedures.RCXCHAR001SP);
	}
	
	/**
	 * 
	 * @return
	 * @throws LACSDException
	 */
	public CharityVO getCharitysList() throws LACSDException{
		super.setQueryType(EXECUTE_QUERY);
		this.action = GET_ALL_Charities;
		this.charityVO = new CharityVO();
		execute();
		return this.charityVO;
	}
	
	
	/**
	 * 
	 * @return
	 * @throws LACSDException
	 */
	public SiteCharityVO getSitesList() throws LACSDException{
		super.setQueryType(EXECUTE_QUERY);
		this.action = GET_SITE_DATA;
		this.siteVO= new SiteCharityVO();
		execute();
		return this.siteVO;
	}
	
	public void addCharity(CharityVO charityVO) throws LACSDException {
		super.setQueryType(EXECUTE_UPDATE);
		this.action = ADD_charity;
		this.charityVO = charityVO;
		execute();
	}
	
	public void delCharity(CharityVO charityVO) throws LACSDException {
		super.setQueryType(EXECUTE_UPDATE);
		this.action = DEL_charity;
		this.charityVO = charityVO;
		execute();
	}
	
	public void assignCharities(SiteCharityVO siteVO) throws LACSDException {
		super.setQueryType(EXECUTE_UPDATE);
		this.action = ASSIGN_SITE_CHARITIES;
		this.siteVO = siteVO;
		execute();
	}
	
	
	@Override
	protected void registerInputs() throws LACSDException {
		// TODO Auto-generated method stub
		setInputParam(1,this.action);
		if(action.equals(ADD_charity)){
			setInputParam(2, this.charityVO.getCharityID());
			setInputParam(3, this.charityVO.getName());
		}
		if(action.equals(DEL_charity)){
			setInputParam(2, this.charityVO.getCharityID());
		}
		if(action.equals(ASSIGN_SITE_CHARITIES)){
			System.out.println("ASSIGN_SITE_CHARITIES");
			System.out.println(this.siteVO.getName());
			setInputParam(2, null);
			setInputParam(3, null);
			setInputParam(4,this.siteVO.getSiteID());
			setInputParam(5,this.siteVO.getName());
		}
	}

	@Override
	protected void registerOutputs() throws LACSDException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getResultsFromResultSet(ResultSet rs) throws SQLException, LACSDException {
		if(action.equalsIgnoreCase(GET_ALL_Charities)){
			getResultsFromCharitysList(rs);
		}
		if(action.equalsIgnoreCase(GET_SITE_DATA)){
				getResultsFromSitesList(rs);
		}
		
	}
	
	private void getResultsFromSitesList (ResultSet rs) throws SQLException, LACSDException{
		ArrayList<SiteCharityVO> siteVOs = new ArrayList<SiteCharityVO>();
		ArrayList<Short> siteIDs = new ArrayList<Short>();
		SiteCharityVO siteVO;
		Short siteID;
		String name;
		String charityName;
		Short charityID;
		
		while(rs.next()){
			
			siteVO = new SiteCharityVO();
			siteID = rs.getShort("SiteID");
			name = rs.getString("Name");
			charityID = rs.getShort("CharityID");
			charityName = rs.getString("CharityName");
			if(!siteIDs.contains(siteID)){
				siteIDs.add(siteID);
				siteVO.setName(name);
				siteVO.setSiteID(siteID);
				siteVO.addToCharities(new CharityVO(charityID, charityName));
				siteVOs.add(siteVO);
			} else {
				for (SiteCharityVO s : siteVOs){
					if(s.getSiteID() == siteID){
						s.addToCharities(new CharityVO(charityID, charityName));
					}
				}
			}
		}
		this.siteVO.setSiteVOs(siteVOs);
	}
	
	private void getResultsFromCharitysList (ResultSet rs) throws SQLException, LACSDException{
		ArrayList<CharityVO> charityVOs = new ArrayList<CharityVO>();
		CharityVO charityVO;
		while(rs.next()){

			charityVO = new CharityVO();
			charityVO.setCharityID(rs.getShort("CharityID"));
			charityVO.setName(rs.getString("Name"));
			charityVO.setInUse(rs.getString("Deletable"));
			charityVOs.add(charityVO);
		}
		this.charityVO.setCharityVOs(charityVOs);
	}

	@Override
	protected void getResultsFromString(String[] output) throws LACSDException {
		// TODO Auto-generated method stub
		
	}

}
