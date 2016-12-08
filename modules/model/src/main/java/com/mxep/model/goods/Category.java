package com.mxep.model.goods;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mxep.model.BaseSortedEntity;
import com.mxep.model.enums.EnumDisplay;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import java.util.List;

/**
 * Entity - 分类
 *
 * @author xingkong1221
 * @since 2016-08-28
 */
@Entity
@Where(clause = "flag=1")
public class Category extends BaseSortedEntity {

    private static final long serialVersionUID = -2899543743570414613L;

    /**
     * 名称
     */
    private String name;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 父分类  id
     */
    private Integer pid;

    /**
     *  父 分类
     */
    private Category category;

    /**
     * 子 分类
     */
    private List<Category> categoryList;

    /**
     *  分类级别
     */
    private Integer level;

    /**
     * 显示标识位
     *
     * 1: 显示  0: 显示
     *
     * @see EnumDisplay
     */
    private byte isDisplay;

    /**
     *  1 普通商品   2 服务详情
     */
    private Integer type;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取 父分类 id
     * @return
     */
    @Column(name = "pid")
    public Integer getPid() {
        return pid;
    }


    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取父 分类
     *
     * @return 父分类
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", referencedColumnName = "id", updatable = false,insertable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    /**
     * 获取子分类
     *
     * @return 子分类
     */
    @OneToMany(mappedBy = "category", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy("sort ASC")
    @NotFound(action = NotFoundAction.IGNORE)
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取分类名称
     *
     * @return 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取分类图标
     *
     * @return 分类图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置分类图标
     *
     * @param icon 分类图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取显示标识位
     *
     * @see EnumDisplay
     * @return 显示标识位
     */
    @Column(name = "is_display")
    public byte getIsDisplay() {
        return isDisplay;
    }

    /**
     * 设置显示标识位
     *
     * @see EnumDisplay
     * @param isDisplay 显示标识位
     */
    public void setIsDisplay(byte isDisplay) {
        this.isDisplay = isDisplay;
    }
}
