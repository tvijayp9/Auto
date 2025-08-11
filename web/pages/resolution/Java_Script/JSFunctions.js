function cancelTransaction()
{
	window.close();
}

function saveTransaction()
{	
	form1.submitType.value = "save";
	form1.submit();
	//submitTransaction("save");
}


function submitTransaction()
{	
	var formOK = true;
	
	var counter = 0;
	//check that all the ticks are green or locks or greys..
	for (var i=0; i<document.images.length; i++) 
	{
		var imageSRC = document.images[i].src;			
		
		var pos = imageSRC.indexOf("orange");
		
			
		if(pos != -1)
		{
			counter++;
			if(counter > 1)
			{				
				formOK = false;
			}						
		}		
	}


	if(formOK == true)
	{
		form1.submitType.value = "submit";
		form1.submit();
	}
	else
	{
		//There are still unresolved ticks...
		alert("Please Approve all elements (with a Orange tick) before submitting.");
	}
}

function textSelected(tickID)
{
	if(tickID == 0)
	{
	 	tickID = "0";
	}
	//change the arrow to grey...	
	for (var i=0; i<document.images.length; i++) 
    {
		if (document.images[i].name == tickID)
		{
			
			var origImage = document.images[i].src;
			var pos = origImage.indexOf("grey");
			
			if(pos == -1)
			{
				document.images[i].src = "../images/Trans_Disp/orangeTick.jpg";
				document.images[i].focus();	
			}					
		}
	}
}

function tickClick(tickID, errorMessage, fileName, field)
{
	alert("Field: "+field.name);

	if(tickID == 0)
	{
	 	tickID = "0";
	}
	
	for (var i=0; i<document.images.length; i++) 
    {	
		if (document.images[i].name == tickID)
		{
			//which is the current image???
			var origImage = document.images[i].src;
			var pos = origImage.indexOf("orange");
			var pos2 = origImage.indexOf("cross");
			
			if(pos != -1)
			{
				//got an ornage tick...
				document.images[i].src = "../images/Trans_Disp/greenTick.jpg";			
			}
			
			if(pos2 != -1)
			{
				//got an cross tick...
				//var r = showModalDialog("ValidError.jsp?id="+errorMessage, field, "toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no,width=500,height=500");				
				
				var r = showModalDialog('aDialog.html', field, 'dialogWidth:250px;dialogHeight:100px;center:1;'); 
				 if (typeof r != "undefined")
				   field.value = r;

				
				
				
				window.open("ValidError.jsp?id="+tickID+"&error="+errorMessage+"&fileName="+fileName, "MyWindow", "toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no,width=500,height=500");
				document.images[i].src = "../images/Trans_Disp/greenTick.jpg";
				/*if(typeof r != "undefined")
				{
					field.value = r;
				}*/	
			}						
		}
	}
	return false;	
}

function copyValue(textName, value)
{
	//change the tick to grey...
	textSelected(textName);
	
	var unName = unescape(textName);
	var unValue = unescape(value);
	//alert("textName: "+unName+", value: "+unValue);
	
	var fields = document.forms[0];
	
	for (i = 0; i < fields.length; i++) 
	{
		// if the current element is an input text field...
		if (fields.elements[i].type == "text")
		{
			curr = fields.elements[i];
			var id = curr.name;			
			
			if(id == unName)
			{
				//alert("found the right one");
				curr.value = unValue;			
			}			
		}		
	}
}
