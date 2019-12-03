/**
 * Called to retrieve data from database; changes ActionName to "retrieve", then submits form
 * @returns
 */
function doRetrieve() {
	//document.getElementById("tableDisplay").style.display = "block";
	document.getElementById("tableRetrieve").value="retrieve";
	document.getElementById("retrieveForm").submit();
	//$('#editModal').modal('hide');
}




/**
 * Called when "Add Rate" is clicked.
 * Turns off any lingering alerts and pink highlighted fields
 * Sets all fields in the modal to null/blank so they are ready to take input from user
 * Changes title of modal to "Add Rate"
 * Sets actionName to "create"
 *  @returns
 */
function doNew(){
	//clears errors for a clean slate every time you click "New Rate"
	turnOffErrors();
	
    //makes Site, Material, and Date editable when creating a new record
    enableEditFields();
    
    //makes rest of Rate fields disabled until a material is selected
    disableRateFields();
	
    document.getElementById("form_rateVO_contPerLb").value = null;
    document.getElementById("form_rateVO_scrapRate").value = null;
    document.getElementById("form_rateVO_redemRate").value = null;
    document.getElementById("form_rateVO_bonusRate").value = null;
    document.getElementById("form_rateVO_smallCountRate").value = null;
    document.getElementById("form_rateVO_largeCountRate").value = null;
    
    //changes modal title to "Add Rate"
    document.getElementById("modalTitle").innerText = "Add Rate";
    generateMaterialDropdown("editMaterialList");
	
	//sets Action Name to create when creating new record
	document.getElementById("addRate").value="create";
}

/**
 * Opens edit modal to edit existing record. Fills in data from table to modal fields. 
 * Makes fields 'Read Only' based on value of hasScrap, hasRedemption, hasBonus, hasCount.
 * Sets actionName to "update"
 * 
 * @param siteName
 * @param matName
 * @param date
 * @param contPerLb
 * @param scrapRate
 * @param redemRate
 * @param bonusRate
 * @param smallCountRate
 * @param largeCountRate
 * @param hasScrap
 * @param hasRedemption
 * @param hasBonus
 * @param hasCount
 * @returns
 */
function doEdit(siteName, matName, date, contPerLb, scrapRate, redemRate, bonusRate, smallCountRate, largeCountRate, hasScrap, hasRedemption, hasBonus, hasCount){
	document.getElementById("modalTitle").innerText = "Update Rate";
	disableEditFields(siteName, matName, date);
	turnOffErrors();

	//sets Action Name to update when updating existing record
	document.getElementById("addRate").value="update";
	
	enableRateFields();
    
	//disables rate fields that do not apply based on the Material selected
    if (hasScrap == 'N')
    	document.getElementById("form_rateVO_scrapRate").disabled = true;
    else
    	document.getElementById("form_rateVO_scrapRate").disabled = false;
    
    if (hasRedemption == 'N')
    	document.getElementById("form_rateVO_redemRate").disabled = true;
    else
    	document.getElementById("form_rateVO_redemRate").disabled = false;
    
    if (hasBonus == 'N')
    	document.getElementById("form_rateVO_bonusRate").disabled = true;
    else
    	document.getElementById("form_rateVO_bonusRate").disabled = false;
    
    if (hasCount == 'N'){
    	document.getElementById("form_rateVO_smallCountRate").disabled = true;
    	document.getElementById("form_rateVO_smallCountRate").disabled = true;
    }
    else{
    	document.getElementById("form_rateVO_smallCountRate").disabled = false;
    	document.getElementById("form_rateVO_smallCountRate").disabled = false;
    }
	
    //fills in existing values to appropriate text fields
    document.getElementById("form_rateVO_contPerLb").value = contPerLb;
    document.getElementById("form_rateVO_scrapRate").value = scrapRate;
    document.getElementById("form_rateVO_redemRate").value = redemRate;
    document.getElementById("form_rateVO_bonusRate").value = bonusRate;
    document.getElementById("form_rateVO_smallCountRate").value = smallCountRate;
    document.getElementById("form_rateVO_largeCountRate").value = largeCountRate;
    	
    
}

/**
 * Called when delete button is pressed; DOES NOT SUBMIT FORM
 * Only prefills uneditable textboxes with fields from table to ask user to confirm the record they want to delete
 * and fills appropriate rateVO values (siteName, matName, date).
 * @param siteName
 * @param matName
 * @param date
 * @returns
 */
function deleteBtn(siteName, matName, date){
	document.getElementById("deleteMaterialListTextfield").value = matName;
	document.getElementById("deleteSiteListTextfield").value = siteName;
	document.getElementById("deleteDateTextfield").value = date;
	
	document.all["rateVO.siteName"].value		= siteName
	document.all["rateVO.matName"].value	= matName
	document.all["rateVO.date"].value		= date
}

/**
 * Called when user confirms they want to delete their selected record; SUBMITS FORM
 * Hidden value deleteSiteID filled in with site ID from editSiteList so when page refreshes, the correct site is already selected,
 * otherwise site ID remains 0 and gets changed to 1 in the setup action.
 * Reformatts date so when a record is deleted, the correct date is prefilled in the Effective Date search bar (requires YYYY-MM-dd format)
 * NOTE: delete function works using site NAME, material NAME, and date.
 * @returns
 */
function doDelete(){

	//setting value of hidden field to value from main site dropdown
	document.getElementById("deleteSiteID").value = document.getElementById("siteIDList").value;

	//formatting date
	document.getElementById("deleteDateTextfield").value = formatDate(document.getElementById("deleteDateTextfield").value);
	
	document.getElementById("delForm").submit();
	$('#editModal').modal('hide');
}


/**
 * Before submitting, checks if there are errors present (required fields missing, optional fields out of bounds, etc).
 * Only checks required fields for new records (actionName = create) since those fields are read only for editing existing records
 * Optional fields (scrap rate, bonus rate, etc.) checked regardless of creating or editing a record.
 * @returns
 */
function doSubmit(){
	
	/**
	 * For Action Name = create
	 * #1. Updates rateVO.date based on value selected from the Calendar Dropdown; prevents value
	 * 		lingering from a previous click on edit from a different record
	 * #2. requiredErrorCheck() checks if required fields (Material, Date) are left blank;
	 * 		function returns true if so and form won't be submitted until required fields filled
	 *		Blank required fields highlighted pink
	 */
	if(document.getElementById("addRate").value == "create"){
		//#1
		document.all["rateVO.date"].value = document.getElementById("editDate").value;
		
		//#2
		if(requiredErrorCheck())
			return;
	}
	
	/**
	 * optionalErrorCheck() checks if optional fields (scrap rate, bonus rate, etc) out of bounds or not;
	 * function returns true if so and form won't be submitted until required fields filled
	 * Out of bound fields given red border
	 */
	if(optionalErrorCheck()) 
		return;
	
	//setting value of hidden field to value from dropdown
	document.getElementById("createSiteID").value = document.getElementById("editSiteList").value;

	//depending on ActionName, will update rateVO.date based on value from the Calendar Dropdown
	if(document.getElementById("addRate").value == "create"){
		document.getElementById("editDateTextfield").value = document.all["rateVO.date"].value;
	}
	document.all["rateVO.date"].value = formatDate(document.all["rateVO.date"].value);
	document.getElementById("form").submit();
}

/**
 * Enables/displays the main 3 Rate Fields (dropdown menus): Site, Material, and Date.
 * Hides uneditable textfields that are shown when editing an existing record
 * @returns
 */
function enableEditFields(){
	document.getElementById("editSiteList").disabled = false;
	document.getElementById("editDate").disabled = false;
	document.getElementById("editDate").value = "";
	
	
	//if editing record, hide uneditable textfields, show dropdowns
	document.getElementById("editMaterialList").style.display="block";
	document.getElementById("editMaterialListTextfield").style.display="none";
	
	document.getElementById("editSiteList").style.display="block";
	document.getElementById("editSiteListTextfield").style.display="none";
	
	document.getElementById("editDate").style.display="block";
	document.getElementById("editDateTextfield").style.display="none";
	document.getElementById("editDateTextfield").style.disabled=true;
}


/**
 * Hides the Site and Material dropdowns and the Date calendar, and replaces them with uneditable textfields that contain
 * the value their dropdowns would have contained (based on what shows on the table).
 * Prevents incorrect values from carrying over from "Add Rate" selections
 * Form is submitted based on:
 * Site ID			: hidden value populated then submitted to database
 * Material Name	: populated from table
 * Date				: populated from table, and dropdown menu disabled since they carry same name (rateVO.date)
 * @param siteName
 * @param matName
 * @param date
 * @returns
 */
function disableEditFields(siteName, matName, date){
	
	document.getElementById("editSiteList").disabled = true;
	
	//if editing record, hide dropdowns, show uneditable textfields, and fill said textfields with value from table
	document.getElementById("editMaterialList").style.display="none";
	document.getElementById("editMaterialListTextfield").style.display="block";
	document.getElementById("editMaterialListTextfield").value = matName;
	
	document.getElementById("editSiteList").style.display="none";
	document.getElementById("editSiteListTextfield").style.display="block";
	document.getElementById("editSiteListTextfield").value = siteName;
	
	document.getElementById("editDate").style.display="none";
	document.getElementById("editDateTextfield").style.display="block";
	document.getElementById("editDateTextfield").style.disabled=false;
	document.getElementById("editDateTextfield").value = date;
	document.all["rateVO.date"].value = date;
}



/**
 * Turns off all error alerts and removes pink highlights from required fields
 * Called so that fields don't linger when clicking on edit or clicking new
 * @returns
 */
function turnOffErrors(){
	noMaterialError.style.display = "none";
	noDateError.style.display = "none";
	
	containersError.style.display="none";
	scrapError.style.display="none";
	redemptionError.style.display="none";
	bonusError.style.display="none";
	smallCountError.style.display="none";
	largeCountError.style.display="none";


	document.getElementById("editMaterialList").style.background="none";
	document.getElementById("editDate").style.background="none";
	
	document.getElementById("form_rateVO_contPerLb").style.borderColor="";
	document.getElementById("form_rateVO_scrapRate").style.borderColor="";
	document.getElementById("form_rateVO_redemRate").style.borderColor="";
	document.getElementById("form_rateVO_bonusRate").style.borderColor="";
	document.getElementById("form_rateVO_smallCountRate").style.borderColor="";
	document.getElementById("form_rateVO_largeCountRate").style.borderColor="";
}



/**
 * Function to check if Required fields were left blank, returns true if so
 * @returns
 */
function requiredErrorCheck(){
	//variables for all Error messages regarding Required Fields
	var noMaterialError = document.getElementById("noMaterialError");
	var noDateError = document.getElementById("noDateError");
	
	//variables for required fields being left empty, used to highlight field pink.
	//We're allowed to make the background property "none" since "edit" uses gray text boxes to display values, not
	//the same dropdowns. Otherwise "none" would make disabled fields transparent/not gray.
	var materialEmpty = document.getElementById("editMaterialList");
	var dateEmpty = document.getElementById("editDate");
	
	if(document.getElementById("editMaterialList").value == ""){
		noMaterialError.style.display = "block";
		materialEmpty.style.background="pink";
	} else{
		noMaterialError.style.display = "none";
		materialEmpty.style.background="none";
	}
	
	if(document.getElementById("editDate").value == ""){
		noDateError.style.display = "block";
		dateEmpty.style.background="pink";
	} else{
		noDateError.style.display = "none";
		dateEmpty.style.background="none";
	}
	
	if(noMaterialError.style.display == "block" ||
			noDateError.style.display == "block")
		return true;
}


/**
 * Function to check if Optional fields are out of bounds, returns true if so
 * @returns
 */
function optionalErrorCheck(){
	//variables for all Error messages regarding Optional Fields
	var containersError = document.getElementById("containersError");
	var scrapError = document.getElementById("scrapError");
	var redemptionError = document.getElementById("redemptionError");
	var bonusError = document.getElementById("bonusError");
	var smallCountError = document.getElementById("smallCountError");
	var largeCountError = document.getElementById("largeCountError");
	
	
	//variables for optional fields being out of bounds, used to add red border.
	//Border used instead of background to avoid disabled fields in "edit" from being
	//transparent when they should be gray
	var containersEmpty = document.getElementById("form_rateVO_contPerLb");
	var scrapEmpty = document.getElementById("form_rateVO_scrapRate");
	var redemptionEmpty = document.getElementById("form_rateVO_redemRate");
	var bonusEmpty = document.getElementById("form_rateVO_bonusRate");
	var smallCountEmpty = document.getElementById("form_rateVO_smallCountRate");
	var largeCountEmpty = document.getElementById("form_rateVO_largeCountRate");
	
	if(document.getElementById("form_rateVO_contPerLb").value <0 || document.getElementById("form_rateVO_contPerLb").value > 999.99){
		containersError.style.display="block";
		containersEmpty.style.borderColor="red";
	}else{
		containersError.style.display="none";
		containersEmpty.style.borderColor="";
	}
	
	if(document.getElementById("form_rateVO_scrapRate").value <0 || document.getElementById("form_rateVO_scrapRate").value > 9.9999){
		scrapError.style.display="block";
		scrapEmpty.style.borderColor="red";
	}else{
		scrapError.style.display="none";
		scrapEmpty.style.borderColor="";
	}
	
	if(document.getElementById("form_rateVO_redemRate").value <0 || document.getElementById("form_rateVO_redemRate").value > 9.9999){
		redemptionError.style.display="block";
		redemptionEmpty.style.borderColor="red";
	}else{
		redemptionError.style.display="none";
		redemptionEmpty.style.borderColor="";
	}
	
	if(document.getElementById("form_rateVO_bonusRate").value <0 || document.getElementById("form_rateVO_bonusRate").value > 9.9999){
		bonusError.style.display="block";
		bonusEmpty.style.borderColor="red";
	}else{
		bonusError.style.display="none";
		bonusEmpty.style.borderColor="";
	}
	
	if(document.getElementById("form_rateVO_smallCountRate").value <0 || document.getElementById("form_rateVO_smallCountRate").value > 9.9999){
		smallCountError.style.display="block";
		smallCountEmpty.style.borderColor="red";
	}else{
		smallCountError.style.display="none";
		smallCountEmpty.style.borderColor="";
	}
	
	if(document.getElementById("form_rateVO_largeCountRate").value <0 || document.getElementById("form_rateVO_largeCountRate").value > 9.9999){
		largeCountError.style.display="block";
		largeCountEmpty.style.borderColor="red";
	}else{
		largeCountError.style.display="none";
		largeCountEmpty.style.borderColor="";			
	}
	//Prevents form from being submitted by return statement until all required fields are not empty
	if(containersError.style.display == "block" ||
			scrapError.style.display == "block" ||
			redemptionError.style.display == "block" ||
			bonusError.style.display == "block" ||
			smallCountError.style.display == "block" ||
			largeCountError.style.display == "block")
		return true;
}

/**
 * Converts date parameter from "MM/dd/YYYY" to "YYYY-MM-dd"; used to fill calendar dropdown which only accepts the latter to prefill
 * @param date
 * @returns
 */
function formatDate(date){
	var formattedDate = date.substring(6,10) + '-' + date.substring(0,2) + '-' + date.substring(3,5);
	return formattedDate;
}

