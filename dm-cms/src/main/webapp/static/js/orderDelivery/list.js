var $searchForm = $("#pagination-form");
var $form = $("#develiverForm");
var $submitBtn = $("#confirmChange");
$(function () {
    var list = {
        /** 初始化 */
        init: function () {
            this.initPagination();
            this.bindFormAction();
            this.loadGoods();
            this.changeDeliveryTime();
            this.orderPackage();
        },

        /** 初始化分页 */
        initPagination: function () {
            Pagination.init({
                pageSize: 12  // 每页显示12条记录
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

        /** 绑定表单事件 */
        bindFormAction: function () {
            var $editForm = $("#contentForm");
            // 提交修正重量后的表单
            $(document).on("click", "#continuePackage", function () {
                var $obj = $(this);
                $obj.button("loading");

                var param = {
                    searchParams: $searchForm.serializeObject(),
                    orderSequence: $("#dialog_orderSequence").val(),
                    orderId: $("#dialog_orderId").val()
                };

                var orderBizGoods = []
                var totalActrualPrice = 0;
                $(".weightVal").each(function () {
                    var obj =  $(this).parent().parent();
                    var actrualPrice = obj.find(".everyMoney").html();
                    var merchantId = obj.find(".merchantId").data("id");
                    var orderGood = {
                        id: $(this).next().val(),
                        actualWeight: $(this).val(),
                        actualPrice: actrualPrice,
                        merchantId : merchantId
                    };
                    orderBizGoods.push(orderGood);
                    totalActrualPrice = totalActrualPrice + Number(actrualPrice);
                });
                if (parseFloat(Number(totalActrualPrice).toFixed(2)) > parseFloat(Number($("#dialog_totalMoney").html()).toFixed(2))) {
                    Dialog.danger("订单打包后的总金额不能大于冻结金额");
                    $obj.button("reset");
                    return;
                }
                param.orderBizGoodsList = orderBizGoods;

                $.ajax({
                    url: $editForm.attr("action"),
                    method: "POST",
                    data: JSON.stringify(param),
                    dataType: "json",
                    contentType: "application/json",
                    beforeSend: function () {
                        $(this).button("loading");
                    },
                    success: function (data) {
                        if (data.code == 0) {
                            Pagination.reload();
                            list.printOrderBill($obj, data);
                        } else {
                            Dialog.danger(data.msg);
                            $obj.button("reset");
                        }
                    }
                });
            });
        },

        printOrderBill: function ($obj, data) {

            if (LODOP && typeof LODOP !== 'undefined') {
                //打印
                print_one(data);

            } else {
                Dialog.confirm("打包确认", "未找到LODOP打印控件,无法打印配送单,是否继续打包?", function (flag) {
                    if (!flag) {
                        $obj.button("reset").removeClass("disabled").attr("disabled", false);
                        $('#packageContent').modal("hide");
                    } else {
                        Dialog.success(data.msg, function () {
                            $obj.button("reset").removeClass("disabled").attr("disabled", false);
                            if (data.code == 0) {
                                dialog.refreshDialog(data);
                            } else {
                                $('#packageContent').modal("hide");
                            }
                        }, 1500);
                    }
                });
            }
        },

        orderPackage: function () {
            $(document).on("click", ".package", function () {
                var id = $(this).attr("data-id");
                //第几个订单
                $("#dialog_orderSequence").val($(this).attr("data-sequence"));
                dialog.show($('#packageContent'));
                dialog.loadDialogData($(this));
            });
        },

        changeDeliveryTime: function () {
            $("#confirmChange").on("click", function () {
                $form.validate({
                    ignore: "",
                    rules: {
                        deliveryDate1: {
                            required: true,
                            date: true,
                        }
                    },
                    messages: {
                        deliveryDate1: {
                            required: "请输入配送时间",
                            date: "输入的时间格式不正确",
                        }
                    },
                    submitHandler: function () {
                        var buttonObj = this.submitButton;
                        $(buttonObj).button("loading");
                        $.ajax({
                            url: window.ctx + "/orderDelivery/changeDeliveryTime",
                            type: "POST",
                            data: {
                                orderId: $("#orderId1").val(),
                                deliveryDate: $("#deliveryDate1").val(),
                                deliverySection: $("#deliverySection1").val()
                            },
                            dataType: "JSON",
                            success: function (data) {
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                    $submitBtn.button("reset");
                                    $("#cancleChange").click();
                                } else {
                                    Dialog.danger(data.msg);
                                    $submitBtn.button("reset");
                                }
                            }
                        });

                    }
                });
            });

        }
    };

    var dialog = {

        loadDialogData: function (that) {
            $.ajax({
                url: ctx + "/order/delivery/findOrder",
                type: "get",
                dataType: "json",
                data: {
                    orderId: that.attr("data-id")
                },
                success: function (data) {
                    dialog.refreshDialog(data);
                }
            });
        },

        prevOrder: function () {
            $(document).on("click", ".btn-dialog-prev", function () {
                dialog.findOrder(0);
            });
        },

        nextOrder: function () {
            $(document).on("click", ".btn-dialog-next", function () {
                dialog.findOrder(1);
            });
        },

        findOrder: function (type) {
            var sequenceVal = $("#dialog_orderSequence").val();
            var formStr = $searchForm.serialize();
            formStr = ReplaceAll(formStr, '+', ' ');
            formStr = ReplaceAll(formStr, '%3A', ':');

            $.ajax({
                url: ctx + "/order/delivery/findNextOrPrevOrder",
                type: "post",
                dataType: "json",
                data: {
                    searchParam: formStr,
                    orderSequence: sequenceVal,
                    type: type
                },
                success: function (data) {
                    if (data.code == 0) {
                        dialog.refreshDialog(data);
                        if (type == 0) {
                            $("#dialog_orderSequence").val(Number(sequenceVal) - 1);
                        } else if (type == 1) {
                            $("#dialog_orderSequence").val(Number(sequenceVal) + 1);
                        }
                    } else {
                        Dialog.warning(data.msg);
                        //$button.button("reset");
                    }
                }
            });
        },

        refreshDialog: function (data) {
            $("#dialog_orderId").val(data.order.id);
            $("#dialog_orderSn").html(data.order.orderSn);
            $("#dialog_memberName").html(data.member.nickName);
            $("#dialog_marketName").html(data.marketName);
            $("#dialog_totalMoney").html(data.order.totalPrice);
            $("#dialog_payMoney").html(data.order.paidMoney);
            $("#dialog_cookName").html(data.cookName || '---');
            $("#dialog_cookServiceHours").html(data.cookServiceHours || 0);
            $("#dialog_cookPaidMoney").html(data.cookPaidMoney || 0);
            var tableContent = [];
            $.each(data.orderBizs, function (index, orderBiz) {
                $.each(orderBiz.orderGoodses, function (i, orderGoods) {
                    tableContent.push("<tr>");
                    tableContent.push("<td>" + (index + 1) + "</td>");
                    tableContent.push("<td><span class='merchantId' data-id='"+orderBiz.merchant.id+"'></span>" + orderBiz.merchant.name + "</td>");
                    tableContent.push("<td>" + orderGoods.name + "&nbsp;&nbsp;" + orderGoods.salePrice + "元/" + orderGoods.unit + orderGoods.unitName + ",数量" + orderGoods.quantity + "份</td>");
                    tableContent.push("<td>" + orderGoods.unit * orderGoods.quantity + "&nbsp" + orderGoods.unitName + "</td>");
                    tableContent.push("<td data-beforeWeight='" + (orderGoods.unit * orderGoods.quantity).toFixed(2) + "'>");
                    tableContent.push("<a class='everyWeight' data-price='" + orderGoods.salePrice / orderGoods.unit + "'>" + orderGoods.unit * orderGoods.quantity + "</a>&nbsp;" + orderGoods.unitName + "");
                    tableContent.push("<input class='weightVal' type='hidden' value='" + orderGoods.unit * orderGoods.quantity + "'/>");
                    tableContent.push("<input class='orderGoodsId' type='hidden' value='" + orderGoods.id + "'/></td>");
                    tableContent.push("<td>" + (orderGoods.salePrice * orderGoods.quantity).toFixed(2) + "</td>");
                    tableContent.push("<td class='everyMoney'>" + (orderGoods.salePrice * orderGoods.quantity).toFixed(2) + "</td>");
                    tableContent.push("</tr>");
                });
            });
            $("#packageContent").find("tbody").empty().html(tableContent.join(""));
            countTotalMoney();
            $('.everyWeight').editable({
                type: 'text',
                pk: 1,
                name: 'username',
                title: '修正重量',
                placement: 'right',
                validate: function (value) {
                    var reg = new RegExp("^[1-9][0-9]*$");
                    if ($.trim(value) === '') return '重量不能为空';
                    else if (!reg.test(value)) return '只能输入大于0的整数';
                },
                success: function (response, newValue) {
                    var totalPrice = newValue * $(this).attr("data-price");
                    $(this).parent().next().next().html(totalPrice.toFixed(2));
                    $(this).parent().find(".weightVal").val(newValue);
                    countTotalMoney();
                }
            });
        },

        show: function ($el) {
            $el.modal();
            dialog.prevOrder();
            dialog.nextOrder();
        }
    };

    list.init();
});


//设置保存和点击上一条和下一条的动画效果
function copyAnimate(type) {
    var $temp = $("#packageContent");
    $temp.removeClass("fadeInLeftBig");
    $temp.removeClass("fadeInRightBig");
    $(".modal-backdrop").remove();

    var $newDialog = $temp.clone();
    $temp.after($newDialog);
    $temp.remove();

    if (type == 0) {
        //上一条
        $newDialog.addClass("fadeInRightBig");
    } else if (type == 1) {
        //下一条
        $newDialog.addClass("fadeInLeftBig");
    }
}

//计算的总金额，与总质量  
function countTotalMoney() {
    var totalMoney = 0;
    var totalWeight = 0;
    //总金额
    $(".everyMoney").each(function () {
        totalMoney += Number($(this).html());
    });
    //总质量
    $(".weightVal").each(function () {
        totalWeight += Number($(this).val());
    });

    var cookPaidMoney = $("#dialog_cookPaidMoney").text();
    if(null != cookPaidMoney && cookPaidMoney != ""){
        totalMoney += Number(cookPaidMoney);
    }

    $("#totalActuallyMoney").html(totalMoney.toFixed(2));
    //$("#totalActuallyWeight").html(totalWeight.toFixed(2));
}

function ReplaceAll(str, sptr, sptr1) {
    while (str.indexOf(sptr) >= 0) {
        str = str.replace(sptr, sptr1);
    }
    return str;
}
