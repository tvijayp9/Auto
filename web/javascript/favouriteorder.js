$(function(){
    $(":button:eq(0)").click(function(){
        var stt=$("#saveAs").val();
        if(stt!=0&&stt!=null){
            if($.trim($("#name").val())!=""){
                $(":button:eq(0)").attr("disabled", "disabled");
                $("form").attr("action", "AddFavouriteOrder.action").submit();
            }
            else{
                alert("The display name for the favourite order is required");
            }
        }
        else{
            alert("Please select save the current shopping cart as");
        }
    })
});