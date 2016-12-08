package com.mxep.model.enums;

/**
 * Enum - 会员账户日志来源
 *
 * @author xingkong1221
 * @since 2016-08-31
 */
public enum EnumBalanceLogSource {

    Recharge((byte)1, "充值"), Buy((byte)2, "订单消费"), Manual((byte)3, "人工修改"), Points((byte)4, "积分兑换"),
    SignIn((byte)5, "签到");

    public byte value;
    public String name;

    EnumBalanceLogSource(byte value, String name) {
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
