package com.mxep.model.enums;

/**
 * Enum - 精选标识位
 *
 * @author xingkong1221
 * @since 2016-08-30
 */
public enum EnumRecommend {
    Yes((byte) 1, "是"), No((byte) 0, "否")
    ;
    private byte status;
    private String label;

    EnumRecommend(byte status, String label) {
        this.status = status;
        this.label = label;
    }

    public byte getStatus() {
        return status;
    }

    public String getLabel() {
        return label;
    }
}
