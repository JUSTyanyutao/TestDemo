package com.mxep.model.enums;

/**
 * Enum - 订单状态
 *
 * @author xingkong1221
 * @since 2016-09-05
 */
public enum EnumServiceOrderStatus {


    PendingPay((byte)1, "待付款"), PendingDelivery((byte)2, "等待派遣"),PendingConfirm( (byte)3,"等待确认完工"),
    PendingComment((byte)4, "交易成功(未评价)"),Completed( (byte)5,"交易成功(已评价)"), Fail((byte)6, "取消交易");

    public byte value;
    public String label;

    EnumServiceOrderStatus(byte value, String label) {
        this.value = value;
        this.label = label;
    }

    public byte getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

}
