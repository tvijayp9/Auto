$(function(){
    $(":button:eq(0)").click(function(){
        var selectedRow = $("#list").getGridParam('selarrrow');
        if((selectedRow!="")&&(selectedRow.length==1)){
            $("#productid").val(selectedRow);
            $("form:eq(1)").attr("action", "EditProduct.action").submit();
        }
        else{
            alert("Please choose one product to edit");
        }
    });
    $(":button:eq(1)").click(function(){
        var selectedRow = jQuery("#list").getGridParam('selarrrow');
        if( selectedRow!="" ){
            jQuery("#list").delGridRow(selectedRow,{
                reloadAfterSubmit:false,
                msg:'Do You Really Want To Delete selected products(s)?',
                caption:'Delete Products',
                bSubmit:'Delete'
            });
        }
        else {
            alert("Please choose at least one product to delete");
        }
    });
    $("#mapProductsFile").click(function(){
        location.href='MapProductsFile.action';
    });
    $("#uploadProducts").click(function(){
        location.href='UploadProductsFile.action';
    });

    var viewFormatter=function(el, cellval, opts){
        $(el).html("<a href=\"#\"><img src=\"images/magnif_glass_button.gif\" border=\"0\" /></a>").click(function(){
            window.open('showProductDetails.action?productid='+opts.rowId,+'viewProduct','width=600,height=400,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
        });
    };
    
    $("#list").jqGrid({
        url:'ShowProducts.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Product Code', 'GTIN', 'Product Name', 'UOM','View'],
        colModel:[ {
            name:'product_code1',
            index:'product_code1',
            width:150
        },
        
        {
            name:'gtin',
            index:'gtin',
            width:150
        },

        {
            name:'product_name',
            index:'product_name',
            width:250
        },

        {
            name:'uom',
            index:'uom',
            width:150
        },
        {
            name:'view',
            width:80,
            formatter:viewFormatter,
            sortable:false
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager'),
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        height: "240px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect:true,
        editurl:'DeleteProducts.action'
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});
