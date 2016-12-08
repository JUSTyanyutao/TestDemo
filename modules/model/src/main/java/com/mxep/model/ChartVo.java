package com.mxep.model;

import java.io.Serializable;

/**
 * @author xingkong1221
 * @date 2015-07-17
 */
public class ChartVo implements Serializable {


    /**   ***/

    /** 名称 */
    private String name;

    /** 值 */
    private String value;

    /**
     * 获取名称
     *
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取值
     *
     * @return 值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置值
     *
     * @param value 值
     */
    public void setValue(String value) {
        this.value = value;
    }
}
