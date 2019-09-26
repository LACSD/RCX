<!--****************************************************************************
//* Copyright (c) 2013 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		sql2KError.jsp
//* Revision: 		1.0
//* Author:			HHO@LACSD.ORG
//* Created On: 	08-01-2013
//* Modified By:	
//* Modified On:	
//*					
//* Description:	NON RECOVERABLE ERROR SCREEN - SQL 2000 DATABASE FAULT
/****************************************************************************** -->
<%@ page import="org.lacsd.common.constants.LACSDWebConstants" %>
<%@ page errorPage="jspError.jsp" %>

<br><br>
<TABLE CELLSPACING="2" CELLPADDING="4" width="98%" class="table-content" align="center">
	<TR>
		<TD class="boldText">An unexpected error has occurred: </TD>
	</TR>
	<TR>	
		<TD ><br>
		<TABLE>
		<TR>
			<TD width="30"></TD>
			<TD class="error"><%=request.getAttribute(LACSDWebConstants.ERROR_MESSAGE)%></TD>
		</TR>
		</TABLE><br>	
		</TD>
	</TR>
</TABLE>



