$(function(){
    $(":button:eq(1)").click(function(){
        var favouriteOrderId=$("#favouriteOrderId").val();
        window.open('PrintFavouriteItemList.action?favouriteOrderId='+favouriteOrderId,'viewOrder','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
    });

    $(":button:eq(0)").click(function(){
        var numericExpression = /^[0-9]+$/;
        var run=true;
        var i=0;
        var elementLength=$(":input").filter(".quantity").length;
        $(":input").filter(".quantity").each(function(){
            if($(this).val()!=""){
                if(($(this).val().match(numericExpression))&&($(this).val().charAt(0)!='0')){
                }
                else{
                    run=false;
                    $(this).focus();
                    return false;
                }
            }
            else{
                i++;
            }
        });
        
        if(run&&(i!=elementLength)){
            $("form").attr("action", "ConfirmFavouriteOrder.action").submit();
        }
        else{
            alert("Invalid Quantity!");
        }
    });
});