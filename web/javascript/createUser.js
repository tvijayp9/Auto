$(function(){
    $("#create").click(function(){
        var userId=$("#userId").val();
        if($.trim(userId)!=""){
            administrationService.checkUserId(userId,function(result){
                if(result){
                    if($.trim($("#password").val())!=""){
                        if($("#roles").val()!=0){
                            $("form").attr("action", "CreateUserWithRole.action").submit();
                        }
                        else{
                            alert("Role is required.");
                        }
                    }
                    else{
                        alert("Password is required.");
                    }
                }
                else{
                    alert("User ID is not available.");
                }
            });
        }
        else{
            alert("User ID is required.");
        }
    });
});