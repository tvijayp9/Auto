$(function(){
    $("#edit").click(function(){
        var selectedRow = $("#list").getGridParam('selrow');
        if( selectedRow!=null ){
            location.href='EditUser.action?id='+selectedRow;
        }
        else{
            alert("Please choose at least one user to edit");
        }
    });

    $("#delete").click(function(){
        var selectedRow = $("#list").getGridParam('selrow');
        if( selectedRow!=null ){
            $("#list").delGridRow(selectedRow,{
                reloadAfterSubmit:false,
                msg:'Delete selected user?',
                caption:'Delete user',
                bSubmit:'Delete',
                top:300,
                left:300
            });
        }
        else {
            alert("Please choose at least one user to delete");
        }
    });

    $("#create").click(function(){
        location.href="CreateUser.action";
    });

    $("#list").jqGrid({
        url:'ShowUsers.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['User ID','Role Name'],
        colModel:[ {
            name:'loginid',
            index:'loginid',
            width:250
        },{
            name:'roleName',
            index:'roleName',
            width:250
        }],
        rowNum:100,
        rowList:[100],
        pager: $('#pager'),
        sortname: 'userId',
        viewrecords: true,
        sortorder: "desc",
        height: "300px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect:false,
        editurl:'DeleteUser.action'
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});