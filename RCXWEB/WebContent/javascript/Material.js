/**
 * 
 */

function doEdit(curMatId)  {

	var row = document.getElementById(curMatId);
	var cols = row.getElementsByTagName("td");


        var name = cols[0].innerText;
        var acv  = cols[1].innerText;
        var asv  = cols[2].innerText;
        var arv  = cols[3].innerText;
        var abv  = cols[4].innerText;
        var ia 	 = cols[5].innerText;
        
        
        
        
        //assign to value for input box inside modal
        $("#name").val(name);
        
        document.getElementById("form_matVO_matID").value = curMatId;
    	
        if(acv=="Y"){
            document.getElementById("acv").checked = true;
        } else{
            document.getElementById("acv").checked = false;

        }
        if(asv=="Y"){
            document.getElementById("asv").checked = true;
        } else {
            document.getElementById("asv").checked = false;
        }
        if(arv=="Y"){
            document.getElementById("arv").checked = true;
        } else {
            document.getElementById("arv").checked = false;
        }
        if(abv=="Y"){
            document.getElementById("abv").checked = true;
        } else {
            document.getElementById("abv").checked = false;
        }
        if(ia=="Y"){
            document.getElementById("ia").checked = true;
        } else {
            document.getElementById("ia").checked = false;
        }
        document.getElementById("modalTitle").innerText = "Edit Material";



}


$(function () {
    $(".btnnew").click(function () {
    
    $("#name").val("");
    
    document.getElementById("form_matVO_matID").value = 0;

	document.getElementById("acv").checked = false;
	
	document.getElementById("asv").checked = false;
	
	document.getElementById("arv").checked = false;
	
	document.getElementById("abv").checked = false;
	
	document.getElementById("ia").checked = true;

    document.getElementById("modalTitle").innerText = "New Material";

    })
})

function delbtn(curMatId)  {

	var row = document.getElementById(curMatId);
	var cols = row.getElementsByTagName("td");

    var name = cols[0].innerText;
    
    document.getElementById("delForm_matVO_matID").value = curMatId;
	
    document.getElementById("delName").innerText = name;




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
	
		
	if(document.all["matVO.name"].value == ""){
		nameInUseError.style.display = "none";

		noDescError.style.display = "block";
		return;
	} else{
		noDescError.style.display = "none";
		
	}

	if(document.getElementById(curRec) != null && document.getElementById("form_matVO_matID").value != document.getElementById(curRec).value){
		nameInUseError.style.display = "block";
		return;
	} else{
		nameInUseError.style.display = "none";

	}

	

		
	if(document.getElementById("acv").checked) {
		document.all["matVO.hasCountValue"].value = "Y"
	} else{
		document.all["matVO.hasCountValue"].value = "N"
	}
	
	if(document.getElementById("asv").checked) {
		document.all["matVO.hasScrapValue"].value = "Y"
	} else{
		document.all["matVO.hasScrapValue"].value = "N"
	}
	
	if(document.getElementById("abv").checked) {
		document.all["matVO.hasBonusValue"].value = "Y"
	} else{
		document.all["matVO.hasBonusValue"].value = "N"
	}
	
	if(document.getElementById("arv").checked) {
		document.all["matVO.hasRedemptionValue"].value = "Y"
	} else{
		document.all["matVO.hasRedemptionValue"].value = "N"
	}
	
	if(document.getElementById("ia").checked) {
		document.all["matVO.isActive"].value = "Y"
	} else{
		document.all["matVO.isActive"].value = "N"
	} 
	document.getElementById("form").submit();
	//$('#editModal').modal('hide');
}

function checkIfNewMat(){
	if (document.getElementById("form_matVO_matID").value == 0 ){
		document.getElementById("ia").checked = true;
	}
}
