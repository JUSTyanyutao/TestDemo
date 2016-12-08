package com.mxep.model.common;

import com.mxep.model.BaseSortedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Entity － 常见问题
 */
@Entity
public class Faq extends BaseSortedEntity {

	private static final long serialVersionUID = -1676495236962450281L;

	/**
	 * 问题
	 */
	private String question;

	/**
	 * 答案
	 */
	private String answer;

	/**
	 * 显示标识位 (0:隐藏  1:显示)
	 *
	 * @see com.mxep.model.enums.EnumDisplay
	 */
	private byte isDisplay;

	/**
	 * 类型 (1:常见问题  2:积分说明)
	 *
	 * @see com.mxep.model.enums.EnumFaqType
	 */
	private int type;

	/**
	 * 获取问题
	 *
	 * @return 问题
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * 设置问题
	 *
	 * @param question 问题
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * 获取答案
	 *
	 * @return 答案
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * 设置答案
	 *
	 * @param answer 答案
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * 获取显示标识位 (0:隐藏  1:显示)
	 *
	 * @see com.mxep.model.enums.EnumDisplay
	 * @return 显示标识位
	 */
	@Column(name = "is_display")
	public byte getIsDisplay() {
		return isDisplay;
	}

	/**
	 * 设置显示标识位 (0:隐藏  1:显示)
	 *
	 * @see com.mxep.model.enums.EnumDisplay
	 * @param isDisplay 显示标识位
	 */
	public void setIsDisplay(byte isDisplay) {
		this.isDisplay = isDisplay;
	}

	/**
	 * 获取类型 (1:常见问题  2:积分说明)
	 *
	 * @see com.mxep.model.enums.EnumFaqType
	 * @return 类型
	 */
	public int getType() {
		return type;
	}

	/**
	 * 设置类型 (1:常见问题  2:积分说明)
	 *
	 * @see com.mxep.model.enums.EnumFaqType
	 * @param type 类型
	 */
	public void setType(int type) {
		this.type = type;
	}
}
