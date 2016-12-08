package com.mxep.model.enums;

/**
 * Enum - 常见问题类型
 *
 * @author xingkong1221
 * @since 2016-09-02
 */
public enum EnumFaqType {
    Normal((byte)1, "常见问题"), Points((byte)2, "积分说明")
    ;
    public byte value;
    public String name;

    EnumFaqType(byte value, String name) {
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
