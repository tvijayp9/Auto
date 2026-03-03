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
        location.href='AddMoreAmcapQuoteItems.action?templateId='+templateId;
    });

    $("#punchout").click(function(){
        var selectedRow = $("#list").getGridParam('selarrrow');
        if( selectedRow!="" ){
           location.href="AmcapQuoteCheckOut.action?itemsId="+selectedRow+"&templateId="+templateId;
        }
        else{
            alert("Please choose at least one item and convert to order");
        }
    });
    
    var unitsFormatter=function(el, cellval, opts){
        $(el).html("Each");

    };

    var taxFormatter=function(el, cellval, opts){
        $(el).html("GST");
    };

    var statusFormatter=function(el, cellval, opts){
        if(cellval==0){
            $(el).html("Ordered");
        }
        else{
            $(el).html(" ");
        }
    };

    $("#list").jqGrid({
        url:'ShowAmcapQuoteItems.action?nd=' + new Date().getTime(),
        datatype: "json",
        colNames:['Qty','Product Item No', 'Product Description','Unit Price','Price','Tax','Cost','Lead Time(from date of order)'],
        colModel:[ {
            name:'quantity',
            width:50,
            sortable:false,
            editable:true,
            editrules:{
                required:true,
                number:true,
                minValue:1,
                maxValue:999
            }
        },
        {
            name:'product_code',
            index:'product_code',
            width:130
        },

        {
            name:'description',
            index:'description',
            width:200
        },
        {
            name:'unitPrice',
            sortable:false,
            width:30,
            hidden:true
        },
        {
            name:'price',
            sortable:false,
            width:80,
            formatter:'currency',
            formatoptions:{
                prefix: "$",
                decimalPlaces: 3
            }
        },
        {
            name:'tax',
            sortable:false,
            width:100,
            formatter:'currency',
            formatoptions:{
                prefix: "$",
                decimalPlaces: 3
            }
        },
        {
            name:'cost',
            sortable:false,
            width:100,
            formatter:'currency',
            formatoptions:{
                prefix: "$",
                decimalPlaces: 3
            }
        },
        {
            name:'leadtime',
            index:'leadtime',
            width:200
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
        editurl:'DeleteQuoteItems.action',
        cellEdit:true,
        cellsubmit:"remote",
        cellurl:"EditAmcapQuoteItemQuantity.action",
        afterSaveCell : function(rowid,name,val,iRow,iCol) {
            var unitPrice=$("#list").getCell(rowid,iCol+4);
            var price=new Number(parseFloat(unitPrice)*parseInt(val)).toFixed(2);
            var tax=new Number(price*0.1).toFixed(2);
            var cost=new Number(Number(price)+Number(tax)).toFixed(2);
            $("#list").setRowData(rowid,{
                price:price.toString()
            });
            $("#list").setRowData(rowid,{
                totaltax:tax.toString()
            });
            $("#list").setRowData(rowid,{
                cost:cost.toString()
            });
            $("#list").trigger("reloadGrid");
        }
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
    
    $("#list1").jqGrid({
        url:'ShowAmcapQuoteItems.action?templateId='+templateId,
        datatype: "json",
        colNames:['Qty','Product Item No', 'Product Description','Unit Price','Price','Tax','Cost','Lead Time(from date of order)'],
        colModel:[ {
            name:'quantity',
            width:60,
            sortable:false
        },

        {
            name:'product_code',
            index:'product_code',
            width:130
        },

        {
            name:'description',
            index:'description',
            width:200
        },
        {
            name:'unitPrice',
            sortable:false,
            width:50,
            hidden:true
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
            name:'tax',
            sortable:false,
            width:100,
            formatter:'currency',
            formatoptions:{
                prefix: "$",
                decimalPlaces: 3
            }
        },
        {
            name:'cost',
            sortable:false,
            width:100,
            formatter:'currency',
            formatoptions:{
                prefix: "$",
                decimalPlaces: 3
            }
        },

        {
            name:'leadtime',
            index:'leadtime',
            width:200
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager'),
        sortname: 'product_code',
        viewrecords: true,
        sortorder: "asc",
        height: "320px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect:false
    });

    $("#list1").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});