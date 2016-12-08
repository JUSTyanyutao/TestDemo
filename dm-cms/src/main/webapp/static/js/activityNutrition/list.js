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
                    url: ctx + "/activityNutrition/{id}"
                },
                deleteBtn: {
                    url: ctx + "/activityNutrition/delete",
                    method: "POST",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },

        /** 跳转到添加页面 */
        redirectAddPage: function() {
            $(".btn-add").on("click", function() {
                location.href = ctx + "/activityNutrition";
            });
        }
    };

    list.init();
});
