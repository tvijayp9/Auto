$(function(){
    var printFormatter=function(el, cellval, opts){
        $(el).html("<a href=\"#\" onclick=\"window.open('printarchivedorder.action?orderid="+opts.rowId+"','viewOrder','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no')\">View</a>");
    };

    $("#list").jqGrid({
        url:'ShowArchivedOrderListDetails.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Order Number','Order Date', 'Delivery Date', 'Status','Comment','View'],
        colModel:[ {
            name:'orderno',
            index:'orderno',
            width:150
        },

        {
            name:'order_date',
            index:'order_date',
            width:200
        },

        {
            name:'delivery_date',
            index:'delivery_date',
            width:150,
            search:false
        },

        {
            name:'status',
            index:'status',
            width:100,
            search:false
        },

        {
            name:'comment',
            width:200,
            sortable:false,
            search:false
        },

        {
            name:'view',
            width:60,
            sortable:false,
            formatter:printFormatter,
            search:false
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager'),
        sortname: 'order_date',
        viewrecords: true,
        sortorder: "desc",
        height: "220px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        subGrid:true,
        subGridRowExpanded: function(subgrid_id, row_id){
            var subgrid_table_id, pager_id;
            subgrid_table_id = subgrid_id+"_t";
            pager_id = "p_"+subgrid_table_id;
            $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
            $("#"+subgrid_table_id).jqGrid({
                url:"ShowArchivedOrderItemDetails.action?orderId="+row_id,
                datatype: "json",
                colNames: ['Product Code','GTIN','Product Name','UOM','Quantity'],
                colModel: [ {
                    name:"Product_Code",
                    index:"Product_Code",
                    width:150
                },

                {
                    name:"gtin",
                    index:"gtin",
                    width:150
                },

                {
                    name:"Product_Name",
                    index:"Product_Name",
                    width:300
                },

                {
                    name:"uom",
                    index:"uom",
                    width:150
                },

                {
                    name:"qty",
                    index:"qty",
                    width:80
                } ],
                rowNum:5,
                rowList:[5,10,20],
                pager: pager_id,
                imgpath: "javascript/jqGrid/themes/basic/images",
                sortname: 'bpm.id',
                sortorder: "asc",
                height: "100px",
                viewrecords: true
            }).navGrid("#"+pager_id,{
                edit:false,
                add:false,
                del:false,
                refresh:false,
                search:false
            })
        }
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
            label:'Order Number:',
            name:'orderno'
        },{
            label:'From :',
            name:'order_date'
        },{
            label:'To :',
            name:'order_date1'
        }],
        formtype:"horizontal",
        enableSearch:true,
        enableClear:true,
        autosearch: false
    });

    $("#sg_order_date","#mysearch").datepicker({
        minDate: new Date(2009,0,1),
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
    $("#sg_order_date1","#mysearch").datepicker({
        minDate: new Date(2009,0,1),
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