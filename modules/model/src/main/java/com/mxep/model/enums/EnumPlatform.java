package com.mxep.model.enums;

/**
 * Enum - 平台
 *
 * @author xingkong1221
 * @since 2016-08-31
 */
public enum EnumPlatform {

    MicroMessage((byte)1, "微信"), Android((byte)2, "安卓"), IOS((byte)3, "苹果"), WebSite((byte)4, "网站"),
    Manual((byte)6, "手工添加"), Unknown((byte)7, "未知");

    public byte value;
    public String name;

    EnumPlatform(byte value, String name) {
        this.value = value;
        this.name = name;
    }

    public static EnumPlatform getType(byte value) {
        for (EnumPlatform map : EnumPlatform.values()) {
            if (map.value == value) {
                return map;
            }
        }
        return null;
    }

    public byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    /**
     * 获取平台类型
     */
    public static EnumPlatform getType(String plat) {
        if ("weixin".equalsIgnoreCase(plat)
                || "wechat".equalsIgnoreCase(plat)
                || "micromessage".equalsIgnoreCase(plat)) {
            return MicroMessage;
        } else if ("android".equalsIgnoreCase(plat)) {
            return Android;
        } else if ("ios".equals(plat)) {
            return IOS;
        } else if ("website".equalsIgnoreCase(plat) || "site".equalsIgnoreCase(plat)) {
            return WebSite;
        } else if ("manual".equalsIgnoreCase(plat)) {
            return Manual;
        } else {
            return Unknown;
        }
    }
}
