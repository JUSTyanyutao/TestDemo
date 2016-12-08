package com.mxep.model.enums;

/**
 * Enum - 会员状态
 *
 * @author xingkong1221
 * @since 2016-08-31
 */
public enum EnumMemberStatus {

    Disable((byte) 0, "禁用"), Enable((byte)1, "可用");

    public byte value;
    public String name;

    EnumMemberStatus(byte value, String name) {
        this.value = value;
        this.name = name;
    }

    public byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static EnumMemberStatus getStatus(byte value) {
        for (EnumMemberStatus map : EnumMemberStatus.values()) {
            if (map.value == value) {
                return map;
            }
        }
        return null;
    }
}
