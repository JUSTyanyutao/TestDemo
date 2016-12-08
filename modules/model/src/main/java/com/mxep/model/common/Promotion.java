package com.mxep.model.common;

import com.mxep.model.BaseEntity;
import com.mxep.model.GlobalConstant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import java.util.Date;

/**
 * Entity - 优惠活动
 *
 * @author xingkong1221
 * @since 2016-01-18
 */
@Entity
public class Promotion extends BaseEntity {

    private static final long serialVersionUID = 211741268251459032L;

    /** 优惠活动名称 */
    private String name;

    /**
     * 优惠活动状态
     * @see GlobalConstant.Status
     */
    private Integer status;

    /**
     * 活动类型
     * @see Promotion.PromotionType
     */
    private Integer promotionType;

    /**
     * 奖品类型
     * @see Promotion.RewardType
     */
    private Integer rewardType;

    /**
     * 优惠规则
     *
     * 1. 优惠券 [{quantity: '数量',coupon: {title:'标题', desc:'描述', denomination: '面额', restrictionMoney: '限制金额', expired: '有效期（单位：天）'}},...]
     * 2. 新用户限购商品 ［{quantity: '数量', goodsId: '商品编号'},...］
     *
     */
    private String rules;

    /** 开始时间 */
    private Date startTime;

    /** 结束时间 */
    private Date endTime;
    
    /** 排序 */
    private Integer sort;


    /**
     * 获取优惠活动名称
     *
     * @return 优惠活动名称
     */
    @Column(columnDefinition = "varchar(50) NOT NULL DEFAULT '' COMMENT '优惠活动名称'")
    public String getName() {
        return name;
    }

    /**
     * 设置优惠活动名称
     *
     * @param name 优惠活动名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取优惠活动的状态
     *
     * @see GlobalConstant.Status
     * @return 优惠活动的状态
     */
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT '0' COMMENT '优惠活动状态'")
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置优惠活动的状态
     *
     * @param status 优惠活动的状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取优惠活动的类型
     *
     * @see Promotion.PromotionType
     * @return 优惠活动的类型
     */
    @Column(columnDefinition = "tinyint(3) UNSIGNED NOT NULL DEFAULT '1' COMMENT '优惠活动的类型'")
    public Integer getPromotionType() {
        return promotionType;
    }

    /**
     * 设置优惠活动的类型
     *
     * @see Promotion.PromotionType
     * @param promotionType 优惠活动的类型
     */
    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    /**
     * 获取奖品类型
     *
     * @see Promotion.RewardType
     * @return 奖品类型
     */
    @Column(columnDefinition = "tinyint(3) UNSIGNED NOT NULL DEFAULT '1' COMMENT '奖品类型'")
    public Integer getRewardType() {
        return rewardType;
    }

    /**
     * 设置奖品类型
     *
     * @see Promotion.RewardType
     * @param rewardType 奖品类型
     */
    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }

    /**
     * 获取活动规则
     *
     * @return 活动规则
     */
    @Column(columnDefinition = "varchar(1000) NOT NULL DEFAULT '' COMMENT '活动规则'")
    public String getRules() {
        return rules;
    }

    /**
     * 设置活动规则
     *
     * @param rules 活动规则
     */
    public void setRules(String rules) {
        this.rules = rules;
    }

    /**
     * 获取开始时间
     *
     * @return 开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '开始时间'")
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 设置结束时间
     *
     * @return 结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '结束时间'")
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    @Column(columnDefinition = "int(4) UNSIGNED NOT NULL DEFAULT '1' COMMENT '排序字段'")
    public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Transient
    public String getPromotionTypeName(){
    	if(PromotionType.getPromotionTypeName(promotionType) != null){
    		return PromotionType.getPromotionTypeName(promotionType).label;
    	}
    	return "";
    }
    
    @Transient
    public String getRewardTypeName(){
    	if(RewardType.getRewardTypeName(rewardType) != null){
    		return RewardType.getRewardTypeName(rewardType).label;
    	}
    	return "";
    }

    /**
     * 优惠活动类型
     */
    public enum PromotionType {
        Register(1, "注册",1), Order(2,"下单",1);

        public Integer value;
        public String label;
        public Integer flag;

        PromotionType(Integer value, String label,Integer flag) {
            this.value = value;
            this.label = label;
            this.flag = flag;
        }
        
        public Integer getValue() {
			return value;
		}
		public void setValue(Integer value) {
			this.value = value;
		}

		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public Integer getFlag() {
			return flag;
		}

		public void setFlag(Integer flag) {
			this.flag = flag;
		}

		public static PromotionType getPromotionTypeName(Integer value) {
        	PromotionType promotionType = null;
            for (PromotionType map : PromotionType.values()) {
                if (value == map.value) {
                	promotionType = map;
                    break;
                }
            }
            return promotionType;
        }
        
    }

    /**
     * 奖品类型
     */
    public enum RewardType {
        Coupon(1, "优惠券"), Fish(2, "新用户")
        ;
        public Integer value;
        public String label;

        RewardType(Integer value, String label) {
            this.value = value;
            this.label = label;
        }
        
        public Integer getValue() {
			return value;
		}
		public void setValue(Integer value) {
			this.value = value;
		}

		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
        
        public static RewardType getRewardTypeName(Integer value) {
        	RewardType rewardType = null;
            for (RewardType map : RewardType.values()) {
                if (value == map.value) {
                	rewardType = map;
                    break;
                }
            }
            return rewardType;
        }
    }
}
