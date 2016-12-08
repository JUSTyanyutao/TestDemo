package com.mxep.model.goods;

import com.mxep.model.BaseEntity;
import com.mxep.model.BaseSortedEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/10/9 0009.
 *
 *  套餐管理
 */
@Entity
@Where(clause = "flag=1")
public class Package extends BaseEntity {

    /**
     *  名称
     */
    private String name;

    /**
     *  类型  1普通商品   2 服务项目
     */
    private byte type;

    /**
     *  分类 Id
     */
    private Integer categoryId;

    /**
     *  分类
     */
    private Category category;

    /**
     *   标注
     */
    private String remark;

    /**
     *   状态  1 可用   0不可用
     */
    private byte status;

    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
