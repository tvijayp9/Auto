$(function(){
    $(":button").click(function(){
        var selectedRow = $("#list").getGridParam('selarrrow');
        if(selectedRow!=""){
            if($("#priceType").val()!=0){
                $(":hidden").val(selectedRow);
                $("form").attr("action", "ChangePriceType.action").submit();
            }
            else{
                alert("Please choose price type");
            }
        }
        else{
            alert("Please choose at least one partner");
        }
    });
    
    $("#list").jqGrid({
        url:'ShowPartnerPriceType.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Company','Price Type'],
        colModel:[ {
            name:'company',
            index:'company',
            width:200
        },

        {
            name:'priceTypeName',
            index:'priceTypeName',
            width:200
        } ],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager'),
        sortname: 'company',
        viewrecords: true,
        sortorder: "asc",
        height: "210px",
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
});
