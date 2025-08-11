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
        location.href='CreateNewQuote.action';
    });

    var viewFormatter=function(el, cellval, opts){
        $(el).html("<a href='ViewQuoteItems.action?templateId="+opts.rowId+"'>"+cellval+"</a>");
    };

    var printFormatter=function(el, cellval, opts){
            $(el).html("<a href=\"#\">Print</a>").click(function(){
                window.open('printQuote.action?qid='+opts.rowId,+'printQuote','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
            });
    };

    var exportFormatter=function(el, cellval, opts){
            $(el).html("<a href='ExportQuote.action?qid="+opts.rowId+"'>Export</a>");
    };

    $("#list").jqGrid({
        url:'QuotesList.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['ID','Quote Reference Number','Quote Name','Print','Export'],
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
        },{
            name:'print',
            width:60,
            sortable:false,
            formatter:printFormatter
        },{
            name:'export',
            width:60,
            sortable:false,
            formatter:exportFormatter
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
        editurl:'DeleteQuotes.action'
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});