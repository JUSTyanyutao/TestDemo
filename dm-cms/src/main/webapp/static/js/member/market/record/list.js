$(function() {

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 15,   // 每页显示12条记录
                // deleteBtn: {
                //     url: ctx + "/member/market/delete",
                //     callback: function(data) {
                //         // 回调函数
                //
                //     }
                // }
            });
        }
    };

    list.init();
    
    
});
