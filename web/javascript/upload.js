$(function(){
    $(":button:eq(0)").click(function(){
        if($("#partner").val()!=1){
            if($("#type").val()!=0){
                if($("#documentId").val()!=""){
                    if($("#file").val()!=""){
                        $("form").attr("action", "doUpload.action").submit();
                    }
                    else{
                        $("#file").focus();
                        alert("Please select your file");
                    }
                }
                else{
                    $("#documentId").focus();
                    alert("Please write your document ID");
                }
            }
            else{
                alert("Please choose your transaction type");
            }
        }
        else{
            alert("Please choose your trading partner");
        }
    });
});