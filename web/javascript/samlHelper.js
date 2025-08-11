function sendSamlAssertion(result){
   var samlForm = '<html><head></head><body><h1>Loading...</h1>'
                        + '<form id="samlForm" name="samlForm" method="post" action="' + result.idpSSOUrl +'">'
                    	+ '<input type="hidden" name="SAMLResponse" value="' + result.samlAssertion + '"/>'
			+ '<input type="hidden" name="relayState" value="' + result.relayState + '"/>'
                        + '</form><script type="text/javascript">document.getElementById("samlForm").submit()</script><body></html>' 
 var partsCatalogue=window.open('','partsCatalogue','width=1000,height=800,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
 partsCatalogue.document.innerHtml = "";
 partsCatalogue.document.write(samlForm);
return partsCatalogue;
}
