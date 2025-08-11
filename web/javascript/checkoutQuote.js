$(function(){
    $("#deliveryDate").datepicker({
        minDate: '+0',
        maxDate: '+1y',
        changeYear: true,
        changeMonth: true,
        dateFormat: 'dd/mm/yy',
        showOn:'both',
        buttonImage:'images/calendar.gif',
        buttonImageOnly:true,
        firstDay:1,
        showStatus: true      
    });

    $(":button:eq(0)").click(function(){
        $("form").attr("action", "GotoCategoriesProducts.action").submit();
    });

    $(":button:eq(1)").click(function(){
        if($("#deliveryDate").val()!=""){
            $(":button:eq(1)").attr("disabled", "disabled");
            $("form").attr("action", "CreateOrderFromShoppingCart.action").submit();
        }
        else{
            alert("Please input order delivery date");
        }
    });
    
    $("#shoppingcart").jqGrid({
        url:'ShowShoppingCart.action?nd='+new Date().getTime(),
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