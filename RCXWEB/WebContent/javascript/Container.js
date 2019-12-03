/**
 * Called when "New Container Type" is clicked; used to create new records
 * Turns off any lingering alerts and pink highlighted fields
 * Sets all fields in the modal to null/blank so they are ready to take input from user
 * Changes title of modal to "Add New Container Type"
 * Sets actionName to "create"
 * @returns
 */
function doNew(){
	//clears errors for a clean slate every time you click "New Container Type"
	turnOffErrors();
	
    document.getElementById("form_containerVO_name").value = null;
    
    //changes modal title to "Add New Container Type"
    document.getElementById("modalTitle").innerText = "Add New Container Type";
	
	//sets Action Name to create when creating new record
	document.getElementById("addContainer").value="create";
}


/**
 * Opens edit modal to edit existing record. Fills in data from table to modal fields. 
 * Sets actionName to "update"
 * @param containerID
 * @param name
 * @returns
 */
function doEdit(containerID, name){
	turnOffErrors();

	document.getElementById("modalTitle").innerText = "Update Container Type";
	//sets Action Name to update when updating existing record
	document.getElementById("addContainer").value="update";
	
    //fills in existing values to appropriate text fields
    document.getElementById("form_containerVO_name").value = name;
    
    //set value of hidden field to value from table 
    document.getElementById("editContainerID").value = containerID;
}


/**
 * Called when delete button is pressed; DOES NOT SUBMIT FORM
 * Prefills readonly textboxes with fields from table to ask user to confirm the record they want to delete
 * and fills appropriate containerVO values (containerID, name).
 * @param containerID
 * @param name
 * @returns
 */
function deleteBtn(containerID, name){
	//setting value of hidden fields to value from table
	document.getElementById("deleteContainerID").value = containerID;
	document.getElementById("deleteContainerName").value = name;
}


/**
 * Called when user confirms they want to delete their selected record; SUBMITS FORM
 * Hidden value deleteContainerID filled in with Container ID from table
 * NOTE: delete function works using site ID
 * @returns
 */
function doDelete(){
	//console.log("document.getElementById(\"containerVO.name\").value : " + document.getElementById("containerVO.name").value)
	console.log("deleteActionName : " + document.getElementById("deleteActionName").value)
	document.getElementById("delForm").submit();
	//$('#editModal').modal('hide');
}

/**
 * Used to submit form involving editing or creating record.
 * Checks if any errors are present before executing stored procedure; won't execute until errors are fixed.
 * @returns
 */
function doSubmit(){
	if(requiredErrorCheck())
		return;
	console.log("document.getElementById(\"editContainerID\").value : " + document.getElementById("editContainerID").value);
	document.getElementById("form").submit();
}

/**
 * Function to check if Required fields were left blank, returns true if so.
 * Textfield highlighted pink and error message displayed as well.
 * @returns
 */
function requiredErrorCheck(){
	//variables for error messages
	var noNameError = document.getElementById("noNameError");
	
	//variables for required fields being left empty, used to highlight field pink
	var nameEmpty = document.getElementById("form_containerVO_name");
	
	if(document.getElementById("form_containerVO_name").value==""){
		noNameError.style.display = "block";
		nameEmpty.style.background="pink";
	}
	else{
		noNameError.style.display = "none";
		nameEmpty.style.background="none";
	}
	
	if(noNameError.style.display == "block")
		return true;
}

/**
 * Turns off all error alerts and removes pink highlights from required fields
 * Called so that fields don't linger when clicking on edit or clicking new
 * @returns
 */
function turnOffErrors(){
	noNameError.style.display = "none";
	document.getElementById("form_containerVO_name").style.background="none";
}