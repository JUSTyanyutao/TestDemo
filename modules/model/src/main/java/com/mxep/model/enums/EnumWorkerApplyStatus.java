package com.mxep.model.enums;

/**
 * Enum - 技师 审核状态
 *
 * @author xingkong1221
 * @since 2016-09-23
 */
public enum EnumWorkerApplyStatus {
    ltOneYear((byte)0, "待审核"), oneYear((byte)1, "已审核"), oneToThreeYear((byte)2, "已驳回"),
    threeToFiveYear((byte)3, "取消资格")
    ;
    public byte value;
    public String label;

    EnumWorkerApplyStatus(byte value, String label) {
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
