$(function() {
    $("#add").click(function() {
        var inputqty = $.trim($("#qty").val());
        var inputproductcode = $.trim($("#productcode").val());
        var inputdescription = $.trim($("#description").val());
        var inputprice = $.trim($("#price").val());
        var inputleadTime = $.trim($("#leadtime").val());
        if (inputqty == "") {
            alert('Qty is required');
            return false;
        } else if (inputproductcode == "") {
            alert('Product Item No is required');
            return false;
        } else if (inputdescription == "") {
            alert('Product Description is required');
            return false;
        } else if (inputprice == "") {
            alert('Price is required');
            return false;
        } else if (inputleadTime == "") {
            alert('Lead Time is required');
            return false;
        } else if(isNaN(inputqty)){
            alert('Qty is numeric');
            return false;
        } else if(isNaN(inputleadTime)){
            alert('Lead Time is numeric');
            return false;
        } else if(Number(inputleadTime) <= 0){
            alert('Lead Time should be greater than 0');
            return false;
        } else if(isNaN(inputprice)){
            alert('Price is numeric');
            return false;
        }
        
            $.ajax({
                type: "POST",
                url: "AddAmcapProductItem.action",
                data: {qty: inputqty, productcode: inputproductcode, description: inputdescription, price: inputprice, leadtime: inputleadTime},
                success: function() {
                    window.close();
                    if (window.opener && !window.opener.closed) {
                        window.opener.location.reload();
                    }
                }
            })
    });
    $("#close").click(function() {
        window.close();
   });
});