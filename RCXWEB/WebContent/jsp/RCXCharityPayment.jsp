<!DOCTYPE html>
<%@ page import="org.lacsd.rcx.action.CharityPaymentAction" %>
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
<script src="/rcx/javascript/CharityPayment.js" type="text/javascript"  ></script>
<script src="https://kit.fontawesome.com/1ede97275a.js" crossorigin="anonymous"></script>

</head>





<body class="bodyBackground" onLoad="setup('<s:property value="charityPaymentVO.siteID"/>','<s:property value="charityPaymentVO.charityID"/>')">
	<div class="py-3">
	  <div class="container">
	
	    <div class="row m-0 p-0">
	      <div class="col-md-12">
	      	<div style="display: text; align-items: center; justify-content: center;" class="row highlight p-1" >
		   		<div class="dropdown mb-1 mt-1 col-md-5">		
 				   	<button class="btn btn-secondary" style="width: 350px; text-align: justify; text-align-last: left;" type="button" id="siteDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<div class="row">				   
						    <span class="col-md-11" style="margin-bottom: 0px;" id="SiteLabel">
						    </span>
						    <i style="float: right" class="fa fa-chevron-down fa-lg mt-1" aria-hidden="true"></i>
					    </div>	
				    </button>
			   		<div class="dropdown-menu mb-1 mt-1" aria-labelledby="siteDropdown">
			   			<s:iterator value="siteVO.siteVOs"  status="iter">
			   				<a class="dropdown-item" id="siteDropdown_<s:property value="siteID"/>" onClick="setSite('<s:property value="%{siteID}" />','<s:property value="%{charities[0].charityID}"/>')"><s:property value="name" /></a>	
			   				<s:textfield type="hidden" id="siteID_%{siteID}" value="%{name}"/>
			   			</s:iterator>
			   		</div>
		   		</div>
				<s:iterator value="siteVO.siteVOs"  status="iter1">
					<div class="dropdown mb-1 mt-1 col-md-5" style="display: none;" id="charityDropdownMenu_<s:property value="siteID"/>">
						
						<button class="btn btn-secondary" style="width: 400px;  text-align: justify; text-align-last: left; " type="button" id="siteDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						   	<div class="row">
							   	<span class="col-md-11" style="margin-bottom: 0px; white-space: nowrap; overflow: hidden" id="CharityLabel_<s:property value="siteID"/>"> 
							   		test
						   		</span>
							    <i style="float: right" class="fa fa-chevron-down fa-lg mt-1 ml-1" aria-hidden="true"></i>
						    </div>
					    </button>
					    
				   		<ul class="dropdown-menu mb-1 mt-1" style="height: auto; max-height: 400px; overflow-x: hidden;" >
				   			<s:iterator value="charities"  status="iter2">
				   				<li>
				   					<span class="dropdown-item" id="charityDropdownItem_<s:property value="charityID"/>_<s:property value="siteID"/>" onClick="setCharity('<s:property value="charityID"/>')">
				   						<s:property value="name" />
				   						<s:textfield type="hidden" id="charityID_%{charityID}" value="%{name}"/>
			   						</span>
				   				</li>
				   			</s:iterator>
				   		</ul>
  				   	</div>
	   			</s:iterator>
    			<div  data-toggle="modal" data-target=".addCharity" class="col-md-2 ">
    				<a class="btn btn btnnew -primary btn-primary " onClick="addBtn('<s:property value="siteVO.siteVOs[0].name" />','<s:property value="siteVO.siteVOs[0].siteID" />','<s:property value="siteVO.siteVOs[0].charities[0].charityID" />')" href="#">New Payment&nbsp;
    					<i class="fa fa-fw fa-plus-circle text-white" ></i> 
   					</a>
 				</div>	    			
    		</div>
	        <div class="table-responsive">
	          <table class="table table-bordered">
	            <thead class="thead-light table-primary text-primary">
	              <tr class="table-success" >
	                <th>Pay Date</th>
	                <th>Payment</th>
	                <th>Edit</th>
	                <th>Delete</th>
	              </tr>
	            </thead>
	            <tbody id="CharityPaymentTable">
	            	<s:iterator value="charityPaymentVO.charityPaymentVOs"  status="iter">
	            	<tr class="tableRow" id="<s:property value="siteID"/><s:property value="charityID"/>">
						<td id="<s:property value="siteID"/><s:property value="charityID"/><s:property value="date"/>"><s:property value="date" /></td>
						<td>$<s:property value="payment" /></td>
						<td><i onClick="editBtn('<s:property value="siteID" />','<s:property value="charityID" />','<s:property value="date" />','<s:property value="payment" />')" style="cursor: pointer;" title="Edit Charity" data-toggle="modal" data-target=".editCharity" class="fa btnedit fa-pencil pl-2 fa-lg text-primary"></i></td>
						<td><i onClick="delbtn('<s:property value="charityID" />','<s:property value="siteID" />','<s:property value="date" />')" style="cursor: pointer;"  title= "Delete Charity" data-toggle="modal" data-target=".deleteCharity" class="fa btndel  fa-trash pl-3  text-danger"></i></td>			
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
		 		<s:form id="delForm" action="CharityPayment" name="delForm" method="post" theme="simple" namespace="/">
	 				<s:hidden name="actionName" value="del"/>
	 				<s:textfield type="hidden" id="delCharID" name="charityPaymentVO.charityID"/>
	 				<s:textfield type="hidden" id="delSiteID" name="charityPaymentVO.siteID"/>
	 				<s:textfield type="hidden" id="delDate" name="charityPaymentVO.date"/>
				</s:form>
			</div>
		</div>
	</div> 
	
	<div class="modal editCharity" id="editModal" style="" >
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content">
		 		<div class="modal-header bg-primary">
		      		<h5 id="modalTitle" class="modal-title text-white ">Edit Payment</h5> <button type="button" class="text-white close" data-dismiss="modal"> <span>×</span> </button>
			   	</div>
			   	<s:form id="form" action="CharityPayment" name="addForm" onkeypress="return doEdit()" method="post" theme="simple" namespace="/">
	 				<s:hidden name="actionName" value="add"/>
					
				   	<div class="pl-1 pt-1">
						<div class="row">
							<div class="col-md-4 ml-1 pt-1"><label for="name">Recycle Center: </label></div> <div class="col-md-7"><Span id="RecycleEditLabel"></Span></div>
							<s:textfield type="hidden" id="siteID" name="charityPaymentVO.siteID"/>
						</div>
						<div class="row">
							<div class="col-md-4 ml-1 pt-1"><label for="name">Charity: </label></div> <div class="col-md-7"> <Span id="CharityEditLabel"></Span> </div>
							<s:textfield type="hidden" id="charityID" name="charityPaymentVO.charityID"/>
						</div>
						<div class="row">
							<div class="col-md-4 ml-1 pt-1"><label for="name">Pay Date: </label></div> <div class="col-md-7"><Span id="DateEditLabel"></Span></div>
							<s:textfield type="hidden" id="date" name="charityPaymentVO.date"/>
						</div>
						<div class="row">
							<div class="col-md-4 ml-1 pt-1"><label for="name">Payment: </label></div> <div class="col-md-7"> <s:textfield id="PaymentEditLabel" name="charityPaymentVO.payment" class="form-control" onkeypress="return captureEnter(event)"/> </div>
						</div>
					</div>
					<div class="pb-1 pt-1" style="text-align: center">
						<div style="cursor: pointer;" class="btn btn-primary"  onClick="doEdit()">
							Save Changes
						</div>
					</div>
				</s:form>
			</div>
		</div>
	</div> 
	
	<div class="modal addCharity" id="addModal" style="" >
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
		 		<div class="modal-header bg-primary">
		      		<h5 id="modalTitle" class="modal-title text-white ">New Payment</h5> <button type="button" class="text-white close" data-dismiss="modal"> <span>×</span> </button>
			   	</div>
			   	<s:form id="addForm" action="CharityPayment" name="newForm" onkeypress="return doAdd()" method="post" theme="simple" namespace="/">
	 				<s:hidden name="actionName" value="add"/>
					
				   	<div class="pl-1 pt-1" style="margin-left:auto;margin-right:auto;">
						<div class="row">
							<div class="col-md-2 ml-1 pt-1"><label for="name">Recycle Center: </label></div> 
							<div class="col-md-7">
						   		<div class="dropdown mt-1 pl-0 col-md-5">		
				 				   	<button class="btn btn-secondary" style="width: 451px; text-align: justify; text-align-last: left;" type="button" id="siteDropdownNew" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										<div class="row">				   
										    <span class="col-md-11" style="margin-bottom: 0px;" id="SiteLabelNew">
										    </span>
										    <i style="float: right" class="fa fa-chevron-down fa-lg mt-1" aria-hidden="true"></i>
									    </div>	
								    </button>
							   		<div class="dropdown-menu mb-1 mt-1" aria-labelledby="siteDropdownNew">
							   			<s:iterator value="siteVO.siteVOs"  status="iter">
							   				<a class="dropdown-item" id="siteDropdownNew_<s:property value="siteID"/>" onClick="setSiteNew('<s:property value="%{name}"/>','<s:property value="%{siteID}" />','<s:property value="%{charities[0].charityID}"/>')"><s:property value="name" /></a>	
							   			</s:iterator>
							   		</div>
						   		</div>
					   			<s:textfield type="hidden" id="siteIDNew" name="charityPaymentVO.siteID"/>
		   					</div>
						</div>
						<div class="row">
							<div class="col-md-2 ml-1 pt-1">
								<label for="name">Charity: </label>
							</div> 
							<div class="col-md-7">
								<s:iterator value="siteVO.siteVOs"  status="iter1">
									<div class="dropdown mb-1 mt-1 pl-0 col-md-5" style="display: none;" id="charityDropdownMenuNew_<s:property value="siteID"/>">
										<button class="btn btn-secondary" style="width: 451px;  text-align: justify; text-align-last: left; " type="button" id="charityDropdownNew" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										   	<div class="row">
											   	<span class="col-md-11" style="margin-bottom: 0px; white-space: nowrap; overflow: hidden" id="CharityLabelNew_<s:property value="siteID"/>"><s:property value="siteVO.siteVOs[0].charities[0].name" /></span>
											    <i style="float: right" class="fa fa-chevron-down fa-lg mt-1 ml-1" aria-hidden="true"></i>
										    </div>
									    </button>
									    
								   		<ul class="dropdown-menu mb-1 mt-1" style="height: auto; max-height: 400px; overflow-x: hidden;" >
								   			<s:iterator value="charities"  status="iter2">
								   				<li>
								   					<span class="dropdown-item" id="charityDropdownItemNew_<s:property value="charityID"/>_<s:property value="siteID"/>" onClick="setCharityNew('<s:property value="charityID"/>')">
								   						<s:property value="name" />
							   						</span>
								   				</li>
								   			</s:iterator>
								   		</ul>
				  				   	</div>
					   			</s:iterator>
					   			<s:textfield type="hidden" id="charityIDNew" name="charityPaymentVO.charityID"/>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 ml-1 pt-1">
								<label for="name">Pay Date: </label>
							</div> 
							<div class="col-md-7"> 
								<s:textfield id="DateAddLabel" type="date" class="form-control" placeholder="MM/DD/YYYY" /> 
								<s:textfield type="hidden" id="dateParam" name="charityPaymentVO.date"/>
							</div>
						</div>
						<div class="row mt-1">
							<div class="col-md-2 ml-1 pt-1">
								<label for="name">Payment: </label>
							</div> 
							<div class="col-md-7"> 
								<s:textfield id="PaymentAddLabel" name="charityPaymentVO.payment" class="form-control" onkeypress="return captureEnter(event)"/> 
							</div>
						</div>
						<div style="display:none" id="invalidDate" class="row col-md-11  pl-3">
							<font color="red">Invalid Date! A payment was already for that date</font>
						</div>
						<div style="display:none" id="invalidPayment" class="row col-md-11  pl-3">
							<font color="red">Invalid Payment! <br>Payment must be greater than $0</font>
						</div>
					</div>
					<div class="pb-1 pt-1" style="text-align: center">
						<div style="cursor: pointer;" class="btn btn-primary"  onClick="doSubmit('<s:property value="siteID"/>','<s:property value="charityID"/>','<s:property value="date"/>')">
							Add Payment
						</div>
					</div>
				</s:form>
			</div>
		</div>
	</div> 


	
</body>

</html>