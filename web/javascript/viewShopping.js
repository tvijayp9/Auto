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
            $.post("AddShoppingCartItem.action",{
                productCode:selectedRow
            },function(result){
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
            });
        }
        else{
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
//                    var punchoutCatalogue=window.open('punchOut.action','punchout','width=800,height=400,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
//					$("form").attr("action", "resetShoppingCart.action").submit();
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
	
    $("#scaniaCatalogue").click(function(){
        supnexusId=document.form.supnexusid.value;
		//alert('supnexusId55=='+supnexusId);
        $(document).stopTime("checkScaniaOrderReceived");
		$.ajaxSetup({ cache: false});
        $.getJSON("GetScaniaInfo.action",function(result){
		
		//alert('available=='+result.available+"...username=="+result.username);
            if(result.available){
               // var partsCatalogue=window.open('https://www.microcatmarket.com/mmcv3/authenticateV3.aspx?language3=en&franchise3=TMC&username3='+result.username+'&password3='+result.password,+'partsCatalogue','width=1000,height=800,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
											//	 https://spp-test.scania.com/Site/User/Login.aspx?Username=demouser@pitzner.com&Password=Scania08
											//alert('username=='+result.username);
			    var scaniaCatalogue=window.open('https://spp.scania.com/Site/CustomerLogin/Login.aspx?Username='+result.username+'&Password='+result.password+'&callback=?',+'scaniaCatalogue','width=1000,height=800,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
				$("body").mask("Waiting...");
                $(document).everyTime("10s","checkScaniaOrderReceived",function(){
                    if(scaniaCatalogue.closed){
                        catalogueService.checkScaniaOrderReceived(result.microcatId,result.accountNumber,supnexusId,result.email,function(result){
                            if(result){
								$.afterCheckOrderReceived(scaniaCatalogue);
                            }
                            else{
                                $("body").unmask();
                            }
                        });
                    }
                    else{
                        catalogueService.checkScaniaOrderReceived(result.microcatId,result.accountNumber,supnexusId,result.email,function(result){
                            if(result){
							    $.afterCheckOrderReceived(scaniaCatalogue);
                            }
                        });
                    }
                },true);
            }
            else{
                alert("There is no free Scania accounts available. Please Try Later.");
            }
        });
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
            width:200
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
                prefix: "$"
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
            sortable:false,
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
            width:250
        },
        {
            name:'unitPrice',
            sortable:false,
            width:100,
            formatter:'currency',
            formatoptions:{
                prefix: "$"
            }
        },
        {
            name:'price',
            sortable:false,
            width:100,
            formatter:'currency',
            formatoptions:{
                prefix: "$"
            }
        },
        {
            name:'tax',
            sortable:false,
            width:100,
			hidden:true,
            formatter:'currency',
            formatoptions:{
                prefix: "$"
            }
        },
        {
            name:'cost',
            width:100,
            sortable:false,
			hidden:true,
            formatter:'currency',
            formatoptions:{
                prefix: "$"
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
			unitPrice=unitPrice.replace("$","")
            var price=new Number(parseFloat(unitPrice)*parseInt(val)).toFixed(2);
            //var tax=new Number(price*0.1).toFixed(2);
            //var cost=new Number(Number(price)+Number(tax)).toFixed(2);
            $("#shoppingcart").setRowData(rowid,{
                price:price.toString()
            });
            //$("#shoppingcart").setRowData(rowid,{
            //    tax:tax.toString()
            //});
            //$("#shoppingcart").setRowData(rowid,{
              //  cost:cost.toString()
            //});
        }
    });

    $("#shoppingcart").navGrid('#shoppingcartpager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});

(function($){
    $.afterCheckOrderReceived=function(scaniaCatalogue){
	//alert('afterCheckOrderReceived');
        $(document).stopTime("checkScaniaOrderReceived");
        $.getJSON("OrderFromScania.action?supnexusId="+supnexusId,function(result){
            if(result.done){
			//alert('result done');
                scaniaCatalogue.close();
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
        });
    }
})(jQuery);
