<!DOCTYPE html>
<%@ page import="org.lacsd.rcx.action.SiteOperationsAction" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib uri="/taglibs/lacsd.tld" prefix="lacsd" %> 



<html>
<style>
tr:hover {background-color:#f5f5f5;}
</style>
<s:hidden name="actionName" /> 
<head>
<jsp:include page="../include/RCXNav.jsp" flush="true">
</jsp:include> 

<link rel="stylesheet" href="/rcx/css/SiteOperations.css" type="text/css">
<script src="/rcx/javascript/SiteOperations.js" type="text/javascript"  ></script>
<script src="https://kit.fontawesome.com/1ede97275a.js" crossorigin="anonymous"></script>

</head>





<body class="bodyBackground" onLoad="setup('<s:property value="cashBoxVO.employeeID"/>')">
	
	<div class="modal newCashBox" id="newCashBox" style="" >
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content" id="newCashBoxDialogue">
		 		<div class=" bg-primary" style="text-align: center">
		      		<h1 class="modal-title text-white " style="display: inline-block">PUENTE HILLS RECYCLE CENTER</h1> <button onclick="cancelNewCashBox()" type="button" class="text-white close" style="margin-left: 0" > <span style="margin-right: 5px;">×</span> </button>
			   	</div>
			   	<div class="newCashBoxContent" style="text-align: center">
			   		<h3>Enter Initial Cash</h3>
			   		<h3> Operator:&nbsp <s:property value="employeeVO.firstName" />&nbsp<s:property value="employeeVO.lastName" /></h3>
			   	</div>
		 		<div id="newCashBoxErr1" style="display: none ">
					<div  class ="col-md-12" style="text-align: center">
							
					</div>
				</div>
		 		<div id="newCashBoxErr" style="display: none ">
					<div  class ="col-md-12" style="text-align: center">
						<font color="red">Opening Cashbox amount must be greater than 0</font>	
					</div>
				</div>
		 		<s:form id="newCashBoxForm" action="SiteOperations" name="newCBForm" method="post" theme="simple" namespace="/" style="margin-bottom: 10px">
	 				<s:hidden name="actionName" value="newCashBox"/>
	 				<div class="row">
	 					<div class="col-md-6" style="text-align: center">
	 						Opening Cashbox
	 					</div>
	 					<div class="col-md-6" style="text-align: center">
	 						Opening Safe Count
	 					</div>
		 				<div class="col-md-6" style="text-align: center">
		 					<s:textfield type="" id="newOpeningAmount" name="cashBoxVO.openingBalance"/>
		 				</div>
		 				<div class="col-md-6" style="text-align: center">
		 					
		 					<s:if test="%{safeVO.siteID=='-1'}">
								<s:textfield type="" id="newSafeCount" name="safeVO.currentBalance" readonly="false"/>
								<s:hidden name="safeVO.siteID" value="-1"/>
							</s:if>
							<s:else>
							    <s:textfield type="" id="newSafeCount" name="safeVO.currentBalance" readonly="true"/>
							</s:else>
		 				</div>
	 				</div>
				</s:form>
				<div class=" bg-primary">
					<div class="pb-1 pt-1" style="text-align: center">
						<div style="cursor: pointer;" class="btn bg-white"  onClick="doCreateCashBox()">
							Save Changes
						</div>
						<div style="cursor: pointer;" class="btn bg-white"  onclick="cancelNewCashBox()">
							Cancel
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal mainMenu" id="mainMenu" style="" >
		<div class="modal-dialog opConsole" role="document">
			<div class="modal-content" id="newmainMenu">
		 		<div class=" bg-primary" style="text-align: center">
		      		<h1 class="modal-title text-white " style="display: inline-block">PUENTE HILLS RECYCLE CENTER</h1> <button  type="button" data-dismiss="modal" class="text-white close" > <span style="margin-right: 5px;">×</span> </button>
			   	</div>
			   	<div  style="border-bottom: inherit; width: 1000px">
			   		<h3 class=" " style="margin-left: 1em; margin-top:.25em;">Operation Console</h3> 
			   	</div>
			   	<div class="row">
					<div class="col-md-4 col-lg-4 ">
						<div style="text-align: center">
				      		<h6 style="display: inline-block"><b>Reports</b></h6> 
					      	<div style= "width: 140px; margin-left: auto; margin-right: auto">
						   		<s:radio cssClass="reportRadio" name="reports" list="reports"  />
						   	</div>
						   	<div style="cursor: pointer;" class="btn bg-secondary text-white"  onClick="">
								Generate
							</div>
					   	</div>
					</div>
					<div class="col-md-4 col-lg-4 cmdFunctions" >
						<div style="text-align: center">
				      		<h6 style="display: inline-block"><b>Command Functions</b> </h6> 
					   </div>
					   	<div style="text-align: center">
							<div style="cursor: pointer;" class="btn bg-secondary text-white cmdFuncBtn"  onclick="">
								New Ticket
							</div>
							<div style="cursor: pointer;" class="btn bg-secondary text-white cmdFuncBtn"  data-toggle="modal" data-target=".updateSafe">
								Bank Drop
							</div>
							<div style="cursor: pointer;" class="btn bg-secondary text-white cmdFuncBtn"  onclick="">
								Reprint/View
							</div>
							<div style="cursor: pointer;" class="btn bg-secondary text-white cmdFuncBtn"  data-toggle="modal" data-target=".addCash">
								Add Cash
							</div>
							<div style="cursor: pointer;" class="btn bg-secondary text-white cmdFuncBtn"  onclick="">
								Void
							</div>
							<div style="cursor: pointer;" class="btn bg-secondary text-white cmdFuncBtn"  data-toggle="modal" data-target=".closeSafe">
								Close Safe
							</div>
							<div style="cursor: pointer;" class="btn bg-secondary text-white cmdFuncBtn"  onclick="">
								Search
							</div>
							<div style="cursor: pointer;" class="btn bg-secondary text-white cmdFuncBtn"  onclick="">
								Manual Mode
							</div>
					   	</div>
					</div>
					<div class="col-md-4 col-lg-4">
						<div class="">
							<div style="text-align: center">
				      			<h6 style="display: inline-block"><b>Operator Information</b></h6>
				      		</div> 
				      		<div class="col-md-12 col-lg-12" style="text-align: center"> 
				      			Cash Box Balance
				      		</div>
			      			<div class="opInfoTxtBox">
			      				$<s:property value="cashBoxVO.currentBalance" />
			      			</div>
			      			<div class="col-md-12 col-lg-12" style="text-align: center"> 
				      			Tickets Processed
				      		</div>
			      			<div class="opInfoTxtBox">
			      				<s:property value="numTicketsProcessed" />
			      			</div>
							<div class="col-md-12 col-lg-12" style="text-align: center"> 
				      			Voids
				      		</div>
			      			<div class="opInfoTxtBox">
			      				<s:property value="numTicketsVoided" />
			      			</div>
     				   	</div>				
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal cancelNewCashBox" id="cancelNewCashBox" style="" >
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content" style="text-align: center">
				<h4 class="mt-1">
					<span><font color="red">Failure to enter the Initial Cash Balance(s) will Terminate the Operation Session.</font></span>
				</h4>
				<h4>
					<span><font color="red">Do you wish to end this session?</font></span>
				</h4>
				<div class=" bg-danger">
					<div class="pb-1 pt-1" style="text-align: center">
						<div style="cursor: pointer;" class="btn bg-white"  onclick="location.href='http://localhost:9080/rcx/jsp/RCXIndex.jsp';">
							Yes
						</div>
						<div style="cursor: pointer;" class="btn bg-white"  onclick="returnToCashBox()">
							No
						</div>
					</div>
				</div>	
			</div>
		</div>
	</div>

	<div class="modal updateSafe" id=updateSafe style="" >
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content" style="text-align: center">
				<h1 class="mt-1">
					<span>PROCESS BANK DROP</span>
				</h1>
				<h3>
					<span>Enter the Bank Drop Amount</span>
				</h3>
					<s:form id="bankDropform" action="SiteOperations" name="updateSafeForm" onkeypress="return doUpdate()" method="post" theme="simple" namespace="/">
	 				<s:hidden name="actionName" value="update"/>
					
				   	<div class="pb-2" style="text-align: center;">
						<s:textfield type="hidden" name="safeVO.bankDrop"/>
						<input type="number" id="bankDropAmt" name="bankDropAmt">
					</div>
				</s:form>
				<div class=" bg-primary">
					<div class="pb-1 pt-1" style="text-align: center">
						<div style="cursor: pointer;" class="btn bg-white"  onclick="doUpdate()">
							Save
						</div>
						<div style="cursor: pointer;" class="btn bg-danger"  data-dismiss="modal">
							<font color="white">Cancel</font>
						</div>
					</div>
				</div>	
			</div>
		</div>
	</div>
	<div class="modal addCash" id=addCash style="" >
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content" style="text-align: center">
				<h1 class="mt-1">
					<span>ADD CASH TO CASHBOX</span>
				</h1>
				<h3>
					<span>Enter the Amount of Additional Cash</span>
				</h3>
					<s:form id="addCashform" action="SiteOperations" name="addCashform" onkeypress="return doAdd()" method="post" theme="simple" namespace="/">
	 				<s:hidden name="actionName" value="add"/>
					
				   	<div class="pb-2" style="text-align: center;">
						<s:textfield type="hidden" name="cashBoxVO.currentBalance"/>
						<input type="number" id="cashAmt" name="cashAmt">
					</div>
				</s:form>
				<div class=" bg-primary">
					<div class="pb-1 pt-1" style="text-align: center">
						<div style="cursor: pointer;" class="btn bg-white"  onclick="doAdd()">
							Save
						</div>
						<div style="cursor: pointer;" class="btn bg-danger"  data-dismiss="modal">
							<font color="white">Cancel</font>
						</div>
					</div>
				</div>	
			</div>
		</div>
	</div>
	<div class="modal closeSafe" id=closeSafe style="" >
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content" style="text-align: center">
				<h1 class="mt-1">
					<span>CLOSE SAFE</span>
				</h1>
				<h3>
					<span>Enter the Closing Safe Count</span>
				</h3>
					<s:form id="closeSafeform" action="SiteOperations" name="closeSafeform" onkeypress="return doClose()" method="post" theme="simple" namespace="/">
	 				<s:hidden name="actionName" value="close"/>
					
				   	<div class="pb-2" style="text-align: center;">
						<s:textfield type="hidden" name="safeVO.closeCount"/>
						<input type="number" id="closeAmt" name="closeAmt">
					</div>
				</s:form>
				<div class=" bg-primary">
					<div class="pb-1 pt-1" style="text-align: center">
						<div style="cursor: pointer;" class="btn bg-white"  onclick="doClose()">
							Save
						</div>
						<div style="cursor: pointer;" class="btn bg-danger"  data-dismiss="modal">
							<font color="white">Cancel</font>
						</div>
					</div>
				</div>	
			</div>
		</div>
	</div>
</body>

</html>