$(function(){
    var urlFormatter=function(el, cellval, opts){
        $(el).html("<a href=\"#\">"+cellval+"</a>").click(function(){
            //alert('pages/resolution/Trans_Disp_FS.jsp?id='+opts.rowId+',MyWindow','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,width=800,height=600')
            //var selectedRow = $("#list").cellval;
            window.open('pages/resolution/Trans_Disp_FS.jsp?id='+opts.rowId,'MyWindow','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,width=800,height=600');
        });
    };

    var viewFormatter=function(el, cellval, opts){
            $(el).html("<a href=\"#\">"+cellval+"</a>").click(function(){

				location.href='processedPrintTransaction.action?ptid='+opts.rowId;
				//alert(document.URL);
					var redirectUrl="http://nexusb2bnetwork.com.au/Auto/temp/print_"+cellval+".htm";
				//setTimeout(function() { window.open(redirectUrl)}, 5000);
				alert("It takes 5-10 seconds to genarate document.Please refresh the page by hitting F5,if it can't open a page.");
	  window.open(redirectUrl,'mywindow','width=1000,height=700,toolbar=yes,menubar=yes,location=no,directories=no,status=no,menubar=yes,scrollbars=yes,copyhistory=no,resizable=no');
       });
    };
    $("#list").jqGrid({
        url:'ShowProcessedTransactionList.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Status','Document No','Partner Name', 'Doc Type', 'Date','Due Date','Total Amt','Comment','Invoice No'],
        colModel:[{
            name:'ResolvedState',
            width:60,
			sortable:false,
            search:false
        },
        {
            name:'transactionnumber',
            index:'transactionnumber',
			formatter:viewFormatter,
            width:100
        },

        {
            name:'partnername',
            index:'partnername',
            width:110,
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
            width:80,
			search:false
        },
		{
            name:'TransAckComment',
            index:'TransAckComment',
            width:130,
			search:false
        },
        {
            name:'AlternateTransactionNumber',
            index:'AlternateTransactionNumber',
            width:80,
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