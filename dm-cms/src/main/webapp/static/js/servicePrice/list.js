$(function() {

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.redirectAddPage();
            this.bindDisableAction();
            this.bindEnableAction();
            //this.loadCity();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/service/price/edit/{id}"
                },
                deleteBtn: {
                    url: ctx + "/service/price/delete",
                    method: "POST",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },

        /**
         * 绑定城市
         */
        loadCity:function(){
            $.ajax({
                url: window.ctx + "/city/getCity",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#city").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择城市",
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
                    $("#city").val($("#city").attr("data-select")).trigger("change");
                }
            })
        },

        /** 绑定禁用操作 */
        bindDisableAction: function() {
            $(".btn-disable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要禁用的商品价格");
                    return;
                }

                Dialog.confirm("禁用商品价格", "确认要禁用这些商品价格？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/service/price/disable",
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

        /** 绑定解禁操作 */
        bindEnableAction: function() {
            $(".btn-enable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要启用的商品价格");
                    return;
                }
                Dialog.confirm("启用商品价格", "确认要启用这些商品价格？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/service/price/enable",
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
        
        /** 跳转页面 */
        redirectAddPage: function() {
            $(".btn-add").on("click", function() {
                location.href = ctx + "/service/price/edit";
            });
        }
    };
    list.init();
});
