$(function(){
    var urlFormatter=function(el, cellval, opts){
        $(el).html("<a href=\"#\">"+cellval+"</a>").click(function(){
            //alert('pages/resolution/Trans_Disp_FS.jsp?id='+opts.rowId+',MyWindow','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,width=800,height=600')
            //var selectedRow = $("#list").cellval;
            window.open('pages/resolution/Trans_Disp_FS.jsp?id='+opts.rowId,'MyWindow','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,width=800,height=600');
        });
    };
    
    var viewFormatter=function(el, cellval, opts){
        if(cellval == 'Open')
        {
            $(el).html("<a href=\"#\"><img src=\"images/Trans_Disp/OpenBook.gif\" border=\"0\" /></a>").click(function(){
//                window.open('pages/resolution/Trans_Disp_FS.jsp?id='+opts.rowId,'MyWindow','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,width=800,height=600');
            window.open('opentransaction.action?id='+opts.rowId,'MyWindow','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,width=800,height=600');
            });
        }
        else
        {
            $(el).html("<a href=\"#\"><img src=\"images/Trans_Disp/lockTick.gif\" border=\"0\" /></a>").click(function(){
                alert('This transaction is locked by another user. Please try again.');
            });
        }
    };
    $("#list").jqGrid({
        url:'ShowTransactionListDetails.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['State','Document No','Partner Name', 'Doc Type', 'Date','Due Date','Total Amt','Comment'],
        colModel:[{
            name:'openstate',
            width:50,
            sortable:false,
            formatter:viewFormatter,
            search:false
        },
        {
            name:'transactionnumber',
            index:'transactionnumber',
            width:100
        },

        {
            name:'partnername',
            index:'partnername',
            width:150,
            sortable:false,
            search:false
        },

        {
            name:'TransactionType',
            width:80,
            sortable:false,
            search:false
        },

        {
            name:'processdate',
            index:'processdate',
            width:120,
            search:false
        },
		{
            name:'TransResponseDate',
            index:'TransResponseDate',
            width:120,
            search:false
        },
		{
            name:'TotalTransactionAmount',
            index:'TotalTransactionAmount',
            width:100,
            search:false
        },
		{
            name:'TransAckComment',
            index:'TransAckComment',
            width:150,
            search:false
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager'),
        sortname: 'transactionnumber',
        viewrecords: true,
        sortorder: "asc",
        height: "355px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        editurl:'DeletePartnerLink.action'
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
	$("#tobeprocessed").click(function(){
        
           // alert("you clicked tobeprocessed");
			$("form").attr("action", "FilterTransactions.action?type=1").submit();
			//location.href='FilterTransactions.action?type=1';
        
    });
	 $("#processed").click(function(){
        
         //   alert("you clicked processed");
			$("form").attr("action", "FilterTransactions.action?type=2").submit();
			//location.href='FilterTransactions.action?type=2';
        
    });
	
	 $("#mysearch").filterGrid("#list",
    {
        filterModel:[{
            label:'Transaction Number:',
            name:'transactionnumber'
        }],
        formtype:"horizontal",
        enableSearch:true,
        enableClear:true,
        autosearch: false
    });

});