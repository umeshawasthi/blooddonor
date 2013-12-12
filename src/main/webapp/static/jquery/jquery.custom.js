
//this function is responsible get district list based on state .
function ajaxCallForDistrict()
{ 
		dropdownreset(document.getElementById("district"));
		var selectedState = document.getElementById("stateList");
		var statedata = selectedState.options[selectedState.selectedIndex].value;
		
		if(statedata == "#"){
			
			return false;
		}
		var formInput='state='+statedata;		
		$.getJSON('search/dropDownRenderer',formInput,function(data) {
			
			$("#district  option:first").val("-1").text("Please select a  district");
			$('.result').html('' + data.districts + '');
			$.each(data.districts,function(index, value){
			var districtid = document.getElementById("district");
			var option=new Option(value,value);
			try{
				districtid.add(option);
			}
			catch(e){
				districtid.appendChild(option);
			}
			});
			});
	}

function searchAjaxCallDistrict()
{ 
	 
	    var actionURL=$("#currentContextPath").val()+'search/dropDownRenderer';
	    dropdownreset(document.getElementById("district_"));
		var selectedState = document.getElementById("stateList_");
		var statedata = selectedState.options[selectedState.selectedIndex].value;
		var formInput='state='+statedata;
		$.getJSON(actionURL,formInput,function(data) {
			$("#district_ option:first").val("-1").text("Please select a  district");
			$('.result').html('' + data.districts + '');
			$.each(data.districts,function(index, value){
			var districtid = document.getElementById("district_");
			var option=new Option(value,value);
			try{
				districtid.add(option);
			}
			catch(e){
				districtid.appendChild(option);
			}
			});
			});
	}
	


	function dropdownreset(obj)
	{
		var len=obj.length;
		for(var k=len-1; k>=0; k--)
		{
			try
			{		
				obj.remove(k);
			}
			catch(e){alert(e);}			
		}
		try{
		// for IE
		obj.add(new Option("",""));
		}catch(e){
		// for FireFox
		obj.appendChild(new Option("",""));
		}
		
	}
	
	
	function ajaxUniqueMailId()
	{ 
		
			var emailTextBox =$('#email').val(); 
			if(emailTextBox!=null || emailTextBox!='null' || emailTextBox!='')
			{
			var formInput='emailId='+emailTextBox;		
			$.getJSON('ajax/verifyEmail',formInput,function(data) {
				$('.result').html('' + data.uniqueIdStatus + '');				
				if(!(data.uniqueIdStatus.trim()==true || data.uniqueIdStatus=='true' ))
					{
					$('#email').val("")
					$('#errorMsg').show();
					$('#errorMsg').html('Emailid already registred');
					}
				 
				});
	       }
		}
	
	
	$(function() {
		$('#btnClick').click(
				function() {
					if(!validateEmail('toMail') || !checkBoxValidate())
						{
						return;
						}
					 
					centerPopup();
					//load popup
					loadPopup();
					var toMail = $('#toMail').val();
					var queryString = 'toMail=' + toMail;
					$(':checkbox:checked').each(
							function(i) {
								queryString = queryString + '&donorIDs='
										+ $(this).val()
							});
					
					$.getJSON('ajax/mailDonorInformation', queryString,
							function(data) {
								$('.result').html('' + data.message + '');
								if (data.message != ''
										|| data.message != undefined
										|| data.message != null) {
									$('#statusMsg').show();
									$('#statusMsg').html(data.message);
									disablePopup();
									//ssetTimeout("$('#status').hide(); ", 3000); //display message for 3 seconds
								}

							});

				});
	});
	
	function feedBackPopup(){
		feedbackCenterPopup();			
		feedbackLoadPopup();		
		};
	
	function nextpage(pageNumber){
		   document.getElementById('p').value=pageNumber;
		   document.donorSearch.submit();
		   
	   }
	  
	function setPageSize(pageSize){
		document.getElementById('pageSize').value=pageSize;
		document.donorSearch.submit();
		
	}

	function searchKeyPress(e){
		if (window.event) { e = window.event; }
	    if (e.keyCode == 13)
	    {
	    	validateCriteria();
	    }

		
	}


	function validateCriteria(flag){
		
		if(flag==true){
		   document.getElementById('criteriaChanged').value=true;
		}
		return true;

	}
	
function validateInputs(fieldId,flag){
	var value = $("#"+fieldId).val();
	
	validateCriteria(flag);
	
	if(value==null || value=="" || value=="-1"){
		$("#"+fieldId).addClass('required');
		return false;
	}

	
}	
	
	var popupStatus = 0;
	function loadPopup(){
		//loads popup only if it is disabled
		if(popupStatus==0){
			$("#backgroundPopup").css({
				"opacity": "0.7"
			});
			$("#backgroundPopup").fadeIn("slow");
			$("#popupContact").fadeIn("slow");
			$("#progress").fadeIn("slow");
			popupStatus = 1;
			
		}
	}

	//disabling popup with jQuery magic!
	function disablePopup(){
		//disables popup only if it is enabled
		if(popupStatus==1){
			$("#backgroundPopup").fadeOut("slow");
			$("#popupContact").fadeOut("slow");
			popupStatus = 0;
		}
	}

	//centering popup
	function centerPopup(){
		//request data for centering
		var windowWidth = document.documentElement.clientWidth;
		var windowHeight = document.documentElement.clientHeight;
		var popupHeight = $("#popupContact").height();
		var popupWidth = $("#popupContact").width();
		//centering
		$("#popupContact").css({
			"position": "absolute",
			"top": windowHeight/2-popupHeight/2,
			"left": windowWidth/2-popupWidth/2
		});
		//only need force for IE6
		
		$("#backgroundPopup").css({
			"height": windowHeight
		});
		
		
	}
	
	var feedbackPopupStatus = 0;
	function feedbackLoadPopup(){
		//loads popup only if it is disabled
		if(feedbackPopupStatus==0){
			$("#feedbackBackgroundPopup").css({
				"opacity": "0.7"
			});
			$("#feedbackBackgroundPopup").fadeIn("slow");
			$("#feedbackPopupContact").fadeIn("slow");
			//$("#progress").fadeIn("slow");
			feedbackPopupStatus = 1;
			
		}
	}

	//disabling popup with jQuery magic!
	function feedbackDisablePopup(){
		//disables popup only if it is enabled
		if(feedbackPopupStatus==1){
			$("#feedbackBackgroundPopup").fadeOut("slow");
			$("#feedbackPopupContact").fadeOut("slow");
			feedbackPopupStatus = 0;
		}
	}

	//centering popup
	function feedbackCenterPopup(){
		//request data for centering
		var windowWidth = document.documentElement.clientWidth;
		var windowHeight = document.documentElement.clientHeight;
		var popupHeight = $("#feedbackPopupContact").height();
		var popupWidth = $("#feedbackPopupContact").width();
		//centering
		$("#feedbackPopupContact").css({
			"position": "absolute",
			"top": windowHeight/2-popupHeight/2,
			"left": windowWidth/2-popupWidth/2
		});
		//only need force for IE6
		
		$("#feedbackBackgroundPopup").css({
			"height": windowHeight
		});
		
		
	}
	
	
	function bloodRequestBackPopup(uuid){
		showBloodRequestCenterPopup()		
		showBloodRequestLoadPopup(uuid)		
		};
	
	var showBloodRequestPopupStatus = 0;
	function showBloodRequestLoadPopup(uuid){
		//loads popup only if it is disabled
		var bloodRequestUUID='bloodRequestId='+uuid;
		$.getJSON('getBloodRequestDetails',bloodRequestUUID,function(data) {
			 
			$.each(data.bloodRequestDTO,function(key, value){
				$('#'+key).html(value);  
			});
			});
		if(feedbackPopupStatus==0){
			$("#showBloodRequestBackgroundPopup").css({
				"opacity": "0.7"
			});
			$("#showBloodRequestBackgroundPopup").fadeIn("slow");
			$("#showBloodRequestPopupContact").fadeIn("slow");
			//$("#progress").fadeIn("slow");
			feedbackPopupStatus = 1;
			
		}
	}

	//disabling popup with jQuery magic!
	function showBloodRequestDisablePopup(){
		//disables popup only if it is enabled
		if(feedbackPopupStatus==1){
			$("#showBloodRequestBackgroundPopup").fadeOut("slow");
			$("#showBloodRequestPopupContact").fadeOut("slow");
			feedbackPopupStatus = 0;
		}
	}
	
	//centering popup
	function showBloodRequestCenterPopup(){
		//request data for centering
		var windowWidth = document.documentElement.clientWidth;
		var windowHeight = document.documentElement.clientHeight;
		var popupHeight = $("#showBloodRequestPopupContact").height();
		var popupWidth = $("#showBloodRequestPopupContact").width();
		//centering
		$("#showBloodRequestPopupContact").css({
			"position": "absolute",
			"top": windowHeight/2-popupHeight/2,
			"left": windowWidth/2-popupWidth/2
		});
		//only need force for IE6
		
		$("#showBloodRequestBackgroundPopup").css({
			"height": windowHeight
		});
		
		
	}
	
	function validateEmail(element)
	{
		var emailValue=$('#'+element).val();
		email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/; 	
		var valid;		
		if(!email_regex.test(emailValue)){
		    valid = false;
		    $('#statusMsgMail').show();
			$('#statusMsgMail').html('Please enter valid email address');
		    return false;
		    
		}
		return true;
		
		
	}
	
	function checkBoxValidate()
	{
		var length='';
		$(':checkbox:checked').each(
				function(i) {
					length=$(this).val()
				});	
		if(length=='')
			{
			$('#statusMsgMail').html('Please select atleast one value');
				return false;
			}
		return true;
	}
	
	function editBloodReqPopup(){
		editBloodLoadPopup();
		editBloodCenterPopup();
	}
	
	var editBloodPopupStatus = 0;
	function editBloodLoadPopup(){
		//loads popup only if it is disabled
		if(editBloodPopupStatus==0){
			$("#editBloodBackgroundPopup").css({
				"opacity": "0.7"
			});
			$("#editBloodBackgroundPopup").fadeIn("slow");
			$("#editBloodPopupContact").fadeIn("slow");
			//$("#progress").fadeIn("slow");
			editBloodPopupStatus = 1;
			
		}
	}

	//disabling popup with jQuery magic!
	function editBloodDisablePopup(){
		//disables popup only if it is enabled
		if(editBloodPopupStatus==1){
			$("#editBloodBackgroundPopup").fadeOut("slow");
			$("#editBloodPopupContact").fadeOut("slow");
			editBloodPopupStatus = 0;
		}
	}

	//centering popup
	function editBloodCenterPopup(){
		//request data for centering
		var windowWidth = document.documentElement.clientWidth;
		var windowHeight = document.documentElement.clientHeight;
		var popupHeight = $("#editBloodPopupContact").height();
		var popupWidth = $("#editBloodPopupContact").width();
		//centering
		$("#editBloodPopupContact").css({
			"position": "absolute",
			"top": windowHeight/2-popupHeight/2,
			"left": windowWidth/2-popupWidth/2
		});
		//only need force for IE6
		
		$("#editBloodBackgroundPopup").css({
			"height": windowHeight
		});
		
	}
	
	
	
	
	
	
	
	
	
	
	