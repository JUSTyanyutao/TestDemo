
var $searchForm = $("#pagination-form");
$(function() {
	
    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.bindMergeAction();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/member/editMember/{id}"
                },
                deleteBtn: {
                    url: ctx + "/member/deleteMember",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },
        
        /** 绑定禁用操作 */
        bindMergeAction: function() {
            $(".btn-merge-action").on("click", function() {
                //var ids = Pagination.getCheckedIds();
                var ids = [];
                var orderIds = [];
                $(".checkbox-item").each(function() {
                    if (this.checked) {
                        ids.push(this.value);
                        var $order = $(this).parents("table").find(".orderId");
                        $order.each(function(){
                        	orderIds.push(this.value);
                        });
                    }
                });

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要合并的记录");
                    return;
                }
                
                // 判断要编辑的记录是否超过限制
                if (ids.length > 1) {
                    Dialog.warning("警告！一次只能合并一条记录");
                    return;
                }

                Dialog.confirm("合并订单", "确认要合并这条记录下的订单,原有订单将会被删除？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/mergeOrder/merge",
                            type: "POST",
                            data: {
                            	orderIds: orderIds
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

