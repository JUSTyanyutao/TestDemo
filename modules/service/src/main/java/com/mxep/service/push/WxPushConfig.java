package com.mxep.service.push;

import com.mxep.model.Constant;

/**
 * 微信推送消息的跳转链接
 *
 * @author ranfi
 * @since 16/5/27
 */
public class WxPushConfig {

    /**
     *  用户充值成功后的通知
     */
    public final static String RECHARGE_SUCCESS_URL = Constant.WEIXIN_DOMAIN + "/#record";


    /**
     *  优惠券页面
     */
    public final static String COUPON_LIST_URL = Constant.WEIXIN_DOMAIN + "/#coupon";

    /**
     * 用户下单成功——跳转到待接单
     */
    public final static String ORDER_LIST_URL = Constant.WEIXIN_DOMAIN + "/#order/2";


    /**
     * 新订单生成提醒工人
     */
    public final static String ORDER_ACCEPT_URL = Constant.WEIXIN_DOMAIN + "/#workerOrder/2";


    /**
     * 工人已接单用户提醒——跳转到订单配送中
     */
    public final static String ORDER_ACCEPTED_URL = Constant.WEIXIN_DOMAIN + "/#order/3";



    /**
     * 用户确认收货——跳转到订单待收货
     */
    public final static String ORDER_CONFIRM_RECEIPT_URL = Constant.WEIXIN_DOMAIN + "/#order/4";

    /**
     * 订单已完成
     */
    public final static String ORDER_COMPLETED_URL = Constant.WEIXIN_DOMAIN + "/#order/5";



}
