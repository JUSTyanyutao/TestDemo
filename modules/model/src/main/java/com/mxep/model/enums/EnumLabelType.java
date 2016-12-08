package com.mxep.model.enums;

/**
 * Enum - 标识位
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum EnumLabelType {
    CarSize(1, "车型尺寸");

    public Integer value;
    public String name;

    EnumLabelType(Integer value, String name) {
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
