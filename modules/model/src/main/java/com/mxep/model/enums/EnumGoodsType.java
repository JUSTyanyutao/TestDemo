package com.mxep.model.enums;

/**
 * Enum - 商品 类型
 *
 */
public enum EnumGoodsType {
    One(1, "商品"),Two(2, "服务"),Three(3,"美容洗车");

    public Integer value;
    public String name;

    EnumGoodsType(Integer value, String name) {
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
