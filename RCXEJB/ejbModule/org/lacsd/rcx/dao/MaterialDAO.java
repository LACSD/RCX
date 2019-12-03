package org.lacsd.rcx.dao;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		MaterialAction.java
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
import org.lacsd.rcx.values.MaterialVO;

public class MaterialDAO extends LACSDSqlServerProcDAO{
	
	/**
	-- A : Retrieves distinct Material info, setting "found" column to Y when MaterialID not NULL, N otherwise.
	-- B : UNUSED - Retrieves all from Material dbo based on given MaterialID
	-- C : Deletes Material from dbo based on given MaterialID
	-- D : Adds new Material to dbo if new (MaterialID=0), or updates Material based on given MaterialID
	-- E : Retrieve Active (IsActive='Y') Material information based on given SiteID
	*/
	
	private static String GET_ALL_MATERIALS = "A";
	
	private static String DEL_MAT = "C";
	private static String ADD_MAT = "D";
	private static String GET_MATERIALS_BY_SITE = "E";

	
	private String action;
	private MaterialVO matVO;
	
	public MaterialDAO(){
		super(RCXStoredProcedures.RCXMATL001SP);
	}
	
	/**
	 * 
	 * @return
	 * @throws LACSDException
	 */
	public MaterialVO getMaterialsList() throws LACSDException{
		
		super.setQueryType(EXECUTE_QUERY);
		this.action = GET_ALL_MATERIALS;
		this.matVO = new MaterialVO();
		execute();
		return this.matVO;
	}
	/**
	 * Return a list of material by site
	 * @return
	 * @throws LACSDException
	 */
    public MaterialVO getMaterialsListBySite(MaterialVO materialVO) throws LACSDException{
		
		super.setQueryType(EXECUTE_QUERY);
		this.action = GET_MATERIALS_BY_SITE;
		this.matVO = materialVO;
		execute();
		return this.matVO;
	}
	
	public void addMaterial(MaterialVO matVO) throws LACSDException {
		super.setQueryType(EXECUTE_UPDATE);
		this.action = ADD_MAT;
		this.matVO = matVO;
		execute();
	}
	
	public void delMaterial(MaterialVO matVO) throws LACSDException {
		super.setQueryType(EXECUTE_UPDATE);
		this.action = DEL_MAT;
		this.matVO = matVO;
		execute();
	}
	
	
	@Override
	protected void registerInputs() throws LACSDException {
		// TODO Auto-generated method stub
		setInputParam(1,this.action);
		if(action.equals(ADD_MAT)){
			setInputParam(2, this.matVO.getMatID());
			setInputParam(3, this.matVO.getName());
			setInputParam(4, this.matVO.getHasCountValue());
			setInputParam(5, this.matVO.getHasScrapValue());					
		    setInputParam(6, this.matVO.getHasRedemptionValue());
		    setInputParam(7, this.matVO.getHasBonusValue());
		    setInputParam(8, this.matVO.getIsActive());
		} else if(action.equals(DEL_MAT)){
			setInputParam(2, this.matVO.getMatID());
		} else if(action.equals(GET_MATERIALS_BY_SITE)){
			setInputParam(2,null);
			setInputParam(3,null);
			setInputParam(4,null);
			setInputParam(5,null);
			setInputParam(6,null);
			setInputParam(7,null);
			setInputParam(8,null);
			setInputParam(9, this.matVO.getSiteID());
		
		}
	}

	@Override
	protected void registerOutputs() throws LACSDException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getResultsFromResultSet(ResultSet rs) throws SQLException, LACSDException {
		if(action.equalsIgnoreCase(GET_ALL_MATERIALS) || action.equals(GET_MATERIALS_BY_SITE)){
			getResultsFromMaterialsList(rs);
		}
		
	}
	
	private void getResultsFromMaterialsList (ResultSet rs) throws SQLException, LACSDException{
		ArrayList<MaterialVO> matVOs = new ArrayList<MaterialVO>();
		MaterialVO matVO;
		while(rs.next()){

			matVO = new MaterialVO();
			matVO.setMatID(rs.getShort("MaterialID"));
			matVO.setName(rs.getString("Name"));
			matVO.setHasBonusValue(rs.getString("HasBonusValue"));
			matVO.setHasRedemptionValue(rs.getString("HasRedemptionValue"));
			matVO.setHasScrapValue(rs.getString("HasScrapValue"));
			matVO.setHasCountValue(rs.getString("HasCountValue"));
			matVO.setIsActive(rs.getString("IsActive"));
			matVO.setInUse((rs.getString("found")));
			matVOs.add(matVO);
		}
		this.matVO.setMatVOs(matVOs);
	}

	@Override
	protected void getResultsFromString(String[] output) throws LACSDException {
		// TODO Auto-generated method stub
		
	}

}
