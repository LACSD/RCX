<!--****************************************************************************
//* Copyright (c) 2013 Sanitation Districts of Los Angeles. All Rights Reserved.
//* Filename:		jspError.jsp
//* Revision: 		1.0
//* Author:			HHO@LACSD.ORG
//* Created On: 	08-02-2013
//* Modified By:	
//* Modified On:	
//*					
//* Description:	NON RECOVERABLE ERROR SCREEN - JSP FAULT
/****************************************************************************** -->

<%@ page isErrorPage="true" %>



<INPUT TYPE="HIDDEN" NAME="actionName">

<TABLE CELLSPACING="2" CELLPADDING="4" width="98%" class="table-content" align="center">

	<TR><TD COLSPAN="2"><BR><BR></TD></TR>
	
	<TR><TD WIDTH="40"><BR></TD>
		<TD><H2>A JSP Page error occurred. The name of the exception is:</H2></TD>
	</TR>
	<TR><TD></TD>
		<TD><%= exception %></TD>
	</TR>

</TABLE>

