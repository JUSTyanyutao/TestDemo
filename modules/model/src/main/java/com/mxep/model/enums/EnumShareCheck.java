package com.mxep.model.enums;

/**
 * Enum-晒单审核状态
 *
 * Created by lq on 2016/9/18.
 */
public enum EnumShareCheck {
    Uncheched((byte)-1, "审核失败"), Checking((byte)0, "审核中"), Success((byte)1, "审核成功")
    ;
    public byte value;
    public String label;

    EnumShareCheck(byte value, String label) {
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
