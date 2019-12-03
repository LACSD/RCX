<!DOCTYPE html>
<%@ page import="org.lacsd.rcx.action.ContainerAction" %>
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
<script src="/rcx/javascript/Container.js" type="text/javascript"  ></script>
</head>





<body class="bodyBackground">

<!--  <input type="hidden" id="curMatName"/> -->

	<div class="py-3">
	  <div class="container">
	
	    <div class="row m-0 p-0">
	      <div class="col-md-12">
	      	<div class="row highlight" style="padding: 10px;">
	      		<div class="col-md-9">
	        			<h6 class=""></h6>
	      		</div>
	    			<div align="right" class="col-md-3"><a data-toggle="modal" data-target=".editContainer" class="btn btn btnnew -primary btn-primary mb-0 mt-0" href="#" onclick="doNew()">New Container Type&nbsp;<i class="fa fa-fw fa-plus-circle text-white" ></i> </a></div>
	    		</div>
	    		
	    	<s:if test="containerVO.containerID == -9999">
	    		<script>
	    		alert('Record already exists with that name!');
	    		</script>
	    		<!-- 
				<div class="col-md-12" id="tableDisplay" style="padding:0px">
				<table class="table" style="width:100%;margin:auto;">
				<tr class="table-danger" align="center"><td style="font-size:20px"><b>Record already exists with that name!</b></td></tr>
				</table>
				</div>	    
				 -->    
	        </s:if>
	        

				<div class="table-responsive">
	          	<table class="table table-bordered">
	            <thead class="thead-light table-primary text-primary">
	              <tr class="table-success" >
	                <th style="text-align: center">Container</th>
	                <th>Edit</th>
	                <th>Delete</th>
	              </tr>
	            </thead>
	           	<tbody>
	            	<s:iterator value="ContainerVO.containerVOs"  status="iter">
	            	<tr class="tableRow" id=<s:property value="containerID" />>
						<td><s:property value="name" /></td>

						<td>
							<i onClick="doEdit('<s:property value="containerID" />',
												'<s:property value="name" />')" 
								style="cursor: pointer;" 
								title="Edit Container" data-toggle="modal" data-target=".editContainer" class="fa btnedit fa-pencil pl-2 fa-lg text-primary"></i></td>

						<td>
							<i style="cursor: pointer;"  title= "Delete" data-toggle="modal" data-target=".deleteContainer" 
								onClick="deleteBtn('<s:property value="containerID" />',
													'<s:property value="name" />')"
								 class="fa btndel  fa-trash pl-3  text-danger"></i></td>
					</tr>
						
	            	</s:iterator>
	            </tbody>
	          </table>
	        </div>

	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal editContainer" id="editModal" style="" >
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content">
		 		<div class="modal-header bg-primary">
		      		<h5 id="modalTitle" class="modal-title text-white ">Edit Container</h5> <button type="button" class="text-white close" data-dismiss="modal"> <span>×</span> </button>
			   	</div>

		 		<s:form id="form" action="Container" name="addContainer" method="post" theme="simple" namespace="/">
		 		<s:hidden name="actionName" id="addContainer"/>
		 		<s:hidden name="containerVO.containerID" id="editContainerID"/>
		 		
		 		
		 			<div style="display:none" id="noNameError" class="row col-md-11  pl-3">
							<font color="red">Container Description Required!</font>
					</div>
		 		<div class="row pt-2">
				<div class="col-md-2 ml-4 pt-1 text-right">Description: </div> <div class="col-md-8"> <s:textfield maxlength="30" name="containerVO.name" class="form-control"/> </div>
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
	
	
	<div class="modal deleteContainer" id="delModal" style="" >
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content">
				<s:form id="delForm" action="Container" name="delForm" method="post" theme="simple" namespace="/">
				<s:hidden name="actionName" value="delete" id="deleteActionName"/>
				<s:hidden name="containerVO.containerID" id="deleteContainerID"/>
		 		
		 		<div class="modal-header bg-danger">
		      		<h5 class="modal-title text-white ">Delete Container Type</h5> <button type="button" class="text-white close" data-dismiss="modal"> <span>×</span> </button>
			   	</div>
			   	<div class="p-2">
			   		<strong>Are you sure you wish to delete the following container type?</strong>
			   		<div>
						<div class="row pt-2">
							<div class="col-md-2 ml-4 pt-1 text-right">Description: </div> <div class="col-md-8"> 
							<s:textfield id="deleteContainerName" maxlength="30" name="containerVO.name" class="form-control" readonly="true"/> </div>
						</div>
					</div>
					<div id=""></div>
			
				
					
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
		 		
				</s:form>
			</div>
		</div>
	</div> 
	

</body>

</html>