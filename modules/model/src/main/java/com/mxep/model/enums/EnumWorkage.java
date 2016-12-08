package com.mxep.model.enums;

/**
 * Enum - 技师 工作年限
 *
 * @author xingkong1221
 * @since 2016-09-23
 */
public enum EnumWorkage {
    ltOneYear((byte)0, "1年之内"), oneYear((byte)1, "1年"), oneToThreeYear((byte)2, "1-3年"),
    threeToFiveYear((byte)3, "3-5年"), FiveToTenYear((byte)4, "5-10年"), gtTenYear((byte)5, "10年以上")
    ;
    public byte value;
    public String label;

    EnumWorkage(byte value, String label) {
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
