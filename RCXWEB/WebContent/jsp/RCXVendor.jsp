<!DOCTYPE html>
<%@ page import="org.lacsd.rcx.action.VendorAction" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib uri="/taglibs/lacsd.tld" prefix="lacsd" %> 
<%@ page import="org.lacsd.rcx.action.VendorAction" %>



<html>
<style>
tr:hover {background-color:#f5f5f5;}
</style>
<s:hidden name="actionName" /> 
<head>
<jsp:include page="../include/RCXNav.jsp" flush="true">
</jsp:include> 

<script src="/rcx/javascript/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="/rcx/javascript/Vendor.js" type="text/javascript"  ></script>
</head>





<body class="bodyBackground">

<!--  <input type="hidden" id="curMatName"/> -->

	<div class="py-3">
	  <div class="container">
	
	    <div class="row m-0 p-0">
	      <div class="col-md-12">
	      	<div class="row highlight" >
	      		<div class="col-md-10">
	        			<h6 class=""></h6>
	      		</div>
	    			<div  data-toggle="modal" data-target=".editVendor" class="col-md-2"><a class="btn btn btnnew -primary btn-primary mb-1 mt-2" href="#">New Vendor&nbsp;<i class="fa fa-fw fa-plus-circle text-white" ></i> </a></div>
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
	            	<s:iterator value="vendVO.vendVOs"  status="iter">
	            	<tr class="tableRow" id=<s:property value="vendID" />>
						<td><s:property value="name" /></td>

						<td>
							<i onClick="doEdit('<s:property value="vendID" />', 
												'<s:property value="name" />',
												'<s:property value="address" />',
												'<s:property value="city" />',
												'<s:property value="state" />',
												'<s:property value="zip" />',
												'<s:property value="phone" />',
												'<s:property value="ext" />',
												'<s:property value="fax" />',
												'<s:property value="contact" />',
												'<s:property value="refNum" />')" 
								style="cursor: pointer;" 
								title="Edit Vendor" data-toggle="modal" data-target=".editVendor" class="fa btnedit fa-pencil pl-2 fa-lg text-primary"></i></td>
						<!-- if vendor in use, gray out delete button and hover over reason why -->
						<s:if test="%{inUse == \"Y\"}">
							<td><i  title="Delete Unavailible:&#013;Vendor in use" class="fa  fa-trash pl-3 text-muted"></i></td>		
						</s:if>
						<s:else>
							<td><i style="cursor: pointer;"  title= "Delete Vendor" data-toggle="modal" data-target=".deleteVendor" 
								onClick="delbtn('<s:property value="vendID" />', 
												'<s:property value="name" />',
												'<s:property value="address" />',
												'<s:property value="city" />',
												'<s:property value="state" />',
												'<s:property value="zip" />',
												'<s:property value="phone" />',
												'<s:property value="ext" />',
												'<s:property value="fax" />',
												'<s:property value="contact" />',
												'<s:property value="refNum" />')"
								 class="fa btndel  fa-trash pl-3  text-danger"></i></td>		
						</s:else>
						<s:hidden  value="%{vendID}" id="%{name}" />	
									
					</tr>
						
	            	</s:iterator>
	            </tbody>
	          </table>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal deleteVendor" id="delModal" style="" >
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
		 		<div class="modal-header bg-danger">
		      		<h5 class="modal-title text-white ">Delete Vendor</h5> <button type="button" class="text-white close" data-dismiss="modal"> <span>×</span> </button>
			   	</div>
			   	<div class="p-2">
			   		<strong>Are you sure you wish to delete the following record?</strong>
			   		<div >
						<div class="row pt-2">
							<div class="col-md-3 ml-2 pt-0 text-left">Vendor</div>
						</div>
						<div class="row pt-2" id="displayID" >
							<!--  <div class="col-md-2 ml-0 pt-1 text-right">ID: </div> <div class="col-md-2"> <s:textfield  name="vendVO.vendID" class="form-control"/></div>  -->
							<div class="col-md-2 ml-0 pt-1 text-right">Reference#:</div> <div class="col-md-4"> <s:textfield name="vendVO.refNum" class="form-control"/></div>							
						</div>
						
						<div class="row pt-2">
							<div class="col-md-2 ml-0 pt-1 text-right">Name: </div> <div class="col-md-9"> <s:textfield  name="vendVO.name" class="form-control"/></div>
						</div>
						<div class="row pt-2">
							<div class="col-md-2 ml-0 pt-1 text-right">Contact: </div> <div class="col-md-9"> <s:textfield  name="vendVO.contact" class="form-control"/></div>
						</div>
					</div>
					<div id="delName"></div>
					<hr>
			
				<div>
					<div class="row pt-0">
							<div class="col-md-3 ml-2 pt-0 text-left">Address</div>
					</div>
						<div class="row pt-2">
							<div class="col-md-2 ml-0 pt-1 text-right">Street: </div> <div class="col-md-9"> <s:textfield  name="vendVO.address" class="form-control"/></div>
						</div>
						<div class="row pt-2">
							<div class="col-md-2 ml-0 pt-1 text-right">City: </div> <div class="col-md-8"> <s:textfield  name="vendVO.city" class="form-control"/></div>
						</div>
						<div class="row pt-2">
							<div class="col-md-2 ml-0 pt-1 text-right">State: </div> <div class="col-md-2"> <s:textfield  name="vendVO.state" class="form-control"/></div>
							<div class="col-md-2 ml-0 pt-1 text-right">Zipcode:</div> <div class="col-md-3"> <s:textfield name="vendVO.zip" class="form-control"/></div>							
						</div>
				</div>
				
				<hr>
				
				<div >
					<div class="row pt-0">
							<div class="col-md-3 ml-2 pt-0 text-left">Phone Numbers</div>
					</div>
						<div class="row pt-2">
							<div class="col-md-2 ml-0 pt-1 text-right">Phone: </div> <div class="col-md-3"> <s:textfield  name="vendVO.phone" class="form-control" /></div>
							<div class="col-md-1 ml-0 pt-1 text-right">Ext.:</div> <div class="col-md-2"> <s:textfield name="vendVO.ext" class="form-control"/></div>							
						</div>
						<div class="row pt-2">
							<div class="col-md-2 ml-0 pt-1 text-right">FAX:</div> <div class="col-md-3"> <s:textfield name="vendVO.fax" class="form-control"/></div>							
						</div>
				</div>
					
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
		 		<s:form id="delForm" action="Vendor" name="delForm" method="post" theme="simple" namespace="/">
	 				<s:hidden name="actionName" value="delete"/>
	 				<s:textfield type="hidden" name="vendVO.vendID"/>
				</s:form>
			</div>
		</div>
	</div> 
	
	<div class="modal editVendor" id="editModal" style="" >
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
		 		<div class="modal-header bg-primary">
		      		<h5 id="modalTitle" class="modal-title text-white ">Edit Vendor</h5> <button type="button" class="text-white close" data-dismiss="modal"> <span>×</span> </button>
			   	</div>

		 		<s:form id="form" action="Vendor" name="addForm" method="post" theme="simple" namespace="/">
		 		<s:hidden name="actionName" value="update"/>
		 		<div>
						<s:textfield type="hidden" name="vendVO.vendID"/>
						<div style="display:none" id="noIDError" class="row col-md-11  pl-3">
							<font color="red">ID Required!</font>
						</div>
						<div style="display:none" id="noNameError" class="row col-md-11  pl-3">
							<font color="red">Name Required!</font>
						</div>
						<div style="display:none" id="noStreetError" class="row col-md-11  pl-3">
							<font color="red">Street Required!</font>
						</div>
						<div style="display:none" id="noCityError" class="row col-md-11  pl-3">
							<font color="red">City Required!</font>
						</div>
						<div style="display:none" id="noStateError" class="row col-md-11  pl-3">
							<font color="red">State Required!</font>
						</div>
						<div style="display:none" id="noZipError" class="row col-md-11  pl-3">
							<font color="red">Zip Required!</font>
						</div>
						<div style="display:none" id="nameInUseError" class="row col-md-11  pl-3">
							<font color="red">Name in use! Choose a different name</font>
						</div>
						
					<div >
						<div class="row pt-2">
							<div class="col-md-3 ml-2 pt-0 text-left">Vendor</div>
						</div>
						<div class="row pt-2" id="displayID" >
							<!--  <div class="col-md-2 ml-0 pt-1 text-right">ID: </div> <div class="col-md-2"> <s:textfield  name="vendVO.vendID" class="form-control"/></div>  -->
							<div class="col-md-2 ml-0 pt-1 text-right">Reference#:</div> <div class="col-md-4"> <s:textfield name="vendVO.refNum" class="form-control"/></div>							
						</div>
						
						<div class="row pt-2">
							<div class="col-md-2 ml-0 pt-1 text-right">Name: </div> <div class="col-md-9"> <s:textfield  name="vendVO.name" class="form-control"/></div>
						</div>
						<div class="row pt-2">
							<div class="col-md-2 ml-0 pt-1 text-right">Contact: </div> <div class="col-md-9"> <s:textfield  name="vendVO.contact" class="form-control"/></div>
						</div>
					</div>
				</div>
				
				<hr>
			
				<div>
					<div class="row pt-0">
							<div class="col-md-3 ml-2 pt-0 text-left">Address</div>
					</div>
						<div class="row pt-2">
							<div class="col-md-2 ml-0 pt-1 text-right">Street: </div> <div class="col-md-9"> <s:textfield  name="vendVO.address" class="form-control"/></div>
						</div>
						<div class="row pt-2">
							<div class="col-md-2 ml-0 pt-1 text-right">City: </div> <div class="col-md-8"> <s:textfield  name="vendVO.city" class="form-control"/></div>
						</div>
						<div class="row pt-2">
							<div class="col-md-2 ml-0 pt-1 text-right">State: </div> <div class="col-md-2"> <s:textfield  name="vendVO.state" class="form-control"/></div>
							<div class="col-md-2 ml-0 pt-1 text-right">Zipcode:</div> <div class="col-md-3"> <s:textfield name="vendVO.zip" class="form-control"/></div>							
						</div>
				</div>
				
				<hr>
				
				<div >
					<div class="row pt-0">
							<div class="col-md-3 ml-2 pt-0 text-left">Phone Numbers</div>
					</div>
						<div class="row pt-2">
							<div class="col-md-2 ml-0 pt-1 text-right">Phone: </div> <div class="col-md-3"> <s:textfield  name="vendVO.phone" class="form-control" placeholder="e.g. (555)111-2345"/></div>
							<div class="col-md-1 ml-0 pt-1 text-right">Ext.:</div> <div class="col-md-2"> <s:textfield name="vendVO.ext" class="form-control"/></div>							
						</div>
						<div class="row pt-2">
							<div class="col-md-2 ml-0 pt-1 text-right">FAX:</div> <div class="col-md-3"> <s:textfield name="vendVO.fax" class="form-control"/></div>							
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
</body>

</html>