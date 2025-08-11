/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(function(){
    $("#ValidFrom").datepicker({
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

    $("#ValidUntil").datepicker({
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

});