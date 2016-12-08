package com.mxep.model.member;

import com.mxep.model.BaseEntity;
import com.mxep.model.base.Area;
import com.mxep.model.common.City;
import com.mxep.model.goods.Category;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0013.
 *  技师
 *
 */
@Entity
@Where(clause = "flag = 1")
public class Worker extends BaseEntity{


    /**
     *  用户 id
     */
    private Integer memberId;

    /**
     *  用户
     */
    private Member member;

    /**
     *  名称
     */
    private String name;

    /**
     *  性别：1：男 2：女  3：保密
     */
    private byte sex;

    /**
     *   身份证
     */
    private String idcard;

    /**
     *   从业时间：1年以内、1年、1~3年、3~5年、5~10年、10年以上
     *   @see com.mxep.model.enums.EnumWorkage
     */
    private String workAge;

    /**
     *   开户行
     */
    private String bankName;

    /**
     *  卡号
     */
    private String bankCardNo;

    /**
     *  微信号
     */
    private String wechat;

    /**
     *  城市 id
     */
    private Integer cityId;

    /**
     *  城市
     */
    private City city;

    /**
     *   工作状态 0 无  1有
     */
    private byte workingStatus;


    /**
     *   驾驶证  0无 1有
     */
    private byte hasDrivingLicense;

    /**
     *  是否有车  0无 1有
     */
    private byte hasCar;

    /**
     *  是否可以上路  0无 1有
     */
    private byte canDrive;

    /**
     *  申请 时间
     */
    private Date applyTime;


    /**
     *  申请状态
     *@see com.mxep.model.enums.EnumWorkerApplyStatus
     * 0: 待审核  1：已审核 2：已驳回  3 取消资格
     */
    private byte applyStatus;

    /**
     *  审批人 id
     */
    private Integer approveUserId;

    /**
     *  审批人
     */
    private Member approveMember;

    /**
     *  所属的 区域经理 id
     */
    private Integer parentMemberId;

    /**
     *  所属区域经理
     */
    private Member parentMember;

    /**
     *  1 内部技师   2外部技师
     */
    private byte type;

    /**
     *  服务 id
     */
    private String categoryId;


    /**
     *  1可用  0不可用
     */
    private byte status;

    /**
     *  链接
     */
    private String url;

    /**
     *  工作类型 1洗车  2贴膜
     */
    private byte workType;

    /**
     *  上传的 图片
     */
    private String pics;

    /**
     *   评分
     */
    private double score;

    /**
     *  备注
     */
    private String remark;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    @Column(name = "approve_user_id")
    public Integer getApproveUserId() {
        return approveUserId;
    }

    public void setApproveUserId(Integer approveUserId) {
        this.approveUserId = approveUserId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approve_user_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Member getApproveMember() {
        return approveMember;
    }

    public void setApproveMember(Member approveMember) {
        this.approveMember = approveMember;
    }

    @Column(name = "parent_member_id")
    public Integer getParentMemberId() {
        return parentMemberId;
    }

    public void setParentMemberId(Integer parentMemberId) {
        this.parentMemberId = parentMemberId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_member_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Member getParentMember() {
        return parentMember;
    }

    public void setParentMember(Member parentMember) {
        this.parentMember = parentMember;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte getWorkType() {
        return workType;
    }

    public void setWorkType(byte workType) {
        this.workType = workType;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getWorkAge() {
        return workAge;
    }

    public void setWorkAge(String workAge) {
        this.workAge = workAge;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    @Column(name = "city_id")
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public byte getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(byte workingStatus) {
        this.workingStatus = workingStatus;
    }

    public byte getHasDrivingLicense() {
        return hasDrivingLicense;
    }

    public void setHasDrivingLicense(byte hasDrivingLicense) {
        this.hasDrivingLicense = hasDrivingLicense;
    }

    public byte getHasCar() {
        return hasCar;
    }

    public void setHasCar(byte hasCar) {
        this.hasCar = hasCar;
    }

    public byte getCanDrive() {
        return canDrive;
    }

    public void setCanDrive(byte canDrive) {
        this.canDrive = canDrive;
    }

    public byte getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(byte applyStatus) {
        this.applyStatus = applyStatus;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
