$(function() {

    var $form = $("#solveForm");
    var $submitBtn = $("#confirmsolve");

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.redirectAddPage();
            //this.solveAction();
            //this.confirmAction();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/param/edit/{id}"
                },
                deleteBtn: {
                    url: ctx + "/param/delete",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },


        /** 绑定处理操作 */
        solveAction: function() {
            $(".btn-solve-action").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要处理的意见反馈");
                    return;
                }
                // 判断要编辑的记录是否超过限制
                if (ids.length > 1) {
                    Dialog.warning("警告！一次只能处理一条意见反馈");
                    return;
                }
                $("#fid").val(ids[0]);
                $("#result").html('');
                $('#solveContent').modal('show');

            });
        },

        /** 确认处理操作 */
        confirmAction: function() {
            $("#confirmsolve").on("click",function(){
                $form.validate({
                    ignore: "",
                    rules: {
                        result:{
                            required:true,
                            maxlength: 255
                        }
                    },
                    messages: {
                        key: {
                            required: "请输入参数变量",
                            maxlength: "产品分类名称长度不能超过{0}个字符"
                        },
                        value: {
                            required: "请输入参数值",
                            maxlength: "产品分类名称长度不能超过{0}个字符"
                        },
                        paramDesc: {
                            required: "请输入参数名称",
                            maxlength: "产品分类名称长度不能超过{0}个字符"
                        }
                    },
                    submitHandler: function() {
                        var buttonObj = this.submitButton;
                        $(buttonObj).button("loading");
                        $.ajax({
                            url: window.ctx+"/poFeedBack/solveFeedBack",
                            type: "POST",
                            data: $form.serialize(),
                            dataType: "JSON",
                            success: function(data) {

                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                    $submitBtn.button("reset");
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

        /** 跳转到添加参数页面 */
        redirectAddPage: function() {
            $(".btn-add").on("click", function() {
                location.href = ctx + "/param";
            });
        }


    };

    list.init();
});
