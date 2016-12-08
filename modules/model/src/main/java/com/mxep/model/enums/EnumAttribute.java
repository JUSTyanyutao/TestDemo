package com.mxep.model.enums;

/**
 * Enum - 配置表
 *
 */
public enum EnumAttribute {
    One(1, "商品特性"),Two(2, "商品类型");

    public Integer value;
    public String name;

    EnumAttribute(Integer value, String name) {
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
