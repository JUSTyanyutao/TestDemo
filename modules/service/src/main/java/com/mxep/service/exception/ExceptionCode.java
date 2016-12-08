package com.mxep.service.exception;

/**
 * 异常码
 */
public enum ExceptionCode {

    NORMAL(0, "OK"),

    // 系统级
    DEFAULT_ERROR_MSG(10000, "服务器被外星人劫走了，请稍后再试!"),
    SERVEREXCEPTION(10001, "服务器忙!"), // 实则服务器异常
    METHODDOESNOTEXIST(10002, "Method does not exist!"), // 接口不存在
    CLIENTAPIERROR(10003, "client api error!"), // 接口不存在
    CLIENT_EXCEPTION(10004, "客户端异常"),

    MISSING_ACCESS_TOKEN(20001, "Missing access_token"), // 缺少access_token
    INVALID_ACCESS_TOKEN(20002, "Invalid access_token"), // 无效的accessToken
    INVALID_REFRESH_TOKEN(20003, "Invalid refresh_token"), // 无效的refresh_token
    PARAMEXCEPTION(20101, "Param to bean exception!"), // Param参数转换异常
    USERNOTEMPTY(20102, "User cannot be empty!"),
    ONEPARAMISEMPTY(20103, "One param is empty!"),
    MISSINGPARAMETER(20104, "Missing parameter!"),
    UPLOADFILEEXCEPTION(20105, "upload file exception!"),


    // 会员接口异常 40****
    DISABLED_MEMBER(40001, "账号被禁用"),
    NONE_MEMBER_INFO(40002, "帐号信息不存在"),
    MEMBER_UN_ACTIVATE(40007, "帐号未激活"),
    INVALID_VERIFYCATION_CODE(40003, "无效的验证码"),
    EXISTS_MEMBER_ACCOUNT(40004, "手机号码已注册"),
    PASSWORD_NOT_EQUAL(40005, "前后两次密码不一致"),
    INVALID_MEMBER_PASSWORD(40006, "密码错误"),
    SEND_REGISTER_SMS_FAILURE(40008, "短信验证码发送失败"),
    INVALID_REGISTER_TOKEN(40009, "无效的注册token"),
    INVALID_PASSWORD_LENGTH(40010, "密码长度需6～15位"),
    INVALID_GENDER_VALUE(40011, "无效的性别参数"),
    NOT_BIND_MOBILE_MEMBER(40012, "非绑定手机用户"),

    // 地址接口异常 40*1**
    NONE_MEMBER_ADDRESS(40101, "地址信息不存在"),
    ILLEGAL_ADDRESS_UPDATE(40102, "非法修改其人地址"),

    // 签到 41***
    HAS_SIGNED(41001, "今日已签到"),

    // 推广记录异常 42***
    NONE_MEMBER_MARKET_RECORD_INFO(42001, "推广记录信息不存在"),
    DUPLICATED_FIRST_ORDER(42002, "重复绑定首次下单"),

    // 晒单异常 43***
    NONE_SHARE_INFO(43001, "晒单信息不存在"),
    SHARE_IS_CHECKING(43002, "晒单信息审核中"),



    // 购物车异常 44***
    INVALID_CART_QUANTITY(44001, "清单数量必须大于0"),
    INVALID_AUCTION_STATUS(44002, "夺宝已经结束，请等待新一期的夺宝活动"),


    // 收藏接口异常
    INVALID_FAVORITE_TYPE(45001, "无效的收藏类型"),



    // 商品接口异常 5****
    NONE_GOODS_INFO(50001, "商品信息不存在"),
    DISABLED_GOODS(50002, "商品已下架"),
    NONE_GOODS_CATEGORY_INFO(50003, "商品分类信息不存在"),

    // 竞拍品异常
    NONE_AUCTION_INFO(51001, "夺宝商品信息不存在"),
    NONE_AUCTION_DATE_INFO(51002, "本期夺宝活动已经结束"),


    // 菜场接口异常 52***
    NONE_FOOD_MARKET_INFO(52001, "菜场信息不存在"),

    // 工人端接口异常 6****
    WORKER_ID_EMPTY(60001, "工人ID为空"),
    WORKER_ORDER_ILLEGAL_UPDATE(60002, "工人订单非法更新"),
    DELIVERY_STATUS_EXCEPTION(600003, "派送状态异常"),
    NOT_FOUND_DELIVERYED_WORKER(60003, "未发现可派送的工人"),
    NO_PERMISSION_DELIVERED(60004, "此工单不属于你配送"),


    // 订单相关的异常 7*****
    BARCODE_CANNOT_BE_EMPTY(700001, "条形码不能为空"),
    BARCODE_NOT_EXISTS(700002, "条形码不存在"),
    ORDER_NOT_EXISTS(700003, "订单不存在"),


    // 全局异常 9****
    INVALID_MOBILE(90001, "手机号码格式不正确"),
    MISSING_PARAMETER(90006, "缺少必要参数"),
    INVALID_PARAMETER(90007, "参数错误"),
    CODE_INCORRECT(90011, "验证码错误"),
    INVALID_LATITUDE(90016, "纬度格式不正确"),
    INVALID_LONGITUDE(90017, "经度格式不正确"),;
    public final Integer errorCode;
    public final String errorMsg;

    ExceptionCode(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static ExceptionCode getExceptionCode(final String exceptionCode) {
        try {
            return ExceptionCode.valueOf(exceptionCode);
        } catch (Exception e) {
        }

        return SERVEREXCEPTION;
    }


}
