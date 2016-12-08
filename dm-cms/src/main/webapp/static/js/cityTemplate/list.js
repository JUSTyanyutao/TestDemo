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
                    url: ctx + "/city/template/edit/{id}"
                },
                deleteBtn: {
                    url: ctx + "/city/template/delete",
                    method: "POST",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },

        /** 禁用操作 */
        bindDisableAction: function() {
            $(".btn-disable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要禁用的模版");
                    return;
                }

                Dialog.confirm("禁用模版", "确认要禁用这些模版？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/city/template/disable",
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

        /** 启用操作 */
        bindEnableAction: function() {
            $(".btn-enable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要启用的模版");
                    return;
                }

                Dialog.confirm("启用模版", "确认要启用这些模版？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/city/template/enable",
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


        /** 跳转到添加模版页面 */
        redirectAddPage: function() {
            $(".btn-add").on("click", function() {
                location.href = ctx + "/city/template/edit";
            });
        }
    };

    list.init();
});
