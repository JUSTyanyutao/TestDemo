package com.mxep.model.log;

import com.mxep.model.BaseEntity;
import com.mxep.model.member.Member;

import javax.persistence.*;

/**
 * Entity - 会员积分日志
 */
@Entity
@Table(name = "mx_log_member_points")
public class LogMemberPoints extends BaseEntity {

	private static final long serialVersionUID = -3353452409425211558L;

	/**
     * 积分
     */
    private Integer points;

    /**
     * 积分使用类型  (1:收入  2:支出)
     *
     * @see com.mxep.model.enums.EnumPointsLogType
     */
    private byte type;

    /**
     * 积分源
     *
     * @see com.mxep.model.enums.EnumPointsLogSource
     */
    private byte source;

    /**
     * 备注
     */
    private String remark;

    /**
     * 会员编号
     */
    private Integer memberId;

    /**
     * 会员
     */
    private Member member;

    /**
     * 获取积分
     *
     * @return 积分
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * 设置积分
     *
     * @param points 积分
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * 获取积分类型
     *
     * @see com.mxep.model.enums.EnumPointsLogType
     * @return 积分类型
     */
    public byte getType() {
        return type;
    }

    /**
     * 设置积分类型
     *
     * @see com.mxep.model.enums.EnumPointsLogType
     * @param type 积分类型
     */
    public void setType(byte type) {
        this.type = type;
    }

    /**
     * 获取来源
     *
     * @see com.mxep.model.enums.EnumPointsLogSource
     * @return 来源
     */
    public byte getSource() {
        return source;
    }

    /**
     * 设置来源
     *
     * @see com.mxep.model.enums.EnumPointsLogSource
     * @param source 来源
     */
    public void setSource(byte source) {
        this.source = source;
    }

    /**
     * 获取备注
     *
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
    @ManyToOne(fetch = FetchType.LAZY)
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
}
