package com.mxep.model.goods;

import com.mxep.model.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2016/10/12 0012.
 */
@Entity
@Where(clause = "flag = 1")
public class Attribute extends BaseEntity {

    /**
     * 属性值定义
     */
    private String name;

    /**
     * 输入类型 1输入框 2 单选框  3 多选框  4下拉框 9 其他方式
     */
    private Integer inputType;

    /**
     *  单位
     */
    private String unit;

    /**
     *  英文单位
     */
    private String unitEn;

    /**
     * 标注
     */
    private String remark;

    /**
     *  状态
     */
    private int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitEn() {
        return unitEn;
    }

    public void setUnitEn(String unitEn) {
        this.unitEn = unitEn;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
