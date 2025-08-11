$(function(){

var messageId=0;
    
	$("#generateInvoice").click(function(){
		var invoiceNo=$("#invoiceNo").val();
		var orderNo=orderNumber.innerHTML;
		//alert('invoiceNo=='+invoiceNo+"...orderNo="+orderNo);
		var selectedRow = $("#prodlist").getGridParam('selarrrow');
        if( selectedRow!="" ){
			if(invoiceNo!=""){
            $.post("generateInvoice.action",{
                productCode:selectedRow,
				invoiceNo:invoiceNo,
				orderNumber:orderNo
            });		
		//$("form").attr("action","generateInvoice.action").submit();
		//window.close();
		 alert("You're invoice has sent to your partner successfully!");
		setTimeout("window.close()", 5);
		//window.close();
		}else{
            alert("Please input invoice number");
		}
		}else{
            alert("Please choose at least one product and add them to Invoice cart");
        }
	});

    

	$("#prodlist").jqGrid({
        url:'showInvoiceDetails.action?messageId='+document.getElementById("messageId").value,
        datatype: "json",
        colNames:['Product Item No','Description','Ordered Quantity', 'Quantity','Unit Price', 'Price','Tax','Cost','Invoice No'],
        colModel:[ {
            name:'product_code',
            index:'product_code',
            width:150
        },

        {
            name:'description',
            index:'description',
            width:170,
            search:false
        },
		 {
            name:'orderedQuantity',
            index:'orderedQuantity',
            width:50,
            search:false
        },
        {
            name:'qty',
            width:60,
			sortable:false,
            editable:true,
            editrules:{
                required:true,
                number:true,
                minValue:1,
                maxValue:999
            }
        },
		{
            name:'unitPrice',
            index:'unitPrice',
            width:80,
			sortable:false,
            editable:true,
            editrules:{
                required:true,
                number:true,
                minValue:0,
                maxValue:999
            }
        },
        {
            name:'price',
            index:'price',
            width:80
        },

        {
            name:'tax',
            index:'tax',
            width:80,
            search:false
        },

        {
            name:'cost',
			index:'cost',
            width:80,
            sortable:false,
            search:false
        },
		{
            name:'invoiceNo',
            index:'invoiceNo',
            width:70,
            search:false
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager1'),
        sortname: "product_code",
        viewrecords: true,
        sortorder: "desc",
        height: "400px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect:true,
        editurl:'ArchiveInBoundMessage.action',
		cellEdit:true,
        cellsubmit:"remote",
        cellurl:"editInvoiceProduct.action",
        afterSaveCell : function(rowid,name,val,iRow,iCol) {
			var unitPrice;
			var price;
			//alert('rowid='+rowid+'...iRow='+iRow+'...iCol='+iCol);
			if(name=='qty'){
				unitPrice=$("#prodlist").getCell(rowid,iCol+1);
				price=new Number(parseFloat(unitPrice)*parseInt(val)).toFixed(3);
			}else{
				qty=$("#prodlist").getCell(rowid,iCol-1);
				price=new Number(parseInt(qty)*parseFloat(val)).toFixed(3);
			}
			
            // unitPrice=$("#prodlist").getCell(rowid,iCol+1);
            // price=new Number(parseFloat(unitPrice)*parseInt(val)).toFixed(2);
            var tax=new Number(price*0.1).toFixed(3);
            var cost=new Number(Number(price)+Number(tax)).toFixed(3);
			//alert('tax='+tax+'...cost='+cost);
            $("#prodlist").setRowData(rowid,{
                price:price.toString()
            });
            $("#prodlist").setRowData(rowid,{
                tax:tax.toString()
            });
            $("#prodlist").setRowData(rowid,{
                cost:cost.toString()
            });
        }
    });
	
    $("#prodlist").navGrid('#pager1',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });

   
});