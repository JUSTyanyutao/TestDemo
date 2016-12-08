$(function() {
	
	
    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.bindDisableAction();
            this.bindEnableAction();
            this.bindApprovalAction();
            this.bindViewOrderAction();
            this.bindViewGoodsAction();
            this.loadFoodMarket();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/merchant/editMerchant/{id}"
                },
                deleteBtn: {
                    url: ctx + "/merchant/deleteMerchant",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },

        
        loadFoodMarket:function(){
        	$.ajax({
        		  url: window.ctx + "/select2/getMarketList",
        		  type:"get",
        		  dataType:'json',
        		  success:function(data){
        				$("#foodMarketSelect").select2({
          				  placeholder: {
          					  id: "-1",
          					  text: "请选择菜场",
          					},
          			      data: data,
          			      matcher: function (params, data) {
          			    	if ($.trim(params.term) === '') {
         					    return data;
         					  }
         					  if (data.text.indexOf(params.term) > -1) {
         						  return $.extend({}, data, true);
         					  }
          					  return null;
          					},
          			      allowClear: true
          			  });

        		  }
        	  })
        },
        
        /**
         * 绑定查看商家订单
         */
        bindViewOrderAction: function() {
            $(".btn-order").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要查看的商家");
                    return;
                }

                if (ids.length >= 2) {
                    Dialog.warning("每次只能查看一个商家的订单");
                    return ;
                }

                location.href = ctx + "/order?merchantId=" + ids[0];
            });
        },
        
        /**
         * 查看商家商品
         */
        bindViewGoodsAction: function() {
            $(".btn-goods").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要查看的商家");
                    return;
                }

                if (ids.length >= 2) {
                    Dialog.warning("每次只能查看一个商家的商品列表");
                    return ;
                }

                location.href = ctx + "/goods?merchantId=" + ids[0];
            });
        },

        /** 绑定禁用操作 */
        bindDisableAction: function() {
            $(".btn-disable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要禁用的商家");
                    return;
                }

                Dialog.confirm("禁用商家", "确认要禁用这些商家？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/merchant/disable",
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
                                }
                            }
                        })
                    }
                });
            });
        },

        /** 绑定解禁操作 */
        bindEnableAction: function() {
            $(".btn-enable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要解禁的商家");
                    return;
                }

                Dialog.confirm("解禁商家", "确认要解禁这些商家？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/merchant/enable",
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
        
        
        
        /** 绑定审批操作 */
        bindApprovalAction: function() {
            $(".btn-approval").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要审批的商家");
                    return;
                }

                Dialog.confirm("审批商家", "确认要审批通过这些商家？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/merchant/approval",
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
        }
    };

    list.init();
});
