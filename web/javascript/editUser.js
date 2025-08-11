$(function(){
    $("#edit").click(function(){
        if($.trim($("#password").val())!=""){
            $("form").attr("action", "SaveUser.action").submit();
        }
        else{
            alert("Password is required.");
        }
    });
});