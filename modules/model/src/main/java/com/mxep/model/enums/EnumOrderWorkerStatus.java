package com.mxep.model.enums;

/**
 * Enum - 订单类型
 *
 * @author xingkong1221
 * @since 2016-09-05
 */
public enum EnumOrderWorkerStatus {

//    0已取消  1锁定中 2未处理  3已完成
    One((byte)0, "已取消"), Two((byte)1, "锁定中"), Three((byte)2, "未处理"),Four((byte)3, "已完成")
    ;
    public byte value;
    public String label;

    EnumOrderWorkerStatus(byte value, String label) {
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
