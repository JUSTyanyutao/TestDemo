$(function() {

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            //this.redirectAddPage();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                deleteBtn: {
                    url: ctx + "/logPayment/delete",
                    method: "POST",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },


        /** 绑定处理操作 */
        solveAction: function() {
            $(".btn-edit-action").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要处理的问题");
                    return;
                }
                // 判断要编辑的记录是否超过限制
                if (ids.length > 1) {
                    Dialog.warning("警告！一次只能处理一条问题");
                    return;
                }
                $("#fid").val(ids[0]);
                $("#result").html('');
                $('#solveContent').modal('show');

            });
        },


        /** 跳转到添加菜单页面 */
        redirectAddPage: function() {
            $(".btn-add").on("click", function() {
                location.href = ctx + "/faq";
            });
        }
    };

    list.init();
});
