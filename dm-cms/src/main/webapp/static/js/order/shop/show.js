$(function() {
    var $form = $("#solveForm");
    var $submitBtn = $("#confirmsolve");
    var list = {
        /** 初始化 */
        init: function() {
            //this.loadWorker();
            this.sureAction();
            //this.orderRemarkAction();
            //this.confirmAction();
            //this.completeAction();
        },

        /** 确认处理操作 */
        sureAction: function() {
            $(".btn-submit").on("click",function(){
                if($("#worker").val() == null)
                {
                    return ;
                }
                $.ajax({
                    url: window.ctx+"/order/worker/updateWorker/"+$("#oid").val()+"/"+$("#worker").val(),
                    type: "POST",
                    data: $form.serialize(),
                    dataType: "JSON",
                    success: function(data) {
                        if (data.code == 0) {
                            Dialog.success(data.msg);
                            //$submitBtn.button("reset");
                            //$("#cancleSolve").click();
                            //$("#remarkLabel").text($("#remark").val());
                            //$("#updateTimeLabel").text(data.data);
                            //exports.reload();
                        }else{
                            Dialog.danger(data.msg);
                        }
                    }
                });
            });
        },











        loadWorker:function(){
            $.ajax({
                url: window.ctx + "/worker/getAllWorker",
                type:"get",
                dataType:'json',
                success:function(data){
                    $("#worker").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择技师",
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
                    $("#worker").val($("#worker").attr("data-select")).trigger("change");
                }
            })
        },

        /** 订单备注操作 */
        orderRemarkAction: function() {
            $("#remarkBtn").on("click", function() {
                var id = $("#orderId").val();
                $("#oid").val(id);
                $("#remark").html('');
                $('#solveContent').modal('show');
            });
        },

        /** 确认处理操作 */
        confirmAction: function() {
            $("#confirmsolve").on("click",function(){
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
                            required:"请输入订单备注",
                            maxlength: "订单备注的长度不能超过{0}个字符"
                        }
                    },
                    submitHandler: function() {
                        var buttonObj = this.submitButton;
                        $(buttonObj).button("loading");
                        $.ajax({
                            url: window.ctx+"/order/updateRemark",
                            type: "POST",
                            data: $form.serialize(),
                            dataType: "JSON",
                            success: function(data) {

                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    $submitBtn.button("reset");
                                    $("#cancleSolve").click();
                                    $("#remarkLabel").text($("#remark").val());
                                    $("#updateTimeLabel").text(data.data);
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

        /** 手动完成订单     */
        completeAction:function(){
            $("#btn-order-completed").click(function(){
                var orderId = $(this).attr("data-id");
                Dialog.confirm("操作提示", "确认完成本次订单？一旦操作完成后，订单流程将全部结束,不可恢复", function(result) {
                    if (result == true) {
                        $.ajax({
                            url: window.ctx+"/order/complete",
                            type: "post",
                            dataType: "JSON",
                            data: {
                                orderId: orderId
                            },
                            success: function(data) {
                                Dialog.success("操作完成",function(){
                                    window.location = location.href;
                                },1000);
                            }
                        });
                    }
                });
            });
        }


    };
    list.init();
});

