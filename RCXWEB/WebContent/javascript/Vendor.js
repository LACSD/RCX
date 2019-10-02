/**
 * Fills in appropriate fields of modal with the correct information, taken from the table.
 * Assigns each field to the correct value;
 * Turns off Error Alerts and Pink Highlighted fields if they are left over from previous actions
 * Changes modal title to "Edit Vendor"
 * @param vendID	Vendor ID
 * @param name		Vendor Name
 * @param address	Vendor Address
 * @param city		Vendor City
 * @param state		Vendor State
 * @param zip		Vendor Zip Code
 * @param phone		Vendor Phone Number
 * @param ext		Vendor Phone Extension
 * @param fax		Vendor Fax Number
 * @param contact	Vendor Contact Information
 * @param refNum	Vendor Reference Number
 * @returns
 */
function doEdit(vendID, name, address, city, state, zip, phone, ext, fax, contact, refNum)  {
		//console.log(state);
		//when editing an existing record, prefills all the correct info into the appropriate textbox fields
		turnOffAlerts();
		turnOffPinkFields();
		
	    document.getElementById("form_vendVO_vendID").value = vendID;
	    document.getElementById("form_vendVO_vendID").readOnly = true;
	    
	    document.getElementById("form_vendVO_refNum").value = refNum;
	    document.getElementById("form_vendVO_name").value = name;
	    document.getElementById("form_vendVO_address").value = address;
	    document.getElementById("form_vendVO_city").value = city;
	    document.getElementById("form_vendVO_state").value = state;
	    document.getElementById("form_vendVO_zip").value = zip;
	    document.getElementById("form_vendVO_phone").value = phone;
	    document.getElementById("form_vendVO_ext").value = ext;
	    document.getElementById("form_vendVO_fax").value = fax;
	    document.getElementById("form_vendVO_contact").value = contact;

        //changes modal title to "Edit Vendor"
        document.getElementById("modalTitle").innerText = "Edit Vendor";



}

/**
 * Called when "New Vendor" is clicked.
 * Turns off any lingering alerts and pink highlighted fields
 * Sets all fields in the modal to null/blank so they are ready to take input from user
 * Changes title of modal to "New Vendor"
 *  @returns
 */
$(function () {
    $(".btnnew").click(function () {
    
    $("#name").val("");
    
    turnOffAlerts();
    turnOffPinkFields();
    //makes sure fields in "New Vendor" field are blank, with a vendID of 0
    document.getElementById("form_vendVO_vendID").value = 0; //null;
    document.getElementById("form_vendVO_vendID").readOnly = true;
    
    document.getElementById("form_vendVO_refNum").value = null;
    document.getElementById("form_vendVO_name").value = null;
    document.getElementById("form_vendVO_address").value = null;
    document.getElementById("form_vendVO_city").value = null;
    document.getElementById("form_vendVO_state").value = null;
    document.getElementById("form_vendVO_zip").value = null;
    document.getElementById("form_vendVO_phone").value = null;
    document.getElementById("form_vendVO_ext").value = null;
    document.getElementById("form_vendVO_fax").value = null;
    document.getElementById("form_vendVO_contact").value = null;

    //changes modal title to "New Vendor"
    document.getElementById("modalTitle").innerText = "New Vendor";

    })
})

/**
 * Called when delete button is clicked
 * Takes in all vendor information as values to populate modal as read only fields
 * to ask user to confirm deletion of record
 * @param vendID
 * @param name
 * @param address
 * @param city
 * @param state
 * @param zip
 * @param phone
 * @param ext
 * @param fax
 * @param contact
 * @param refNum
 * @returns
 */
function delbtn(vendID, name, address, city, state, zip, phone, ext, fax, contact, refNum)  {

	//passes vendID to delete function to delete correct record
    document.getElementById("delForm_vendVO_vendID").value = vendID;
    
	//fills in the "Are you sure you want to delete this record" modal with the correct current record info
    document.getElementById("vendVO_refNum").value = refNum;
    document.getElementById("vendVO_refNum").readOnly = true;

    document.getElementById("vendVO_name").value = name;
    document.getElementById("vendVO_name").readOnly = true;

    document.getElementById("vendVO_contact").value = contact;
    document.getElementById("vendVO_contact").readOnly = true;

    document.getElementById("vendVO_address").value = address;
    document.getElementById("vendVO_address").readOnly = true;

    document.getElementById("vendVO_city").value = city;
    document.getElementById("vendVO_city").readOnly = true;

    document.getElementById("vendVO_state").value = state;
    document.getElementById("vendVO_state").readOnly = true;

    document.getElementById("vendVO_zip").value = zip;
    document.getElementById("vendVO_zip").readOnly = true;

    document.getElementById("vendVO_phone").value = phone;
    document.getElementById("vendVO_phone").readOnly = true;

    document.getElementById("vendVO_ext").value = ext;
    document.getElementById("vendVO_ext").readOnly = true;

    document.getElementById("vendVO_fax").value = fax;
    document.getElementById("vendVO_fax").readOnly = true;




}




/**
 * Calls the Delete function in the stored procedure
 * @returns
 */
function doDelete() {
	document.getElementById("delForm").submit();
	$('#editModal').modal('hide');
}

/**
 * Called when information of an edited existing record or new new record is populated and submitted
 * Checks if any required fields are left empty and will throw an error message if so, as well as
 * highlighting the empty required fields pink
 * Also checks if name entered is already in use while taking into consideration the editing of an existing field
 * Form is not submitted until required fields (Name, Address, City, State, Zip) are populated
 * @returns
 */
function doSubmit() {
	//variables for all Error messages
	var noIDError = document.getElementById("noIDError");
	var noNameError = document.getElementById("noNameError");
	var noStreetError = document.getElementById("noStreetError");
	var noCityError = document.getElementById("noCityError");
	var noStateError = document.getElementById("noStateError");
	var noZipError = document.getElementById("noZipError");
	var nameInUseError = document.getElementById("nameInUseError");
	
	//variables for required fields being left empty, used to highlight field pink
	var nameEmpty = document.getElementById("form_vendVO_name");
	var streetEmpty = document.getElementById("form_vendVO_address");
	var cityEmpty = document.getElementById("form_vendVO_city");
	var stateEmpty = document.getElementById("form_vendVO_state");
	var zipEmpty = document.getElementById("form_vendVO_zip");
	
	if(document.getElementById("form_vendVO_vendID").value == 0){
		//noIDError.style.display = "block";
		//return;
	} else{
		noIDError.style.display = "none";

	}

	//Checks if required fields are left blank and displays associated error message
	if(document.getElementById("form_vendVO_name").value == ""){
		noNameError.style.display = "block";
		nameEmpty.style.background="pink";
	} else{
		noNameError.style.display = "none";
		nameEmpty.style.background="none";
	}

	if(document.getElementById("form_vendVO_address").value == ""){
		noStreetError.style.display = "block";
		streetEmpty.style.background="pink";
	} else{
		noStreetError.style.display = "none";
		streetEmpty.style.background="none";
	}

	if(document.getElementById("form_vendVO_city").value == ""){
		noCityError.style.display = "block";
		cityEmpty.style.background="pink";
	} else{
		noCityError.style.display = "none";
		cityEmpty.style.background="none";
	}

	if(document.getElementById("form_vendVO_state").value == ""){
		noStateError.style.display = "block";
		stateEmpty.style.background="pink";
	} else{
		noStateError.style.display = "none";
		stateEmpty.style.background="none";
	}

	if(document.getElementById("form_vendVO_zip").value == ""){
		noZipError.style.display = "block";
		zipEmpty.style.background="pink";
	} else{
		noZipError.style.display = "none";
		zipEmpty.style.background="none";
	}

	//Checks if name entered when editing/adding a record already exists and throws nameInUse error if so. Takes care of case when updating a record without changing the name.
	//curVend holds name of current ID entered; document.getElementById(curVend) will be null if you're changing the name of a record to something new that doesn't exist in table
	//will not be null if new name entered already exists in table
		//if it exists in the table, it will compare the vendID of the old entry and new entry.
		//if they are equal, no error is thrown since it registers as updating an existing record
		//if they are not equal, then an error is thrown since you are trying to change the name to one that already exists in the table
	var curVend = document.getElementById("form_vendVO_name").value;
	//console.log(curVend);
	//console.log(document.getElementById(curVend));
	if(document.getElementById(curVend) != null && document.getElementById("form_vendVO_vendID").value != document.getElementById(curVend).value){
		nameInUseError.style.display = "block";
		return;
	} else
		nameInUseError.style.display = "none";	

	//Prevents form from being submitted by return statement until all required fields are not empty
	if(noIDError.style.display == "block" ||
		noNameError.style.display == "block" ||
		noStreetError.style.display == "block" ||
		noCityError.style.display == "block" ||
		noStateError.style.display == "block" ||
		noZipError.style.display == "block" ||
		nameInUseError.style.display == "block")
		return;

	document.getElementById("form").submit();
	//$('#editModal').modal('hide');
}

/**
 * Turns off all error alerts when clicking on new vendor or edit; used to clear lingering errors
 * variables do not have to be declared here because errors are given unique IDs in jsp
 * @returns
 */
function turnOffAlerts(){
	noIDError.style.display = "none";
	noNameError.style.display = "none";
	noStreetError.style.display = "none";
	noCityError.style.display = "none";
	noStateError.style.display = "none";
	noZipError.style.display = "none";
	nameInUseError.style.display = "none";

}

/**
 * Turns off pink highlight when clicking on new vendor or edit; used to clear lingering pink highlights
 * variables do not have to be declared here because errors are given unique IDs in jsp
 * @returns
 */
function turnOffPinkFields(){
	document.getElementById("form_vendVO_name").style.background="none";
	document.getElementById("form_vendVO_address").style.background="none";
	document.getElementById("form_vendVO_city").style.background="none";
	document.getElementById("form_vendVO_state").style.background="none";
	document.getElementById("form_vendVO_zip").style.background="none";
}
