$(function(){
    $(":button:eq(0)").click(function(){
        if($("#transactionType").val()!=0){
            $("form:eq(0)").attr("action", "ShowTransactionTypes.action").submit();
        }
        else{
            alert("Please select transaction type");
        }
    });

    $(":button:eq(1)").click(function(){
        if($("#transactionType").val()!=0){
            $("form:eq(0)").attr("action", "getmytranstypes.action").submit();
        }
        else{
            alert("Please select transaction type");
        }
    });

    $("#add").click(function(){
        var stt=$("#selectedTransactionTypes").val();
        if(stt!=0&&stt!=null){
            $("form:eq(1)").attr("action", "AddTransactionTypes.action").submit();
        }
        else{
            alert("Please select transaction type");
        }
    });

    $("#delete").click(function(){
        var t=$("#trans").val();
        if(t!=0&&t!=null){
            $("form:eq(1)").attr("action", "deletemytranstypes.action").submit();
        }
        else{
            alert("Please select transaction type");
        }
    });
});