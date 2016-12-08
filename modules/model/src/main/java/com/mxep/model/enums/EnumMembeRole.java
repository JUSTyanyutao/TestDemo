package com.mxep.model.enums;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public enum EnumMembeRole {

    // 企业用户  就是 4s店
    Member((byte) 1, "普通会员"), Shoper((byte)2, "企业用户"),
    Worker((byte)3,"工人技师"),Manager((byte)4,"区域经理");
//    EnterpriseMember((byte)5,"企业会员"),StreetShop((byte)6,"街边店");

    public byte value;
    public String name;

    EnumMembeRole(byte value, String name) {
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
