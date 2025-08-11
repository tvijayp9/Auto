var supnexusId;
var categoryName;
$(function(){
    $("#search").click(function(){
        var searchFor=$("#searchFor").val();
        var searchIn=$("#searchIn").val();
        $("#sitename").val("");
        if(searchFor!=""){
            $("#list").setGridParam({
                url:"ShowBuyerProductsBySupplier.action?searchFor="+searchFor+"&searchIn="+searchIn
            });
            $("#list").setGridParam({
                page:1
            });
            $("#list").setGridParam({
                rowNum:10
            });
            $("#categories").empty();
            $("#list").trigger("reloadGrid");
             
        }
        else{
            alert("Please enter the details of the search");
        }
    });

    $("#clear").click(function(){
        $("#searchFor").val("");
        $("#sitename").val("");
        $("#list").setGridParam({
            url:"ShowBuyerProductsBySupplier.action?nd="+new Date().getTime()
        });
        $("#list").setGridParam({
            page:1
        });
        $("#list").setGridParam({
            rowNum:10
        });
        $("#categories").empty();
        $("#list").trigger("reloadGrid");
    });
    
    $("#search1").click(function(){
        var siteName=$("#sitename").val();
        categoryName=$("#categories option:selected").text();
        if(searchFor!=""){
            $("#list").setGridParam({
                url:"ShowBuyerProductsBySupplier.action?sitename="+siteName+"&categoryname="+categoryName
            });
            $("#list").setGridParam({
                page:1
            });
            $("#list").setGridParam({
                rowNum:10
            });
            $("#searchFor").val("");
            $("#list").trigger("reloadGrid");
        }
        else{
            alert("Please enter the details of the search");
        }
    });
    
    $("#clearsite").click(function(){
        $("#sitename").val("");
        $("#list").setGridParam({
            url:"ShowBuyerProductsBySupplier.action?nd="+new Date().getTime()
        });
        $("#list").setGridParam({
            page:1
        });
        $("#list").setGridParam({
            rowNum:10
        });
        $("#categories").empty();
        $("#list").trigger("reloadGrid");
    });
    
    $("#add").click(function(){
        var selectedRow = $("#list").jqGrid('getGridParam','selarrrow');
		 if( selectedRow!="" ){
            $("#add").attr("disabled", "disabled");
			$.ajax({
				type: "POST",
				traditional: true,
				url: "AddCatalogueShoppingCartItem.action",
				async: false,
				data: {productCode:selectedRow},
				dataType: "json",
			success: function(result){
                $("#shoppingcart").setGridParam({
                    url:"ShowCatalogueShoppingCart.action?nd="+new Date().getTime()
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
    });

	$("#punchout").click(function(){
	    $.getJSON("CheckShoppingCart.action",function(result){
            if(result.check){
                    $("form").attr("action", "punchOut.action").submit();
			}
            else{
                alert("There is no item in your shopping cart, you could not check out");
            }
        });

    });

    $("#sitename").change(
            function() {
                $('#categories').html('');
                 var sitename=$("#sitename").val();
                $.ajax({
                    url: "readCategoriesBySite.action?sitename="+sitename,
                    dataType: 'json',
                    contentType: 'application/json',
                    type: 'POST',
                    async: true,
                    success: function(res) {
                        console.log(res.categoryNames.length);
                        $('#categories').append('<option></option>');
                        for (var i = 0; i < res.categoryNames.length; i++) {
                            console.log(" " + res.categoryNames[i]);
                            $('#categories').append(
                                    '<option value=' + res.categoryNames[i] + '>'
                                    + res.categoryNames[i]
                                    + '</option>');
                        }
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
	   }});
    });

	var unitsFormatter=function(el, cellval, opts){
		return 'Each';
    };

    var taxFormatter=function(el, cellval, opts){
		return "GST";
    };
    $("#list").jqGrid({
        url:'ShowBuyerProductsBySupplier.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['S.O.H','Location - Machine Type','Asset Name – Category','Product Item No', 'Product Description','Unit Price','Units','Tax'],
        colModel:[ {
            name:'soh',
            index:'soh',
            hidden:true,
            width:50
        },
        {
            name:'site_name',
            index:'site_name',
            width:175
        },
        {
            name:'category_name',
            index:'category_name',
            width:200
        },
		{
            name:'product_code',
            index:'product_code',
            width:125
        },

        {
            name:'description',
            index:'description',
            width:300
        },
        {
            name:'price1',
            sortable:false,
            width:75,
            formatter:'currency',
            formatoptions:{
                prefix: "$",
                decimalPlaces: 2
            }
        },
        {
            name: 'units',
            sortable: false,
            width: 50,
            hidden:true,
            formatter: unitsFormatter
        },
        {
            name: 'tax',
            sortable: false,
            width: 50,
            hidden:true,
            formatter: taxFormatter
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager'),
        sortname: 'site_name',
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
        colNames:['Qty','Site Name','Category Name','Product Item No', 'Product Description','Unit Price','Price','Tax','Cost'],
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
            name:'site_name',
            sortable:true,
            width:100
        },
        {
            name:'category_name',
            sortable:true,
            width:150
        },
        {
            name:'product_code',
            sortable:false,
            width:150
        },

        {
            name:'description',
            sortable:false,
            width:220
        },
        {
            name:'unitPrice',
            sortable:false,
            width:75,
            hidden:true
        },
        {
            name:'price',
            sortable:false,
            width:75,
            formatter:'currency',
            formatoptions:{
                prefix: "$",
                decimalPlaces: 2
            }
        },
        {
            name:'tax',
            sortable:false,
            width:75,
            formatter:'currency',
            formatoptions:{
                prefix: "$",
                decimalPlaces: 2
            }
        },
        {
            name:'cost',
            width:75,
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
        editurl:'DeleteCatalogueShoppingCartItem.action',
        cellEdit:true,
        cellsubmit:"remote",
        cellurl:"EditCatalogueShoppingCart.action",
        afterSaveCell : function(rowid,name,val,iRow,iCol) {
            var unitPrice=$("#shoppingcart").getCell(rowid,iCol+6);
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
		}});
    }
})(jQuery);
