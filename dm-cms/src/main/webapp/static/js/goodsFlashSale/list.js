$(function() {
	
	
	

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.bindDisableAction();
            this.bindEnableAction();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/goodsFlashSale/editGoodsFlashSale/{id}"
                },
                deleteBtn: {
                    url: ctx + "/goodsFlashSale/deleteGoodsFlashSale",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },
        
        /** 绑定禁用操作 */
        bindDisableAction: function() {
            $(".btn-disable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要下架的限时抢购活动");
                    return;
                }

                Dialog.confirm("下架限时抢购商品", "确认要下架这些限时抢购活动？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/goodsFlashSale/disable",
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
                    Dialog.warning("请先选择您要上架的限时抢购活动");
                    return;
                }

                Dialog.confirm("上架限时抢购商品", "确认要上架这些限时抢购活动？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/goodsFlashSale/enable",
                            type: "POST",
                            data: {
                                id: ids
                            },
                            dataType:"json",
                            success: function(data) {
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
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
