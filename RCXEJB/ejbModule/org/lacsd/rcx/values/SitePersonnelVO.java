package org.lacsd.rcx.values;

import java.io.Serializable;
import java.util.ArrayList;

/******************************************************************************
//* Copyright (c) 2013 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		SitePersonnelVO.java
//* Revision: 		1.0
//* Author:			hho@lacsd.org
//* Created On: 	Aug 1, 2013
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/

public class SitePersonnelVO implements Serializable{

	private static final long serialVersionUID = -8263971290219773134L;
	private String employeeID;
	private int siteID;
	private String siteName;
	private boolean notify = false;
	private boolean remediationNotify = false;
	private boolean active  = false;
	private int personnelID;
	private int assignedID;
	private String employeeName;
	private String jobClass;
	private int roleID;
	private String roleName = "";
	private String displayName ;
	private String email;
	
	private ArrayList<SitePersonnelVO> sitePersonnelVOs;
	
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public int getSiteID() {
		return siteID;
	}
	public void setSiteID(int siteID) {
		this.siteID = siteID;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	public int getAssignedID() {
		return assignedID;
	}
	public void setAssignedID(int assignedID) {
		this.assignedID = assignedID;
	}
	public ArrayList<SitePersonnelVO> getSitePersonnelVOs() {
		return sitePersonnelVOs;
	}
	public void setSitePersonnelVOs(ArrayList<SitePersonnelVO> sitePersonnelVOs) {
		this.sitePersonnelVOs = sitePersonnelVOs;
	}
	
	public boolean isNotify() {
		return notify;
	}
	public void setNotify(boolean notify) {
		this.notify = notify;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getPersonnelID() {
		return personnelID;
	}
	public void setPersonnelID(int personnelID) {
		this.personnelID = personnelID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isRemediationNotify() {
		return remediationNotify;
	}
	public void setRemediationNotify(boolean remediationNotify) {
		this.remediationNotify = remediationNotify;
	}
		
}
