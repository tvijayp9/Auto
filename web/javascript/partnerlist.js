$(function(){
    var urlFormatter=function(el, cellval, opts){
        $(el).html("<a href='http://"+cellval+"' target='_blank'>"+cellval+"</a>");
    };

        var microcatFormatter=function(el, cellval, opts){
        $(el).html("<a href='ViewMicrocatAccount.action?nexusId="+cellval+"'>View</a>");
    };

    $("#edit").click(function(){
        var selectedRow = $("#list").getGridParam('selrow');
        if( selectedRow!=null ){
            location.href='EditPartner.action?nexusId='+selectedRow;
        }
        else{
            alert("Please choose at least one partner to edit");
        }
    });
    
    $("#list").jqGrid({
        url:'ShowPartnerListDetails.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Company','Email', 'Phone NO.', 'State','Country','Price Type','Microcat Account'],
        colModel:[ {
            name:'company',
            index:'company',
            width:150
        },

        {
            name:'email',
            width:150,
            sortable:false,
            search:false
        },

        {
            name:'phno',
            width:100,
            sortable:false,
            search:false
        },

        {
            name:'state',
            index:'state',
            width:80,
            search:false
        },

        {
            name:'country',
            index:'country',
            width:100,
            search:false
        },

        {
            name:'priceTypeName',
            width:170,
            sortable:false,
            search:false
        },
    {
            name:'microcat',
            width:110,
            sortable:false,
            formatter:microcatFormatter,
            search:false
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager'),
        sortname: 'company',
        viewrecords: true,
        sortorder: "asc",
        height: "300px",
        multiselect:true,
        imgpath: "javascript/jqGrid/themes/basic/images"
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
            label:'Company:',
            name:'company'
        }],
        formtype:"horizontal",
        enableSearch:true,
        enableClear:true,
        autosearch: false
    });
});
