package com.mxep.model.enums;

/**
 * Enum - 时间服务表
 *
 */
public enum EnumWeeks {
    One(1, "周一"),Two(2, "周二"),Three(3, "周三"),Four(4, "周四"),Five(5, "周五"),Six(6, "周六"),Seven(7, "周日");

    public Integer value;
    public String name;

    EnumWeeks(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public static LabelType getLabelType(Integer value) {
//        LabelType labelType = null;
//        for (LabelType map : LabelType.values()) {
//            if (value == map.value) {
//                labelType = map;
//                break;
//            }
//        }
//        return labelType;
//    }


}
