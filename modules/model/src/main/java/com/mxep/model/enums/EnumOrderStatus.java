package com.mxep.model.enums;

/**
 * Enum - 订单状态
 *
 * @author xingkong1221
 * @since 2016-09-05
 */
public enum EnumOrderStatus {


    PendingPay((byte)1, "待付款"), PendingDelivery((byte)2, "待发货"),PendingConfirm( (byte)3,"待确认"),
    PendingComment((byte)4, "交易成功(未评价)"),Completed( (byte)5,"交易成功(已评价)"), Fail((byte)6, "取消交易");

    public byte value;
    public String label;

    EnumOrderStatus(byte value, String label) {
        this.value = value;
        this.label = label;
    }

    public byte getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static EnumOrderStatus getStatus(byte value) {
        for (EnumOrderStatus map : EnumOrderStatus.values()) {
            if (map.value == value) {
                return map;
            }
        }
        return null;
    }

}
