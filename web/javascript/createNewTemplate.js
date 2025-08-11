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
            $.post("AddTemplateOrderItem.action",{
                productCode:selectedRow
            },function(result){
                $("#template").setGridParam({
                    url:"ShowTemplate.action?nd="+new Date().getTime()
                });
                $("#template").setGridParam({
                    page:1
                });
                $("#template").setGridParam({
                    rowNum:10
                });
                $("#template").trigger("reloadGrid");
                $("#list").resetSelection();
                $("#add").removeAttr("disabled");
            });
        }
        else{
            alert("Please choose at least one product and add them to template");
        }
    });

    $("#delete").click(function(){
        var selectedRow = $("#template").getGridParam('selarrrow');
        if( selectedRow!="" ){
            $("#template").delGridRow(selectedRow,{
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
            $.getJSON("CheckTemplate.action",function(result){
                if(result.check){
                    $("form").attr("action", "SaveNewTemplate.action").submit();
                }
                else{
                    alert("There is not item in your template, you could not save");
                }
            });
        }
        else{
            alert("Please input template name");
        }
    });

    $("#save1").click(function(){
        $.getJSON("CheckTemplate.action",function(result){
            if(result.check){
                $("form").attr("action", "SaveModifiedTemplate.action").submit();
            }
            else{
                alert("There is not item in your template, you could not save");
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
    
    $("#template").jqGrid({
        url:'ShowTemplate.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['S.O.H.','Product Item No', 'Product Description','Unit Price','Units','Tax'],
        colModel:[{
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
        pager: $('#templatepager'),
        viewrecords: true,
        height: "235px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        editurl:'DeleteTemplateOrderItem.action',
        multiselect:true
    });

    $("#template").navGrid('#templatepager',{
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
        $.getJSON("CombineOrdersFromMicrocatForFavourite.action?supnexusId="+supnexusId,function(result){
            if(result.done){
                partsCatalogue.close();
                $("body").unmask();
                $("#template").setGridParam({
                    url:"ShowTemplate.action?nd="+new Date().getTime()
                });
                $("#template").setGridParam({
                    page:1
                });
                $("#template").setGridParam({
                    rowNum:10
                });
                $("#template").trigger("reloadGrid");
            }
        });
    }
})(jQuery);
