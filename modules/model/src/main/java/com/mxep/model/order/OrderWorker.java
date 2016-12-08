package com.mxep.model.order;

import com.mxep.model.BaseEntity;
import com.mxep.model.member.Worker;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
@Entity
@Where(clause = "flag = 1")
public class OrderWorker extends BaseEntity {

    /**
     *   订单
     */
    private Integer orderId;

    /**
     *  订单
     */
    private Order order;

    /**
     *  技师
     */
    private Integer workerId;

    /**
     *  技师
     */
    private Worker worker;

//    /**
//     *    抢单状态:
//     0待抢单
//     1抢单失败
//     2抢单成功
//     */
//    private byte acceptStatus;

    /**
     *  服务日期
     */
    private Date serviceDate;

    /**
     *  工单状态 0已取消  1锁定中 2未处理  3已完成
     *  @see com.mxep.model.enums.EnumServiceOrderStatus
     */
    private byte status;

    /**
     *   备注
     */
    private String remark;

    @Column(name = "order_id")
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",insertable = false,updatable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(name = "worker_id")
    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    @OneToOne()
    @JoinColumn(name = "worker_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
