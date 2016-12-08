package com.mxep.model;

/**
 * 全局常量
 *
 * @author xingkong1221
 * @date 2015-11-16
 */
public class GlobalConstant {

    /**
     * 状态常量定义 1:正常 0:禁用
     *
     * @author ranfi
     */
    public enum Status {

        Enable(1, "可用"), Disable(0, "禁用"), Deleted(-1, "删除");

        public int value;
        public String name;

        Status(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static String getStatusName(int status) {
            return status == Status.Enable.value ? Status.Enable.name : Status.Disable.name;
        }
    }

    /**
     * 删除状态
     * <p>
     * 1: 已删除   0: 未删除
     */
    public enum DeleteStatus {
        Normal(0, "未删除"), Deleted(1, "已删除");
        private Integer value;
        private String name;

        DeleteStatus(Integer value, String name) {
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
    }

    /**
     * 性别
     */
    public enum Gender {
        MALE(1, "男"), FEMALE(2, "女"), UNKNOWN(0, "未知");

        public int value;
        public String name;

        Gender(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static String getGenderName(int value) {
            for (Gender gender : Gender.values()) {
                if (gender.value == value) {
                    return gender.name;
                }
            }
            return "未知";
        }
    }

    /**
     * 平台类型
     */
    public enum PlatType {

        WEIXIN(1, "微信"), IOS(2, "苹果"), ANDROID(3, "安卓"), OTHERS(4, "其他");

        public int type;
        public String name;

        PlatType(int type, String name) {
            this.type = type;
            this.name = name;
        }

        public int getValue() {
            return type;
        }

        public void setValue(int value) {
            this.type = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static String getPlatType(int type) {
            for (PlatType platType : PlatType.values()) {
                if (platType.type == type) {
                    return platType.name;
                }
            }
            return "其他";
        }

        public static PlatType getPlatType(String name) {
            for (PlatType platType : PlatType.values()) {
                if (platType.name().equalsIgnoreCase(name)) {
                    return platType;
                }
            }
            return PlatType.OTHERS;
        }
    }
}
