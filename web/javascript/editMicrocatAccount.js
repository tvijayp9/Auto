$(function(){ 
    $(":button:eq(0)").click(function(){
        var run=true;
        $(":input").filter(".textValue").each(function(){
            if($.trim($(this).val())==""){
                run=false;
                $(this).focus();
                return false;
            }
        });
        if(run){
            $("form").attr("action", "DoEditMicrocatAccount.action").submit();
        }else{
            alert("The value is required!");
        }
    });
});