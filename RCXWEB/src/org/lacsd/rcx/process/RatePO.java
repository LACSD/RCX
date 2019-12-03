package org.lacsd.rcx.process;

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.dao.ejb.RateDAOBean;
import org.lacsd.rcx.values.RateVO;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		RatePO.java
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Oct 7, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

public class RatePO {
	@javax.ejb.EJB private RateDAOBean rateDAOBean; 
	
	
	/**
	 * Create private instance of the Rate DAO Bean
	 */
	public RatePO(){
		rateDAOBean = new RateDAOBean();
	}
	
	/**
	 * Call function through bean that retrieves list of rates
	 * @return
	 * @throws LACSDException
	 */
	public RateVO getRateList(RateVO rateVO) throws LACSDException{
		return rateDAOBean.getRateList(rateVO);
	}
	
	
	
	public void updateRate(RateVO rateVO) throws LACSDException{
		rateDAOBean.updateRate(rateVO);
	}
	
	public RateVO createRate(RateVO rateVO) throws LACSDException{
		return rateDAOBean.createRate(rateVO);
	}
	
	public void deleteRate(RateVO rateVO) throws LACSDException{
		rateDAOBean.deleteRate(rateVO);
	}
}
