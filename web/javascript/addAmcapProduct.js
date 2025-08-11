$(function() {
    $("#add").click(function() {
        var inputqty = $.trim($("#qty").val());
        var inputproductcode = $.trim($("#productcode").val());
        var inputdescription = $.trim($("#description").val());
        var inputprice = $.trim($("#price").val());
        
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
        } else if(isNaN(inputqty)){
            alert('Qty is numeric');
            return false;
        } else if(isNaN(inputprice)){
            alert('Price is numeric');
            return false;
        }
        
            $.ajax({
                type: "POST",
                url: "AddAmcapProductItem.action",
                data: {qty: inputqty, productcode: inputproductcode, description: inputdescription, price: inputprice},
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