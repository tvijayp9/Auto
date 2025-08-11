$(function(){
    var userId=$("#userId").val();

    $("#assign").click(function(){
        //if($.trim($("#loginId").val())!=""){
			
            var existingCount=$("#existingCount").val();
            var selectedRow = $("#list").getGridParam('selarrrow');
			//if(selectedRow=='')
			//	selectedRow = 0;
            var selectedRow2 = $("#list2").getGridParam('selarrrow');
			//alert('aexistingCount='+existingCount+"..selectedRow="+selectedRow+"..selectedRow2="+selectedRow2);
            //if((existingCount-selectedRow2.length+selectedRow.length)>0){
                $("#moreUsers").val(selectedRow2);
                $("#existingUsers").val(selectedRow);
                $("form").attr("action","drPartner.action").submit();
           // }
           // else{
           //     alert("One user has one partner at least");
           // }
      //  }
       // else{
           // alert("User name is required.");
       // }
    });

    $("#list").jqGrid({
        url:'showAssignedPartners.action?userId='+userId,
        datatype: "json",
        colNames:['Number','Partner Name'],
        colModel:[ {
            name:'partner_id',
            index:'partner_id',
            width:200
        },
        {
            name:'partner_name',
            index:'partner_name',
            width:200
        }],
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
        url:'showExistingPartners.action?userId='+userId,
        datatype: "json",
        colNames:['Number','Partner Name'],
        colModel:[ {
            name:'pid',
            index:'pid',
            width:200
        },
        {
            name:'name',
            index:'name',
            width:200
        }],
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