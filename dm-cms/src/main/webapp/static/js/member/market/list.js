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
                    url: ctx + "/member/market/{id}"
                },
                deleteBtn: {
                    url: ctx + "/member/market/delete",
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
                    Dialog.warning("请先选择您要禁用的推广管理人员");
                    return;
                }

                Dialog.confirm("禁用", "确认要禁用这些推广管理人员？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/member/market/disable",
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
                    Dialog.warning("请先选择您要解禁的推广管理人员");
                    return;
                }

                Dialog.confirm("解禁", "确认要解禁这些推广管理人员？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/member/market/enable",
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
        }
    };

    list.init();
    
    
});
