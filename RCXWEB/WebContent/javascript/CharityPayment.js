/**
 * 
 */

var addChar = "";
var addSite = "";
var addDate = "";
var delChar = "";
var delSite = "";
var delDate = "";
var activeChar = ""
var activeSite = "";
var activeSiteName = "";



function editBtn(site, charity, date, payment)  {
	console.log(site);
	console.log(charity);
	console.log(date);
	console.log(payment);
	console.log(document.getElementById("siteID_" + site).value);
	console.log(document.getElementById("charityID_" + charity).value);
	document.getElementById("RecycleEditLabel").innerText = document.getElementById("siteID_" + site).value;
	document.getElementById("CharityEditLabel").innerText = document.getElementById("charityID_" + charity).value;
	document.getElementById("DateEditLabel").innerText = date;
	document.getElementById("siteID").value = site;
	document.getElementById("charityID").value = charity;
	document.getElementById("date").value = date;
	document.getElementById("PaymentEditLabel").value = '$' + payment;
}




function addBtn()  {
	setSiteNew(activeSiteName,activeSite,activeChar)
	$('#DateAddLabel').val(getToday())
	document.getElementById("invalidPayment").style.display = "none";
	document.getElementById("invalidDate").style.display = "none";
}

function setSiteNew(activeSiteName,siteID,charityID){
	document.getElementById("SiteLabelNew").textContent = activeSiteName
	document.getElementById("siteIDNew").value = siteID
	if(addSite != ""){
		document.getElementById("charityDropdownMenuNew_" + addSite).style.display = 'none'
	}
	document.getElementById("charityDropdownMenuNew_" + siteID).style.display = ''
	addSite = siteID;
	setCharityNew(charityID)
}

function setCharityNew(charityID){

	document.getElementById("CharityLabelNew_" + addSite).textContent = document.getElementById("charityDropdownItem_" + charityID + "_" + addSite).textContent;
	document.getElementById("charityIDNew").value = charityID
	addChar = charityID;
}

function delbtn(charityID,siteID,date)  {

	 delChar = charityID;
	 delSite = siteID;
	 delDate = date;
	console.log(delChar + " " + delSite + " " + delDate)

}

function doDelete() {
	document.getElementById("delCharID").value = delChar;
	document.getElementById("delSiteID").value = delSite;
	document.getElementById("delDate").value   = delDate;
	document.getElementById("delForm").submit();
}

function doEdit(){
	if(document.getElementById("PaymentEditLabel").value[0] == '$'){
		document.getElementById("PaymentEditLabel").value = document.getElementById("PaymentEditLabel").value.substr(1);
	}
	document.getElementById("form").submit();
}

function doSubmit() {
	var siteID,charityID,date;
	date = $('#DateAddLabel').val()
	console.log(date);
	if(date != ""){
		date = formatDate(date)
		id=addSite+addChar+date;
	}  else{
		id = ""
	}
	if(date != "" && document.getElementById(id) == null && $('#PaymentAddLabel').val() != 0.0 && new Date($('#DateAddLabel').val()) >= new Date(getToday())){
		console.log("Date: " + date);
		$('#dateParam').val(date);
		console.log($('#dateParam').val());
		document.getElementById("addForm").submit();
	} else{
		document.getElementById("invalidPayment").style.display = "none";
		
	
		if($('#PaymentAddLabel').val() == 0.0){
			document.getElementById("invalidPayment").style.display = "block";
		}
		document.getElementById("invalidDate").style.display = "block";
		if(date == ""){
			console.log("case 1")
			document.getElementById("invalidDate").innerHTML = '<font color="red">Invalid Date: <br>A date is Required</br></font>'
		} else if (new Date($('#DateAddLabel').val()) < new Date(getToday())){
			console.log("case 2")
			document.getElementById("invalidDate").innerHTML = '<font color="red">Invalid Date: ' + date + '<br>Please enter a date on or after ' + formatDate(getToday()) + '</br></font>'
		} else if (document.getElementById(id) != null) {
			console.log("case 3")
			document.getElementById("invalidDate").innerHTML = '<font color="red">Invalid Date! <br>A payment already exists for that date</br></font>'
		}
		else{
			document.getElementById("invalidDate").style.display = "none";
		}
		
	}
	
	//$('#editModal').modal('hide');
}

function setup(siteID,charityID){
	console.log(siteID + " " + charityID)
	setSite(siteID,charityID);
}

function setSite(siteID,charityID){
	console.log("setSite(): "  + siteID + " " + charityID);
	if(activeSite != ""){
		document.getElementById("charityDropdownMenu_" + activeSite).style.display = 'none'
	}
	document.getElementById("SiteLabel").textContent = "Recycle Center: " + document.getElementById("siteDropdown_" + siteID).textContent;
	activeSiteName = document.getElementById("siteDropdown_" + siteID).textContent;
	document.getElementById("charityDropdownMenu_" + siteID).style.display = ''
	activeSite = siteID;
	setCharity(charityID)
}


function setCharity(charityID){
	console.log(activeSite + " Charity: " + "charityDropdownItem_" + charityID + "_" + activeSite)
	document.getElementById("CharityLabel_" + activeSite).textContent = "Charity: " + document.getElementById("charityDropdownItem_" + charityID + "_" + activeSite).textContent;
	var list = document.getElementById("CharityPaymentTable");
	var childDivs = list.getElementsByTagName('tr');
	for( i=0; i< childDivs.length; i++ ){
		if(childDivs[i].id != activeSite + "" + charityID){
				childDivs[i].style.display = 'none'
			
		} else{
			childDivs[i].style.display = ''
		}
	}
	activeChar = charityID;
}

function getToday(){
	var today = new Date();
	var dd = String(today.getDate()).padStart(2, '0');
	var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
	var yyyy = today.getFullYear();

	today = yyyy + '-' + mm + '-' + dd;
	return today;
}

function formatDate(date){
	dates = date.split('-')
	date = dates[1]+ '/' + dates[2] + '/' + dates[0]
	return date
}

