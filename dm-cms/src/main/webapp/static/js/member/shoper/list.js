$(function() {
	
	
	

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.bindDisableAction();
            this.bindEnableAction();
            this.gotoCar();
            this.gotoShop();
            this.bindViewAddressAction();
            //this.bindshoppingCarAction();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/member/editShoperMember/{id}"
                },
                deleteBtn: {
                    url: ctx + "/member/deleteMember",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },

        /**
         * 绑定查看购物篮
         */
        bindshoppingCarAction: function() {
            $(".btn-shoppingCar-action").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要查看的用户");
                    return;
                }

                if (ids.length >= 2) {
                    Dialog.warning("每次只能查看一个会员的购物篮");
                    return ;
                }

                location.href = ctx + "/member/shoppingCar?uid=" + ids[0];
            });
        },


        /**
         * 绑定查看收货地址按钮
         */
        bindViewAddressAction: function() {
            $(".btn-address").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要查看的用户");
                    return;
                }

                if (ids.length >= 2) {
                    Dialog.warning("每次只能查看一个会员的收货地址");
                    return ;
                }

                location.href = ctx + "/member/addresses?uid=" + ids[0];
            });
        },

        /** 绑定禁用操作 */
        bindDisableAction: function() {
            $(".btn-disable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要禁用的用户");
                    return;
                }

                Dialog.confirm("禁用用户", "确认要禁用这些用户？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/member/disable",
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
                    Dialog.warning("请先选择您要解禁的用户");
                    return;
                }

                Dialog.confirm("解禁用户", "确认要解禁这些用户？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/member/enable",
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
        /** 跳转至 车 列表 */
        gotoCar: function() {
            $(".btn-car").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择一个用户");
                    return;
                }

                if (ids.length > 1) {
                    Dialog.warning("请勿选择多条数据");
                    return;
                }
                
                location.href = ctx + "/member/car/list/"+ids;
            });
        },

        /** 跳转至 场地 列表 */
        gotoShop: function() {
            $(".btn-shop").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择一个用户");
                    return;
                }

                if (ids.length > 1) {
                    Dialog.warning("请勿选择多条数据");
                    return;
                }

                location.href = ctx + "/member/shop/list/"+ids;
            });
        },

        /** 导出事件 */
        memberExport: function() {
            $(".btn-export").on("click", function() {
                var currentHref = window.location.href;
                var newHref = window.ctx+"/member/export?fileName=memberReport";
                if(currentHref.indexOf("?") > -1){
                    newHref = newHref+"&"+currentHref.split("?")[1];
                }
                newHref = newHref+"&"+$("#pagination-form").serialize();
                //console.log(newHref);
                window.location.href = newHref;
            });
        }
    };

    list.init();

    
});
