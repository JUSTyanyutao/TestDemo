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
                    url: ctx + "/promotion/editPromotion/{id}"
                },
                deleteBtn: {
                    url: ctx + "/promotion/deletepromotion",
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
                    Dialog.warning("请先选择您要下架的优惠活动");
                    return;
                }

                Dialog.confirm("下架优惠活动", "确认要下架这些优惠活动？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/promotion/disable",
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
                    Dialog.warning("请先选择您要上架的优惠活动");
                    return;
                }

                Dialog.confirm("上架优惠活动", "确认要上架这些优惠活动？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/promotion/enable",
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
