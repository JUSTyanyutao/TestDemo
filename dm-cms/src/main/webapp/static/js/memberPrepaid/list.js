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
                    url: ctx + "/memberPrepaid/editMemberPrepaid/{id}"
                },
                deleteBtn: {
                    url: ctx + "/memberPrepaid/deleteMemberPrepaid",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },
        
    };

    list.init();
    
    
});
