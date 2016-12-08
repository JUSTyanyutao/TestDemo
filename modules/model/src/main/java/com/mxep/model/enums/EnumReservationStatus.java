package com.mxep.model.enums;

/**
 * Enum - 秒杀预约状态
 *
 * @author xingkong1221
 * @since 2016-09-02
 */
public enum EnumReservationStatus {
    PendingUse((byte)0, "待使用"), Used((byte)1, "已使用"), Back((byte)2, "已退还")
    ;
    public byte value;
    private String name;

    EnumReservationStatus(byte value, String name) {
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
