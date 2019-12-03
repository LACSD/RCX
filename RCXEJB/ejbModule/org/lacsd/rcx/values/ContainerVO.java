package org.lacsd.rcx.values;

import java.io.Serializable;
import java.util.ArrayList;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		ContainerVO.java
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Nov 27, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/
public class ContainerVO implements Serializable{

	private static final long serialVersionUID = 8999163791700355135L;

	private short containerID;
	private String name;
	
	private ArrayList<ContainerVO> containerVOs;

	public short getContainerID() {
		return containerID;
	}

	public void setContainerID(short containerID) {
		this.containerID = containerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ContainerVO> getContainerVOs() {
		return containerVOs;
	}

	public void setContainerVOs(ArrayList<ContainerVO> containerVOs) {
		this.containerVOs = containerVOs;
	}
	
	
}
