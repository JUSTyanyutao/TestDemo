package com.mxep.model.enums;

/**
 * Enum - 会员积分记录类型
 *
 * @author xingkong1221
 * @since 2016-09-01
 */
public enum EnumPointsLogType {

    Income((byte)1, "积分获取"), Expense((byte)2, "积分消费");

    public byte value;
    public String name;

    EnumPointsLogType(byte value, String name) {
        this.value = value;
        this.name = name;
    }

    public byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
