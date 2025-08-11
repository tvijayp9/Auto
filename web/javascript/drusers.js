$(function(){
    $("#assign").click(function(){
        var selectedRow = $("#list").getGridParam('selrow');
        if( selectedRow!=null ){
            location.href='beforeuserassign.action?userId='+selectedRow;
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
        url:'showDrUsers.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['User Name'],
        colModel:[ {
            name:'Loginid',
            index:'Loginid',
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
        multiselect:false
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});