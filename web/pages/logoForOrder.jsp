<%@ taglib prefix="s" uri="/struts-tags" %>
<!--<script type="text/javascript" src="javascript/logoForOrder.js"></script> -->
<script type="text/javascript" src="javascript/jquery.easynews.js"></script>
<link href="css/logo.css" rel="stylesheet" type="text/css"/>
<div id="header-contentsWithoutMenu">
    <div id="header-logo">
        <table width="100%">
            <tr>
                <td class="valignMiddle">
                    <a href="" title="Homepage">
                            <img src="images/${sessionScope.logoname}" alt="Homepage" title="Homepage" />
                        </a>
                </td>
            <!--    <td>
                    <div id='showhere'></div>
                    <div id=news_button>
                        <img src="images/news/prev.gif" align="absmiddle" id=news_prev><img src="images/news/pause.gif" align="absmiddle" id=news_pause><img src="images/news/next.gif" align="absmiddle" id=news_next >
                    </div>

                    <div id=news_display></div>
                    <div id=mynews></div>
                </td> -->
            </tr>
        </table>
    </div> <!-- end #header-logo -->
                         
    <div id="header-logoutWelcome" class="alignCenter valignMiddle">
        <div id="header-logoutWelcome-content">
            <img src="images/nexuspowered.jpg"/>
            <br/>
            <br/>
            <span class="bold">&middot;&nbsp;<a href="<s:url action="logout"/>" title="Log Out"><s:text name="Log Out"/></a>&nbsp;&middot;</span>
            <br/>
            Welcome - <%= session.getAttribute("mycompany").toString()%>
            <br/>
            <span class="bold"></span>
        </div>
    </div><!-- end #header-search -->	
</div><!-- end #header-contentsWithoutMenu -->	
