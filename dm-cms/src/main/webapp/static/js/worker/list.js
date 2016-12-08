$(function() {
	
	var $form = $("#solveForm");
	

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.solveAction();
            this.confirmAction();
            this.gotoCar();
            this.gotoShop();
            this.bindViewAddressAction();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/worker/edit/{id}"
                },
                deleteBtn: {
                    url: ctx + "/worker/delete",
                    callback: function(data) {
                        // 回调函数
                        //if (data.code == 0) {
                        //    Dialog.success(data.msg);
                        //    Pagination.reload();
                        //}else{
                        //    Dialog.danger(data.msg);
                        //}

                    }
                }
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


        /** 绑定处理操作 */
        solveAction: function() {
            $(".btn-solve-action").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要审核的技师");
                    return;
                }
                 //判断要编辑的记录是否超过限制
                if (ids.length > 1) {
                    Dialog.warning("警告！一次只能审核一个技师");
                    return;
                }
                $("#id").val(ids[0]);

                /**
                 * 获取 服务 分类
                 */

                $.ajax({
                    url: window.ctx + "/category/getCategoryByType/2",
                    type:"get",
                    dataType:'json',
                    success:function(data){
                        $("#categoryId").select2({
                            placeholder: {
                                id: "-1",
                                text: "请选择服务分类",
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
                            multiple:true,
                            allowClear: true
                        });
                        //$("#categoryId").val($("#categoryId").attr("data-select")).trigger("change");
                    }
                });
                $('#solveContent').modal('show');
            });
        },

        /** 确认处理操作 */
        confirmAction: function() {
            $(".solveBtn").on("click",function(){
                $form.validate({
                    ignore: "",
                    rules: {
                        remark:{
                            required:true,
                            maxlength: 255
                        }
                    },
                    messages: {
                        remark:{
                            required:"请输入处理结果",
                            maxlength: "处理结果的长度不能超过{0}个字符"
                        }
                    },
                    submitHandler: function() {
                        var buttonObj = this.submitButton;
                        $(buttonObj).button("loading");
                        var status = 1;
                        if($(buttonObj).text() == "反驳中..."){
                            status = 2;
                        }
                        if(status == 1) {
                            if($("#categoryId").val() == null) {
                                Dialog.danger("请选择服务类型");
                                $(buttonObj).button("reset");
                                return;
                            }
                        }
                        $.ajax({
                            url: window.ctx+"/worker/approve?status="+status,
                            type: "POST",
                            data: $form.serialize(),
                            dataType: "JSON",
                            success: function(data) {
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                    $(".solveBtn").button("reset");
                                    $("#remark").val("");
                                    $("#cancleSolve").click();
                                    //exports.reload();
                                }else{

                                    Dialog.danger(data.msg);
                                    $submitBtn.button("reset");
                                }
                            }
                        });
                    }
                });
            });
        },


    };

    list.init();
    
    
});
