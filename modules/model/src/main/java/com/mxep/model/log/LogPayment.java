package com.mxep.model.log;

import com.mxep.model.BaseEntity;
import com.mxep.model.member.Member;
import com.mxep.model.order.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entity - 支付纪录
 * 
 * @author xingkong1221
 * @since 2015-11-17
 */
@Entity
@Table(name = "dm_pay_log")
public class LogPayment implements Serializable {

	private static final long serialVersionUID = -9110872791172565115L;

	@Id
	@GeneratedValue
	private Integer id;

	/**
	 *  订单 编号
	 */
	private String orderSn;

	/**
	 *
	 */
	private String tradeNo;

	/**
	 *  支付金额
	 */
	private BigDecimal payAmount;


	/**
	 * 支付类型
	 * 
	 * @see com.mxep.model.enums.EnumPaymentType
	 */
	private byte payType;

	/**
	 * 支付结果
	 * 
//	 * @see com.mxep.model.enums.EnumPaymentStatus
	 */
	@Column(name = "pay_status")
	private String status;

	/**
	 *  支付账号
	 */
	private String payAccount;

	/**
	 * 支付时间
	 */
	private Date payTime;


	/**
	 * 实例化一个支付记录
	 */
	public LogPayment() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public byte getPayType() {
		return payType;
	}

	public void setPayType(byte payType) {
		this.payType = payType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
}
