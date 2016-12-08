$(function() {

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.redirectAddPage();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/faq/ponint/{id}"
                },
                deleteBtn: {
                    url: ctx + "/faq/ponint/delete",
                    method: "POST",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },

        /** 跳转到添加菜单页面 */
        redirectAddPage: function() {
            $(".btn-add").on("click", function() {
                location.href = ctx + "/faq/ponint";
            });
        }
    };

    list.init();
    $(document).on("click",".messageContent",function(){
        $("#myModal .modal-body").html($(this).html());
    });
});
