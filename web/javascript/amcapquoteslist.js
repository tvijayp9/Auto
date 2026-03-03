$(function(){
    $("#delete").click(function(){
        var selectedRow = jQuery("#list").getGridParam('selarrrow');
        if( selectedRow!="" ){
            jQuery("#list").delGridRow(selectedRow,{
                reloadAfterSubmit:false,
                msg:'Delete selected quote(s)?',
                caption:'Delete quote',
                bSubmit:'Delete'
            });
        }
        else {
            alert("Please choose at least one quote to delete");
        }
    });

    $("#create").click(function(){
        location.href='CreateNewAmcapQuote.action';
    });

    var viewFormatter=function(el, cellval, opts){
        $(el).html("<a href='ViewAmcapQuoteItems.action?templateId="+opts.rowId+"'>"+cellval+"</a>");
    };
    
    var printFormatter=function(el, cellval, opts){
            $(el).html("<a href=\"#\">Print</a>").click(function(){
                window.open('printQuote.action?qid='+opts.rowId,+'printQuote','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
            });
    };

    $("#list").jqGrid({
        url:'QuotesList.action?nd='+new Date().getTime(),   
        datatype: "json",
        colNames:['ID','Quote Reference Number','Quote Name','Comment','Print','Export'],
        colModel:[ {
            name:'id',
            hidden:true
        },

        {
            name:'qrn',
            index:'qrn',
            width:200,
            formatter:viewFormatter
        },
    {
            name:'qname',
            index:'qname',
            width:200
        },
        {
            name:'comment',
            index:'comment',
            width:255
        },{
            name:'print',
            width:60,
            sortable:false,
            formatter:printFormatter
        },{
            name:'export',
            width:60,
            sortable:false,
             hidden:true
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager'),
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        height: "320px",
        multiselect:true,
        editurl:'DeleteQuotes.action',
        imgpath: "javascript/jqGrid/themes/basic/images"
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
    
    $("#list1").jqGrid({
        url:'QuotesList.action?nd='+new Date().getTime(),   
        datatype: "json",
        colNames:['ID','Quote Reference Number','Quote Name','Comment','Print','Export'],
        colModel:[ {
            name:'id',
            hidden:true
        },

        {
            name:'qrn',
            index:'qrn',
            width:200,
            formatter:viewFormatter
        },
    {
            name:'qname',
            index:'qname',
            width:200
        },
        {
            name:'comment',
            index:'comment',
            width:255
        },{
            name:'print',
            width:60,
            sortable:false,
            formatter:printFormatter
        },{
            name:'export',
            width:60,
            sortable:false,
             hidden:true
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager1'),
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        height: "320px",
        multiselect:false,
        editurl:'DeleteQuotes.action',
        imgpath: "javascript/jqGrid/themes/basic/images"
    });

    $("#list1").navGrid('#pager1',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});