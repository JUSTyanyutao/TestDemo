package com.mxep.model;

/**
 * Entity － 心愿
 *
 * @author xingkong1221
 * @since 2016-08-29
 */
public class Wish extends BaseSortedEntity {

    private static final long serialVersionUID = -5713701904042758823L;

    /**
     * 心愿商品名称
     */
    private String name;

    /**
     * 商品连接地址
     */
    private String url;

    /**
     * 描述，100字内
     */
    private String desc;

    /**
     * 图片
     */
    private String pic;

    /**
     * 显示标识位
     */
    private byte isDisplay;
}
