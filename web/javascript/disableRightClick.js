document.oncontextmenu = function(e) {
    if ( e && e.button == 2 && e.preventDefault )
        e.preventDefault();
    else
        window.event.returnValue = false;
}
document.onkeydown=function()
{
    if ((window.event.altKey)&&
        ((window.event.keyCode==37)||
            (window.event.keyCode==39)))
            {
        window.event.returnValue=false;
    }

    if(window.event.keyCode==8){
        var element=window.event.srcElement;
        if(element.tagName!="TEXTAREA"&&
            !(element.tagName=="INPUT"&&
                (element.type=="text"||
                    element.type=="password"||
                    element.type=="file"))){
            window.event.keyCode=0;
            window.event.returnValue=false;
        }
    }

    if ((window.event.keyCode==116)||
        (window.event.ctrlKey && window.event.keyCode==82)){
        window.event.keyCode=0;
        window.event.returnValue=false;
    }
    if (window.event.keyCode==122){
        window.event.keyCode=0;window.event.returnValue=false;
    }

    if (window.event.ctrlKey && window.event.keyCode==78) window.event.returnValue=false;

    if (window.event.shiftKey && window.event.keyCode==121)window.event.returnValue=false;

    if (window.event.srcElement.tagName == "A" && window.event.shiftKey)
        window.event.returnValue = false;
} 