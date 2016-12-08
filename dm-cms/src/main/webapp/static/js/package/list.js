$(function() {

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.redirectAddPage();
            this.bindDisableAction();
            this.bindEnableAction();
            this.exportAuction();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/package/edit/{id}"
                },
                deleteBtn: {
                    url: ctx + "/package/delete",
                    method: "POST",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },

        exportAuction:function(){

            $("#export").click(function(){
                var currentHref = window.location.href;
                var newHref = window.ctx+"/brand/export?fileName=goodsReport";
                if(currentHref.indexOf("?") > -1){
                    newHref = newHref+"&"+currentHref.split("?")[1];
                }
                newHref = newHref+"&"+$("#pagination-form").serialize();
                //console.log(newHref);
                window.location.href = newHref;
            });
        },

        /** 禁用操作 */
        bindDisableAction: function() {
            $(".btn-disable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要禁用的套餐");
                    return;
                }

                Dialog.confirm("禁用套餐", "确认要禁用这些套餐？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/package/disable",
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
                    Dialog.warning("请先选择您要启用的套餐");
                    return;
                }

                Dialog.confirm("启用套餐", "确认要启用这些套餐？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/package/enable",
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


        /** 跳转到添加按钮页面 */
        redirectAddPage: function() {
            $(".btn-add").on("click", function() {
                location.href = ctx + "/package/edit";
            });
        }
    };

    list.init();
});