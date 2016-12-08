package com.mxep.model.base;

import com.mxep.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;


/**
 * Entity - 微信用户
 */
@Entity
@Table(name = "sys_wx_user")
public class WxUser extends BaseEntity {

	private static final long serialVersionUID = 7955616141585495737L;

	/**
	 * 微信用户的唯一标识
	 */
	private String openId;

	/**
	 * 微信昵称
	 */
	private String nickname;

	/**
	 * 性别
	 *
	 * @see com.mxep.model.enums.EnumGender
	 */
	private byte sex;

//	/**
//	 * 用户头像
//	 */
//	private String avatar;

	/**
	 * 省
	 */
	private String province;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 国家
	 */
	private String country;

	/**
	 * 特权信息json数组
	 */
	private String privilege;

	/**
	 * 开放平台id
	 */
	private String unionid;

	/**
	 * 公众号关注标识
	 *
	 * 0:未关注  1:已关注
	 */
	private byte subscribe;

	/**
	 * 关注时间
	 */
	private Date subscribeTime;

	/**
	 * 语言
	 */
	private String language;

	/**
	 * 获取微信用户的唯一标识
	 *
	 * @return 微信用户的唯一标识
     */
	@Column(name = "openid")
	public String getOpenId() {
		return openId;
	}

	/**
	 * 设置微信用户的唯一标识
	 *
	 * @param openId 微信用户的唯一标识
     */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * 获取微信昵称
	 *
	 * @return 微信昵称
     */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置微信昵称
	 *
	 * @param nickname 微信昵称
     */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取性别
	 *
	 * @see com.mxep.model.enums.EnumGender
	 * @return 性别
     */
	public byte getSex() {
		return sex;
	}

	/**
	 * 设置性别
	 *
	 * @see com.mxep.model.enums.EnumGender
	 * @param sex 性别
     */
	public void setSex(byte sex) {
		this.sex = sex;
	}

//	/**
//	 * 获取头像
//	 *
//	 * @return 头像
//     */
//	public String getAvatar() {
//		return avatar;
//	}
//
//	/**
//	 * 设置头像
//	 *
//	 * @param avatar 头像
//     */
//	public void setAvatar(String avatar) {
//		this.avatar = avatar;
//	}

	/**
	 * 获取省份
	 *
	 * @return 省份
     */
	public String getProvince() {
		return province;
	}

	/**
	 * 设置省份
	 *
	 * @param province 省份
     */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 获取城市
	 *
	 * @return 城市
     */
	public String getCity() {
		return city;
	}

	/**
	 * 设置城市
	 *
	 * @param city 城市
     */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 获取国家
	 *
	 * @return 国家
     */
	public String getCountry() {
		return country;
	}

	/**
	 * 设置国家
	 *
	 * @param country 国家
     */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 获取特权信息json数组
	 *
	 * @return 特权信息json数组
     */
	public String getPrivilege() {
		return privilege;
	}

	/**
	 * 设置特权信息json数组
	 *
	 * @param privilege 特权信息json数组
     */
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	/**
	 * 获取开放平台id
	 *
	 * @return 开放平台id
     */
	public String getUnionid() {
		return unionid;
	}

	/**
	 * 设置开放平台id
	 *
	 * @param unionid 开放平台id
     */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	/**
	 * 获取订阅标识
	 * 0:未订阅  1:已关注
	 *
	 * @return 订阅标识
     */
	public byte getSubscribe() {
		return subscribe;
	}

	/**
	 * 设置订阅标识
	 * 0:未订阅  1:已关注
	 *
	 * @param subscribe 订阅标识
     */
	public void setSubscribe(byte subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * 获取关注时间
	 *
	 * @return 关注时间
     */
	@Column(name = "subscribe_time")
	public Date getSubscribeTime() {
		return subscribeTime;
	}

	/**
	 * 设置关注时间
	 *
	 * @param subscribeTime 关注时间
     */
	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	/**
	 * 获取语言
	 *
	 * @return 语言
     */
	public String getLanguage() {
		return language;
	}

	/**
	 * 设置语言
	 *
	 * @param language 语言
     */
	public void setLanguage(String language) {
		this.language = language;
	}
}