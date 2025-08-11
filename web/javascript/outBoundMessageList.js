$(function(){
    var downloadFormatter=function(el, cellval, opts){
        if(cellval=="1"){
            $(el).html(" ");
        }
        else {
            $(el).html("<a href='downloadFileOUTQ.action?inqmessageid="+opts.rowId+"'>Download</a>");
        }
    };

    var viewFormatter=function(el, cellval, opts){
        if(cellval=="1"){
            $(el).html("<a href=\"#\" onclick=\"window.open('viewOrderFromOutboundTransaction.action?messageId="+opts.rowId+"','viewOrder','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no')\">View</a>");
        }
        else {
            $(el).html("<a href='viewUploadFile1.action?inqmessageid="+opts.rowId+"'>View</a>");
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
        url:'ShowOutBoundListDetails.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Document Id','Transaction Type', 'To', 'Date Sent','Status','Download','View'],
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
        height: "300px",
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

        $("#mysearch").filterGrid("#list",
    {
        filterModel:[{
            label:'Document Id:',
            name:'vch_document_id'
        },{
            label:'To(Supplier):',
            name:'company'
        },{
            label:'From :',
            name:'dt_received'
        },{
            label:'TO :',
            name:'dt1_received'
        }],
        formtype:"horizontal",
        enableSearch:true,
        enableClear:true,
        autosearch: false
    });

    $("#sg_dt_received","#mysearch").datepicker({
        minDate: '-30',
        maxDate: '+0',
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
        minDate: '-30',
        maxDate: '+0',
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