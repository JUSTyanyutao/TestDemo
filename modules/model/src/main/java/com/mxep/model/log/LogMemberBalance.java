package com.mxep.model.log;

import com.mxep.model.BaseEntity;
import com.mxep.model.enums.EnumBalanceLogSource;
import com.mxep.model.member.Member;
import com.mxep.model.order.Order;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity - 会员账户(梦想币)日志
 * 
 * @author zlj
 * @since  2016-2-16
 */
@Entity
@Table(name = "mx_log_member_balance")
public class LogMemberBalance extends BaseEntity {

	private static final long serialVersionUID = -7484050839640400483L;

	/**
	 * 消费(梦想币)
	 */
	public BigDecimal money;

	/**
	 * 账户余额
	 */
	public BigDecimal balance;

	/**
	 * 源头 (1:充值  2:消费  3:手工修改  4:积分兑换)
	 *
	 * @see EnumBalanceLogSource
	 */
	public byte source;

	/**
	 * 收入支出类型 (1:收入  2:支出)
	 *
	 * @see com.mxep.model.enums.EnumBalanceLogType
	 */
	public byte type;

	/**
	 * 用户id
	 */
	public Integer memberId;

	/**
	 * 会员
	 */
	private Member member;

	/**
	 * 备注
	 */
	public String remark;

	/**
	 * 订单id
	 */
	private Integer orderId;

	/**
	 * 订单
	 */
	public Order order;

	/**
	 * 获取消费金额（梦想币）
	 *
	 * @return 消费金额（梦想币）
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * 设置消费金额（梦想币）
	 *
	 * @param money 消费金额（梦想币）
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * 获取账户余额
	 *
	 * @return 账户余额
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * 设置账户余额
	 *
	 * @param balance 账户余额
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * 获取日志来源
	 *
	 * @see EnumBalanceLogSource
	 * @return 来源
	 */
	public byte getSource() {
		return source;
	}

	/**
	 * 设置日志来源
	 *
	 * @see EnumBalanceLogSource
	 * @param source 来源
	 */
	public void setSource(byte source) {
		this.source = source;
	}

	/**
	 * 获取收入支付类型（0:收入 1:支出）
	 *
	 * @see com.mxep.model.enums.EnumBalanceLogType
	 * @return 收入支付类型
	 */
	public byte getType() {
		return type;
	}

	/**
	 * 设置收入支付类型（0:收入 1:支出）
	 *
	 * @see com.mxep.model.enums.EnumBalanceLogType
	 * @param type 收入支付类型
	 */
	public void setType(byte type) {
		this.type = type;
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
	 * 获取订单id
	 *
	 * @return 订单id
	 */
	@Column(name = "order_id")
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * 设置订单id
	 *
	 * @param orderId 订单id
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取订单信息
	 *
	 * @return 订单信息
	 */
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", insertable = false, updatable = false)
	public Order getOrder() {
		return order;
	}

	/**
	 * 设置订单信息
	 *
	 * @param order 订单信息
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
}