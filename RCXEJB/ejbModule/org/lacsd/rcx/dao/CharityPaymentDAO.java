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
import org.lacsd.rcx.values.CharityPaymentVO;
import org.lacsd.rcx.values.CharityVO;
import org.lacsd.rcx.values.SiteCharityVO;

public class CharityPaymentDAO extends LACSDSqlServerProcDAO{
	private static String Get_All_Charity_Payments = "A";
	private static String Del_Charity_Payment = "B";
	private static String Add_Charity_Payment = "D";
	private static String GET_SITE_DATA = "C";


	
	private String action;
	private CharityPaymentVO charityPaymentVO;
	private SiteCharityVO siteVO;
	
	public CharityPaymentDAO(){
		super(RCXStoredProcedures.RCXCHAR002SP);
	}
	
	/**
	 * Get list of payments
	 * @return CharityPaymentVO
	 * @throws LACSDException
	 */
	public CharityPaymentVO getCharityPaymentsList() throws LACSDException{
		super.setQueryType(EXECUTE_QUERY);
		this.action = Get_All_Charity_Payments;
		this.charityPaymentVO = new CharityPaymentVO();
		execute();
		return this.charityPaymentVO;
	}
	
	
	/**
	 * Get list of sites 
	 * @return SiteCharityVO
	 * @throws LACSDException
	 */
	public SiteCharityVO getSitesList() throws LACSDException{
		super.setQueryType(EXECUTE_QUERY);
		this.action = GET_SITE_DATA;
		this.siteVO= new SiteCharityVO();
		execute();
		return this.siteVO;
	}
	
	/**
	 * Add a new charity payment
	 * @return void
	 * @throws LACSDException
	 */
	public void addCharity(CharityPaymentVO charityPaymentVO) throws LACSDException {
		super.setQueryType(EXECUTE_UPDATE);
		this.action = Add_Charity_Payment;
		this.charityPaymentVO = charityPaymentVO;
		execute();
	}
	
	/**
	 * deletes a charity payment
	 * @return void
	 * @throws LACSDException
	 */
	public void delCharity(CharityPaymentVO charityPaymentVO) throws LACSDException {
		super.setQueryType(EXECUTE_UPDATE);
		this.action = Del_Charity_Payment;
		this.charityPaymentVO = charityPaymentVO;
		execute();
	}
	
	
	
	@Override
	protected void registerInputs() throws LACSDException {
		// TODO Auto-generated method stub
		setInputParam(1,this.action);
		if(action.equals(Add_Charity_Payment)){
			System.out.println("Called add charity " + this.charityPaymentVO);
			setInputParam(2, this.charityPaymentVO.getSiteID());
			setInputParam(3, this.charityPaymentVO.getCharityID());
			setInputParam(4, this.charityPaymentVO.getDate());
			setInputParam(5, this.charityPaymentVO.getPayment());
		}
		if(action.equals(Del_Charity_Payment)){
			System.out.println("Called del charity " + this.charityPaymentVO);
			setInputParam(2, this.charityPaymentVO.getSiteID());
			setInputParam(3, this.charityPaymentVO.getCharityID());
			setInputParam(4, this.charityPaymentVO.getDate());
		}
	}

	@Override
	protected void registerOutputs() throws LACSDException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getResultsFromResultSet(ResultSet rs) throws SQLException, LACSDException {
		if(action.equalsIgnoreCase(Get_All_Charity_Payments)){
			
			getResultsFromCharityPaymentsList(rs);
		}
		if(action.equalsIgnoreCase(GET_SITE_DATA)){
				getResultsFromSitesList(rs);
		}
		
	}
	
	/**
	 * get site list
	 * @return void
	 * @throws LACSDException
	 */
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
			//if new Site create new site VO and add first charity to SiteVO.Charities
			if(!siteIDs.contains(siteID)){
				siteIDs.add(siteID);
				siteVO.setName(name);
				siteVO.setSiteID(siteID);
				siteVO.addToCharities(new CharityVO(charityID, charityName));
				siteVOs.add(siteVO);
			} else {
				//site already exists add new charity to already existing site
				for (SiteCharityVO s : siteVOs){
					if(s.getSiteID() == siteID){
						s.addToCharities(new CharityVO(charityID, charityName));
					}
				}
			}
		}
//		System.out.println("CharityPaymentDAO getResultsFromSiteList");
//		for (SiteCharityVO sc : siteVOs){
//			System.out.println(sc);
//		}
		this.siteVO.setSiteVOs(siteVOs);
	}
	
	/**
	 * get list of charity payments
	 * @return void
	 * @throws LACSDException
	 */
	private void getResultsFromCharityPaymentsList (ResultSet rs) throws SQLException, LACSDException{
		ArrayList<CharityPaymentVO> charityPaymentVOs = new ArrayList<CharityPaymentVO>();
		CharityPaymentVO charityPaymentVO;
		DateConvert dc = DateConvert.getInstance();
		while(rs.next()){

			charityPaymentVO = new CharityPaymentVO();
			charityPaymentVO.setSiteID(rs.getShort("SiteID"));
			charityPaymentVO.setCharityID(rs.getShort("CharityID"));
			charityPaymentVO.setDate(dc.getStringOfSQLTimestamp(rs.getTimestamp("PayDate"),"MM/dd/yyyy"));
			charityPaymentVO.setPayment(rs.getFloat("Payment"));
			charityPaymentVOs.add(charityPaymentVO);
		}
//		System.out.println("CharityPaymentDAO getResultsFromCharityPaymentsList");
//		for (CharityPaymentVO sc : charityPaymentVOs){
//			System.out.println(sc);
//		}
		this.charityPaymentVO.setCharityPaymentVOs(charityPaymentVOs);
	}

	@Override
	protected void getResultsFromString(String[] output) throws LACSDException {
		// TODO Auto-generated method stub
		
	}

}
