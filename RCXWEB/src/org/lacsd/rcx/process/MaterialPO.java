package org.lacsd.rcx.process;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		MaterialDetailPO.java
//* Revision: 		1.0
//* Author:			christophersimmons@lacsd.org
//* Created On: 	Aug 21, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.values.MaterialVO;
import org.lacsd.rcx.dao.ejb.MaterialDAOBean;

public class MaterialPO {
	@javax.ejb.EJB private MaterialDAOBean materialDAOBean;
	
	public MaterialPO(){
		materialDAOBean = new MaterialDAOBean();

	}
	
	public MaterialVO getMaterialsList() throws LACSDException{
		return materialDAOBean.getMaterialsList();
	}
	
	public void addMaterial(MaterialVO matVO) throws LACSDException {
		materialDAOBean.addMaterial(matVO);
	}
	
	public void delMaterial(MaterialVO matVO) throws LACSDException {
		materialDAOBean.delMaterial(matVO);
	}
}
