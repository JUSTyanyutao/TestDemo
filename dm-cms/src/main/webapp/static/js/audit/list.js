$(function() {
	
	
	

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.bindAuditAction();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/goodsApproval/auditApply/{id}"
                },
                deleteBtn: {
                    url: ctx + "/goodsApproval/deleteApply",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },
        
        /** 绑定禁用操作 */
        bindAuditAction: function() {
            $(".btn-audit-action").on("click", function() {
                var ids = Pagination.getCheckedIds();
                
                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要审批的申请");
                    return;
                }

                Dialog.confirm("审核申请", "确认要审核这些申请？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/goodsApproval/audit",
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

        
        

    };

    list.init();
    
    
});
