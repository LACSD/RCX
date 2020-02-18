<!DOCTYPE html>
<%@ page import="org.lacsd.rcx.action.RateAction" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib uri="/taglibs/lacsd.tld" prefix="lacsd" %> 



<html>
<style>
tr{background-color:#f5f5f5;}
</style>

<head>
<jsp:include page="../include/RCXNav.jsp" flush="true">
</jsp:include> 

<script src="/rcx/javascript/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="/rcx/javascript/Rate.js" type="text/javascript"  ></script>

</head>

<script >

	var json = null;
	//Json used to dynamically set material dropdown based on SiteID; used by internal code here
	function getMaterialBySite(siteID) {
		$.ajax({
				'async': false,
				'global': false,
				'url': "/rcx/Common.action?actionName=getMaterial&materialVO.siteID=" + siteID,
				'dataType': "json",
				'success': function (data) {
				json = data.materialVO.matVOs;
			}
		});
	}
 
 
	//Used to get material dropdown based on SiteID; Used by Site Dropdown on page
	function getMaterialBySiteRefresh(siteID) {
		document.getElementById("retrieveForm").action = "Rate.action";
		document.getElementById("tableRetrieve").value = "<%=RateAction.RETRIEVE%>";
		document.getElementById("retrieveForm").submit();
	}

	//Used to generate Material Dropdown based on selected ID; used by material dropdown in edit modal and main page
	function generateMaterialDropdown(listID) {
		if (json == null) {
			setTimeout(generateMaterialDropdown, 100);//delay so page does not refresh before data has been loaded
		} else {
			var selectedValue = '<s:property value="rateVO.materialID"/>';
			var str = '<option value=""></option>';
			for (var i = 0; i< json.length; i++) {
				str += '<option value="' + json[i].matID+ '" '
				if (json[i].matID == selectedValue) {
					str += ' selected ';
				}
				str += '>' +json[i].name + '</option>';
		}
			document.getElementById(listID).innerHTML = str;
		}
	}
	
	$(function () {
		getMaterialBySite(<s:property value="%{rateVO.siteID}"/>); //pass site dynamically based on siteVO.siteID
		generateMaterialDropdown("materialList");
	});
  
	function generateMaterialForEdit(siteID) {
		getMaterialBySite(siteID);
		generateMaterialDropdown("editMaterialList"); //material dropdown in edit modal changes dynamically with site dropdown
	}
  
  	//clears all rate fields; called inside checkMaterial()
	function clearRateFields(){
		document.all["rateVO.contPerLb"].value="";
		document.all["rateVO.largeCountRate"].value="";
		document.all["rateVO.smallCountRate"].value="";
		document.all["rateVO.scrapRate"].value="";
		document.all["rateVO.redemRate"].value="";
		document.all["rateVO.bonusRate"].value="";
	}
   
   //disables all rate fields in new modal; called when empty option for material is selected
	function disableRateFields(){
   		document.all["rateVO.contPerLb"].disabled=true;
   		document.all["rateVO.largeCountRate"].disabled=true;
		document.all["rateVO.smallCountRate"].disabled=true;
		document.all["rateVO.scrapRate"].disabled=true;
		document.all["rateVO.redemRate"].disabled=true;
		document.all["rateVO.bonusRate"].disabled=true;
	}
   
   
   //enables all rate fields, used to clear any lingering disabled fields
	function enableRateFields(){
   		document.all["rateVO.contPerLb"].disabled=false;
   		document.all["rateVO.largeCountRate"].disabled=false;
		document.all["rateVO.smallCountRate"].disabled=false;
		document.all["rateVO.scrapRate"].disabled=false;
		document.all["rateVO.redemRate"].disabled=false;
		document.all["rateVO.bonusRate"].disabled=false;
	}
   
   
  //Checks material and disables fields based on Material Properties (hasCount, hasBonus, etc.)
	function checkMaterial(materialID) {
	  	console.log("materialID: " + materialID);
	  	clearRateFields()
	  	//if empty option is selected for Material, end function to not check properties of null material
	  	if(materialID==""){
	  		disableRateFields();
	  		return;
	  	}
		var matObj;
		for (var i = 0; i< json.length; i++) {
		   	if (materialID == json[i].matID) {
		   	matObj = json[i];
		   	break;
		   	}
		}

		enableRateFields();
		if (matObj.hasCountValue == 'N'){
			document.all["rateVO.largeCountRate"].value="";
			document.all["rateVO.smallCountRate"].value="";
			document.all["rateVO.largeCountRate"].disabled=true;
			document.all["rateVO.smallCountRate"].disabled=true;
		}
		else{
			document.all["rateVO.largeCountRate"].disabled=false;
			document.all["rateVO.smallCountRate"].disabled=false;
		}
		
		if (matObj.hasScrapValue == 'N'){
			document.all["rateVO.scrapRate"].value="";
			document.all["rateVO.scrapRate"].disabled=true;
		}
		else{
			document.all["rateVO.scrapRate"].disabled=false;
		}
		
		if (matObj.hasRedemptionValue == 'N'){
			document.all["rateVO.redemRate"].value="";
			document.all["rateVO.redemRate"].disabled=true;
		}
		else{
			document.all["rateVO.redemRate"].disabled=false;
		}
		
		if (matObj.hasBonusValue == 'N'){
			document.all["rateVO.bonusRate"].value="";
			document.all["rateVO.bonusRate"].disabled=true;
		}
		else{
			document.all["rateVO.bonusRate"].disabled=false;
		}
	}

	
	//Used so fields with this function only allow numbers and a single decimal point to be entered
	function fieldNumberValidate(field){
		var length = field.value.length;
		if(field.value.length > 0){
			if(".".indexOf(String(field.value.substring(length-1))) != -1) //last character entered is "."
				for(var i=0; i<=length-2; i++)
					if(String(field.value.charAt(i)) == ".")
						field.value = field.value.substring(0,length-1);
			if("1234567890.".indexOf(String(field.value.substring(length-1))) == -1) //last character entered not a number
				field.value = field.value.substring(0,length-1);
		}
		
		
	}
	
	//Value of rateVO.siteID given value of Site dropdown value selected; used in edit Modal
	$('input[name="rateVO.siteID"]').val(
		$("#editSiteList option:selected").val()
	);
	

  
</script>



<body class="bodyBackground">
	<div class="py-3">
	  <div class="container-fluid">
	
	    <div class="row m-0 p-0">
	    
	    	<!-- Dropdown and Date selection -->

	      <div class="table-responsive">
	      
	      <s:set var="theme" value="'simple'" scope="page" /> <!-- prevents added labels from messing up formatting -->
	      <s:form  id="retrieveForm" name = "retrieveForm" method="post" >
	      <s:hidden name="actionName" id="tableRetrieve"/>

	          
	          <table class="table" style="width:80%;margin-left:auto;margin-right:auto; margin-bottom: 0px">

				<tr>
					<td>


				            
				            
				             
				              
				                <th>Recycle Center: 
					                <s:select   name="rateVO.siteID" id="siteIDList" list="siteVO.siteVOs" listKey="siteID" listValue="siteName" required="true" onchange="getMaterialBySiteRefresh(this.value);"/>
					            </th>
				                
				                <th>Material: 
				                	<select name="rateVO.materialID" id="materialList">
				              		 </select>
				                </th>
				                
				                <th>Effective Date 
				                <!-- TEMPORARY DEFAULT VALUE -->
				                	<s:textfield  type="date" name="rateVO.date" /> <!-- struts tag for date field -->
				                </th>
				                
				              <th>
				              
				              <input type="button" class="btn btn btnnew -primary btn-primary mb-1 mt-2" value="Search" id="btnSearch" onClick="doRetrieve()"/></th>
			              

				<tr>
					
		            <td align="right" style="padding:5px" colspan="12">
		            <a data-toggle="modal" data-target=".editRate" class="btn btn btnnew -primary btn-primary mb-0 mt-0" href="#" onclick="doNew()">Add Rate&nbsp;<i class="fa fa-fw fa-plus-circle text-white" ></i> </a>
		            </td>
		            
		        </tr>

	            <tbody>
	            </tbody>
	            </table>
	        
	      

	    
	      
	    	
	    	
		 	
			<table class="table" style="width:80%;margin:auto;">


			<s:if test="rateVO.rateVOs.size() >0">
			<tr>
			<td style="padding: 0px;"><br/>
				<lacsd:PagingTable pagingProperty="pagingVO" formName="retrieveForm" property="rateVO.rateVOs" navPos="bottom" tableStyle="pagingTable" navStyle="pagingNav" printOption="true" pageCssURL="/lfg/css/LFGStyle.css?v=1.1">
					<lacsd:PagingRow headerStyle="pagingHeader" evenRowStyle="pagingEvenRow" oddRowStyle="pagingOddRow">
					    <lacsd:PagingBoundColumn property="matName" title="Material" sortable="true"/>
					    <lacsd:PagingBoundColumn property="date" title="Effective Date" sortable="false" />
					    <lacsd:PagingBoundColumn property="contPerLb" title="Containers Per Pound" />
					    <lacsd:PagingBoundColumn property="scrapRate" title="Scrap Rate" sortable="false" dateFormat="false"/>
						<lacsd:PagingBoundColumn property="redemRate" title="Redemption Rate"/>
						<lacsd:PagingBoundColumn property="bonusRate" title="Bonus Rate" sortable="false"/>
						<lacsd:PagingBoundColumn property="smallCountRate" title="Small Count Rate" sortable="false"/>
						<lacsd:PagingBoundColumn property="largeCountRate" title="Large Count Rate" sortable="false"/>
						<lacsd:PagingColumn title="Edit" align="center">
							<i onClick="doEdit('<lacsd:PagingProperty value="siteName" />', 
												'<lacsd:PagingProperty value="matName" />',
												'<lacsd:PagingProperty value="date" />',
												'<lacsd:PagingProperty value="contPerLb" />',
												'<lacsd:PagingProperty value="scrapRate" />',
												'<lacsd:PagingProperty value="redemRate" />',
												'<lacsd:PagingProperty value="bonusRate" />',
												'<lacsd:PagingProperty value="smallCountRate" />',
												'<lacsd:PagingProperty value="largeCountRate" />',
												
												'<lacsd:PagingProperty value="hasScrap" />',
												'<lacsd:PagingProperty value="hasRedemption" />',
												'<lacsd:PagingProperty value="hasBonus" />',
												'<lacsd:PagingProperty value="hasCount" />')"  
											style="cursor: pointer;" 
											title="Edit Rate" data-toggle="modal" data-target=".editRate" class="fa btnedit fa-pencil pl-0 fa-lg text-primary"></i>
						</lacsd:PagingColumn>
						<lacsd:PagingColumn title="Delete" align="center">
							<i onClick="deleteBtn('<lacsd:PagingProperty value="siteName" />', 
												'<lacsd:PagingProperty value="matName" />',
												'<lacsd:PagingProperty value="date" />')"
											style="cursor: pointer;" 
											title= "Delete Rate" data-toggle="modal" data-target=".deleteRate"  class="fa btndel  fa-trash pl-0  text-danger"></i>
						</lacsd:PagingColumn>
					</lacsd:PagingRow>
				</lacsd:PagingTable>
			</td>
			</tr>
			</s:if>
			
			<s:elseif test="rateVO.materialID == -9999">
				<div class="col-md-10" id="tableDisplay" style="">
				<table class="table" style="width:50%;margin:auto;">
				<tr class="table-danger" align="center"><td style="font-size:20px"><b>Record already exists for that material, site, and date combination!</b></td></tr>
				</table>
				</div>
			</s:elseif>
			
			
			<s:else>
			
				
				<div class="col-md-10" id="tableDisplay" style="">
				<table class="table" style="width:50%;margin:auto;">
				<tr class="table-danger" align="center"><td style="font-size:20px"><b>No Records Found!</b></td></tr>
				</table>
				</div>
			</s:else>
<!--  </table>-->
	          
	          
	          

	        </table>
	        </s:form>
	        </div>
	      </div>
	    </div>
	  </div>
	  
	<!--</div>-->
	
		<div class="modal editRate" id="editModal" style="" >
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
		 		<div class="modal-header bg-primary">
		      		<h5 id="modalTitle" class="modal-title text-white ">Edit Rate</h5> <button type="button" class="text-white close" data-dismiss="modal"> <span>×</span> </button>
			   	</div>

		 		<s:form id="form" action="Rate" name="addRate" method="post" theme="simple" namespace="/">
		 		<s:hidden name="actionName" id="addRate"/>
		 		<div>
						<div style="display:none" id="noMaterialError" class="row col-md-11  pl-3">
							<font color="red">Material Required!</font>
						</div>
						
						<div style="display:none" id="noDateError" class="row col-md-11  pl-3">
							<font color="red">Date Required!</font>
						</div>
						
						<div style="display:none" id="containersError" class="row col-md-11  pl-3">
							<font color="red">Containers/Pound value out of range! Please enter a value between 0 and 999.99!</font>
						</div>
						
						<div style="display:none" id="scrapError" class="row col-md-11  pl-3">
							<font color="red">Scrap Rate value out of range! Please enter a value between 0 and 9.9999!</font>
						</div>
						
						<div style="display:none" id="redemptionError" class="row col-md-11  pl-3">
							<font color="red">Redemption Rate value out of range! Please enter a value between 0 and 9.9999!</font>
						</div>
						
						<div style="display:none" id="bonusError" class="row col-md-11  pl-3">
							<font color="red">Bonus Rate value out of range! Please enter a value between 0 and 9.9999!</font>
						</div>
						
						<div style="display:none" id="smallCountError" class="row col-md-11  pl-3">
							<font color="red">Small Material Count Rate value out of range! Please enter a value between 0 and 9.9999!</font>
						</div>
						
						<div style="display:none" id="largeCountError" class="row col-md-11  pl-3">
							<font color="red">Large Material Count Rate value out of range! Please enter a value between 0 and 9.9999!</font>
						</div>
						

						
					<div >
						<div class="row pt-2" id="displayID" >
							<div class="col-md-3 ml-0 pt-1 text-right">Recycle Center: </div> <div class="col-md-6"> 
							<s:select   name="siteID" id="editSiteList" list="siteVO.siteVOs" listKey="siteID" listValue="siteName"  onchange="generateMaterialForEdit(this.value);" value="{rateVO.siteID}"/>							
							<s:hidden name="rateVO.siteID" id="createSiteID"/>
							<s:textfield style="background:#e6e6e6" name="rateVO.siteName" theme="simple" id="editSiteListTextfield" readonly="true" size="30"></s:textfield></div>
						</div>
						
						<div class="row pt-2">
							<div class="col-md-3 ml-0 pt-1 text-right">Material: </div> <div class="col-md-6"> 
							<select name="rateVO.materialID" id="editMaterialList"  onchange="checkMaterial(this.value); turnOffErrors()" ></select>
							<s:textfield style="background:#e6e6e6" name="rateVO.matName" theme="simple" id="editMaterialListTextfield" readonly="true" size="30"></s:textfield></div>
						</div>
						<div class="row pt-2">
							<div class="col-md-3 ml-0 pt-1 text-right">Effective Date: </div> <div class="col-md-3"> 
							<s:textfield  type="date" name="date" id="editDate" disabled="" readonly="" />
							<s:textfield style="background:#e6e6e6" name="rateVO.date" theme="simple" id="editDateTextfield" readonly="true" size="10"></s:textfield></div>
						</div>
					</div>
				</div>
				
				<hr>
			
				<div>
					<div class="row pt-0">
							<div class="col-md-3 ml-2 pt-0 text-left">Rates</div>
					</div>
						<div class="row pt-2">
							<div class="col-md-5 ml-0 pt-1 text-right">Containers/Pound: </div> <div class="col-md-4"> <s:textfield  maxlength="6" min="0" max="999.99" step=".01"  name="rateVO.contPerLb" onKeyDown="fieldNumberValidate(this)" onKeyUp="fieldNumberValidate(this)" class="form-control"/></div>
						</div>
						<div class="row pt-2">
							<div class="col-md-5 ml-0 pt-1 text-right">Scrap Rate: </div> <div class="col-md-4"> <s:textfield maxlength="6" min="0" max="9.9999" step=".0001" name="rateVO.scrapRate" onKeyDown="fieldNumberValidate(this)" onKeyUp="fieldNumberValidate(this)" class="form-control"/></div>
						</div>
						<div class="row pt-2">
							<div class="col-md-5 ml-0 pt-1 text-right">Redemption Rate: </div> <div class="col-md-4"> <s:textfield maxlength="6" min="0" max="9.9999" step=".0001" name="rateVO.redemRate" onKeyDown="fieldNumberValidate(this)" onKeyUp="fieldNumberValidate(this)" class="form-control"/></div>
						</div>
						<div class="row pt-2">
							<div class="col-md-5 ml-0 pt-1 text-right">Bonus Rate: </div> <div class="col-md-4"> <s:textfield maxlength="6" min="0" max="9.9999" step=".0001" name="rateVO.bonusRate" onKeyDown="fieldNumberValidate(this)" onKeyUp="fieldNumberValidate(this)" class="form-control"/></div>
						</div>
						<div class="row pt-2">
							<div class="col-md-5 ml-0 pt-1 text-right">Small Material Count Rate: </div> <div class="col-md-4"> <s:textfield maxlength="6" min="0" max="9.9999" step=".0001" name="rateVO.smallCountRate" onKeyDown="fieldNumberValidate(this)" onKeyUp="fieldNumberValidate(this)" class="form-control"/></div>
						</div>
						<div class="row pt-2">
							<div class="col-md-5 ml-0 pt-1 text-right">Large Material Count Rate: </div> <div class="col-md-4"> <s:textfield maxlength="6" min="0" max="9.9999" step=".0001" name="rateVO.largeCountRate" onKeyDown="fieldNumberValidate(this)" onKeyUp="fieldNumberValidate(this)" class="form-control"/></div>
						</div>

				</div>
				
				<hr>

					<div class="pb-1 pt-1" style="text-align: center">
						<div style="cursor: pointer;" class="btn btn-primary"  onClick="doSubmit()">
							Save Changes
						</div>
					</div>
				</s:form> 	
			</div>
		</div>
	</div> 
	
	
	
	
	<div class="modal deleteRate" id="delModal" style="" >
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content">
				<s:form id="delForm" action="Rate" name="delForm" method="post" theme="simple" namespace="/">
				<s:hidden name="actionName" value="delete"/>
				<s:hidden name="rateVO.siteID" id="deleteSiteID"/>
		 		
		 		<div class="modal-header bg-danger">
		      		<h5 class="modal-title text-white ">Delete Rate</h5> <button type="button" class="text-white close" data-dismiss="modal"> <span>×</span> </button>
			   	</div>
			   	<div class="p-2">
			   		<strong>Are you sure you wish to delete the following record?</strong>
			   		<div >
						<div class="row pt-2" id="" >
							<div class="col-md-4 ml-0 pt-1 text-right">Recycle Center:</div> <div class="col-md-8"> 
							<s:textfield style="background:#e6e6e6" name="rateVO.siteName" theme="simple" id="deleteSiteListTextfield" readonly="true" size="30"></s:textfield></div>						
						</div>
						
						<div class="row pt-2">
							<div class="col-md-4 ml-0 pt-1 text-right">Material: </div> <div class="col-md-8"> 
							<s:textfield style="background:#e6e6e6" name="rateVO.matName" theme="simple" id="deleteMaterialListTextfield" readonly="true" size="30"></s:textfield></div>
						</div>
						<div class="row pt-2">
							<div class="col-md-4 ml-0 pt-1 text-right">Effective Date: </div> <div class="col-md-8"> 
							<s:textfield style="background:#e6e6e6" name="rateVO.date" theme="simple" id="deleteDateTextfield" readonly="true" size="10"></s:textfield></div>
						</div>
					</div>
					<div id=""></div>
					<hr>
			
				
					
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