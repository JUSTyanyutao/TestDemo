package com.mxep.model.order;

import com.mxep.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * Entity - 订单日志
 *
 * @author xingkong1221
 * @since 2015-11-17
 */
@Entity
public class OrderLog extends BaseEntity {

    private static final long serialVersionUID = 8149860438628529071L;

    /**
     * 订单编号
     */
    private Integer orderId;

    /**
     * 订单
     */
    private Order order;

    /**
     *
     */
    private byte status = 1;

    /**
     * 描述说明
     */
    private String logDesc;

    /**
     * 实例化一个订单日志
     */
    public OrderLog() {
    }

    /**
     * 获取订单编号
     *
     * @return 订单编号
     */
    @Column(name = "order_id")
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单编号
     *
     * @param orderId 订单编号
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单
     *
     * @return 订单
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    public Order getOrder() {
        return order;
    }

    /**
     * 设置订单
     *
     * @param order 订单
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     */
    public byte getStatus() {
        return status;
    }

    /**
     */
    public void setStatus(byte status) {
        this.status = status;
    }

    public String getLogDesc() {
        return logDesc;
    }

    public void setLogDesc(String logDesc) {
        this.logDesc = logDesc;
    }
}
