
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<HTML>
<HEAD>
<meta http-equiv="expires" content="0">
<meta http-equiv="Pragma" content="no-cache">

<TITLE>Table of Contents</TITLE>
<SCRIPT language="JavaScript1.2">

document.onkeydown = function ()
{
	var kcode = event.keyCode;

	if(kcode > 111 && kcode < 124)
	{
		 alert("Function Keys Cannot be used in TradeRoute.");
		 event.keyCode = 0;
		event.returnValue = false;
		event.cancelBubble = true;
		return false;
	}
}


document.onhelp =  function ()
{
    //alert('Visit http://javascript.faqts.com/ for help');
    return false;
}


var selectedElementId = 1;
var root;

var nextUrl = "";
var nextFileName = "";
var nextType = "";

var checkUrl = "";
var checkFileName = "";
var checkType = "";
var elementtype="";

// bottom.htm functions
var cancelButtonHit = "";
var title1 = "";
function button_help()
{
	window.open('Trans_Disp_Help.htm');
}
function refreshParent(){
			if (window.opener && !window.opener.closed) {
window.opener.location.reload();
} 
}
function button_cancel()
{
    cancelButtonHit = "yes";
	var loc = parent.frames[2].document.location;
	var str = loc.toString();
	var pos = str.indexOf("Splash");

	if(pos != -1)
	{
		// the splash screen
		parent.content.document.form1.buttonClicked.value = "cancel";
		submitContent();
		alert("This Transaction will now be cancelled.");
		setTimeout("top.window.close();", 1000);
	}
	else
	{
		var formStr = parent.content.document.forms[0];

		if(formStr == null)
		{
			alert("This Transaction will now be cancelled.");
			setTimeout("top.window.close();", 1000);
		}
		else
		{
			// not the splash screen
			parent.content.document.form1.buttonClicked.value = "cancel";
			submitContent();
			alert("This Transaction will now be cancelled.");
			setTimeout("top.window.close();", 1000);
		}
	}

	//return false;
}

function form_cancel()
{
    // alert("inside form_cancel");
	if(cancelButtonHit != "yes")
	{
		var loc = parent.frames[2].document.location;
		var str = loc.toString();
		var pos = str.indexOf("Splash");

		if(pos != -1)
		{
			// the splash screen
			parent.content.document.form1.buttonClicked.value = "cancel";
			submitContent();
			alert("This Window will now be closed.");
			setTimeout("top.window.close();", 1000);
		}
		else
		{
			var formStr = parent.content.document.forms[0];

			if(formStr == null)
			{
				alert("This Window will now be closed.");
				setTimeout("top.window.close();", 1000);
			}
			else
			{
				// not the splash screen
				parent.content.document.form1.buttonClicked.value = "cancel";
				submitContent();
				alert("This Window will now be closed.");
				setTimeout("top.window.close();", 1000);
			}
		}
	}
	else
	{
		window.close();
	}
}

function button_comment()
{
    var loc = parent.content.document.location;
	var str = loc.toString();
	var pos = str.indexOf("Trans_Disp_Content.jsp");
   
	if(pos != -1)
	{
		var formStr = parent.content.document.forms[0];
            var reason1 = prompt("Please enter a Comment for this Transaction", "Type your Comment here");
			if(reason1!=null){
            parent.content.document.form1.buttonClicked.value = "comment";
parent.content.document.form1.doccomment.value=reason1;	
			submitContent();
            setTimeout("sendLocation()", 1000);
			//setTimeout("alert('Transaction Has Been commented Successfully.');", 5000);
			//setTimeout("top.window.close();", 2000);
			refreshParent();
            }
}
	else
	{
		alert("Please use the structure on the left to navigate the transaction.");
	}
}

function button_print()
{
	var loc = parent.frames[2].document.location;
	var str = loc.toString();
	var pos = str.indexOf("Trans_Disp_Content.jsp");
	//if(pos != -1)
	//{
		var formStr = parent.content.document.forms[0];

		if(formStr == null)
		{
			alert("Please use the structure on the left to navigate the transaction.");
		}
		else
		{
			parent.content.document.form1.buttonClicked.value = "print";
			submitContent();
			nextUrl=null;
			//alert("Transaction has been printed...");
			setTimeout("sendLocation()", 1000);
            setTimeout("printdocument()", 2000);
			//var printfileName = document.forms[0].nextFileName.value;
            //alert("print3.."+printfileName);
            //window.open('http://localhost:8080/TradeRoute_Nexus/print_20100507115833-1.htm','mywindow','width=800,height=600,toolbar=no,menubar=yes,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
            //window.open("D:\\XmlYesDev\\nexus v3\\web\\temp\\print_"+printfileName+".htm",'mywindow','width=800,height=600,toolbar=yes,menubar=yes,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
            //window.open("http://202.4.235.224:8081/Auto/temp/print_"+printfilename+".htm",'mywindow','width=1000,height=700,toolbar=yes,menubar=yes,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
		}
}

function printdocument(){
    var printfilename='<%=session.getAttribute("newFileName").toString()%>';
	alert("It takes 5-10 seconds to genarate document.Please refresh the page by hitting F5,if it can't open a page.");
//    window.open("http://203.206.178.177:8080/Auto/temp/print_"+printfilename+".htm",'mywindow','width=1000,height=700,toolbar=yes,menubar=yes,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
    window.open("http://nexusb2bnetwork.com.au/Auto/temp/print_"+printfilename+".htm",'mywindow','width=1000,height=700,toolbar=yes,menubar=yes,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
}

function openWin(app, fileName, transType)
{
	var url = "../Transaction_Display/"+app+".jsp?name="+fileName+"&transType="+transType;
	//alert("Opening url: "+url);
	var r = showModalDialog(url, null, "resizable:no;status:no;dialogWidth:700px;dialogHeight:500px");
	//var r = window.open( url,'MyWindow','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,width=800,height=600');
	//var r = window.open( url,'MyWindow','fullscreen');
	//return false;
	//window.location.reload();
}


function button_print_exp()
{
	var fName = document.form1.fileName.value;
	//openWin("Export_Print", fName, "Export");

	var url = "../Transaction_Display/Export_Print.jsp?name="+fName+"&transType=Export";
	var r = window.open(url,'ViewField','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no,width=600,height=400');

}

function button_accept_exp()
{

}


function button_save()
{
	//alert("Button Save 1");
	cancelButtonHit = "yes";
	var loc = parent.frames[2].document.location;
    //alert("loc..."+loc);
	var str = loc.toString();

	var pos = str.indexOf("Trans_Disp_Content.jsp");
    //alert("pos..."+pos);
	//alert("Button Save 2");
	if(pos != -1)
	{
		//alert("Button Save 3");
		var formStr = parent.content.document.forms[0];
		//alert("Button Save 4");

		if(formStr == null)
		{
		//alert("Button Save 5");
			alert("Please use the structure on the left to navigate the transaction.");
		}
		else
		{
		//alert("Button Save 6..."+parent.content.document.forms[0].buttonClicked.value);
			parent.content.document.forms[0].buttonClicked.value = "save";
			//alert("Button Save 7");
			submitContent();
			//alert("Button Save 8");
			alert("\t\t\tTransaction Saved Successfully:\nYou can make changes to this transaction by re-opening it from the Transaction Centre Window.");
			//alert("Button Save 9");
			setTimeout("top.window.close();", 1000);

		}
	}
	else
	{
		alert("Please use the structure on the left to navigate the transaction.");
	}
}
function button_save_kala(nUrl, fName, tType,etitle)
{
	//alert("Button Save nUrl="+nUrl+"..fName=="+fName+"...tType="+);
	cancelButtonHit = "yes";
	//var loc = parent.frames[2].document.location;
    //var str = loc.toString();

	//var pos = str.indexOf("Trans_Disp_Content.jsp");
    //if(pos != -1)
	//{
		var index = parent.content.document.getElementById("reasonType");
		var selecttype = index.options[index.selectedIndex].value;
		//alert("selecttype=="+selecttype+"...index="+index);
		if(selecttype>0)
		{
			var formStr = parent.content.document.forms[0];
			if(formStr == null)
			{
				alert("Please use the structure on the left to navigate the transaction.");
			}
			else
			{
				parent.content.document.forms[0].buttonClicked.value = "save";
				submitContent();
				//alert("\t\t\tTransaction Saved Successfully:\nYou can make changes to this transaction by re-opening it from the 	Transaction Centre Window.");
				//setTimeout("top.window.close();", 1000);
				checkUrl = nUrl;
				checkFileName = fName;
				checkType = tType;
				elementtype=etitle;
				setTimeout("checkCurrValues()", 1000);
			}
		}
		else{
			alert("Please select reason type before save the Line Item.");
		}
	//}
	//else
	//{
	//	alert("Please use the structure on the left to navigate the transaction.");
	//}
}

function button_reject()
{
   var loc = parent.content.document.location;
	var str = loc.toString();
	var pos = str.indexOf("Trans_Disp_Content.jsp");
   
	if(pos != -1)
	{
		var formStr = parent.content.document.forms[0];

		if(formStr == null)
		{
			alert("Please use the structure on the left to navigate the transaction.");
		}
		else
		{
            var reason = prompt("Please enter a Rejection Reason for this Transaction", "Type your Reason here");
			if(reason!=null){
            parent.content.document.form1.buttonClicked.value = "reject";
            parent.content.document.form1.rejectreason.value=reason;
			submitContent();
			setTimeout("alert('Transaction Has Been Rejected Successfully.');", 5000);
			setTimeout("top.window.close();", 2000);
			refreshParent();
            }
			//top.window.location.reload();
		}
	}
	else
	{
		alert("Please use the structure on the left to navigate the transaction.");
	}
}

function button_modify()
{
   var loc = parent.content.document.location;
	var str = loc.toString();
	var pos = str.indexOf("Trans_Disp_Content.jsp");

	if(pos != -1)
	{
		var formStr = parent.content.document.forms[0];

		if(formStr == null)
		{
			alert("Please use the structure on the left to navigate the transaction.");
		}
		else
		{


			var reason = prompt("Please enter a comment for this Transaction", "Type your comment here");
			if(reason!=null){
            parent.content.document.form1.buttonClicked.value = "reject";
           // parent.content.document.form1.rejectreason.value=reason;
			submitContent();
			setTimeout("alert('Transaction Has Been Modified Successfully.');", 5000);
			setTimeout("top.window.close();", 2000);
			refreshParent();
            }
			//top.window.location.reload();
		}
	}
	else
	{
		alert("Please use the structure on the left to navigate the transaction.");
	}
}
function button_kala_reject()
{
   var loc = parent.content.document.location;
	var str = loc.toString();
	var pos = str.indexOf("Trans_Disp_Content.jsp");
   
	if(pos != -1)
	{
		var formStr = parent.content.document.forms[0];

		if(formStr == null)
		{
			alert("Please use the structure on the left to navigate the transaction.");
		}
		else
		{
            parent.content.document.form1.buttonClicked.value = "reject";
            submitContent();
			setTimeout("alert('Transaction Has Been Rejected Successfully.');", 5000);
			setTimeout("top.window.close();", 2000);
			refreshParent();
        }
	}
	else
	{
		alert("Please use the structure on the left to navigate the transaction.");
	}
}
function button_pending()
{
	alert("Pending Functionality not implemented in this version.");
}

function button_send()
{
	var loc = parent.content.document.location;
	var str = loc.toString();
	var pos = str.indexOf("Trans_Disp_Content.jsp");

	var result = checkContent();

	if(result == true)
	{
		if(pos != -1)
		{
			var formStr = parent.content.document.forms[0];

			if(formStr == null)
			{
				alert("Please use the structure on the left to navigate the transaction.");
			}
			else
			{
				parent.content.document.form1.buttonClicked.value = "send";
				submitContent();
				alert("Acknoledgement Sent Successfully.");
				top.window.close();
			}
		}
		else
		{
			alert("Please use the structure on the left to navigate the transaction.");
		}
	}
	else
	{
		alert("Please Approve all elements (with a Orange tick) before submitting.");
	}
}



function button_accept_CO()
{
    var browserName=navigator.appName;
	var loc = parent.content.document.location;
	var str = loc.toString();
	var pos = str.indexOf("Trans_Disp_Content.jsp");
    var url;
	var result = checkContent();

	if(result == true)
	{
		if(pos != -1)
		{
			var formStr = parent.content.document.forms[0];

			if(formStr == null)
			{
				alert("Please use the structure on the left to navigate the transaction.");
			}
			else
			{
				// show the print form screen
               // if (browserName=="Microsoft Internet Explorer"){
		//		url = "pages/resolution/PrintForm.jsp?fileName="+nextFileName+"&printForm=false";
                //}else{
                  //  url = "PrintForm.jsp?fileName="+nextFileName+"&printForm=false";
                //}
		//		var r = showModalDialog(url, null, 'dialogWidth=590px;dialogHeight=480px');

		//		if(r == "true")
		//		{
                    var invoice_no = prompt("Please enter an Invoice Number for this Transaction", "Type your Invoice Number here");
					if(invoice_no!=null){
                    parent.content.document.form1.buttonClicked.value = "accept";
                    parent.content.document.form1.acceptInvoiceNo.value=invoice_no;
					submitContent();
                    setTimeout("alert('Transaction Has Been Accepted Successfully.');", 5000);
                    setTimeout("top.window.close();", 2000);
					//alert("Transaction Has Been Accepted Successfully.");
					refreshParent();
                 //   }
					//top.window.close();
				}
			}
		}
		else
		{
			alert("Please use the structure on the left to navigate the transaction.");
		}
	}
	else
	{
		alert("Please Approve all elements (with a Orange tick) before submitting.");
	}
}

function button_accept_PO()
{
	var loc = parent.content.document.location;
	var str = loc.toString();
	var pos = str.indexOf("Trans_Disp_Content.jsp");

	var result = checkContent();

	if(result == true)
	{
		if(pos != -1)
		{
            var formStr = parent.content.document.forms[0];

			if(formStr == null)
			{
				alert("Please use the structure on the left to navigate the transaction.");
			}
			else
			{
                var invoiceno = prompt("Please enter an Invoice Number for this Transaction", "Type your Invoice Number here");
				if(invoiceno!=null){
                parent.content.document.form1.buttonClicked.value = "accept";
                parent.content.document.form1.acceptInvoiceNo.value=invoiceno;
				submitContent();
				setTimeout("alert('Transaction Has Been Accepted Successfully.');", 5000);
                setTimeout("top.window.close();", 2000);
               // alert("Transaction Has Been Accepted Successfully.");
			   refreshParent();
               }
				//top.window.close();
				//top.window.location.reload();
			}
		}
		else
		{
			alert("Please use the structure on the left to navigate the transaction.");
		}
	}
	else
	{
		alert("Please Approve all elements (with a Orange tick) before submitting.");
	}
}

function button_kala_accept_PO()
{

	var loc = parent.content.document.location;
	var str = loc.toString();
	var pos = str.indexOf("Trans_Disp_Content.jsp");

	var result = checkContent();

	if(result == true)
	{
		if(pos != -1)
		{
            var formStr = parent.content.document.forms[0];

			if(formStr == null)
			{
				alert("Please use the structure on the left to navigate the transaction.");
			}
			else
			{
                parent.content.document.form1.buttonClicked.value = "accept";
				submitContent();
				setTimeout("alert('Transaction Has Been Accepted Successfully.');", 5000);
                setTimeout("top.window.close();", 2000);
               // alert("Transaction Has Been Accepted Successfully.");
			   refreshParent();
            }
		}
		else
		{
			alert("Please use the structure on the left to navigate the transaction.");
		}
	}
	else
	{
		alert("Please Approve all elements (with a Orange tick) before submitting.");
	}
}

// END bottom.htm functions

//************************************* ADDED FUNCTIONS *************************************

// check for any orange ticks on the current content form when submitting
function checkContent()
{
	var formOK = true;

	var counter = 0;
	//check that all the ticks are green or locks or greys..
	for (var i=0; i<parent.content.document.images.length; i++)
	{
		var imageSRC = parent.content.document.images[i].src;
		var pos = imageSRC.indexOf("orange");

		if(pos != -1)
		{
			formOK = false;
		}
	}
	return formOK;
}

// submit the content.jsp page in the frameset
function submitContent()
{
	//alert("Submit Content 1");
	parent.content.document.forms[0].submit();
	//alert("Submit Content 2");
}

//************************************* END ADDED FUNCTIONS *************************************


// JavaScript Document



/************ This is the string that is sent back from the Trans_Disp_Bean.java class *****************
var e1 = createObject(1, -1, "Purchase Order Root","PROCESS_PO_007","TestPurchaseOrder1.xml");
var e2 = createObject(2, "25", "Purchase Order Lines", "PROCESS_PO_007", "TestPurchaseOrder1.xml");
append(e1 , e2);
var e3 = createObject(3, "99", "PO Line", "PROCESS_PO_007", "TestPurchaseOrder1.xml");
append(e2 , e3);
var e8 = createObject(8, "75", "PO Line", "PROCESS_PO_007", "TestPurchaseOrder1.xml");
append(e2 , e8);
*/

// Create an object and return it to the assigned var
function createObject(id, edxID, englishName, transType, fileName,bgcolor) {
  var element = new Array();
  element[0] = id;
  element[1] = edxID;
  element[2] = englishName;
  element[3] = transType;
  element[4] = fileName;
  element[5] = bgcolor;
  element[6] = 0;
 // alert("create object,,"+element[5]);
  return element;
}

// Append a child to a parent object
function append(parent, child) {
  parent[parent.length] = child;
}

// Redraw the entire left tree frame window
function redrawTree() {
   // alert('redrawTree');
  var doc = top.left.window.document;
   // alert('2redrawTree');
  doc.clear();
   // alert('3redrawTree');
  doc.write("<HTML><HEAD><title>On The Fly</title></HEAD>");
  doc.write("<BODY BGCOLOR='#FFFFFF'>");
   // alert('4redrawTree');
  redrawNode(root, doc, 0, 1, "",1);
   // alert('5redrawTree');
  doc.write("</BODY></HTML>");
  doc.close();
}

// called when the openbook icon is clicked
function closeBook() {
   // alert("closebook");
  root[6] = 0;
  redrawTree();
}

// called when the closed book icon is clicked
function openBook() {
  //alert("openbook...");
  root[6] = 1;
  redrawTree();
}

// Recursive function that redraws the book structure...
function redrawNode(foldersNode, doc, level, lastNode, leftSide,idx)
{
	//alert("into redraw node 1");
  // create all required variables...
  var j=0;
  var i=0;
  var tick;
 // alert("1 into redraw node 1");
  var id = foldersNode[0];
 // alert("11 into redraw node 1");
  var url = foldersNode[1];
  var title = foldersNode[2];
  var transType = foldersNode[3];
  var fileName = foldersNode[4];
//alert("2into redraw node 1");
  var hasSubNode = (foldersNode.length>7);
  //var expanded = foldersNode[5];
  var bg=foldersNode[5];
  var expanded = foldersNode[6];

//  alert("bg..."+bg);
  if(bg=='RED')
        tick = "cross";
    else
        tick = "green";
//alert("into redraw node 2");
  doc.write("<TABLE BORDER=0 CELLSPACING=0" + " CELLPADDING=0>");
  doc.write("<TR><TD VALIGN=middle NOWRAP>");
  doc.write(leftSide);
//alert("3into redraw node 1.."+id);
   // if this is the book/root...
  //alert("inside redrawNode id.."+id);
  if (id==1)
  {
  //	alert("into redraw node 3");
  	// If the root has no children...
    //alert("root[6]..."+root[6]);
    if (root[6]==0)
      doc.write("<A HREF='javascript:top.openBook()'><IMG SRC=images/Trans_Disp/ClosedBook.gif BORDER=0></A>");
    else
      doc.write("<A HREF='javascript:top.closeBook()'><IMG SRC=images/Trans_Disp/OpenBook.gif BORDER=0></A>");
  }

  // create the linking code to call the clickNode function...
  var nodeLink = "<A HREF='javascript:top.clickNode(" + id + ")'>";


  // Level 0 is to the very left of the table...
  //alert("level.."+level);
  if (level>0)
  {
  	// the last folder in array
    //alert("lastnode.."+lastNode);
    if (lastNode)
	{
	  // if folders[5] has a value in it...
      // alert("last node hasSubNode.."+hasSubNode);
      if (hasSubNode)
	  {
	    //output the correct image...
        //  alert("last node..expanded.."+expanded);
        if (expanded)
          doc.write(nodeLink + "<IMG SRC='images/Trans_Disp/transLastNodeMinus.gif'" + " BORDER=0 >" + "</A>");
        else
          doc.write(nodeLink + "<IMG SRC='images/Trans_Disp/transLastNodePlus.gif'" + " BORDER=0 >" + "</A>");
      }
      else
	  {
	  	// This is the last node...
        doc.write("<IMG SRC='images/Trans_Disp/transLastNode.gif'" + " >");
        //if(title=='Item Line')
            doc.write("<font size='-1' face='Courier New, Courier, mono'><IMG SRC='images/Trans_Disp/"+tick+"Tick.jpg'></font>");
	  }
	  // add a blank spacer to the leftside...
      leftSide += "<IMG SRC='images/Trans_Disp/transblank.gif'" + " >";
    }
    else
	{
	  //not last folder
       //alert("no last node hasSubNode.."+hasSubNode);
      if (hasSubNode)
	  {
         //alert("no last node expanded.."+expanded);
        if (expanded)
          doc.write(nodeLink + "<IMG SRC='images/Trans_Disp/transNodeMinus.gif'" + " BORDER=0 >" + "</A>");
        else
          doc.write(nodeLink + "<IMG SRC='images/Trans_Disp/transNodePlus.gif'" + " BORDER=0 ></A>");
      }
      else
	  {
        doc.write("<IMG SRC='images/Trans_Disp/transNode.gif'>");
        //if(title=='Item Line')
            doc.write("<font size='-1' face='Courier New, Courier, mono'><IMG SRC='images/Trans_Disp/"+tick+"Tick.jpg'></font>");
	  }
      leftSide += "<IMG SRC='images/Trans_Disp/transVertLine.gif'>";
    }
  }

    doc.write("<TD>&nbsp;");
  // output the link to the clickEleemnt function...
  ///alert("url.."+url);
  if(url != "-1")
  {
	if(title=='Item Line') {
		doc.write("<font size='-1'><A HREF='javascript:top.clickElement("+ id + ", \"" + url + "\", \""+fileName+"\", \""+transType+"\", \""+title+"\")'>" + title+ " "+(idx-3) + "</A></font>");
		}
	else{	
	doc.write("<font size='-1'><A HREF='javascript:top.clickElement("+ id + ", \"" + url + "\", \""+fileName+"\", \""+transType+"\", \""+title+"\")'>" + title + "</A></font>");
	}
  }
  else
  {
	doc.write("<font size='-1'>"+ title + "</font>");
  }

  doc.write("</TABLE>")

  // Recursive part

  if (hasSubNode && expanded)
  {
//alert("hasSubNode && expanded.."+hasSubNode+"..."+expanded);
  	// move the level out to the right by one place...
    level++;
//	 alert("level.."+level+"..foldersNode.length."+foldersNode.length);
    for (i=7; i<foldersNode.length;i++)
	{
      if (i==foldersNode.length-1)
      	redrawNode(foldersNode[i], doc, level, 1, leftSide,++idx);
      else
        redrawNode(foldersNode[i], doc, level, 0, leftSide,++idx);
	}
  }
}


function clickElement(id, url, fileName, type,title) {
  // submit the content page
  var result = checkContent();
  title1=title;
  if(result == true)
  {
   //   alert("clickeleemnet.."+clickElement);
	  submitContent();
	  selectedElementId = id;
      redrawTree();
	  //parent.frames[2].location = "Trans_Disp_Content.jsp?id="+url+"&fileName="+fileName+"&transType="+type;
	  //parent.content.location = "Trans_Disp_Content.jsp?id="+url+"&fileName="+fileName+"&transType="+type;
	  nextUrl = url;
	  nextFileName = fileName;
	  nextType = type;
	  parent.content.document.form1.buttonClicked.value = "";
	  setTimeout("sendLocation()", 1000);
  }
  else
  {
  	alert("Please Approve all elements (with a Orange tick) before submitting.");
  }
}

function buttonCheckValues(nUrl, fName, tType,etitle)
{
	var result = checkContent();
	if(result == true)
	{
		checkUrl = nUrl;
		checkFileName = fName;
		checkType = tType;
		elementtype=etitle;
        parent.content.document.forms[0].buttonClicked.value = "checkvalues";
        submitContent();
		setTimeout("checkCurrValues()", 1000);
	}
	else
	{
		alert("Please Approve all elements (with a Orange tick) before submitting.");
	}
}

function checkCurrValues(nUrl, fName, tType)
{
	//alert("checkCurrValues location 1 url: "+nextUrl+", name: "+nextFileName+"' type:"+nextType);
	parent.content.location = "pages/resolution/Trans_Disp_Content.jsp?id="+checkUrl+"&fileName="+checkFileName+"&transType="+checkType+"&title="+elementtype;
}

function sendLocation()
{
	//alert("sending location 1 url: "+title1+", name: "+nextFileName+"' type:"+nextType);
	parent.content.location = "pages/resolution/Trans_Disp_Content.jsp?id="+nextUrl+"&fileName="+nextFileName+"&transType="+nextType+"&title="+title1;
	//parent.content.location = "pages/resolution/Trans_Disp_Content.jsp?title="+title1;
	//parent.content.location = "selectelement.action?eid="+nextUrl+"&fileName="+nextFileName+"&transType="+nextType;
}

// changes the clicked node from/to expanded/closed states...
function toggleNode(foldersNode, folderId)
{
  //alert("Calling toggle Node with :\n\nfoldersNode [0]: "+foldersNode[0]+"\nfoldersNode [1]: "+foldersNode[1]+"\nfoldersNode [2]: "+foldersNode[2]+"\nfoldersNode [3]: "+foldersNode[3]+"\nfoldersNode [4]: "+foldersNode[4]+"\nfoldersNode [5]: "+foldersNode[5]+"\n\nfolderID: "+folderId);
  // is this is the right node...
  if (foldersNode[0]==folderId)
  {
  	// change the element in the array to 0 or 1...
    foldersNode[6] = 1 - foldersNode[6];
  }
  else if (foldersNode[6]) //this node has children?
  {
  	// Else if this is not the clicked Node...
	//alert("foldersNode.length: "+foldersNode.length);
    for (var i=4; i< foldersNode.length; i++)
	{
      toggleNode(foldersNode[i], folderId);
	}
  }
}


function clickNode(folderId) {
    //alert("in clicknode");
  toggleNode(root, folderId);

  redrawTree();
}

function initialize() {
   // alert("initialize");
 // root = e1;
//var  abc='<s:property value="xmlString"/>';
//alert('2e1 val....'+abc);
//root = abc;
root = e1;
  redrawTree();
}

<%
	out.println(session.getAttribute("xmlString"));
%>

</SCRIPT>


<%
    String errorText = session.getAttribute("errorText").toString();
    String bottomSource=session.getAttribute("bottomSource").toString();
    String newFileName=session.getAttribute("newFileName").toString();
    String supNexusId=session.getAttribute("supplierid").toString();
	//String errorText = "Test ERROR";
	if(errorText.equals(""))
	{


%>
	<form name="form1">
	<input type="hidden" name="fileName" value="<%=newFileName%>" />
    <input type="hidden" name="supNexusId" value="<%=supNexusId%>" />
	</form>
	</HEAD>

<frameset rows="75,*,42" cols="*" framespacing="0" frameborder="no" border="0" onLoad="initialize()"  onBeforeUnload="form_cancel()">
  <% if(!supNexusId.equalsIgnoreCase("500000")){ %>
         <frame src="pages/resolution/Trans_Disp_Top_kalamunda.htm" name="topFrame" scrolling="NO" noresize >
   <% } else{ %>
           <frame src="pages/resolution/Trans_Disp_Top.htm" name="topFrame" scrolling="NO" noresize >
   <% } %>
	<frameset rows="*" cols="166,634" framespacing="0" frameborder="yes" border="1" bordercolor="#000000">
    <frame src="pages/resolution/Trans_Disp_Left.htm" name="left" id="left">
    <frame src="pages/resolution/Trans_Disp_Content_Splash.jsp?fileName=<%=newFileName%>" name="content" id="content">
  </frameset>
  <frame src="<%=bottomSource%>" name="bottomFrame" scrolling="NO" noresize>
</frameset>
	<noframes></noframes>
	</HTML>

<%
	}
	else
	{
	     out.println("error occured");
		// error occurred, delete this display object.
       // tManager.removeDisplayObject("<s:property value='newFileName'/>");
%>
	</HEAD>
	 <table width="80%" border="0" align="center" cellspacing="0" cellpadding="0">
        <tr>
          <td width="13%">&nbsp;</td>
          <td width="77%">&nbsp;</td>
          <td width="10%">&nbsp;</td>
        </tr>
        <tr>
          <td width="13%">&nbsp;</td>
          <td width="77%">&nbsp;</td>
          <td width="10%">&nbsp;</td>
        </tr>
        <tr bgcolor="#CC3300">
          <td colspan="3"> <div align="center"><b><font color="#FFFFFF" face="Courier New, Courier, mono">ERROR</font></b></div></td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td colspan="3">&nbsp;</td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td width="13%">&nbsp;</td>
          <td width="77%"><font size="-1" face="Courier New, Courier, mono">An
            Error occurred while processing this page. Please refer to the error
            message below.</font></td>
          <td width="10%">&nbsp;</td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td width="13%">&nbsp;</td>
          <td width="77%"><font size="-1" face="Courier New, Courier, mono">&nbsp;</font></td>
          <td width="10%">&nbsp;</td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td width="13%">&nbsp;</td>
          <td width="77%"><font size="-1" face="Courier New, Courier, mono"><b>Error:
            <%=errorText%> </b> </font></td>
          <td width="10%">&nbsp;</td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td width="13%">&nbsp;</td>
          <td width="77%"><font size="-1" face="Courier New, Courier, mono">&nbsp;</font></td>
          <td width="10%">&nbsp;</td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td width="13%">&nbsp;</td>
          <td width="77%"><font size="-1" face="Courier New, Courier, mono">Please
            contact your Systems Administrator to resolve this problem. </font></td>
          <td width="10%">&nbsp;</td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td colspan="3">&nbsp;</td>
        </tr>
        <tr bgcolor="#CC3300">
          <td colspan="3">&nbsp;</td>
        </tr>
      </table>
	</HTML>

<%
	}
%>


