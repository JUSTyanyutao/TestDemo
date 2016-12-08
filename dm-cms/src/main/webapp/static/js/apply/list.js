$(function() {
	
	
	

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/goodsApproval/editApply/{id}"
                },
                deleteBtn: {
                    url: ctx + "/goodsApproval/deleteApply",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },
        
        

    };

    list.init();
    
    
});
