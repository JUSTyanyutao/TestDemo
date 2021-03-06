package com.mxep.service.push;

/**
 * 短信模板消息定义
 *
 * @author ranfi
 * @since 4/26/16
 */
public enum SmsMessageTemplate {

    /**
     * 注册验证码
     */
    VALIDATE_CODE("89694", "【怎么吃】亲，您的验证码是{0}，将于{1}分钟以后失效"),

    /**
     * 下单成功短信通知——用户
     */
    ORDER_SUBMIT_SUCCESS_TO_USER("87204", "【怎么吃】亲，下单成功了，您的订单{0}将于{1}配送到家。如有疑问，详询{2}"),

    /**
     * 下单成功短信通知——商家
     */
    ORDER_SUBMIT_SUCCESS_TO_MERCHANT("87295", "【怎么吃】商家您好，您有新的订单{0}， 总价为{1}元， 请于{2}之前打包配送到{3}分拣中心。 如有疑问，详询{4}"),


    /**
     * 下单成功短信通知——大厨
     */
    ORDER_SUBMIT_SUCCESS_TO_COOK("89637", "【怎么吃】{0}大厨您好，您有新的订单{1}，服务时长：{2}小时，请于{3}之前到达{4}。如有疑问，详询{5}"),

    /**
     * 充值成功后的短信通知
     */
    RECHARGE_SUCCESS("87205", "【怎么吃】亲，您的账户{0}已经充值{1}元，当前余额{2}元，请及时查询。如有疑问，欢迎来电咨询，客服电话{3}"),

    /**
     * 接单短信通知——用户
     */
    ACCEPT_ORDER_SUCCESS("87208", "【怎么吃】亲，您的订单 {0}已派送给配送员:{1}(联系方式{2})。如有疑问，详询{3}"),

    /**
     * 配送中订单短信通知
     */
    DELIVERING_ORDER_SUCCESS("87211", "【怎么吃】亲，您的订单 {0}正在配送至{1}，配送员:{2}， 联系方式:{3}。如有疑问，详询{4}"),


    /**
     * 账户提现
     */
    ACCOUNT_WITHDRAW_SUCCESS("87215", "【怎么吃】亲，已从您的账户{0}提现{1}元，当前账户余额{2}元，请及时查询。如有疑问，欢迎来电咨询，客服电话{3}"),


    /**
     * 提醒用户确认收货
     */
    REMINDER_CONFIRM_RECEIPT("89696", " 【怎么吃】亲，您的订单{0}已在{1}配送到{2}，请及时确认收货。如有疑问，请拨打客服电话{3}。"),


    /**
     * 自动确认收货之前的提醒
     */
    REMINDER_AUTO_CONFIRM_RECEIPT("90490", " 【怎么吃】亲，系统将于{0}小时后对您的订单{1}做自动确认收货, 请及时查询确认. 如有疑问，请拨打客服电话{2}。"),


    /**
     * 自动确认收货
     */
    AUTO_CONFIRM_RECEIPT("90489", " 【怎么吃】亲，系统已于{0}对您的订单{1}进行了自动确认收货处理, 请及时查询. 如有疑问，请拨打客服电话{2}。"),

    
    /**
     * 提醒客人准时参加宴会
     */
    REMIND_ERATTEND_PARTY("90496", " 【怎么吃】亲，您参与的宴会{0}将于{1}开始，记得准时参与哦，宴会主人期待您的光临。如有疑问，请拨打客服电话{2}。"),
    
    
    /**
     * 商家审核
     */
    MERCHANT_VERIFIED("91434", " 【怎么吃】商家{0}您好，您的店铺资料已审核通过，登陆账号为{1}，请登陆商家端查看。如有疑问，请联系{2}"),

    /**
     * 取消订单后通知大厨
     */
    CANCEL_ORDER_SMS2COOK("99449", "【怎么吃】{0}大厨您好，用户已经取消了您的订单{1}。如有疑问，详询{2}."),

    /**
     * 取消订单后通知商家审核
     */
    CANCEL_ORDER_SMS2MERCHANT("99592", "【怎么吃】商家您好，您的订单{0}，已被取消。如有疑问，详询{1}.");


    public String tempId;
    public String message;

    SmsMessageTemplate(String tempId, String message) {
        this.tempId = tempId;
        this.message = message;
    }
}
