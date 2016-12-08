package com.mxep.model.member;

import com.mxep.model.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity - 会员接口token
 *
 * @author xingkong1221
 * @since  2015-12-01
 */
@Entity
public class MemberToken extends BaseEntity {

    private static final long serialVersionUID = -7219544441384016006L;

    /**
     * 会员编号
     */
    private Integer memberId;

    /**
     * 会员
     */
    private Member member;

    /**
     * 接口调用凭证
     */
    private String accessToken;

    /**
     * 置换凭证
     */
    private String refreshToken;

    /**
     * 过期时间
     */
    private Date expireTime;

    /***
     * 实例化一个会员接口token
     */
    public MemberToken() {
    }

    /**
     * 获取会员编号
     *
     * @return 会员编号
     */
    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * 设置会员编号
     *
     * @param memberId 会员编号
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取会员信息
     *
     * @return 会员信息
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    public Member getMember() {
        return member;
    }

    /**
     * 设置会员信息
     *
     * @param member 会员信息
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * 获取接口调用凭证
     *
     * @return 接口调用凭证
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 设置接口调用凭证
     *
     * @param accessToken 接口调用凭证
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 获取置换凭证
     *
     * @return 置换凭证
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * 设置置换凭证
     *
     * @param refreshToken 置换凭证
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * 获取过期时间
     *
     * @return 过期时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 设置过期时间
     *
     * @param expireTime 过期时间
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
