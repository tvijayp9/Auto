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
        var selectedRow = $("#list").jqGrid('getGridParam','selarrrow');
		 if( selectedRow!="" ){
            $("#add").attr("disabled", "disabled");
			$.ajax({
				type: "POST",
				traditional: true,
				url: "AddShoppingCartItem.action",
				async: false,
				data: {productCode:selectedRow},
				dataType: "json",
			success: function(result){
                $("#shoppingcart").setGridParam({
                    url:"ShowShoppingCart.action?nd="+new Date().getTime()
                });
                $("#shoppingcart").setGridParam({
                    page:1
                });
                $("#shoppingcart").setGridParam({
                    rowNum:10
                });
                $("#shoppingcart").trigger("reloadGrid");
                $("#list").resetSelection();
                $("#add").removeAttr("disabled");
            }
       });
        }else{
            alert("Please choose at least one product and add them to shopping cart");
        }
    });

	$("#delete").click(function(){
        var selectedRow = $("#shoppingcart").getGridParam('selrow');
        if( selectedRow!=null ){
            $("#shoppingcart").delGridRow(selectedRow,{
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

    $("#checkout").click(function(){
		$.getJSON("CheckShoppingCart.action",function(result){
		    if(result.check){
		          $("form").attr("action", "CheckOut.action").submit();
            }
            else{
                alert("There is no item in your shopping cart, you could not check out");
            }
        });

    });

 $("#submit1").click(function(){
       $("form").attr("action", hookUrl).submit();
//       $("form").attr("action", "resetShoppingCart.action").submit();
    });

	$("#punchout").click(function(){
	    $.getJSON("CheckShoppingCart.action",function(result){
            if(result.check){
                    $("form").attr("action", "punchOut.action").submit();
                    //var punchoutCatalogue=window.open('punchOut.action','punchout','width=800,height=400,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
					//$("form").attr("action", "resetShoppingCart.action").submit();
			}
            else{
                alert("There is no item in your shopping cart, you could not check out");
            }
        });

    });

    $("#cxmlpunchout").click(function(){
	    $.getJSON("CheckShoppingCart.action",function(result){
            if(result.check){
                    $("form").attr("action", "cxmlpunchOut.action").submit();
           }
            else{
                alert("There is no item in your shopping cart, you could not check out");
            }
        });

    });

    $("#partsCatalogue").click(function(){
        supnexusId=document.form.supnexusid.value;
        $(document).stopTime("checkOrderReceived");
        //jQuery.getJSON("GetMicrocatInfo.action",function(result){
	$.ajax({
				type: "POST",
				traditional: true,
				url: "GetMicrocatInfo.action",
				cache: false,
				async: false,
				dataType: "json",	
		success: function(result){
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
       // });
	   }});
    });

	var unitsFormatter=function(el, cellval, opts){
		//   $(el).html("Each");
		return 'Each';
    };

    var taxFormatter=function(el, cellval, opts){
        //$(el).html("GST");
		return "GST";
    };
    $("#list").jqGrid({
        url:'ShowSupplierProductsByCategory.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['S.O.H.','Product Item No', 'Product Description','Unit Price','Units','Tax'],
        colModel:[ {
            name:'soh',
            index:'soh',
			hidden:true,
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
            width:250
        },
        {
            name:'price',
            sortable:false,
            width:100,
            formatter:'currency',
            formatoptions:{
                prefix: "$",
                decimalPlaces: 2
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

	jQuery("#list").jqGrid('navGrid','#pager',{add:false,del:false,edit:false, refresh:false,search:false});

    $("#shoppingcart").jqGrid({
	    url:'ShowShoppingCart.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Qty','S.O.H.','Product Item No', 'Product Description','Unit Price','Price','Tax','Cost'],
        colModel:[ {
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
        },
		{
            name:'soh',
            hidden:true,
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
                decimalPlaces: 2
            }
        },
        {
            name:'tax',
            sortable:false,
            width:100,
            formatter:'currency',
            formatoptions:{
                prefix: "$",
                decimalPlaces: 2
            }
        },
        {
            name:'cost',
            width:100,
            sortable:false,
            formatter:'currency',
            formatoptions:{
                prefix: "$",
                decimalPlaces: 2
            }
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#shoppingcartpager'),
        viewrecords: true,
        height: "235px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        editurl:'DeleteShoppingCartItem.action',
        cellEdit:true,
        cellsubmit:"remote",
        cellurl:"EditShoppingCart.action",
        afterSaveCell : function(rowid,name,val,iRow,iCol) {
            var unitPrice=$("#shoppingcart").getCell(rowid,iCol+4);
            var price=new Number(parseFloat(unitPrice)*parseInt(val)).toFixed(2);
            var tax=new Number(price*0.1).toFixed(2);
            var cost=new Number(Number(price)+Number(tax)).toFixed(2);
            $("#shoppingcart").setRowData(rowid,{
                price:price.toString()
            });
            $("#shoppingcart").setRowData(rowid,{
                tax:tax.toString()
            });
            $("#shoppingcart").setRowData(rowid,{
                cost:cost.toString()
            });
        }
    });
jQuery("#shoppingcart").jqGrid('navGrid','#shoppingcartpager',{add:false,del:false,edit:false,refresh:false,search:false});
});

(function($){
    $.afterCheckOrderReceived=function(partsCatalogue){
        $(document).stopTime("checkOrderReceived");
		

//$.ajax({
 // url: "CombineOrdersFromMicrocat.action?supnexusId="+supnexusId,
 // cache: false,
//  dataType: "json",
 // success: function(result) {
    
//}});
	  //  $.getJSON("CombineOrdersFromMicrocat.action?supnexusId="+supnexusId,function(result){
	  $.ajax({
				type: "POST",
				traditional: true,
				url: "CombineOrdersFromMicrocat.action",
				async: false,
				data: {supnexusId:supnexusId},
				dataType: "json",
			success: function(result){
			
            if(result.done){
			    partsCatalogue.close();
                $("body").unmask();
                $("#shoppingcart").setGridParam({
                    url:"ShowShoppingCart.action?nd="+new Date().getTime()
                });
                $("#shoppingcart").setGridParam({
                    page:1
                });
                $("#shoppingcart").setGridParam({
                    rowNum:10
                });
                $("#shoppingcart").trigger("reloadGrid");
            }
       // });
		}});
    }
})(jQuery);
