   var xmlhttp=null;



  function retrieveURL(url)
  {
       // code for Mozilla, etc.
		if (window.XMLHttpRequest)
		  {
		   xmlhttp=new XMLHttpRequest();
		  }
		// code for IE
		else if (window.ActiveXObject)
		  {
          xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		if (xmlhttp!=null)
		  {
		  alert('xmlhttp is not null');
		  xmlhttp.onreadystatechange=showResult;
		  xmlhttp.open("POST",url,true);
		  xmlhttp.send(url);
		  }
		else
		  {
		  alert("Your browser does not support XMLHTTP.");
		  }
  }

  function showResult(){
        if(xmlhttp.readyState == 4){
          alert("response="+xmlhttp.status);

          if (xmlhttp.status == 200) {

          alert('successfully sent');
	  }
	  }
	  }