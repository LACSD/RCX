package org.lacsd.rcx.process;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		VendorPO.java
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Sep 17, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.values.VendorVO;
import org.lacsd.rcx.dao.ejb.VendorDAOBean;

public class VendorPO {
	@javax.ejb.EJB private VendorDAOBean vendorDAOBean;
	
	/**
	 * Create private instance of the Vendor DAO Bean
	 */
	public VendorPO(){
		vendorDAOBean = new VendorDAOBean();
	}
	
	/**
	 * Call function through bean that retrieves list of vendors
	 * @return
	 * @throws LACSDException
	 */
	public VendorVO getVendList() throws LACSDException{
		return vendorDAOBean.getVendList();
	}
	
	/**
	 * Passes a vendoVO as a parameter to either add it to/update it in database
	 * by calling update function through bean
	 * @param vendVO
	 * @throws LACSDException
	 */
	public void updateVend(VendorVO vendVO) throws LACSDException{
		vendorDAOBean.updateVend(vendVO);
	}
	
	/**
	 * Passes vendVO as parameter to delete it from database calling
	 * delete function through bean
	 * @param vendVO
	 * @throws LACSDException
	 */
	public void deleteVend(VendorVO vendVO) throws LACSDException{
		vendorDAOBean.deleteVend(vendVO);
	}

}
