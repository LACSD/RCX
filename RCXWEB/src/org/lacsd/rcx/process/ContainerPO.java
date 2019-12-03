package org.lacsd.rcx.process;

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.dao.ejb.ContainerDAOBean;
import org.lacsd.rcx.values.ContainerVO;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		ContainerPO.java
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Nov 27, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/
public class ContainerPO {
	@javax.ejb.EJB private ContainerDAOBean containerDAOBean;
	
	
	/**
	 * Create private instance of the Container Type DAO Bean
	 */
	public ContainerPO(){
		containerDAOBean = new ContainerDAOBean();
	}
	
	/**
	 * Call function through bean that retrieves list of Container Types
	 * @return
	 * @throws LACSDException
	 */
	public ContainerVO createContainer(ContainerVO containerVO) throws LACSDException{
		return containerDAOBean.createContainer(containerVO);
	}
	
	public ContainerVO getContainerList(ContainerVO containerVO) throws LACSDException{
		return containerDAOBean.getContainerList(containerVO);
	}
	
	public void updateContainer(ContainerVO containerVO) throws LACSDException{
		containerDAOBean.updateContainer(containerVO);
	}
	
	public void deleteContainer(ContainerVO containerVO) throws LACSDException{
		containerDAOBean.deleteContainer(containerVO);
	}
}
