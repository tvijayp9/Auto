$(function(){
    var roleId=$("#roleId").val();

    $("#save").click(function(){
        if($.trim($("#roleName").val())!=""){
            var existingCount=$("#existingCount").val();
            var selectedRow = $("#list").getGridParam('selarrrow');
            var selectedRow2 = $("#list2").getGridParam('selarrrow');
            if((existingCount-selectedRow2.length+selectedRow.length)>0){
                $("#moreTabs").val(selectedRow);
                $("#existingTabs").val(selectedRow2);
                $("form").attr("action","EditRoleWithTab.action").submit();
            }
            else{
                alert("One role has one tab at least");
            }
        }
        else{
            alert("Role name is required.");
        }
    });

    $("#list").jqGrid({
        url:'ShowMoreTabs.action?roleId='+roleId,
        datatype: "json",
        colNames:['Function','Module'],
        colModel:[ {
            name:'tab_name',
            index:'tab_name',
            width:200
        },
        {
            name:'section_name',
            index:'section_name',
            width:200
        }],
        rowNum:100,
        rowList:[100],
        pager: $('#pager'),
        sortname: 'section_id,tab_id',
        viewrecords: true,
        sortorder: "asc",
        height: "220px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect:true
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });

    $("#list2").jqGrid({
        url:'ShowExistingTabs.action?roleId='+roleId,
        datatype: "json",
        colNames:['Function','Module'],
        colModel:[ {
            name:'tab_name',
            index:'tab_name',
            width:200
        },
        {
            name:'section_name',
            index:'section_name',
            width:200
        }],
        rowNum:100,
        rowList:[100],
        pager: $('#pager2'),
        sortname: 'section_id,tab_id',
        viewrecords: true,
        sortorder: "asc",
        height: "220px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect:true
    });

    $("#list2").navGrid('#pager2',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });
});