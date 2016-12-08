package com.mxep.model.enums;

/**
 * Enum - 出售方式
 *
 * @author xingkong1221
 * @since 2016-09-19
 */
public enum EnumResaleType {
    SaleToMember((byte) 1, "转卖用户"), SaleToPlatform((byte)2, "转卖平台")
    ;
    public byte value;
    public String label;

    EnumResaleType(byte value, String label) {
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
