var supnexusId;
$(function(){
    $("#search").click(function(){
        var searchFor=$("#searchFor").val();
        var searchIn=$("#searchIn").val();
        if(searchFor!=""){
            $("#productlist").setGridParam({
                url:"ShowBuyerProductsBySupplier.action?searchFor="+searchFor+"&searchIn="+searchIn
            });
            $("#productlist").setGridParam({
                page:1
            });
            $("#productlist").setGridParam({
                rowNum:10
            });
            $("#productlist").trigger("reloadGrid");
        }
        else{
            alert("Please enter the details of the search");
        }
    });

    $("#clear").click(function(){
        $("#searchFor").val("");
        $("#productlist").setGridParam({
            url:"ShowBuyerProductsBySupplier.action?nd="+new Date().getTime()
        });
        $("#productlist").setGridParam({
            page:1
        });
        $("#productlist").setGridParam({
            rowNum:10
        });
        $("#productlist").trigger("reloadGrid");
    });

	$("#delete").click(function(){
        var selectedRow = $("#productlist").getGridParam('selrow');
        if( selectedRow!=null ){
            $("#productlist").delGridRow(selectedRow,{
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

        
    $("#productlist").jqGrid({
        url:'ShowBuyerProductsBySupplier.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['S.O.H','Location - Machine Type','Asset Name – Category','Product Item No', 'Product Description','Unit Price','Tax','Cost'],
        colModel:[  {
            name:'soh',
            index:'soh',
            hidden:true,
            width:50
        },
        {
            name:'site_name',
            index:'site_name',
            width:150
        },
        {
            name:'category_name',
            index:'category_name',
            width:150
        },
		{
            name:'product_code',
            index:'product_code',
            width:150
        },

        {
            name:'description',
            index:'description',
            width:300,
            editable:true
        },
        {
            name:'price1',
            sortable:false,
            width:100,
            formatter:'currency',
            editable:true,
            formatoptions:{
                prefix: "$",
                decimalPlaces: 2
            },
            editrules:{
                required:true,
                number:true,
                minValue:1,
                maxValue:999
            }
        },
        {
            name:'tax',
            sortable:false,
            width:100,
            hidden:true
        },
        {
            name:'cost',
            width:100,
            sortable:false,
            hidden:true
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager'),
        sortname: 'site_name',
        viewrecords: true,
        sortorder: "asc",
        cellEdit:true,
        height: "235px",
        editurl:'deleteProductItemAction.action',
        imgpath: "javascript/jqGrid/themes/basic/images",
        cellurl:"EditProductDetails.action"
    });
	jQuery("#productlist").jqGrid('navGrid','#pager',{add:false,del:false,edit:false, refresh:false,search:false});
});