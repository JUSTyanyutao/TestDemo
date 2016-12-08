$(function() {
	
	var $form = $("#solveForm");
	

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.solveAction();
            this.confirmAction();
            this.recommendAction();
            this.disRecommendAction();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                //editBtn: {
                //    url: ctx + "/worker/edit/{id}"
                //},
                deleteBtn: {
                    url: ctx + "/goods/show/delete",
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


        /** 绑定处理操作 */
        solveAction: function() {
            $(".btn-solve-action").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要审核的大家说");
                    return;
                }
                // //判断要编辑的记录是否超过限制
                //if (ids.length > 1) {
                //    Dialog.warning("警告！一次只能审核一个技师");
                //    return;
                //}
                $("#id").val(ids);
                $('#solveContent').modal('show');
            });
        },

        /** 确认处理操作 */
        confirmAction: function() {
            $(".solveBtn").on("click",function(){
                $form.validate({
                    ignore: "",
                    //rules: {
                    //    remark:{
                    //        required:true,
                    //        maxlength: 255
                    //    },
                    //},
                    //messages: {
                    //    remark:{
                    //        required:"请输入处理结果",
                    //        maxlength: "处理结果的长度不能超过{0}个字符"
                    //    },
                    //},
                    submitHandler: function() {
                        var buttonObj = this.submitButton;
                        $(buttonObj).button("loading");
                        var status = 1;
                        if($(buttonObj).text() == "反驳中..."){
                            status = 2;
                        }
                        $.ajax({
                            url: window.ctx+"/goods/show/approve?status="+status,
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


        /** 置顶操作 */
        recommendAction: function() {
            $(".btn-recommend-action").on("click",function(){
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要置顶的大家说");
                    return;
                }
                Dialog.confirm("置顶大家说", "确认要置顶这些大家说？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: window.ctx+"/goods/show/recommend",
                            type: "POST",
                            data: {
                                id: ids
                            },
                            dataType: "JSON",
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


        /**  取消置顶操作 */
        disRecommendAction: function() {
            $(".btn-disRecommend-action").on("click",function(){
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要取消置顶的大家说");
                    return;
                }
                Dialog.confirm("取消置顶大家说", "确认要取消置顶这些大家说？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: window.ctx+"/goods/show/disRecommend",
                            type: "POST",
                            data: {
                                id: ids
                            },
                            dataType: "JSON",
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
