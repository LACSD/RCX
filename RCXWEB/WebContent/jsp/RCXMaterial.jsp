<!DOCTYPE html>
<%@ page import="org.lacsd.rcx.action.MaterialAction" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib uri="/taglibs/lacsd.tld" prefix="lacsd" %> 
<%@ page import="org.lacsd.rcx.action.MaterialAction" %>



<html>
<style>
tr:hover {background-color:#f5f5f5;}
</style>
<s:hidden name="actionName" /> 
<head>
<jsp:include page="../include/RCXNav.jsp" flush="true">
</jsp:include> 

<script src="/rcx/javascript/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="/rcx/javascript/Material.js" type="text/javascript"  ></script>
</head>





<body class="bodyBackground">

<input type="hidden" id="curMatName"/>

	<div class="py-3">
	  <div class="container">
	
	    <div class="row m-0 p-0">
	      <div class="col-md-12">
	      	<div class="row highlight" >
	      		<div class="col-md-10">
	        			<h6 class=""></h6>
	      		</div>
	    			<div  data-toggle="modal" data-target=".editMaterial" class="col-md-2"><a class="btn btn btnnew -primary btn-primary mb-1 mt-2" href="#">New Material&nbsp;<i class="fa fa-fw fa-plus-circle text-white" ></i> </a></div>
	    		</div>
	        <div class="table-responsive">
	          <table class="table table-bordered">
	            <thead class="thead-light table-primary text-primary">
	              <tr class="table-success" >
	                <th>Name</th>
	                <th>Count Value</th>
	                <th>Scrap Value</th>
	                <th>Redemption Value</th>
	                <th>Bonus Value</th>
	                <th>Active</th>
	                <th>Edit</th>
	                <th>Delete</th>
	              </tr>
	            </thead>
	            <tbody>
	            	<s:iterator value="matVO.matVOs"  status="iter">
	            	<tr class="tableRow" id=<s:property value="matID" />>
						<td><s:property value="name" /></td>
						<td><s:property value="hasCountValue" /></td>
						<td><s:property value="hasScrapValue" /></td>
						<td><s:property value="hasRedemptionValue" /></td>
						<td><s:property value="hasBonusValue" /></td>
						<td><s:property value="isActive" /></td>
						<td><i onClick="doEdit('<s:property value="matID" />')" style="cursor: pointer;" title="Edit Material" data-toggle="modal" data-target=".editMaterial" class="fa btnedit fa-pencil pl-2 fa-lg text-primary"></i></td>
						<s:if test="%{inUse == \"1\"}">
							<td><i  title="Delete Unavailible:&#013;Material in use" class="fa  fa-trash pl-3 text-muted"></i></td>		
						</s:if>
						<s:else>
							<td><i style="cursor: pointer;"  title= "Delete Material" data-toggle="modal" data-target=".deleteMaterial" onClick="delbtn(<s:property value="matID" />)" class="fa btndel  fa-trash pl-3  text-danger"></i></td>		
						</s:else>
						<s:hidden  value="%{matID}" id="%{name}" />				
					</tr>
						
	            	</s:iterator>
	            </tbody>
	          </table>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal deleteMaterial" id="delModal" style="" >
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
		 		<div class="modal-header bg-danger">
		      		<h5 class="modal-title text-white ">Delete Material</h5> <button type="button" class="text-white close" data-dismiss="modal"> <span>×</span> </button>
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
		 		<s:form id="delForm" action="Material" name="delForm" method="post" theme="simple" namespace="/">
	 				<s:hidden name="actionName" value="del"/>
	 				<s:textfield type="hidden" name="matVO.matID"/>
				</s:form>
			</div>
		</div>
	</div> 
	
	<div class="modal editMaterial" id="editModal" style="" >
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content">
		 		<div class="modal-header bg-primary">
		      		<h5 id="modalTitle" class="modal-title text-white ">Edit Material</h5> <button type="button" class="text-white close" data-dismiss="modal"> <span>×</span> </button>
			   	</div>

		 		<s:form id="form" action="Material" name="addForm" onkeypress="return doSubmit()" method="post" theme="simple" namespace="/">
	 				<s:hidden name="actionName" value="add"/>
					<s:textfield type="hidden" name="matVO.matID"/>
					<s:textfield type="hidden" name="matVO.hasCountValue"/>
					<s:textfield type="hidden" name="matVO.hasScrapValue"/>
					<s:textfield type="hidden" name="matVO.hasBonusValue"/>
					<s:textfield type="hidden" name="matVO.hasRedemptionValue"/>
					<s:textfield type="hidden" name="matVO.isActive"/>
				   	<div class="pl-1 pt-1">
						<div class="row">
							<div class="col-md-3 ml-1 pt-1"><label for="name">Description: </label></div> <div class="col-md-8"> <s:textfield id="name" name="matVO.name" class="form-control" onkeypress="return captureEnter(event)"/></div>
						</div>
						<div style="display:none" id="noDescError" class="row col-md-11  pl-3">
							<font color="red">Description Required!</font>
						</div>
						<div style="display:none" id="nameInUseError" class="row col-md-11  pl-3">
							<font color="red">Name in use! Choose a different name</font>
						</div>
						<div class="pl-1">
							<div class="row col-md-12">
								<div class="pt-1">
									<s:checkbox  id="acv" name="acv" fieldValue="false"/>
									<label>Accepts Count Value</label>
								</div>
							</div>
							<div class="row col-md-12">
								<div class="pt-1">
									<s:checkbox  id="asv" name="asv" fieldValue="false" />
									<label>Accepts Scrap Value</label>
								</div>
							</div>
							<div class="row col-md-12">
								<div class="pt-1">
									<s:checkbox  id="arv" name="arv" fieldValue="false"/>
									<label>Accepts Redemption Value</label>
								</div>
							</div>
							<div class="row col-md-12">
								<div class="pt-1">
									<s:checkbox  id="abv" name="abv" fieldValue="false" />
									<label>Accepts Bonus Value</label>
								</div>
							</div>
							<div class="row col-md-12">
								<div class="pt-1">
									<s:checkbox  id="ia" onClick="checkIfNewMat()" name="ia" fieldValue="false" />
									<label>Is Active?</label>
								</div>
							</div>
						</div>
					</div>
					<div class="pb-1" style="text-align: center">
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