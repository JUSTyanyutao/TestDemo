$(function() {

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.redirectAddPage();
            this.bindDisableAction();
            this.bindEnableAction();
            this.exportAuction();
            this.recommendFlagAction();
            this.disRecommendFlagAction();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/brand/edit/{id}"
                },
                deleteBtn: {
                    url: ctx + "/brand/delete",
                    method: "POST",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },

        exportAuction:function(){

            $("#export").click(function(){
                var currentHref = window.location.href;
                var newHref = window.ctx+"/brand/export?fileName=goodsReport";
                if(currentHref.indexOf("?") > -1){
                    newHref = newHref+"&"+currentHref.split("?")[1];
                }
                newHref = newHref+"&"+$("#pagination-form").serialize();
                //console.log(newHref);
                window.location.href = newHref;
            });
        },

        /** 下架操作 */
        bindDisableAction: function() {
            $(".btn-disable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要下架的品牌");
                    return;
                }

                Dialog.confirm("下架品牌", "确认要下架这些品牌？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/brand/disable",
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

        /** 上架品牌 */
        bindEnableAction: function() {
            $(".btn-enable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要上架的品牌");
                    return;
                }

                Dialog.confirm("上架品牌", "确认要上架这些品牌？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/brand/enable",
                            type: "POST",
                            data: {
                                id: ids
                            },
                            dataType:"json",
                            success: function(data) {
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                }else{
                                    Dialog.danger(data.msg);
                                }
                            }
                        })
                    }
                });
            });
        },

        /** 一键置顶品牌 */
        recommendFlagAction: function() {
            $(".btn-recommendFlag").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要置顶的品牌");
                    return;
                }

                Dialog.confirm("置顶品牌", "确认要置顶这些品牌？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/brand/recommend",
                            type: "POST",
                            data: {
                                id: ids
                            },
                            dataType:"json",
                            success: function(data) {
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                }else{
                                    Dialog.danger(data.msg);
                                }
                            }
                        })
                    }
                });
            });
        },


        /** 取消置顶品牌 */
        disRecommendFlagAction: function() {
            $(".btn-disRecommendFlag").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要取消置顶的品牌");
                    return;
                }

                Dialog.confirm("取消置顶品牌", "确认要取消置顶这些品牌？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/brand/disRecommend",
                            type: "POST",
                            data: {
                                id: ids
                            },
                            dataType:"json",
                            success: function(data) {
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                }else{
                                    Dialog.danger(data.msg);
                                }
                            }
                        })
                    }
                });
            });
        },


        /** 跳转到添加商品页面 */
        redirectAddPage: function() {
            $(".btn-add").on("click", function() {
                location.href = ctx + "/brand/edit";
            });
        }
    };

    list.init();
});
