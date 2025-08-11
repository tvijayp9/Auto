var supnexusId;
$(function(){
    $("#search").click(function(){
        var searchFor=$("#searchFor").val();
        var searchIn=$("#searchIn").val();
        if(searchFor!=""){
            $("#list").setGridParam({
                url:"ShowSupplierProductsByCategory.action?searchFor="+searchFor+"&searchIn="+searchIn
            });
            $("#list").setGridParam({
                page:1
            });
            $("#list").setGridParam({
                rowNum:10
            });
            $("#list").trigger("reloadGrid");
        }
        else{
            alert("Please enter the details of the search");
        }
    });

    $("#clear").click(function(){
        $("#searchFor").val("");
        $("#list").setGridParam({
            url:"ShowSupplierProductsByCategory.action?nd="+new Date().getTime()
        });
        $("#list").setGridParam({
            page:1
        });
        $("#list").setGridParam({
            rowNum:10
        });
        $("#list").trigger("reloadGrid");
    });

    $("#add").click(function(){
        var selectedRow = $("#list").getGridParam('selarrrow');
        if( selectedRow!="" ){
            $("#add").attr("disabled", "disabled");
            $.post("AddQuoteItem.action",{
                productCode:selectedRow
            },function(result){
                $("#newQuote").setGridParam({
                    url:"ShowQuote.action?nd="+new Date().getTime()
                });
                $("#newQuote").setGridParam({
                    page:1
                });
                $("#newQuote").setGridParam({
                    rowNum:10
                });
                $("#newQuote").trigger("reloadGrid");
                $("#list").resetSelection();
                $("#add").removeAttr("disabled");
            });
        }
        else{
            alert("Please choose at least one product and add them to quote");
        }
    });

    $("#delete").click(function(){
         var selectedRow = $("#newQuote").getGridParam('selrow');
        if( selectedRow!=null ){
            $("#newQuote").delGridRow(selectedRow,{
                reloadAfterSubmit:false,
                msg:'Delete selected item(s)?',
                caption:'Delete item',
                bSubmit:'Delete',
                top:300,
                left:300
            });
        }
        else {
            alert("Please choose at least one item to delete");
        }
    });

    $("#save").click(function(){
        if($("#templateName").val()!=""){
            $.getJSON("CheckQuote.action",function(result){
                if(result.check){
                    $("form").attr("action", "SaveNewQuote.action").submit();
                }
                else{
                    alert("There is not item in your quote, you could not save");
                }
            });
        }
        else{
            alert("Please input quote name");
        }
    });

    $("#save1").click(function(){
        $.getJSON("CheckQuote.action",function(result){
            if(result.check){
                $("form").attr("action", "SaveModifiedQuote.action").submit();
            }
            else{
                alert("There is not item in your quote, you could not save");
            }
        });
    });
	
	$("#modify").click(function(){
        $("form").attr("action", "modifyQuote.action").submit();
    });
	
	 $("#checkout").click(function(){
        if($("#templateName").val()!=""){
            $.getJSON("CheckQuote.action",function(result){
                if(result.check){
                    $("form").attr("action", "CheckoutNewQuote.action").submit();
                }
                else{
                    alert("There is not item in your quote, you could not save");
                }
            });
        }
        else{
            alert("Please input quote name");
        }
    });

	var unitsFormatter=function(el, cellval, opts){
        $(el).html("Each");

    };

    var taxFormatter=function(el, cellval, opts){
        $(el).html("GST");
    };

    $("#list").jqGrid({
        url:'ShowSupplierProductsByCategory.action?nd='+new Date().getTime(),
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
        height: "235px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect:true
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
    
    $("#newQuote").jqGrid({
        url:'ShowQuote.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Qty','S.O.H.','Product Item No', 'Product Description','Unit Price','Price','Tax','Cost'],
        colModel:[{
            name:'quantity',
            width:100,
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
            formatoptions:{
                prefix: "$",
                decimalPlaces: 3
            }
        },
        {
            name:'totaltax',
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
            width:100,
            sortable:false,
            formatter:'currency',
            formatoptions:{
                prefix: "$",
                decimalPlaces: 3
            }
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#templatepager'),
        viewrecords: true,
        height: "235px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        editurl:'DeleteQuoteItem.action',
        cellEdit:true,
        cellsubmit:"remote",
        cellurl:"EditQuote.action",
		afterSaveCell : function(rowid,name,val,iRow,iCol) {
            var unitPrice=$("#newQuote").getCell(rowid,iCol+4);
            var price=new Number(parseFloat(unitPrice)*parseInt(val)).toFixed(2);
            var tax=new Number(price*0.1).toFixed(2);
            var cost=new Number(Number(price)+Number(tax)).toFixed(2);
			//alert('price='+price);
            $("#newQuote").setRowData(rowid,{
                price:price.toString()
            });
            $("#newQuote").setRowData(rowid,{
                totaltax:tax.toString()
            });
            $("#newQuote").setRowData(rowid,{
                cost:cost.toString()
            });
        }
    });
	
	
	
       
        
		
		

    $("#newQuote").navGrid('#templatepager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });

        $("#partsCatalogue").click(function(){
        supnexusId=document.form.supnexusid.value;
        $(document).stopTime("checkOrderReceived");
        $.getJSON("GetMicrocatInfo.action",function(result){
            if(result.available){
                var partsCatalogue = sendSamlAssertion(result);
                $("body").mask("Waiting...");
                $(document).everyTime("10s","checkOrderReceived",function(){
                    if(partsCatalogue.closed){
                        catalogueService.checkOrderReceived(result.microcatId,result.accountNumber,supnexusId,function(result){
                            if(result){
                                $.afterCheckOrderReceived(partsCatalogue);
                            }
                            else{
                                $("body").unmask();
                            }
                        });
                    }
                    else{
                        catalogueService.checkOrderReceived(result.microcatId,result.accountNumber,supnexusId,function(result){
                            if(result){
                                $.afterCheckOrderReceived(partsCatalogue);
                            }
                        });
                    }
                },true);
            }
            else{
                alert("There is no free microcat account available. Please Try Later.");
            }
        });
    });
});

(function($){
    $.afterCheckOrderReceived=function(partsCatalogue){
        $(document).stopTime("checkOrderReceived");
        $.getJSON("CombineOrdersFromMicrocatForQuote.action?supnexusId="+supnexusId,function(result){
            if(result.done){
                partsCatalogue.close();
                $("body").unmask();
                $("#newQuote").setGridParam({
                    url:"ShowQuote.action?nd="+new Date().getTime()
                });
                $("#newQuote").setGridParam({
                    page:1
                });
                $("#newQuote").setGridParam({
                    rowNum:10
                });
                $("#newQuote").trigger("reloadGrid");
            }
        });
    }
})(jQuery);
