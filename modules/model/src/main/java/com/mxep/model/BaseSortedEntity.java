package com.mxep.model;

import javax.persistence.MappedSuperclass;

/**
 * Entity - 实体排序基类
 *
 * @author xingkong1221
 * @since 2016-08-28
 */
@MappedSuperclass
public class BaseSortedEntity extends BaseEntity {

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 获取排序值
     *
     * @return 排序值
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序值
     *
     * @param sort 排序值
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
