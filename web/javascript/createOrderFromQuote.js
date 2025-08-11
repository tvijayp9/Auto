$(function(){
    $(":button:eq(0)").click(function(){
        var expression = /^\w+$/;
        if($("#orderNumber").val().match(expression)){
            $(":button:eq(0)").attr("disabled", "disabled");
            $("form").attr("action", "CreateOrderItemsForQuote.action").submit();
        }
        else{
            alert("Please input letters or numbers only");
        }
    });

    $(":button:eq(1)").click(function(){
        var answer = confirm("Are You Sure? Do you Really Want to Cancel this Order?")
        if (answer){
            $("form").attr("action", "cancelorderreason.action").submit();
        }
    });

    $(":button:eq(2)").click(function(){
        var expression = /^\w+$/;
        if($("#orderNumber").val().match(expression)){
            $(":button:eq(2)").attr("disabled", "disabled");
            $("form").attr("action", "CreateFavouriteOrderForShoppingCart.action").submit();
        }
        else{
            alert("Please input letters or numbers only");
        }
    });
    
        $("#shoppingcart").jqGrid({
        url:'ShowQuoteShoppingCart.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Qty','S.O.H.','Product Item No', 'Product Description','Unit Price','Price','Tax','Cost'],
        colModel:[ {
            name:'quantity',
            width:100,
            sortable:false
        },
        {
            name:'soh',
            sortable:false,
            width:100
        },

        {
            name:'product_code',
            sortable:false,
            width:150
        },

        {
            name:'description',
            sortable:false,
            width:200
        },
        {
            name:'unitPrice',
            sortable:false,
            width:100,
            hidden:true
        },
        {
            name:'price',
            sortable:false,
            width:100,
            formatter:'currency',
            formatoptions:{prefix: "$",decimalPlaces: 3}
        },
        {
            name:'tax',
            sortable:false,
            width:100,
            formatter:'currency',
            formatoptions:{prefix: "$",decimalPlaces: 3}
        },
        {
            name:'cost',
            width:100,
            sortable:false,
            formatter:'currency',
            formatoptions:{prefix: "$",decimalPlaces: 3}
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#shoppingcartpager'),
        viewrecords: true,
        height: "235px",
        imgpath: "javascript/jqGrid/themes/basic/images"
    });

    $("#shoppingcart").navGrid('#shoppingcartpager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});