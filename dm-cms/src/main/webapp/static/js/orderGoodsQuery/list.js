$(function() {
	
	
	

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.orderSearchByCustomStatus();
            this.exportExcel();
            this.bindDisableAction();
            this.bindEnableAction();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12  // 每页显示12条记录
            });
        },

        /**根据自定义状态去搜索*/
        orderSearchByCustomStatus : function() {
            $(".btn-order-search").on("click", function () {
                $("#search_EQ_deliveryStatus").val($(this).attr("data-deliveryStatus"));
                var formData = $("#pagination-form").serialize();
                $(".btn-order-search").removeClass("btn-success").addClass("btn-default");;
                $(this).addClass("btn-success");
                Pagination.reload(formData);
            });
        },

        exportExcel :function(){
            $(".btn-export").on("click",function(){
                var currentHref = window.location.href;
                var newHref = window.ctx+"/orderGoodsQuery/export?fileName=orderGoodsReport&"+$("#pagination-form").serialize();
                if(currentHref.indexOf("?") > -1){
                    newHref = newHref+"&"+currentHref.split("?")[1];
                }
                window.location.href = newHref;
            });
        },

        /** 绑定禁用操作 */
        bindDisableAction: function() {
            $(".btn-disable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要禁用的区域");
                    return;
                }

                Dialog.confirm("禁用区域", "确认要禁用这些区域？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/siteStation/disable",
                            type: "POST",
                            data: {
                                id: ids
                            },
                            dataType:"json",
                            success: function(data) {
                            	
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                    //exports.reload();
                                }else{
                                	Dialog.danger(data.msg);
                                }
                            }
                        })
                    }
                });
            });
        },

        /** 绑定解禁操作 */
        bindEnableAction: function() {
            $(".btn-enable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要解禁的区域");
                    return;
                }

                Dialog.confirm("解禁区域", "确认要解禁这些区域？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/siteStation/enable",
                            type: "POST",
                            data: {
                                id: ids
                            },
                            dataType:"json",
                            success: function(data) {
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                }else{
                                	Dialog.danger(data.msg);
                                }
                            }
                        })
                    }
                });
            });
        },
       

    };

    list.init();
    
    
});
