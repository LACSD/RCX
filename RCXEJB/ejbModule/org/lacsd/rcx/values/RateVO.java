package org.lacsd.rcx.values;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.ibm.security.util.calendar.BaseCalendar.Date;

/******************************************************************************
//* Copyright (c) 2019 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		RateVO.java
//* Revision: 		1.0
//* Author:			carlosarojas@lacsd.org
//* Created On: 	Oct 4, 2019
//* Modified By:    
//* Modified On:    
//* 
//* Description:	
/******************************************************************************/


/**
 * Private variables for all fields of Rate Database, and an array list of rateVOs
 * Getters/Setters for all these variables contained here
 * @author carlosarojas
 *
 */
public class RateVO implements Serializable{

	private static final long serialVersionUID = 7480591057910571630L;

	private short siteID;
	private short materialID;
	private String date;
	
	private String siteName;
	private String matName;
	
	private BigDecimal contPerLb;
	private BigDecimal scrapRate;
	private BigDecimal redemRate;
	private BigDecimal bonusRate;
	private BigDecimal smallCountRate;
	private BigDecimal largeCountRate;
	
	//Values that determine whether field editable or not; fields are 'Y' or 'N'
	private String isActive;
	private String hasScrap;
	private String hasRedemption;
	private String hasBonus;
	private String hasCount;
	
	//Value that determines whether the rate is being created or updated; 'Y' for new, 'N' for not.
	private String newRate;
	
	
	
	private ArrayList<RateVO> rateVOs;



	public short getSiteID() {
		return siteID;
	}



	public void setSiteID(short siteID) {
		this.siteID = siteID;
	}



	public short getMaterialID() {
		return materialID;
	}



	public void setMaterialID(short materialID) {
		this.materialID = materialID;
	}



	







	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getSiteName() {
		return siteName;
	}



	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}



	public String getMatName() {
		return matName;
	}



	public void setMatName(String matName) {
		this.matName = matName;
	}



	public BigDecimal getContPerLb() {
		return contPerLb;
	}



	public void setContPerLb(BigDecimal contPerLb) {
		this.contPerLb = contPerLb;
	}



	public BigDecimal getScrapRate() {
		return scrapRate;
	}



	public void setScrapRate(BigDecimal scrapRate) {
		this.scrapRate = scrapRate;
	}



	public BigDecimal getRedemRate() {
		return redemRate;
	}



	public void setRedemRate(BigDecimal redemRate) {
		this.redemRate = redemRate;
	}



	public BigDecimal getBonusRate() {
		return bonusRate;
	}



	public void setBonusRate(BigDecimal bonusRate) {
		this.bonusRate = bonusRate;
	}



	public BigDecimal getSmallCountRate() {
		return smallCountRate;
	}



	public void setSmallCountRate(BigDecimal smallCountRate) {
		this.smallCountRate = smallCountRate;
	}



	public BigDecimal getLargeCountRate() {
		return largeCountRate;
	}



	public void setLargeCountRate(BigDecimal largeCountRate) {
		this.largeCountRate = largeCountRate;
	}



	public String getIsActive() {
		return isActive;
	}



	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}



	public String getHasScrap() {
		return hasScrap;
	}



	public void setHasScrap(String hasScrap) {
		this.hasScrap = hasScrap;
	}



	public String getHasRedemption() {
		return hasRedemption;
	}



	public void setHasRedemption(String hasRedemption) {
		this.hasRedemption = hasRedemption;
	}



	public String getHasBonus() {
		return hasBonus;
	}



	public void setHasBonus(String hasBonus) {
		this.hasBonus = hasBonus;
	}



	public String getHasCount() {
		return hasCount;
	}



	public void setHasCount(String hasCount) {
		this.hasCount = hasCount;
	}



	public String getNewRate() {
		return newRate;
	}



	public void setNewRate(String newRate) {
		this.newRate = newRate;
	}



	public ArrayList<RateVO> getRateVOs() {
		return rateVOs;
	}



	public void setRateVOs(ArrayList<RateVO> rateVOs) {
		this.rateVOs = rateVOs;
	}

	
	


	
	
	

	
}
