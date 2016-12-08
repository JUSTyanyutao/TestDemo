$(function() {

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.redirectAddPage();
            this.bindDisableAction();
            this.bindEnableAction();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/carPart/edit/{id}"
                },
                deleteBtn: {
                    url: ctx + "/carPart/delete",
                    method: "POST",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },

        /** 禁用部位 */
        bindDisableAction: function() {
            $(".btn-disable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要禁用的部位");
                    return;
                }

                Dialog.confirm("禁用部位", "确认要禁用这些部位？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/carPart/disable",
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

        /** 启用 */
        bindEnableAction: function() {
            $(".btn-enable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要启用的部位");
                    return;
                }

                Dialog.confirm("启用", "确认要启用这些部位？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/carPart/enable",
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


        /** 跳转到添加部位页面*/
        redirectAddPage: function() {
            $(".btn-add").on("click", function() {
                location.href = ctx + "/carPart/edit";
            });
        }
    };

    list.init();
});
