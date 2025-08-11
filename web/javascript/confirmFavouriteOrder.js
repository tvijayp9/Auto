$(function(){
    $("#orderDate").datepicker({
        minDate: '+0',
        maxDate: '+1y',
        changeYear: true,
        changeMonth: true,
        dateFormat: 'dd/mm/yy',
        showOn:'both',
        buttonImage:'images/calendar.gif',
        buttonImageOnly:true,
        firstDay:1,
        showStatus: true      
    });

    $(":button:eq(0)").click(function(){
      $("form").attr("action", "CreateFavouriteOrder.action").submit();
        });

    $(":button:eq(1)").click(function(){
        if($("#orderDate").val()!=""){
            $(":button:eq(1)").attr("disabled", "disabled");
            $("form").attr("action", "CreateOrder.action").submit();
        }
        else{
            alert("Please input order delivery date");
        }
    });
    
});