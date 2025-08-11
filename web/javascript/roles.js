$(function(){
    $("#edit").click(function(){
        var selectedRow = $("#list").getGridParam('selrow');
        if( selectedRow!=null ){
            location.href='EditRole.action?roleId='+selectedRow;
        }
        else{
            alert("Please choose at least one role to edit");
        }
    });

    $("#delete").click(function(){
        var selectedRow = $("#list").getGridParam('selrow');
        if( selectedRow!=null ){
            administrationService.checkRoleAssignedToUser(selectedRow,function(result){
                if(result){
                    $("#list").delGridRow(selectedRow,{
                        reloadAfterSubmit:false,
                        msg:'Delete selected role?',
                        caption:'Delete role',
                        bSubmit:'Delete',
                        top:300,
                        left:300
                    });
                }
                else{
                    alert("This role has been assigned to users, it can not be deleted");
                }
            });
        }
        else {
            alert("Please choose at least one role to delete");
        }
    });

    $("#create").click(function(){
        location.href="CreateRole.action";
    });

    $("#list").jqGrid({
        url:'ShowRoles.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Role Name'],
        colModel:[ {
            name:'name',
            index:'name',
            width:320
        }],
        rowNum:100,
        rowList:[100],
        pager: $('#pager'),
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        height: "300px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect:false,
        editurl:'DeleteRole.action'
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});