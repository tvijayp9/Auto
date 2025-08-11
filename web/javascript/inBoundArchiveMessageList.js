$(function(){
    $("#archive").click(function(){
        var selectedRow = jQuery("#list").getGridParam('selarrrow');
        if( selectedRow!="" ){
            jQuery("#list").delGridRow(selectedRow,{
                reloadAfterSubmit:false,
                msg:'Archive selected record(s)?',
                caption:'Archive record',
                bSubmit:'Archive'
            });
        }
        else {
            alert("Please choose at least one transaction to archive");
        }
    });

    $("#viewSelected").click(function(){
        var selectedRow = jQuery("#list").getGridParam('selarrrow');
        if( selectedRow!="" ){
            window.open('viewSelectedOrderFromArchivedInboundTransaction.action?id='+selectedRow,+'viewOrder','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
            $.each(selectedRow,function(index,value){
                $("#list").setCell(value,"b_status","Viewed","","");
            });
        }
        else {
            alert("Please choose at least one transaction to view");
        }
    });


    var downloadFormatter=function(el, cellval, opts){
        if(cellval=="1"){
            $(el).html(" ");
        }
        else {
            $(el).html("<a href='downloadFileINQ.action?inqmessageid="+opts.rowId+"'>Download</a>");
        }
    };

    var viewFormatter=function(el, cellval, opts){
        if(cellval=="1"){
            $(el).html("<a href=\"#\">View</a>").click(function(){
                window.open('viewOrderFromArchivedInboundTransaction.action?messageId='+opts.rowId,+'viewOrder','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
                $("#list").setCell(opts.rowId,"b_status","Viewed","","");
            });
        }
        else {
            $(el).html("<a href='viewUploadFile.action?inqmessageid="+opts.rowId+"'>View</a>");
        }
    };

    var statusFormatter=function(el, cellval, opts){
        if(cellval=="0"){
            $(el).html("<span class='rowWithColor'>New</span>");
        }
        else {
            $(el).html("Viewed");
        }
    };

    $("#list").jqGrid({
        url:'ShowInBoundArchivedListDetails.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Document Id','Transaction Type', 'From', 'Date Received','Status','Download','View'],
        colModel:[ {
            name:'vch_document_id',
            index:'vch_document_id',
            width:170
        },

        {
            name:'vch_description',
            index:'vch_description',
            width:170,
            search:false
        },

        {
            name:'company',
            index:'company',
            width:170
        },

        {
            name:'dt_received',
            index:'dt_received',
            width:170
        },

        {
            name:'b_status',
            index:'b_status',
            width:50,
            formatter:statusFormatter,
            search:false
        },

        {
            name:'download',
            width:70,
            sortable:false,
            formatter:downloadFormatter,
            search:false
        },

        {
            name:'view',
            width:60,
            sortable:false,
            formatter:viewFormatter,
            search:false
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager'),
        sortname: 'dt_received',
        viewrecords: true,
        sortorder: "desc",
        height: "320px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect:true,
        editurl:'ArchiveInBoundMessage.action'
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });

    $("#mysearch").filterGrid("#list",
    {
        filterModel:[{
            label:'Document Id:',
            name:'vch_document_id'
        },{
            label:'From(Buyer):',
            name:'company'
        },{
            label:'From :',
            name:'dt_received'
        },{
            label:'To :',
            name:'dt1_received'
        }],
        formtype:"horizontal",
        enableSearch:true,
        enableClear:true,
        autosearch: false
    });

    $("#sg_dt_received","#mysearch").datepicker({
        minDate: new Date(2009,0,1),
        maxDate: '-30',
        changeYear: true,
        changeMonth: true,
        dateFormat: 'yy-mm-dd',
        showOn:'both',
        buttonImage:'images/calendar.gif',
        buttonImageOnly:true,
        firstDay:1,
        showStatus: true
    });
    $("#sg_dt1_received","#mysearch").datepicker({
        minDate: new Date(2009,0,1),
        maxDate: '-30',
        changeYear: true,
        changeMonth: true,
        dateFormat: 'yy-mm-dd',
        showOn:'both',
        buttonImage:'images/calendar.gif',
        buttonImageOnly:true,
        firstDay:1,
        showStatus: true
    });
});