package com.mxep.model.search;

import com.mxep.model.BaseSortedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 搜索关键字
 */
@Entity
public class SearchKeywords extends BaseSortedEntity {

	private static final long serialVersionUID = -3796013487911781472L;

	/**
	 * 搜索关键字
	 */
	private String keywords;

	/**
	 * 搜索次数
	 */
	private Integer count = 0;

	/**
	 * 显示标识位
	 *
	 * @see com.mxep.model.enums.EnumDisplay
	 */
	private byte isDisplay;

	/**
	 * 获取关键字
	 *
	 * @return 关键字
     */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * 设置关键字
	 *
	 * @param keywords 关键字
     */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * 获取搜索次数
	 *
	 * @return 搜索次数
     */
	public Integer getCount() {
		return count;
	}

	/**
	 * 设置搜索次数
	 *
	 * @param count 搜索次数
     */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 获取显示标识位
	 *
	 * @see com.mxep.model.enums.EnumDisplay
	 * @return 显示标识位
     */
	@Column(name = "is_display")
	public byte getIsDisplay() {
		return isDisplay;
	}

	/**
	 * 设置显示标识位
	 *
	 * @see com.mxep.model.enums.EnumDisplay
	 * @param isDisplay 显示标识位
     */
	public void setIsDisplay(byte isDisplay) {
		this.isDisplay = isDisplay;
	}
}
