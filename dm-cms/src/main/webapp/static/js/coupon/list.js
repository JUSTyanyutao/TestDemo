$(function() {

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.redirectAddPage();
            this.updateCoupnAction();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/member/coupon/edit/{id}"
                },
                deleteBtn: {
                    url: ctx + "/member/coupon/delete",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },
        
        updateCoupnAction: function() {
            $(".btn-update").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择数据");
                    return;
                }

                Dialog.confirm("设为无效", "确认要将这些优惠券设为无效？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/member/coupon/update",
                            type: "POST",
                            data: {
                                id: ids
                            },
                            dataType:"json",
                            success: function(data) {
                            	
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                }
                            }
                        })
                    }
                });
            });
        },
        
        /** 跳转到添加菜单页面 */
        redirectAddPage: function() {
            $(".btn-add").on("click", function() {
                location.href = ctx + "/member/addCoupon/"+$("#memberId").val();
            });
        }

    };

    list.init();
    
});
