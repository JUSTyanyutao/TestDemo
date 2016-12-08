package com.mxep.model.log;

import com.mxep.model.member.Member;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Entity
public class Sms implements Serializable {

    private static final long serialVersionUID = -4354340935148130056L;

    private Integer id;

    private Integer memberId;

    private Member member;

    private Integer smsTemplateId;

    private SmsTemplate smsTemplate;

    private byte status;

    private byte flag;

    private Date createTime;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Column(name = "sms_template_id")
    public Integer getSmsTemplateId() {
        return smsTemplateId;
    }

    public void setSmsTemplateId(Integer smsTemplateId) {
        this.smsTemplateId = smsTemplateId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sms_template_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public SmsTemplate getSmsTemplate() {
        return smsTemplate;
    }

    public void setSmsTemplate(SmsTemplate smsTemplate) {
        this.smsTemplate = smsTemplate;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
