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

	$("#list2").jqGrid({
        url:'getPartners.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Partner Name'],
        colModel:[ {
            name:'name',
            index:'name',
            width:220
        },
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