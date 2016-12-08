package com.mxep.model.order;

import com.mxep.model.member.Worker;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
@Entity
public class OrderWorkerStatistics implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "worker_id")
    private Integer workerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Worker worker;

    private Date serviceDate;

    private Integer amOrderNum;

    private Integer pmOrderNum;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

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

    public Integer getAmOrderNum() {
        return amOrderNum;
    }

    public void setAmOrderNum(Integer amOrderNum) {
        this.amOrderNum = amOrderNum;
    }

    public Integer getPmOrderNum() {
        return pmOrderNum;
    }

    public void setPmOrderNum(Integer pmOrderNum) {
        this.pmOrderNum = pmOrderNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
