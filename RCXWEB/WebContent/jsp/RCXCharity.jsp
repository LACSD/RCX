<!DOCTYPE html>
<%@ page import="org.lacsd.rcx.action.CharityAction" %>
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

<script src="/rcx/javascript/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="/rcx/javascript/Charity.js" type="text/javascript"  ></script>
<script src="https://kit.fontawesome.com/1ede97275a.js" crossorigin="anonymous"></script>

</head>





<body class="bodyBackground" onLoad="setup()">

<input type="hidden" id="curCharityName"/>

	<div class="py-3">
	  <div class="container">
	
	    <div class="row m-0 p-0">
	      <div class="col-md-12">
	      	<div class="row highlight" >
	      	<div class="col-md-8"></div>
	    			<div  data-toggle="modal" data-target=".editCharity" class="col-md-2 "><a class="btn btn btnnew -primary btn-primary mb-1 mt-2" href="#">New Charity&nbsp;<i class="fa fa-fw fa-plus-circle text-white" ></i> </a></div>
	    			<div  data-toggle="modal" data-target=".assignToSite" class="col-md-2"><a class="btn btn btnnew -primary btn-primary mb-1 mt-2" href="#">Assign To Site&nbsp;<i class="fa fa-fw fa-plus-circle text-white" ></i> </a></div>
	    			
	    		</div>
	        <div class="table-responsive">
	          <table class="table table-bordered">
	            <thead class="thead-light table-primary text-primary">
	              <tr class="table-success" >
	                <th>Name</th>
	                <th>Edit</th>
	                <th>Delete</th>
	              </tr>
	            </thead>
	            <tbody>
	            	<s:iterator value="charityVO.charityVOs"  status="iter">
	            	<tr class="tableRow" id=<s:property value="charityID" />>
						<td><s:property value="name" /></td>
						<td><i onClick="doEdit('<s:property value="charityID" />')" style="cursor: pointer;" title="Edit Charity" data-toggle="modal" data-target=".editCharity" class="fa btnedit fa-pencil pl-2 fa-lg text-primary"></i></td>
						<s:if test="%{inUse == \"N\"}">
							<td><i  title="Delete Unavailible:&#013;Charity in use" class="fa  fa-trash pl-3 text-muted"></i></td>		
						</s:if>
						<s:else>
							<td><i style="cursor: pointer;"  title= "Delete Charity" data-toggle="modal" data-target=".deleteCharity" onClick="delbtn(<s:property value="charityID" />)" class="fa btndel  fa-trash pl-3  text-danger"></i></td>		
						</s:else>
						<s:hidden  value="%{charityID}" id="%{name}" />				
					</tr>
						
	            	</s:iterator>
	            </tbody>
	          </table>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal deleteCharity" id="delModal" style="" >
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
		 		<div class="modal-header bg-danger">
		      		<h5 class="modal-title text-white ">Delete Charity</h5> <button type="button" class="text-white close" data-dismiss="modal"> <span>×</span> </button>
			   	</div>
			   	<div class="p-2">
			   		Are you sure you wish to delete:
					<div id="delName"></div>
				</div>

				<div class="row pl-4 pr-4 pb-1 pt-1">
					<div style="cursor: pointer;" class=" col-md-5 btn btn-danger"  onClick="doDelete()">
						Yes
					</div>
					<div class="col-md-2">
					</div>
					<div style="cursor: pointer;" class="col-md-5 btn btn-primary"  data-dismiss="modal">
						No
					</div>
				</div>
		 		<s:form id="delForm" action="Charity" name="delForm" method="post" theme="simple" namespace="/">
	 				<s:hidden name="actionName" value="del"/>
	 				<s:textfield type="hidden" name="charityVO.charityID"/>
				</s:form>
			</div>
		</div>
	</div> 
	
	<div class="modal editCharity" id="editModal" style="" >
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content">
		 		<div class="modal-header bg-primary">
		      		<h5 id="modalTitle" class="modal-title text-white ">Edit Charity</h5> <button type="button" class="text-white close" data-dismiss="modal"> <span>×</span> </button>
			   	</div>

		 		<s:form id="form" action="Charity" name="addForm" onkeypress="return doSubmit()" method="post" theme="simple" namespace="/">
	 				<s:hidden name="actionName" value="add"/>
					<s:textfield type="hidden" name="charityVO.charityID"/>
				   	<div class="pl-1 pt-1">
						<div class="row">
							<div class="col-md-3 ml-1 pt-1"><label for="name">Description: </label></div> <div class="col-md-8"> <s:textfield id="name" name="charityVO.name" class="form-control" onkeypress="return captureEnter(event)"/></div>
						</div>
						<div style="display:none" id="noDescError" class="row col-md-11  pl-3">
							<font color="red">Description Required!</font>
						</div>
						<div style="display:none" id="nameInUseError" class="row col-md-11  pl-3">
							<font color="red">Name in use! Choose a different name</font>
						</div>
					</div>
					<div class="pb-1 pt-1" style="text-align: center">
						<div style="cursor: pointer;" class="btn btn-primary"  onClick="doSubmit()">
							Save Changes
						</div>
					</div>
				</s:form> 	
			</div>
		</div>
	</div> 
	
	<div class="modal assignToSite" id="assignToSite" style="" >
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
		 		<div class="modal-header bg-primary">
		      		<h5 id="modalTitle" class="modal-title text-white ">Assign Charity to Site</h5> <button type="button" class="text-white close" data-dismiss="modal"> <span>×</span> </button>
			   	</div>
			   	<div class="row align-items-center justify-content-center mb-1 mt-1">
			   		<div class="dropdown">
			   			
	 				   	<button class="btn btn-secondary dropdown-toggle" type="button" id="siteDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    Site: <s:property value="siteVO.siteVOs[0].name" />
					    </button>
				   		<div class="dropdown-menu" aria-labelledby="siteDropdown">
	
				   			<s:iterator value="siteVO.siteVOs"  status="iter">
				   				<a class="dropdown-item" id="siteDropdown_<s:property value="%{#iter.index}"/>" onClick="setSite('<s:property value="%{#iter.index}"/>')"><s:property value="name" /></a>
				   				<s:hidden id="siteID_%{#iter.index}" value="%{siteID}" />
				   			</s:iterator>
				   		</div>
			   		</div>
			   	</div>
			   	<div class="row  justify-content-center mb-1 ml-1 mr-1">
				   	<div class="col-md-5" >
   	 	   				<div class="justify-content-center align-items-center">
							<h4 align="center">Available Charities</h4>
						</div>
					   	<div class="table-wrapper-scroll-y my-custom-scrollbar">
		   		            <table id="charitiesTable" class="table table-bordered">
		   		            	<tbody id="avalCharities">
							   		<s:iterator value="charityVO.charityVOs"  status="iter">
							   			<tr class="tableRow" onclick="markRowActive(this)" id="avalChar_<s:property value="charityID" />">
							   				<td><s:property value="name" /></td>
							   			</tr>
							   		</s:iterator>
						   		</tbody>
							</table>
						</div>
					</div>
					<div class="col-md-2 mt-2">
						<div class="row justify-content-center align-items-center mt-5">
							<a class="btn btn -primary btn-primary mb-1 mt-2" href="#" onClick="addCharity()">Add&nbsp;<i class="fa fa-fw fas fa-angle-double-right text-white" ></i> </a>
							<a class="btn btn -primary btn-danger mb-1 mt-2" href="#"onClick="removeCharity()"><i class="fa fa-fw fas fa-angle-double-left text-white" ></i>Remove&nbsp; </a>
						</div>

					</div>
				   	<div class="col-md-5" id="sites">
   						<div class="justify-content-center align-items-center">
							<h4 align="center">Assigned Charities</h4>
						</div>
					   	<div class="table-wrapper-scroll-y my-custom-scrollbar">
					   	<s:iterator value="siteVO.siteVOs"  status="iter">
					   		<table class="table table-bordered" id="site_<s:property value="%{#iter.index}"/>">
					   			<tbody id="avalCharities_site_<s:property value="%{#iter.index}"/>">
										<s:iterator value="charities"  status="iter">
											<tr id="siteChar_<s:property value="charityID"/>" class="tableRow"  onclick="markRowActive(this)">
												<td  ><s:property value="name"/></td>
											</tr>
										</s:iterator>
							   	</tbody>
						   	</table>
				   		</s:iterator>
					   	</div>
					</div>
				</div>
				<s:form id="assignForm" action="Charity" name="assignForm" method="post" theme="simple" namespace="/">
					<s:hidden name="actionName" value="assign"/>
					<s:textfield id="site" type="hidden" name="siteVO.siteID"/>
					<!-- name is being used here to hold a list of charity ids as a string -->
					<s:textfield id="charities" type="hidden" name="siteVO.name"/>
				</s:form>
				<div style="cursor: pointer;" class="btn btn-primary m-2"  onClick="doAssign()">
					Save Changes
				</div>
			</div>
		</div>
	</div>
</body>

</html>