$(function(){
    var nexusId=$("#nexusId").val();
    
    $("#edit").click(function(){
        var selectedRow = $("#list").getGridParam('selrow');
        if( selectedRow!=null ){
            location.href='EditMicrocatAccount.action?accountId='+selectedRow;
        }
        else{
            alert("Please choose at least one account to edit");
        }
    });

    $("#delete").click(function(){
        var selectedRow = $("#list").getGridParam('selarrrow');
        if( selectedRow.length>0 ){
                    $("#list").delGridRow(selectedRow,{
                        reloadAfterSubmit:false,
                        msg:'Delete selected account?',
                        caption:'Delete account',
                        bSubmit:'Delete',
                        top:300,
                        left:300
                    });
        }
        else {
            alert("Please choose at least one account to delete");
        }
    });

    $("#create").click(function(){
        location.href="CreateOneMicrocatAccount.action?nexusId="+nexusId;
    });

    var statusFormatter=function(el, cellval, opts){
        if(cellval=="0"){
             $(el).html("Busy");
        }
        else{
             $(el).html("Idle");
        }
    };

    $("#list").jqGrid({
        url:'ShowMicrocats.action?nexusId='+nexusId,
        datatype: "json",
        colNames:['Account Number','Username','Password','Status'],
        colModel:[ {
            name:'account_number',
            index:'account_number',
            width:150
        },
        {
            name:'username',
            index:'username',
            width:150
        },
        {
            name:'password',
            index:'password',
            width:150
        },
        {
            name:'status',
            index:'status',
            width:150,
            formatter:statusFormatter
        }],
        rowNum:100,
        rowList:[100],
        pager: $('#pager'),
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        height: "300px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect:true,
        editurl:'DeleteMicrocatAccount.action'
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});