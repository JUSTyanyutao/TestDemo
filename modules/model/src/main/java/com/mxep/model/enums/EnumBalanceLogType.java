package com.mxep.model.enums;

/**
 * Enum - 会员账户日志类型
 *
 * @author xingkong1221
 * @since 2016-08-31
 */
public enum EnumBalanceLogType {

    Income((byte)1, "收入"), Expense((byte)2, "支出");

    public byte value;
    public String name;

    EnumBalanceLogType(byte value, String name) {
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
