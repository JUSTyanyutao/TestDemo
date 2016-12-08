var $searchForm = $("#pagination-form");
$(function () {

    var $form = $("#solveForm");

    var list = {

        /** 初始化 */
        init: function () {
            this.initPagination();
            this.orderSnAction();
            this.confirmAction();
            this.exportServiceOrder();
            //this.loadGoods();
        },

        /** 初始化分页 */
        initPagination: function () {
            Pagination.init({
                pageSize: 12   // 每页显示12条记录
                //editBtn: {
                //    url: ctx + "/member/editMember/{id}"
                //},
                //deleteBtn: {
                //    url: ctx + "/member/deleteMember",
                //    callback: function (data) {
                //        // 回调函数
                //
                //    }
                //}
            });
        },

        exportServiceOrder:function(){
            $("#export").click(function(){
                var currentHref = window.location.href;
                var newHref = window.ctx+"/order/worker/export?fileName=orderWorkerReport";
                if(currentHref.indexOf("?") > -1){
                    newHref = newHref+"&"+currentHref.split("?")[1];
                }
                newHref = newHref+"&"+$("#pagination-form").serialize();
                window.location.href = newHref;
            });
        },

        loadGoods: function () {
            $.ajax({
                url: window.ctx + "/select2/getGroupGoods",
                dataType: 'json',
                success: function (data) {
                    $("#goodsSelect").select2({
                        placeholder: {
                            id: "-1",
                            text: "请选择商品",
                        },
                        data: data,
                        templateResult: function (state) {
                            if (!state.id) {
                                return state.text;
                            }
                            var $state = $(
                                '<span><img src="' + state.img + '" style="height:15px;width:18px" /> ' + state.text + '</span>'
                            );
                            return $state;
                        },
                        allowClear: true
                    });

                }
            })

        },

        /** 订单备注操作 */
        orderSnAction: function() {
            $(".btn-updateOrderSn").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要发货的订单");
                    return;
                }
                if (ids.length > 1) {
                    Dialog.warning("一次只能处理一个订单");
                    return;
                }

                $("#oid").val(ids[0]);
                //$("#orderSn").html('');
                $('#solveContent').modal('show');
            });
        },

        /** 确认处理操作 */
        confirmAction: function() {
            $("#confirmsolve").on("click",function(){
                $form.validate({
                    ignore: "",
                    rules: {
                        orderSn:{
                            required:true,
                            digits:true
                        }
                    },
                    messages: {
                        remark:{
                            required:"请输入订单单号",
                            digits:"只能输入整数"
                        }
                    },
                    submitHandler: function() {
                        var buttonObj = this.submitButton;
                        $(buttonObj).button("loading");
                        $.ajax({
                            url: window.ctx+"/order/updateOrderSn",
                            type: "POST",
                            data: $form.serialize(),
                            dataType: "JSON",
                            success: function(data) {

                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    $("#confirmsolve").button("reset");
                                    $("#cancleSolve").click();
                                    Pagination.reload();
                                    //exports.reload();
                                }else{
                                    Dialog.danger(data.msg);
                                    $("#confirmsolve").button("reset");
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


