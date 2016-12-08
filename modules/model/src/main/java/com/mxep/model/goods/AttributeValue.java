package com.mxep.model.goods;

import com.mxep.model.BaseEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/10/12 0012.
 */
@Entity
@Where(clause = "flag = 1")
public class AttributeValue extends BaseEntity {

    /**
     *属性 id
     */
    private int attrId;

    /**
     *  属性
     */
    private Attribute attribute;

    /**
     * 属性值
     */
    private String attrValue;

    /**
     *  颜色 16进制
     */
    private String color;

    /**
     *  百分比
     */
    private String percentum;

    /**
     *  状态
     */
    private byte status;

    public String getPercentum() {
        return percentum;
    }

    public void setPercentum(String percentum) {
        this.percentum = percentum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "attr_id")
    public int getAttrId() {
        return attrId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attr_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}


