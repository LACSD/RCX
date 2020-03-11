/**
 * 
 */



function setup(employeeID){
	console.log("setup employeeID: " + employeeID)
	document.getElementById("newCashBoxErr" ).style.display = 'none'
	if (employeeID == -1){
		$('#newCashBox').modal('show')
	} else{
		$('#mainMenu').modal('show')
	}
	
}

function cancelNewCashBox(){
	$('#newCashBox').modal('hide')
	$('#cancelNewCashBox').modal('show')
}

function returnToCashBox(){
	$('#newCashBox').modal('show')
	$('#cancelNewCashBox').modal('hide')
}

function doUpdate(){
	document.getElementById("bankDropform_safeVO_bankDrop").value = parseFloat(document.getElementById("bankDropform_safeVO_bankDrop").value) + parseFloat(document.getElementById("bankDropAmt").value);
	console.log("doUpdate() bankDropTotal: " + document.getElementById("bankDropform_safeVO_bankDrop").value)
	document.getElementById("bankDropform").submit();
}

function doAdd(){
	document.getElementById("addCashform_cashBoxVO_currentBalance").value =  parseFloat(document.getElementById("cashAmt").value);
	console.log("doAdd() addedCash: " + document.getElementById("cashAmt").value)
	document.getElementById("addCashform").submit();
}

function doClose(){
	document.getElementById("closeSafeform_safeVO_closeCount").value =  parseFloat(document.getElementById("closeAmt").value);
	console.log("doClose() closeCount: " + document.getElementById("closeAmt").value)
	document.getElementById("closeSafeform").submit();
}

function doCreateCashBox(){
	if(document.getElementById("newOpeningAmount").value == 0){
		document.getElementById("newCashBoxErr" ).style.display = ''
	} else {
		document.getElementById("newCashBoxErr" ).style.display = 'none'
		document.getElementById("newCashBoxForm").submit();
	}
}



