package com.mxep.model.enums;

/**
 * Enum - 积分记录
 *
 * @author xingkong1221
 * @since 2016-09-01
 */
public enum EnumPointsLogSource {

    Share((byte)1, "晒单奖励"), GameReward((byte)2, "游戏奖励"), GameConsume((byte)3, "游戏花费"),
    Exchange((byte)4, "积分兑换");

    public byte value;
    public String name;

    EnumPointsLogSource(byte value, String name) {
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
