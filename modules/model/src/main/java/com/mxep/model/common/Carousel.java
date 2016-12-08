package com.mxep.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.mxep.model.BaseSortedEntity;
import com.mxep.model.GlobalConstant;

/**
 * Entity - 轮播图
 * 
 * @author xingkong1221
 * @since 2015-12-01
 */
@Entity
public class Carousel extends BaseSortedEntity {

	private static final long serialVersionUID = 3051530440059673918L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 图片
	 */
	private String pic;

	/**
	 * 链接地址
	 */
	private String link;

	/**
	 * 显示标识位
	 * 
	 * @see com.mxep.model.enums.EnumDisplay
	 */
	private byte isDisplay;

	/**
	 * 投放平台
	 * 
	 * @see com.mxep.model.enums.EnumPlatformType
	 */
	private byte platform;

	/**
	 * 实例化一个轮播图
	 */
	public Carousel() {
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取图片
	 * 
	 * @return 图片
	 */
	public String getPic() {
		return pic;
	}

	/**
	 * 设置图片
	 * 
	 * @param pic
	 *            图片
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}

	/**
	 * 获取链接地址
	 * 
	 * @return 链接地址
	 */
	public String getLink() {
		return link;
	}

	/**
	 * 设置链接地址
	 * 
	 * @param link
	 *            链接地址
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * 获取显示标识位
	 *
	 * @return 显示标识位
	 */
	@Column(name = "is_display")
	public byte getIsDisplay() {
		return isDisplay;
	}

	/**
	 * 设置显示标识位
	 *
	 * @param isDisplay 显示标识位
	 */
	public void setIsDisplay(byte isDisplay) {
		this.isDisplay = isDisplay;
	}

	/**
	 * 获取投放平台
	 * 
	 * @see com.mxep.model.enums.EnumPlatformType
	 * @return 投放平台
	 */
	public byte getPlatform() {
		return platform;
	}

	/**
	 * 设置投放平台
	 * 
	 * @see com.mxep.model.enums.EnumPlatformType
	 * @param platform
	 *            投放平台
	 */
	public void setPlatform(byte platform) {
		this.platform = platform;
	}


}
