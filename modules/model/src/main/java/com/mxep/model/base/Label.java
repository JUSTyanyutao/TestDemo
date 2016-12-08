package com.mxep.model.base;

import com.mxep.model.BaseEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Entity - 标签
 *
 * @author xingkong1221
 * @date 2015-11-16
 */
@Entity
public class Label extends BaseEntity {

    private static final long serialVersionUID = 5127851097625099811L;

    /**
     * 标签类型
     *
     * @see com.mxep.model.enums.EnumLabelType
     */
    private Integer type;

    /**
     * 名称
     */
    private String name;

    /**
     * 标签值
     */
    private String value;

    /**
     * 实例化一个标签
     */
    public Label() {
    }

    /**
     * 获取标签类型
     *
     * @return 标签类型
     * @see Label.LabelType
     */
    @Column(columnDefinition = "tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '标签类型'")
    public Integer getType() {
        return type;
    }

    /**
     * 设置标签类型
     *
     * @param type 标签类型
     * @see com.mxep.model.enums.EnumLabelType
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取标签名称
     *
     * @return 标签名称
     */
    @Column(length = 30, nullable = false, columnDefinition = "varchar(30) NOT NULL DEFAULT '' COMMENT '标签名称'")
    public String getName() {
        return name;
    }

    /**
     * 设置标签名称
     *
     * @param name 标签名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取标签值
     *
     * @return 标签值
     */
    @Column(length = 30, nullable = false, columnDefinition = "varchar(30) NOT NULL DEFAULT '' COMMENT '标签值'")
    public String getValue() {
        return value;
    }

    /**
     * 设置标签值
     *
     * @param value 标签值
     */
    public void setValue(String value) {
        this.value = value;
    }
}
