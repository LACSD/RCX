<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="org.lacsd.rcx.action.MaterialAction" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib uri="/taglibs/lacsd.tld" prefix="lacsd" %> 
<html>
<head>
	<jsp:include page="../include/RCXNav.jsp" flush="true"/> 
	<script src="/rcx/javascript/jquery-3.4.1.min.js" type="text/javascript"  ></script>
	
	<script src="/rcx/javascript/NewTicket.js" type="text/javascript"  ></script>
	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body class="bodyBackground">
<div class="container">
  <div class="card m-5 bg-primary">
    <div class="card p-5 m-2">
	<div style="cursor: pointer;" class="btn btn-primary"  onClick="doGet()">
		Do Get
	</div>
	<div id="div1">
		
	</div>
    </div>
  </div>
</div>
</body>
</html>