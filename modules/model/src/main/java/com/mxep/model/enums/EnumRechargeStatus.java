package com.mxep.model.enums;

/**
 * Enum - 充值状态
 *
 * @author xingkong1221
 * @since 2016-09-05
 */
public enum EnumRechargeStatus {

    Overtime((byte)-2, "超时"), Fail((byte)-1,"支付失败"), Pending_pay((byte)0,"等待支付"), Success((byte)1,"支付成功");

    public byte value;
    public String name;

    EnumRechargeStatus(byte value,String name) {
        this.value = value;
        this.name = name;
    }

    public byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getLabel(byte value) {
        if (value == Overtime.value) {
            return "支付超时";
        } else if (value == Fail.value) {
            return "支付失败";
        } else if (value == Pending_pay.value) {
            return "等待支付";
        } else if (value == Success.value) {
            return "已付款";
        } else {
            return "未知状态";
        }
    }
}
