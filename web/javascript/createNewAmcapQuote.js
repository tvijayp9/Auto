var supnexusId;
var categoryName;
$(function() {
    $("#search").click(function() {
        var searchFor = $("#searchFor").val();
        var searchIn = $("#searchIn").val();
        $("#sitename").val("");
        if (searchFor != "") {
            $("#list").setGridParam({
                url: "ShowBuyerProductsBySupplier.action?searchFor=" + searchFor + "&searchIn=" + searchIn
            });
            $("#list").setGridParam({
                page: 1
            });
            $("#list").setGridParam({
                rowNum: 10
            });
            $("#categories").empty();
            $("#list").trigger("reloadGrid");

        }
        else {
            alert("Please enter the details of the search");
        }
    });

    $("#clear").click(function() {
        $("#searchFor").val("");
        $("#sitename").val("");
        $("#list").setGridParam({
            url: "ShowBuyerProductsBySupplier.action?nd=" + new Date().getTime()
        });
        $("#list").setGridParam({
            page: 1
        });
        $("#list").setGridParam({
            rowNum: 10
        });
        $("#categories").empty();
        $("#list").trigger("reloadGrid");
    });

    $("#search1").click(function() {
        var siteName = $("#sitename").val();
        categoryName = $("#categories option:selected").text();
        if (searchFor != "") {
            $("#list").setGridParam({
                url: "ShowBuyerProductsBySupplier.action?sitename=" + siteName + "&categoryname=" + categoryName
            });
            $("#list").setGridParam({
                page: 1
            });
            $("#list").setGridParam({
                rowNum: 10
            });
            $("#searchFor").val("");
            $("#list").trigger("reloadGrid");
        }
        else {
            alert("Please enter the details of the search");
        }
    });

    $("#clearsite").click(function() {
        $("#sitename").val("");
        $("#list").setGridParam({
            url: "ShowBuyerProductsBySupplier.action?nd=" + new Date().getTime()
        });
        $("#list").setGridParam({
            page: 1
        });
        $("#list").setGridParam({
            rowNum: 10
        });
        $("#categories").empty();
        $("#list").trigger("reloadGrid");
    });

    $("#sitename").change(
            function() {
                $('#categories').html('');
                var sitename = $("#sitename").val();
                $.ajax({
                    url: "readCategoriesBySite.action?sitename=" + sitename,
                    dataType: 'json',
                    contentType: 'application/json',
                    type: 'POST',
                    async: true,
                    success: function(res) {
                        console.log(res.categoryNames.length);
                        $('#categories').append('<option></option>');
                        for (var i = 0; i < res.categoryNames.length; i++) {
                            console.log(" " + res.categoryNames[i]);
                            $('#categories').append(
                                    '<option value=' + res.categoryNames[i] + '>'
                                    + res.categoryNames[i]
                                    + '</option>');
                        }
                    }
                });
            });

    $("#add").click(function() {
        var selectedRow = $("#list").jqGrid('getGridParam', 'selarrrow');
        if (selectedRow != "") {
            $("#add").attr("disabled", "disabled");
            $.ajax({
                type: "POST",
                traditional: true,
                url: "AddAmcapQuoteItem.action",
                async: false,
                data: {productCode: selectedRow},
                dataType: "json",
                success: function(result) {
                    $("#newQuote").setGridParam({
                        url: "ShowAmcapQuote.action?nd=" + new Date().getTime()
                    });
                    $("#newQuote").setGridParam({
                        page: 1
                    });
                    $("#newQuote").setGridParam({
                        rowNum: 10
                    });
                    $("#newQuote").trigger("reloadGrid");
                    $("#list").resetSelection();
                    $("#add").removeAttr("disabled");
                }
            });
        }
        else {
            alert("Please choose at least one product and add them to quote");
        }
    });

    $("#delete").click(function() {
        var selectedRow = $("#newQuote").getGridParam('selrow');
        if (selectedRow != null) {
            $("#newQuote").delGridRow(selectedRow, {
                reloadAfterSubmit: false,
                msg: 'Delete selected item(s)?',
                caption: 'Delete item',
                bSubmit: 'Delete',
                top: 300,
                left: 300
            });
        }
        else {
            alert("Please choose at least one item to delete");
        }
    });

    $("#save").click(function() {
        if ($("#templateName").val() != "") {
            $.getJSON("CheckQuote.action", function(result) {
                if (result.check) {
                    $("form").attr("action", "SaveNewQuote.action").submit();
                }
                else {
                    alert("There is not item in your quote, you could not save");
                }
            });
        }
        else {
            alert("Please input quote name");
        }
    });

    $("#save1").click(function() {
        $.getJSON("CheckQuote.action", function(result) {
            if (result.check) {
                $("form").attr("action", "SaveModifiedAmcapQuote.action").submit();
            }
            else {
                alert("There is not item in your quote, you could not save");
            }
        });
    });

    $("#addRecord").click(function() {
        var quotename =  $("#quoteName").val();
        window.open('pages/catelouge/addAmcapProduct.jsp?quoteName='+quotename, 'HelpWindow', 'toolbar=no,top=500,left=500,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=500,height=300');
    });


    $("#modify").click(function() {
        $("form").attr("action", "modifyQuote.action").submit();
    });

    $("#checkout").click(function() {
        if ($("#quoteName").val() != "") {
            var quotename = $("#quoteName").val();
            location.href = 'SaveNewAmcapQuote.action?quoteName=' + quotename;
        }
        else {
            alert("Please input quote name");
        }
    });

    var unitsFormatter = function(el, cellval, opts) {
        $(el).html("Each");

    };

    var taxFormatter = function(el, cellval, opts) {
        $(el).html("GST");
    };

    $("#list").jqGrid({
        url: 'ShowBuyerProductsBySupplier.action?nd=' + new Date().getTime(),
        datatype: "json",
        colNames: ['S.O.H', 'Location - Machine Type', 'Asset Name – Category', 'Product Item No', 'Product Description', 'Unit Price', 'Units', 'Tax'],
        colModel: [{
                name: 'soh',
                index: 'soh',
                hidden: true,
                width: 50
            },
            {
                name: 'site_name',
                index: 'site_name',
                width: 175
            },
            {
                name: 'category_name',
                index: 'category_name',
                width: 200
            },
            {
                name: 'product_code',
                index: 'product_code',
                width: 125
            },
            {
                name: 'description',
                index: 'description',
                width: 300
            },
            {
                name: 'price1',
                sortable: false,
                width: 75,
                formatter: 'currency',
                formatoptions: {
                    prefix: "$",
                    decimalPlaces: 2
                }
            },
            {
                name: 'units',
                sortable: false,
                width: 50,
                hidden: true,
                formatter: unitsFormatter
            },
            {
                name: 'tax',
                sortable: false,
                width: 50,
                hidden: true,
                formatter: taxFormatter
            }],
        rowNum: 10,
        rowList: [10, 25, 50, 100],
        pager: $('#pager'),
        sortname: 'site_name',
        viewrecords: true,
        sortorder: "asc",
        height: "235px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect: true
    });

    jQuery("#list").jqGrid('navGrid', '#pager', {add: false, del: false, edit: false, refresh: false, search: false});

    $("#newQuote").jqGrid({
        url: 'ShowAmcapQuote.action?nd=' + new Date().getTime(),
        datatype: "json",
        colNames: ['Qty', 'S.O.H.', 'Product Item No', 'Product Description', 'Unit Price', 'Price', 'Tax', 'Cost'],
        colModel: [{
                name: 'quantity',
                width: 100,
                sortable: false,
                editable: true,
                editrules: {
                    required: true,
                    number: true,
                    minValue: 1,
                    maxValue: 999
                }
            }, {
                name: 'soh',
                sortable: false,
                hidden: true,
                width: 100
            },
            {
                name: 'product_code',
                sortable: false,
                width: 200
            },
            {
                name: 'description',
                sortable: false,
                width: 275
            },
            {
                name: 'unitPrice',
                sortable: false,
                width: 100,
                hidden: true
            },
            {
                name: 'price',
                sortable: false,
                width: 100,
                formatter: 'currency',
                formatoptions: {
                    prefix: "$",
                    decimalPlaces: 3
                }
            },
            {
                name: 'totaltax',
                sortable: false,
                width: 110,
                formatter: 'currency',
                formatoptions: {
                    prefix: "$",
                    decimalPlaces: 3
                }
            },
            {
                name: 'cost',
                width: 110,
                sortable: false,
                formatter: 'currency',
                formatoptions: {
                    prefix: "$",
                    decimalPlaces: 3
                }
            }],
        rowNum: 10,
        rowList: [10, 25, 50, 100],
        pager: $('#templatepager'),
        viewrecords: true,
        height: "235px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        editurl: 'DeleteQuoteItem.action',
        cellEdit: true,
        cellsubmit: "remote",
        cellurl: "EditQuote.action",
        afterSaveCell: function(rowid, name, val, iRow, iCol) {
            var unitPrice = $("#newQuote").getCell(rowid, iCol + 4);
            var price = new Number(parseFloat(unitPrice) * parseInt(val)).toFixed(2);
            var tax = new Number(price * 0.1).toFixed(2);
            var cost = new Number(Number(price) + Number(tax)).toFixed(2);
            $("#newQuote").setRowData(rowid, {
                price: price.toString()
            });
            $("#newQuote").setRowData(rowid, {
                totaltax: tax.toString()
            });
            $("#newQuote").setRowData(rowid, {
                cost: cost.toString()
            });
        }
    });
    jQuery("#newQuote").jqGrid('templatepager', '#pager', {add: false, del: false, edit: false, refresh: false, search: false});
    
     $("#save1").click(function(){
        $.getJSON("CheckQuote.action",function(result){
            if(result.check){
                $("form").attr("action", "SaveModifiedQuote.action").submit();
            }
            else{
                alert("There is not item in your quote, you could not save");
            }
        });
    });

});


