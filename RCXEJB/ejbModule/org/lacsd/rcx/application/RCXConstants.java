package org.lacsd.rcx.application;

import java.util.HashMap;

/******************************************************************************
//* Copyright (c) 2013 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		LFGConstants.java
//* Revision: 		1.0
//* Author:			hho@lacsd.org
//* Created On: 	Oct 10, 2013
//* Modified By:    
//* Modified On:    
//* 
//* Description:	Application constants
/******************************************************************************/

public class RCXConstants {
	public static final String RECOVERABLE ="RECOVERABLE";
	public static String PERSONAL_INFO = "PersonalInfo";
	public static String SYSTEM_ID = "SYSTEMID";
	public static String APP_ROLE_ID = "ROLEID";
	public static int IT_SYSTEM_ADMIN = 1;
	public static int LFG_SYSTEM_ADMIN = 2;
	public static int LFG_SUPERVISOR = 3;
	public static int LFG_USER = 4;
	public static int WELL_DATA = 1;
	public static int SAMPLE_PORT_DATA = 2;
	public static String REPORT_FOLDER = "/Landfill Gas Monitoring/";
	
	public static String ADJUST_TYPE             = "A";
	public static String MONITOR_SERIES          = "B";
	public static String WELL_SECTION            = "C";
	public static String SAMPLE_PORT_SECTION     = "D";
	public static String WELL_STATUS             = "E";
	public static String WELL_TYPE               = "F";
	public static String SAMPLE_PORT_TYPE        = "G";
	public static String CASING_MATERIAL         = "H";
	public static String CASING_DIAMETER1        = "I";
	public static String FLOW_DEVICE             = "J";
	public static String INSTALL_METHOD          = "K";
	public static String DRAWING                 = "L";
	public static String CONTROL_VALVE_TYPE      = "M";
	public static String PIPE_MATERIAL           = "N";
	public static String PIPE_SIZE               = "O";
	public static String LATERAL_MATERIAL        = "P";
	public static String PROBE_TYPE              = "Q";
	public static String FILL_CONF_MATERIAL      = "R";
	public static String PROBE_COMMENTS          = "S";
	public static String SOUND_PROFILE_SERIES    = "T";
	public static String FLARE                   = "W";
	public static String BLOWER                  = "X";
	public static String BLOWER_STATUS           = "Y";
	public static String PH_GAS_LOCATION         = "Z";
	public static String LIQUID_LOCATION         = "1";
	public static String LIQUID_SUBLOCATION      = "2";
	public static String END_WELL                = "3";
	public static String LATERAL_PIPE_RATING     = "4";
	public static String LIQUID_TYPE             = "5";
	public static String LIQUID                  = "6";
	public static String PIPE_DETAIL             = "7";
	public static String LOCATION                = "8";
	public static String PROBE_BOUNDARY          = "9";
	public static String WELL_PUMP               = "10"; //List of existing well pump
	public static String WELL_PUMP_SERIES        = "11"; //monitor series for well pump
	public static String PUMP_TYPE               = "12"; // pump type
	public static  HashMap<String, String> acceptabeValues = new HashMap<String, String>();
	
	static {
		 acceptabeValues.put(RCXConstants.WELL_STATUS,  "Well Status");
		 acceptabeValues.put(RCXConstants.INSTALL_METHOD,  "Installation Method");
		 acceptabeValues.put(RCXConstants.WELL_TYPE, "Well Type");
		 acceptabeValues.put(RCXConstants.CASING_MATERIAL,  "Casing Material");
		 acceptabeValues.put(RCXConstants.CONTROL_VALVE_TYPE,  "Control Valve Type");
		 acceptabeValues.put(RCXConstants.LATERAL_MATERIAL,  "Lateral Material");
		 acceptabeValues.put(RCXConstants.FLOW_DEVICE, "Flow Device Name");
		 acceptabeValues.put(RCXConstants.PROBE_TYPE, "Probe Type");
		 acceptabeValues.put(RCXConstants.BLOWER,  "Blower");
		 acceptabeValues.put(RCXConstants.FLARE,  "Flare Name");
		 acceptabeValues.put(RCXConstants.PIPE_DETAIL, "Pipe Details");
		 acceptabeValues.put(RCXConstants.LIQUID_TYPE, "Liquid Types");
		 acceptabeValues.put(RCXConstants.LIQUID,  "Liquids");
		 acceptabeValues.put(RCXConstants.MONITOR_SERIES, "Monitoring Series");
		 acceptabeValues.put(RCXConstants.ADJUST_TYPE, "Well Adjustment Comments");
		 acceptabeValues.put(RCXConstants.LOCATION, "Landfill Locations");
	}
	
}
