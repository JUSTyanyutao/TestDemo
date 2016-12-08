package com.mxep.model.member;

import com.mxep.model.BaseEntity;
import com.mxep.model.enums.EnumPlatform;

import javax.persistence.*;

/**
 * Entity - 会员信息
 *
 * @author xingkong1221
 * @since 2015-11-18
 */
@Entity
public class MemberProfile extends BaseEntity {

    private static final long serialVersionUID = -9022405918962016710L;

    /**
     * 会员编号
     */
    private Integer memberId;

    /**
     * 会员
     */
    private Member member;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 应用版本号
     */
    private String appVersion;

    /**
     * 性别
     * 0:未知 1:男性 2:女性
     *
     * @see com.mxep.model.enums.EnumGender
     */
    private byte gender;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 平台类型（来源）
     *
     * @see EnumPlatform
     */
    private byte platform;


    /**
     * 实例化一个会员信息
     */
    public MemberProfile() {
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
     * 获取会员
     *
     * @return 会员
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false , updatable = false)
    public Member getMember() {
        return member;
    }

    /**
     * 设置会员
     *
     * @param member 会员
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * 获取昵称
     *
     * @return 昵称
     */
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取应用版本号
     *
     * @see EnumPlatform
     * @return 应用版本号
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * 设置应用版本号
     *
     * @see EnumPlatform
     * @param appVersion 应用版本号
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    /**
     * 获取性别
     *
     * @see com.sun.tools.corba.se.idl.EnumGen
     * @return 性别
     */
    public byte getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @see com.mxep.model.enums.EnumGender
     * @param gender 性别
     */
    public void setGender(byte gender) {
        this.gender = gender;
    }

    /**
     * 获取真实姓名
     *
     * @return 真实姓名
     */
    @Column(name = "realname")
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取平台
     *
     * @see EnumPlatform
     * @return 平台
     */
    public byte getPlatform() {
        return platform;
    }

    /**
     * 设置平台
     *
     * @see EnumPlatform
     * @param platform 平台
     */
    public void setPlatform(byte platform) {
        this.platform = platform;
    }
}
