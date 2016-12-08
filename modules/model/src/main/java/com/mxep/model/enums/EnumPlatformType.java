package com.mxep.model.enums;

/**
 * Enum - 平台类型
 *
 * @author xingkong1221
 * @since 2016-09-04
 */
public enum EnumPlatformType {

    All((byte)7,"全部"),
    MicroMessage((byte)1, "微信"), IOS((byte)2, "苹果"), Android((byte)4, "安卓"), MicroAndIOS((byte)3,"微信,苹果"),
    MicroAndAndroid((byte)5,"微信,安卓"), IOSAndAndroid((byte)6,"苹果,安卓");

    public byte value;
    public String name;

    EnumPlatformType(byte value, String name) {
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
