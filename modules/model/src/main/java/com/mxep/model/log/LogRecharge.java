package com.mxep.model.log;

import com.mxep.model.BaseEntity;
import com.mxep.model.member.Member;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity - 充值日志
 */
@Entity
@Table(name = "mx_log_recharge")
public class LogRecharge extends BaseEntity {

	private static final long serialVersionUID = 532486780571201577L;

	/**
	 * 会员id
	 */
	private Integer memberId;

	/**
	 * 会员
	 */
	private Member member;

	/**
	 * 状态
	 *
	 * @see com.mxep.model.enums.EnumRechargeStatus
	 */
	private byte status;

	/**
	 * 充值金额
	 */
	private BigDecimal money;

	/**
	 * 奖励金额
	 */
	private BigDecimal bonus;

	/**
	 * 充值描述
	 */
	private String desc;

	/**
	 * 充值序列号
 	 */
	private String rechargeSn;

	/**
	 * 支付记录编号
	 */
	private Integer paymentId;

	/**
	 * 支付记录
	 */
	private LogPayment payment;

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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", insertable = false, updatable = false)
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
	 * 获取充值状态
	 *
	 * @see com.mxep.model.enums.EnumRechargeStatus
	 * @return 充值状态
	 */
	public byte getStatus() {
		return status;
	}

	/**
	 * 设置充值状态
	 *
	 * @see com.mxep.model.enums.EnumRechargeStatus
	 * @param status 充值状态
	 */
	public void setStatus(byte status) {
		this.status = status;
	}

	/**
	 * 获取充值金额
	 *
	 * @return 充值金额
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * 设置充值金额
	 *
	 * @param money 充值金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * 获取奖励金额
	 *
	 * @return 奖励金额
	 */
	public BigDecimal getBonus() {
		return bonus;
	}

	/**
	 * 设置奖励金额
	 *
	 * @param bonus 奖励金额
	 */
	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	/**
	 * 获取描述
	 * 、
	 * @return 描述
	 */
	@Column(name = "`desc`")
	public String getDesc() {
		return desc;
	}

	/**
	 * 设置描述
	 *
	 * @param desc 描述
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 获取充值序列号
	 *
	 * @return 充值序列号
	 */
	public String getRechargeSn() {
		return rechargeSn;
	}

	/**
	 * 设置充值序列号
	 *
	 * @param rechargeSn 充值序列号
	 */
	public void setRechargeSn(String rechargeSn) {
		this.rechargeSn = rechargeSn;
	}

	/**
	 * 获取支付记录编号
	 *
	 * @return 支付记录编号
	 */
	@Column(name = "payment_id")
	public Integer getPaymentId() {
		return paymentId;
	}

	/**
	 * 设置支付记录编号
	 *
	 * @param paymentId 支付记录编号
	 */
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * 获取支付记录
	 *
	 * @return 支付记录
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_id", insertable = false, updatable = false)
	public LogPayment getPayment() {
		return payment;
	}

	/**
	 * 设置支付记录
	 *
	 * @param payment 支付记录
	 */
	public void setPayment(LogPayment payment) {
		this.payment = payment;
	}
}