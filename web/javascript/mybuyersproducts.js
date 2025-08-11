function validateForm(formObj) {
                 if(formObj.partner.value==0){
                    alert("Please Select A Partner!");
                    formObj.partner.focus();
                    return false;
                }
            }
            
$(function(){
   $(":button:eq(0)").click(function(){
        var selectedRow = jQuery("#list").getGridParam('selarrrow');
        if( selectedRow!="" ){
            jQuery("#list").delGridRow(selectedRow,{
                reloadAfterSubmit:true,
                msg:'Do You Really Want To Delete selected products(s)?',
                caption:'Delete Products',
                bSubmit:'Delete'
            });
        }
        else {
            alert("Please choose at least one product to delete");
        }
    });

   $(":button:eq(1)").click(function(){
        var selectedRow = jQuery("#list").getGridParam('selarrrow');
        if( selectedRow!="" && selectedRow.length==1){
            window.open('editbuyerproductmapping.action?id='+selectedRow,+'viewOrder','width=300,height=200,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
        }
        else {
            alert("Please choose one product mapping to Edit");
        }
    });
    
    $("#list").jqGrid({
        url:'ShowMyBuyersProduct.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Product Code', 'GTIN', 'Product Name', 'UOM'],
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
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager'),
        sortname: 'id',
        viewrecords: true,
        sortorder: "asc",
        height: "210px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect:true,
        editurl:'DeleteMyBuyerProducts.action'
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});
