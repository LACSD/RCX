package org.lacsd.rcx.action;

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.common.action.RCXGenericAction;
import org.lacsd.rcx.process.ContainerPO;
import org.lacsd.rcx.values.ContainerVO;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		ContainerAction.java
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Nov 27, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

public class ContainerAction extends RCXGenericAction{
	
	private static final long serialVersionUID = -380736494683369391L;
	
	public static final String SET_UP = "setup";
	
	public static final String CREATE = "create";
	
	public static final String RETRIEVE = "retrieve";
	
	public static final String UPDATE = "update";
	
	public static final String DELETE = "delete";
	
	
	
	private ContainerVO containerVO;
	
	public ContainerAction(){
		setContainerVO(new ContainerVO());
	}
	
	public void initErrControl() {
		//		Custom Error Exceptions Not Implemented! 
	}
	
	
	
	@Override
	protected String executeWithResult() throws LACSDException, Throwable {
		if(actionName.equals(SET_UP))
			return doSetup();
		if(actionName.equals(CREATE))
			return doCreate();
		if(actionName.equals(UPDATE))
			return doUpdate();
		if(actionName.equals(DELETE))
			return doDelete();
		return null;
	}
	
	/**
	 * Function deletes single record from database by calling deleteContainer(containerVO) through PO
	 * @return
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doDelete() throws LACSDException, Throwable {
		new ContainerPO().deleteContainer(containerVO);
		doSetup();
		return SET_UP;
	}

	
	/**
	 * Function updates a single record in database by calling updateContainer(containerVO) through PO
	 * @return
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doUpdate() throws LACSDException, Throwable {
		new ContainerPO().updateContainer(containerVO);
		doSetup();
		return SET_UP;
	}

	/**
	 * Function updates database by creating new record if it is unique by calling createContainer(containerVO) through PO 
	 * @return
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doCreate() throws LACSDException, Throwable {
		this.containerVO = new ContainerPO().createContainer(containerVO);
		doSetup();
		return SET_UP;
	}

	
	/**
	 * Function fetches all data from database to display  getContainerList(containerVO) through PO
	 * @return
	 * @throws LACSDException
	 * @throws Throwable
	 */
	private String doSetup() throws LACSDException, Throwable {
		this.containerVO = new ContainerPO().getContainerList(containerVO);
		return SET_UP;
	}

	public ContainerVO getContainerVO() {
		return containerVO;
	}
	public void setContainerVO(ContainerVO containerVO) {
		this.containerVO = containerVO;
	}
	
}
