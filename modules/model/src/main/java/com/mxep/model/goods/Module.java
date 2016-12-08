package com.mxep.model.goods;

import com.mxep.model.BaseSortedEntity;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2016/10/11 0011.
 *
 * 按钮
 *
 */
@Entity
@Where(clause = "flag = 1")
public class Module extends BaseSortedEntity {

    /**
     *  名称
     */
    private String name;

    /**
     *  1 商品详情  2 服务列表
     */
    private Integer link;

    /**
     *图标
     */
    private String icon;

    /**
     * 分类  id
     */
    private String categoryIds;

    /**
     *  状态  1启用  0禁用
     */
    private Byte status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLink() {
        return link;
    }

    public void setLink(Integer link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
