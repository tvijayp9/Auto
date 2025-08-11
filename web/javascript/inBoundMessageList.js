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
            window.open('viewSelectedOrderFromInboundTransaction.action?id='+selectedRow,+'viewOrder','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
            $.each(selectedRow,function(index,value){
                $("#list").setCell(value,"b_status","Viewed","","");
            });
        }
        else {
            alert("Please choose at least one transaction to view");
        }
    });

    $("#viewNew").click(function(){
        $.getJSON("AllNewOrdersCount.action",function(data){
            if(data.count==0){
                alert("You do not have new orders to print");
            }
            else if(data.count>100){
                alert("too many orders to print");
            }
            else{
                window.open('viewNewOrderFromInboundTransaction.action',+'viewOrder','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
                $("#list").oneTime(5000,function(){
                    $(this).trigger("reloadGrid");
                });
            }
        });
    });
	
	$("#generateInvoice").click(function(){
		var invoiceNo=$("#invoiceNo").val();
		var orderNo=orderNumber.innerHTML;
		
		$("form").attr("action","generateInvoice.action").submit();
		//window.close();
		setTimeout("window.close()", 5);
		//window.close();
	});

    var downloadFormatter=function(el, cellval, opts){
        if(cellval=="1"){
            $(el).html(" ");
        }
        else {
            $(el).html("<a href='downloadFileINQ.action?inqmessageid="+opts.rowId+"'>Download</a>");
        }
    };
	
	var invoiceFormatter=function(el, cellval, opts){
        if(cellval=="Ausdrill"){
            $(el).html("<a href=\"#\">Invoice</a>").click(function(){
			this.messageId=opts.rowId;
			var company = $("#list").getCell(opts.rowId,"company");  
			var orderNo = $("#list").getCell(opts.rowId,"vch_document_id"); 
//alert('opts.rowId=='+opts.rowId+"...."+orderNo);			
			//url:"viewinvoiceTransaction.action?messageId="+opts.rowId+'&company='+company;
				
			//	location.href='EditRole.action?roleId='+selectedRow;
				
	
	
	window.open('viewinvoiceTransaction.action?messageId='+opts.rowId+'&company='+company+'&orderNo='+orderNo,+'viewOrder','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
	//window.open('showInvoiceData.action','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
              //  $("#list").setCell(opts.rowId,"b_status","Viewed","","");
	
	
            });
        }
      //  else {
       //     $(el).html("<a href='viewUploadFile.action?inqmessageid="+opts.rowId+"'>View</a>");
        //}
    };

    var viewFormatter=function(el, cellval, opts){
        if(cellval=="1"){
            $(el).html("<a href=\"#\">View</a>").click(function(){
			var company = $("#list").getCell(opts.rowId,"company");  
				window.open('viewOrderFromInboundTransaction.action?messageId='+opts.rowId+'&company='+company,+'viewOrder','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
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
        url:'ShowInBoundListDetails.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Document Id','Transaction Type', 'From', 'Date Received','Status','Invoice','View'],
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
            formatter:invoiceFormatter,
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
        multiselect:false,
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
            label:'From:',
            name:'dt_received'
        },{
            label:'To:',
            name:'dt1_received'
        }],
        formtype:"horizontal",
        enableSearch:true,
        enableClear:true,
        autosearch: false
    });

    $("#sg_dt_received","#mysearch").datepicker({
        minDate: '-31',
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
        minDate: '-31',
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