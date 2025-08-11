$(function(){
    var printFormatter=function(el, cellval, opts){
        $(el).html("<a href=\"#\" onclick=\"window.open('printorder.action?orderid="+opts.rowId+"','viewOrder','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no')\">View</a>");
    };

    $("#list").jqGrid({
        url:'ShowFutureOrderList.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Order Number','Order Date', 'Delivery Date', 'Status','Comment','Quote Reference NO.','View'],
        colModel:[ {
            name:'orderno',
            index:'orderno',
            width:150
        },

        {
            name:'order_date',
            index:'order_date',
            width:150
        },

        {
            name:'delivery_date',
            index:'delivery_date',
            width:100,
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
            width:150,
            sortable:false,
            search:false
        },
{
            name:'qrn',
            width:150,
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
        sortname: 'delivery_date',
        viewrecords: true,
        sortorder: "desc",
        height: "320px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        subGrid:true,
        subGridRowExpanded: function(subgrid_id, row_id){
            var subgrid_table_id, pager_id;
            subgrid_table_id = subgrid_id+"_t";
            pager_id = "p_"+subgrid_table_id;
            $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
            $("#"+subgrid_table_id).jqGrid({
                url:"ShowOrderItemDetails.action?orderId="+row_id,
                datatype: "json",
                colNames: ['Product Item No','Product Description','Quantity','Price','Tax','Cost'],
                colModel: [ {
                    name:'product_code',
                    index:'product_code',
                    width:150
                },

                {
                    name:'description',
                    index:'description',
                    width:200
                },{
                    name:'qty',
                    width:100,
                    index:'qty'
                }, {
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
                    name:'tax',
                    sortable:false,
                    width:100,
                    formatter:'currency',
                    formatoptions:{
                        prefix: "$",
                        decimalPlaces: 3
                    }
                },
                {
                    name:'cost',
                    width:100,
                    sortable:false,
                    formatter:'currency',
                    formatoptions:{
                        prefix: "$",
                        decimalPlaces: 3
                    }
                } ],
                rowNum:5,
                rowList:[5,10,20],
                pager: pager_id,
                imgpath: "javascript/jqGrid/themes/basic/images",
                sortname: 'id',
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
            label:'Order Date:',
            name:'order_date'
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
});