/**
 * 
 */

var activeChar = "";
var activeSite = "";
var activeSiteChar = "";

function doEdit(curCharityId)  {
    console.log(curCharityId);
	var row = document.getElementById(curCharityId);
	var cols = row.getElementsByTagName("td");


    var name = cols[0].innerText;

    //assign to value for input box inside modal
    $("#name").val(name);
    
    document.getElementById("form_charityVO_charityID").value = curCharityId;
	
    document.getElementById("modalTitle").innerText = "Edit Charity";



}


function markRowActive(row){
	if(row.id.substring(0,4) == 'aval'){
		$(row).addClass('bg-primary text-white').siblings().removeClass('bg-primary text-white');
		activeChar = row;
	}
	if (row.id.substring(0,4) == 'site'){
		$(row).addClass('bg-danger text-white').siblings().removeClass('bg-danger text-white');
		activeSiteChar = row;
	}
}

function addCharity(){
	$(activeChar).removeClass('bg-danger text-white')
	var temp = activeChar.cloneNode(true);
	$(temp).removeClass('bg-primary text-white')
	temp.id = "siteChar" + activeChar.id.substr(8)
	if(activeChar != ""){
		$('#' + activeSite + ' > tbody:first-child').prepend(temp);
		
	}
	
	setSite(activeSite.substr(5));
}

function removeCharity(){
	$(activeSiteChar).removeClass('bg-secondary text-white')
	if(activeSiteChar != ""){
		$(activeSiteChar).remove();
		
	}
	setSite(activeSite.substr(5));
}

function setup(){
	setSite(0);
}


$(function () {
    $(".btnnew").click(function () {
    
    $("#name").val("");
    
    document.getElementById("form_charityVO_charityID").value = 0;


    document.getElementById("modalTitle").innerText = "New Charity";

    })
})

function delbtn(curCharityId)  {

	var row = document.getElementById(curCharityId);
	var cols = row.getElementsByTagName("td");

    var name = cols[0].innerText;
    
    document.getElementById("delForm_charityVO_charityID").value = curCharityId;
	
    document.getElementById("delName").innerText = name;

}

function doAssign()  {

	var siteCharList =  document.getElementById("avalCharities_"+activeSite);
	var siteChildList = siteCharList.getElementsByTagName("tr");
	var charityListString = "";
	for (j = 0; j < siteChildList.length; j++){
		console.log((siteChildList[j]).id.substr(9))
		charityListString += ((siteChildList[j]).id.substr(9) + ',')
	}
	console.log(charityListString);
	document.getElementById("site").value = document.getElementById("siteID_"+ activeSite.substr(5)).value;
	document.getElementById("charities").value = charityListString;
	document.getElementById("assignForm").submit();

}

function setSite(idx){
	activeSite = "site_" + idx;
	document.getElementById("siteDropdown").innerText = document.getElementById("siteDropdown_" + idx).innerText;
	
	//create list of site tables
	var list = document.getElementById("sites");
	var childDivs = list.getElementsByTagName('table');
	
	//create list of available charities
	var charList = document.getElementById("avalCharities");
	var charChildElem = charList.getElementsByTagName("tr");
	
	//set all available charities to display
	for( i=0; i< charChildElem.length; i++ ){
			charChildElem[i].style.display = '';
		
	}
	
	//create list for active site charities 
	var siteCharList =  document.getElementById("avalCharities_site_"+idx);
	var siteChildList = siteCharList.getElementsByTagName("tr");
	
	//show only active site table
	for( i=0; i< childDivs.length; i++ ){
		if(childDivs[i].id != "site_" + idx){
				childDivs[i].style.display = 'none'
			
		} else{
			childDivs[i].style.display = ''
		}
	}
	
	//hide all charities currently assigned to active site
	for (j = 0; j < siteChildList.length; j++){
		$(siteChildList[j]).removeClass('bg-secondary text-white')
		if(siteChildList[j].id == "siteChar_0"){
			$(siteChildList[j]).remove();
		}
		for( i=0; i< charChildElem.length; i++ ){
			if(siteChildList[j].id.substr(8) == charChildElem[i].id.substr(8)){
				charChildElem[i].style.display = 'none';
			} 
		}
	}
}



function doDelete() {
	document.getElementById("delForm").submit();
	$('#editModal').modal('hide');
}


function captureEnter(event){
    if (event.which == 13 || event.keyCode == 13) {
        doSubmit();
        return false;
    }
	return true
}

function doSubmit() {
	var noDescError = document.getElementById("noDescError");
	var nameInUseError = document.getElementById("nameInUseError");
	noDescError.style.display = "none";
	nameInUseError.style.display = "none";

	
	
	var curRec = document.getElementById("name").value
	
		
	if(document.all["charityVO.name"].value == ""){

		noDescError.style.display = "block";
		return;
	} else{
		noDescError.style.display = "none";
		
	}

	if(document.getElementById(curRec) != null && document.getElementById("form_charityVO_charityID").value != document.getElementById(curRec).value){
		nameInUseError.style.display = "block";
		return;
	} else{
		nameInUseError.style.display = "none";

	}

	
	document.getElementById("form").submit();
	//$('#editModal').modal('hide');
}

function checkIfNewCharity(){
	if (document.getElementById("form_charityVO_charityID").value == 0 ){
		document.getElementById("ia").checked = true;
	}
}
