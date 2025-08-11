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
        location.href='AddMoreQuoteItems.action?templateId='+templateId;
    });

    $("#shoppingcart").click(function(){
        var selectedRow = $("#list").getGridParam('selarrrow');
        if( selectedRow!="" ){
            var exit=false;
            for(var i=0;i<selectedRow.length;i++){
                var status=$("#list").getCell(selectedRow[i],8);
                if(status.match("Ordered")){
                    exit=true;
                    break;
                }
            }
            if(exit){
                alert("The items that have been ordered previously cannot be re-ordered from this quotation.");
            }
            else{
                location.href="QuoteCheckOut.action?itemsId="+selectedRow+"&templateId="+templateId;
            }
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
        url:'ShowQuoteItems.action?templateId='+templateId,
        datatype: "json",
        colNames:['Qty','S.O.H.','Product Item No', 'Product Description','Unit Price','Units','Tax','Status'],
        colModel:[ {
            name:'quantity',
            width:80,
            sortable:false,
            editable:true,
            editrules:{
                required:true,
                number:true,
                minValue:1,
                maxValue:999
            }
        },{
            name:'soh',
            index:'soh',
            width:80
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
            width:80,
            formatter:unitsFormatter
        },
        {
            name:'tax',
            sortable:false,
            width:80,
            formatter:taxFormatter
        },
        {
            name:'status',
            sortable:false,
            width:80,
            formatter:statusFormatter
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
        cellurl:"EditQuoteItemQuantity.action"
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});