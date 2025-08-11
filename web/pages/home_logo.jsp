<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="header">               
    <div id="header-contentsWithoutMenu">
        <div id="header-logo">
            <table width="100%">
                <tr>
                    <td class="valignMiddle">
                        <a href="" title="Nexus Homepage"><img src="images/nexus-logo.jpg" width="97px" height="41px" alt="Nexus Homepage" title="Nexus Homepage" /></a>
                    </td>
                    <td class="padLeft12 valignTop">
                        <a href="http://www.xmlyes.com" title="XML Yes Homepage"><img src="images/xml-yes-logo.jpg" width="29px" height="25px" alt="XML Yes Homepage" title="XML Yes Homepage" /></a>
                    </td>
                </tr>
            </table>
        </div> 
        <div id="header-logoutWelcome" class="alignCenter valignMiddle">
        <div id="header-logoutWelcome-content">
      
            <span class="bold"></span>
        </div>
    </div><!-- end #header-search -->	
    </div>
    <table width="100%">
        <tr>
            <td class="valignMiddle">
                <a href="" title="Nexus Homepage"><img src="images/consulting3.jpg" alt="Nexus Homepage" title="Nexus Homepage" /></a>
            </td>                    
        </tr>
    </table>            
    <div id="subMenu">
        <s:i18n name="homePage">
        <ul>
            <li><a href="<s:url action="welcome.action"/>" title="Home" ><s:text name="homePage.home"/></a></li>
            <li><a title="About" ><s:text name="homePage.about"/></a></li>
            <li><a href="<s:url action="Register.action"/>" title="Registration" ><s:text name="homePage.registration"/></a></li>
            <li><a href="<s:url action="forgetpassword.action"/>" title="Forgot Password" ><s:text name="homePage.forgotPassword"/></a></li>
            <li><s:url id="url" action="login.action"/><s:a href="%{#url}"><s:text name="homePage.login"/></s:a></li>
            <li><a href="<s:url action="contact.action"/>"><s:text name="homePage.contactUs"/></a></li>
        </ul>
        </s:i18n>
    </div>
</div>
