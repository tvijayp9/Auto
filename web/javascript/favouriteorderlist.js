$(function(){
    $("#delete").click(function(){
        var selectedRow = jQuery("#list").getGridParam('selarrrow');
        if( selectedRow!="" ){
            jQuery("#list").delGridRow(selectedRow,{
                reloadAfterSubmit:false,
                msg:'Delete selected template(s)?',
                caption:'Delete template',
                bSubmit:'Delete'
            });
        }
        else {
            alert("Please choose at least one template to delete");
        }
    });

    $("#create").click(function(){
        location.href='CreateNewTemplate.action';
    });

    var viewFormatter=function(el, cellval, opts){
        $(el).html("<a href='ViewTemplateOrderItems.action?templateId="+opts.rowId+"'>"+cellval+"</a>");
    };

    $("#list").jqGrid({
        url:'FavouriteOrderList.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['ID','Favourite Order Name'],
        colModel:[ {
            name:'id',
            hidden:true
        },

        {
            name:'favouritename',
            index:'favouritename',
            width:420,
            formatter:viewFormatter
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager'),
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        height: "320px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect:true,
        editurl:'DeleteFavouriteOrder.action'
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});