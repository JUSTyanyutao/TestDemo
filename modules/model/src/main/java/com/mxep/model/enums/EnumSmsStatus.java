package com.mxep.model.enums;

/**
 * Enum - 短信状态
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum EnumSmsStatus {
    Failure((byte)0, "未验证"), PendingSend((byte)1, "已验证"), Success((byte)-1, "已失效")
    ;
    public byte value;
    public String label;

    EnumSmsStatus(byte value, String label) {
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
