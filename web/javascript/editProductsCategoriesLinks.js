var sourceCategoryId=0;
var destinationCategoryId=0;

$(function(){
    $("#move").click(function(){
        var selectedRow = $("#list").getGridParam('selarrrow');
        if(sourceCategoryId!=0){
            if( selectedRow!="" ){
                if(destinationCategoryId!=0){
                    if(sourceCategoryId!=destinationCategoryId){
                        selectedRow=destinationCategoryId+","+selectedRow;
                        $("#list").delGridRow(selectedRow,{
                            reloadAfterSubmit:false,
                            msg:'Move Products to destination category?',
                            caption:'Move products',
                            bSubmit:'Move'
                        });
                    }else{
                        alert("Destination category should not be the same as source category");
                    }
                    
                }
                else{
                    alert("Please choose a destination category");
                }
            }
            else {
                alert("Please choose at least one product to move");
            }
        }
        else{
            alert("Please choose a source category");
        }
    });

    $("#list").jqGrid({
        url:'ShowProductsByCategory.action?nd='+new Date().getTime(),
        datatype: "json",
        colNames:['Product Code','GTIN', 'Product Name', 'UOM'],
        colModel:[ {
            name:'product_code1',
            index:'product_code1',
            width:150
        },

        {
            name:'gtin',
            index:'gtin',
            width:150
        },

        {
            name:'product_name',
            index:'product_name',
            width:250
        },

        {
            name:'uom',
            index:'uom',
            width:150
        }],
        rowNum:10,
        rowList:[10,25,50,100],
        pager: $('#pager'),
        sortname: 'id',
        viewrecords: true,
        sortorder: "asc",
        height: "235px",
        imgpath: "javascript/jqGrid/themes/basic/images",
        multiselect:true,
        editurl:'LinkProductsWithCategory.action'
    });

    $("#list").navGrid('#pager',{
        edit:false,
        add:false,
        del:false,
        refresh:false,
        search:false
    });

    $("#sourceCategories").tree({
        data    : {
            type    : "json", // ENUM [json, xml_flat, xml_nested, predefined]
            method  : "GET",        // HOW TO REQUEST FILES
            async   : true,        // BOOL - async loading onopen
            async_data : function (NODE) {
                return {
                    id : $(NODE).attr("id") || -1
                }
            }, // PARAMETERS PASSED TO SERVER
            url     : "ShowCategories.action",        // FALSE or STRING - url to document to be used (async or not)
            json    : false,        // FALSE or OBJECT if type is JSON and async is false - the tree dump as json
            xml     : false         // FALSE or STRING
        },
        selected    : "0",        // FALSE or STRING or ARRAY
        opened      : [],           // ARRAY OF INITIALLY OPENED NODES
        languages   : [],           // ARRAY of string values (which will be used as CSS classes - so they must be valid)
        path        : false,        // FALSE or STRING (if false - will be autodetected)
        cookies     : false,        // FALSE or OBJECT (prefix, open, selected, opts - from jqCookie - expires, path, domain, secure)
        ui      : {
            dots        : true,     // BOOL - dots or no dots
            rtl         : false,    // BOOL - is the tree right-to-left
            animation   : 0,        // INT - duration of open/close animations in miliseconds
            hover_mode  : true,     // SHOULD get_* functions chage focus or change hovered item
            scroll_spd  : 4,
            theme_path  : false,    // Path to themes
            theme_name  : "apple",// Name of theme
            context     : false
        },
        rules   : {
            multiple    : false,    // FALSE | CTRL | ON - multiple selection off/ with or without holding Ctrl
            metadata    : false,    // FALSE or STRING - attribute name (use metadata plugin)
            type_attr   : "rel",    // STRING attribute name (where is the type stored if no metadata)
            multitree   : false,    // BOOL - is drag n drop between trees allowed
            createat    : "bottom", // STRING (top or bottom) new nodes get inserted at top or bottom
            use_inline  : false,    // CHECK FOR INLINE RULES - REQUIRES METADATA
            clickable   : "all",    // which node types can the user select | default - all
            renameable  : "all",    // which node types can the user select | default - all
            deletable   : "all",    // which node types can the user delete | default - all
            creatable   : "all",    // which node types can the user create in | default - all
            draggable   : "none",   // which node types can the user move | default - none | "all"
            dragrules   : "all",    // what move operations between nodes are allowed | default - none | "all"
            drag_copy   : false,    // FALSE | CTRL | ON - drag to copy off/ with or without holding Ctrl
            droppable   : [],
            drag_button : "left"
        },
        lang : {
            new_node    : "New Category",
            loading     : "Loading ..."
        },
        callback    : {             // various callbacks to attach custom logic to
            // before focus  - should return true | false
            beforechange: function(NODE,TREE_OBJ) {
                return true
            },
            beforeopen  : function(NODE,TREE_OBJ) {
                return true
            },
            beforeclose : function(NODE,TREE_OBJ) {
                return true
            },
            // before move   - should return true | false
            beforemove  : function(NODE,REF_NODE,TYPE,TREE_OBJ) {
                return true
            },
            // before create - should return true | false
            beforecreate: function(NODE,REF_NODE,TYPE,TREE_OBJ) {
                return true
            },
            // before rename - should return true | false
            beforerename: function(NODE,LANG,TREE_OBJ) {
                return true
            },
            // before delete - should return true | false
            beforedelete: function(NODE,TREE_OBJ) {
                return true
            },

            onJSONdata  : function(DATA,TREE_OBJ) {
                return DATA;
            },
            onselect    : function(NODE,TREE_OBJ) {
                sourceCategoryId=$(NODE).attr("id");
                if(sourceCategoryId!=0){
                    $(".selectedSourceCategory").text($(NODE).children("a").text());
                    $("#list").setGridParam({
                        url:"ShowProductsByCategory.action?categoryId="+sourceCategoryId
                    });
                    $("#list").setGridParam({
                        page:1
                    });
                    $("#list").setGridParam({
                        rowNum:10
                    });
                    $("#list").trigger("reloadGrid");
                }
                else{
                    $(".selectedSourceCategory").text("");
                }
            },                  // node selected
            ondeselect  : function(NODE,TREE_OBJ) { },                  // node deselected
            onchange    : function(NODE,TREE_OBJ) { },                  // focus changed
            onrename    : function(NODE,LANG,TREE_OBJ,RB) {},              // node renamed ISNEW - TRUE|FALSE, current language
            onmove      : function(NODE,REF_NODE,TYPE,TREE_OBJ,RB) { }, // move completed (TYPE is BELOW|ABOVE|INSIDE)
            oncopy      : function(NODE,REF_NODE,TYPE,TREE_OBJ,RB) { }, // copy completed (TYPE is BELOW|ABOVE|INSIDE)
            oncreate    : function(NODE,REF_NODE,TYPE,TREE_OBJ,RB) {}, // node created, parent node (TYPE is createat)
            ondelete    : function(NODE, TREE_OBJ,RB) { },                  // node deleted
            onopen      : function(NODE, TREE_OBJ) { },                 // node opened
            onopen_all  : function(TREE_OBJ) { },                       // all nodes opened
            onclose     : function(NODE, TREE_OBJ) { },                 // node closed
            error       : function(TEXT, TREE_OBJ) { },                 // error occured
            // double click on node - defaults to open/close & select
            ondblclk    : function(NODE, TREE_OBJ) {
                if(sourceCategoryId==0){
                    TREE_OBJ.toggle_branch.call(TREE_OBJ, NODE); TREE_OBJ.select_branch.call(TREE_OBJ, NODE);
                }
            },
            // right click - to prevent use: EV.preventDefault(); EV.stopPropagation(); return false
            onrgtclk    : function(NODE, TREE_OBJ, EV) { },
            onload      : function(TREE_OBJ) { },
            onfocus     : function(TREE_OBJ) { },
            ondrop      : function(NODE,REF_NODE,TYPE,TREE_OBJ) {}
        }
    });

    $("#destinationCategories").tree({
        data    : {
            type    : "json", // ENUM [json, xml_flat, xml_nested, predefined]
            method  : "GET",        // HOW TO REQUEST FILES
            async   : true,        // BOOL - async loading onopen
            async_data : function (NODE) {
                return {
                    id : $(NODE).attr("id") || -1
                }
            }, // PARAMETERS PASSED TO SERVER
            url     : "ShowCategories.action",        // FALSE or STRING - url to document to be used (async or not)
            json    : false,        // FALSE or OBJECT if type is JSON and async is false - the tree dump as json
            xml     : false         // FALSE or STRING
        },
        selected    : "0",        // FALSE or STRING or ARRAY
        opened      : [],           // ARRAY OF INITIALLY OPENED NODES
        languages   : [],           // ARRAY of string values (which will be used as CSS classes - so they must be valid)
        path        : false,        // FALSE or STRING (if false - will be autodetected)
        cookies     : false,        // FALSE or OBJECT (prefix, open, selected, opts - from jqCookie - expires, path, domain, secure)
        ui      : {
            dots        : true,     // BOOL - dots or no dots
            rtl         : false,    // BOOL - is the tree right-to-left
            animation   : 0,        // INT - duration of open/close animations in miliseconds
            hover_mode  : true,     // SHOULD get_* functions chage focus or change hovered item
            scroll_spd  : 4,
            theme_path  : false,    // Path to themes
            theme_name  : "apple",// Name of theme
            context     : false
        },
        rules   : {
            multiple    : false,    // FALSE | CTRL | ON - multiple selection off/ with or without holding Ctrl
            metadata    : false,    // FALSE or STRING - attribute name (use metadata plugin)
            type_attr   : "rel",    // STRING attribute name (where is the type stored if no metadata)
            multitree   : false,    // BOOL - is drag n drop between trees allowed
            createat    : "bottom", // STRING (top or bottom) new nodes get inserted at top or bottom
            use_inline  : false,    // CHECK FOR INLINE RULES - REQUIRES METADATA
            clickable   : "all",    // which node types can the user select | default - all
            renameable  : "all",    // which node types can the user select | default - all
            deletable   : "all",    // which node types can the user delete | default - all
            creatable   : "all",    // which node types can the user create in | default - all
            draggable   : "none",   // which node types can the user move | default - none | "all"
            dragrules   : "all",    // what move operations between nodes are allowed | default - none | "all"
            drag_copy   : false,    // FALSE | CTRL | ON - drag to copy off/ with or without holding Ctrl
            droppable   : [],
            drag_button : "left"
        },
        lang : {
            new_node    : "New Category",
            loading     : "Loading ..."
        },
        callback    : {             // various callbacks to attach custom logic to
            // before focus  - should return true | false
            beforechange: function(NODE,TREE_OBJ) {
                return true
            },
            beforeopen  : function(NODE,TREE_OBJ) {
                return true
            },
            beforeclose : function(NODE,TREE_OBJ) {
                return true
            },
            // before move   - should return true | false
            beforemove  : function(NODE,REF_NODE,TYPE,TREE_OBJ) {
                return true
            },
            // before create - should return true | false
            beforecreate: function(NODE,REF_NODE,TYPE,TREE_OBJ) {
                return true
            },
            // before rename - should return true | false
            beforerename: function(NODE,LANG,TREE_OBJ) {
                return true
            },
            // before delete - should return true | false
            beforedelete: function(NODE,TREE_OBJ) {
                return true
            },

            onJSONdata  : function(DATA,TREE_OBJ) {
                return DATA;
            },
            onselect    : function(NODE,TREE_OBJ) {
                destinationCategoryId=$(NODE).attr("id");
                if(destinationCategoryId!=0){
                    $(".selectedDestinationCategory").text($(NODE).children("a").text());
                }
                else{
                    $(".selectedDestinationCategory").text("");
                }
            },                  // node selected
            ondeselect  : function(NODE,TREE_OBJ) { },                  // node deselected
            onchange    : function(NODE,TREE_OBJ) { },                  // focus changed
            onrename    : function(NODE,LANG,TREE_OBJ,RB) {},              // node renamed ISNEW - TRUE|FALSE, current language
            onmove      : function(NODE,REF_NODE,TYPE,TREE_OBJ,RB) { }, // move completed (TYPE is BELOW|ABOVE|INSIDE)
            oncopy      : function(NODE,REF_NODE,TYPE,TREE_OBJ,RB) { }, // copy completed (TYPE is BELOW|ABOVE|INSIDE)
            oncreate    : function(NODE,REF_NODE,TYPE,TREE_OBJ,RB) {}, // node created, parent node (TYPE is createat)
            ondelete    : function(NODE, TREE_OBJ,RB) { },                  // node deleted
            onopen      : function(NODE, TREE_OBJ) { },                 // node opened
            onopen_all  : function(TREE_OBJ) { },                       // all nodes opened
            onclose     : function(NODE, TREE_OBJ) { },                 // node closed
            error       : function(TEXT, TREE_OBJ) { },                 // error occured
            // double click on node - defaults to open/close & select
            ondblclk    : function(NODE, TREE_OBJ) {
                if(destinationCategoryId==0){
                    TREE_OBJ.toggle_branch.call(TREE_OBJ, NODE); TREE_OBJ.select_branch.call(TREE_OBJ, NODE);
                }
            },
            // right click - to prevent use: EV.preventDefault(); EV.stopPropagation(); return false
            onrgtclk    : function(NODE, TREE_OBJ, EV) { },
            onload      : function(TREE_OBJ) { },
            onfocus     : function(TREE_OBJ) { },
            ondrop      : function(NODE,REF_NODE,TYPE,TREE_OBJ) {}
        }
    });
});