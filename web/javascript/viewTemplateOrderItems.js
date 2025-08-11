$(function(){
    var templateId=$("#templateId").val();

    $("#delete").click(function(){
        var selectedRow = jQuery("#list").getGridParam('selarrrow');
        if( selectedRow!="" ){
            jQuery("#list").delGridRow(selectedRow,{
                reloadAfterSubmit:false,
                msg:'Delete selected item(s)?',
                caption:'Delete items',
                bSubmit:'Delete'
            });
        }
        else {
            alert("Please choose at least one item to delete");
        }
    });

    $("#add").click(function(){
        location.href='AddMoreTemplateOrderItems.action?templateId='+templateId;
    });

    $("#shoppingcart").click(function(){
        var selectedRow = $("#list").getGridParam('selarrrow');
        if( selectedRow!="" ){
            $("#shoppingcart").attr("disabled", "disabled");
            $.post("AddTemplateOrderItemsToShoppingcart.action",{
                templateOrderItemsId:selectedRow
            },function(result){
                location.href='CreateNewOrderForShoppingCart.action';
            });
        }
        else{
            alert("Please choose at least one item and add them to shopping cart");
        }
    });

    var unitsFormatter=function(el, cellval, opts){
        $(el).html("Each");

    };

    var taxFormatter=function(el, cellval, opts){
        $(el).html("GST");
    };

    $("#list").jqGrid({
        url:'ShowTemplateOrderItems.action?templateId='+templateId,
        datatype: "json",
        colNames:['S.O.H.','Product Item No', 'Product Description','Unit Price','Units','Tax'],
        colModel:[ {
            name:'soh',
            index:'soh',
            width:100
        },

        {
            name:'product_code',
            index:'product_code',
            width:150
        },

        {
            name:'description',
            index:'description',
            width:200
        },
        {
            name:'price',
            sortable:false,
            width:100,
            formatter:'currency',
            formatoptions:{
                prefix: "$",
                decimalPlaces: 3
            }
        },
        {
            name:'units',
            sortable:false,
            width:100,
            formatter:unitsFormatter
        },
        {
            name:'tax',
            sortable:false,
            width:100,
            formatter:taxFormatter
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager'),
        sortname: 'product_code',
        viewrecords: true,
        sortorder: "asc",
        height: "320px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect:true,
        editurl:'DeleteTemplateOrderItems.action'
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});