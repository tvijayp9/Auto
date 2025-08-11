$(function(){
    $("#create").click(function(){
        if($.trim($("#roleName").val())!=""){
            var selectedRow = $("#list").getGridParam('selarrrow');
            if(selectedRow!=""){
                $("#tabs").val(selectedRow);
                $("form").attr("action","CreateRoleWithTab.action").submit();
            }
            else{
                alert("Please choose at least one tab");
            }
        }
        else{
            alert("Role name is required.");
        }
    });

    $("#list").jqGrid({
        url:'ShowTabs.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Function','Module'],
        colModel:[ {
            name:'tab_name',
            index:'tab_name',
            width:250
        },
        {
            name:'section_name',
            index:'section_name',
            width:250
        }],
        rowNum:100,
        rowList:[100],
        pager: $('#pager'),
        sortname: 'section_id,tab_id',
        viewrecords: true,
        sortorder: "asc",
        height: "240px",
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
});