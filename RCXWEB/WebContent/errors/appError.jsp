<!--****************************************************************************
//* Copyright (c) 2013 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		appError.jsp
//* Revision: 		1.0
//* Author:			HHO@LACSD.ORG
//* Created On: 	08-01-2013
//* Modified By:	
//* Modified On:	
//*					
//* Description:	NON RECOVERABLE ERROR SCREEN - APPLICATION FAULT
/****************************************************************************** -->
<%@ page import="org.lacsd.common.constants.LACSDWebConstants" %>
<%@ page errorPage="jspError.jsp" %>  
  
<br><br>
<TABLE CELLSPACING="2" CELLPADDING="4" width="98%" class="table-content" align="center">
	<TR>
	    <TD width="20"></TD>
		<TD class="boldText" colspan="2">An unexpected error has occurred.&nbsp;&nbsp;Please try again.<br/>If problem persists, please contact Help Desk at ext. 1006</TD>
		<TD width="20"></TD>
	</TR>
	<tr>
	    <td></td>
	    <TD width="20"></TD>
		<TD class="error"><%=request.getAttribute(LACSDWebConstants.ERROR_MESSAGE)%></TD>
	    <TD width="20"></TD>
	</tr>
	<tr>
	    <td></td>
		<TD class="boldText" colspan="2">Time Occurs: <%=new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm a").format(new java.util.Date())%></TD>
		<TD width="20"></TD>
	</tr>
</TABLE>
