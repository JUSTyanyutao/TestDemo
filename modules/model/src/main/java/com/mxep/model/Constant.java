/**
 * 2014-12-8
 * model
 */
package com.mxep.model;


/**
 * @author hxj
 */
public class Constant {

    public static final Integer PAGE = 1;
    public static final Integer PAGE_SIZE = 15;

    public static final String OK = "OK";
    public static final String FAIL = "FAIL";

    public static final Integer SYSTEM_ADMIN_USER = 1;//系统管理员ID

    public static final String WEIXIN_DOMAIN = "http://weixin.zenmechi.cc";

    public static final String IMAGE_DOMAIN = "http://image.zenmechi.cc";

    public static final String STATIC_DOMAIN = "http://static.zenmechi.cc";

    public static final String IMAGE_UPLOAD_URL = "http://image.zenmechi.cc/upload?ftype=image";

    public static final String DESC_HTML_PATH = "/opt/howeat/html";

    //免运费最低金额参数
    public final static String LOW_FREE_MONEY = "low_free_money"; //免运费最低参数

    //快递费用参数
    public final static String FREIGHT_MONEY = "freight_money"; //快递费用

    public static final Double SEARCH_DISTANCE = 3.0; // km

    public static final String THUMB_IMAGE_SIZE = "";// 列表页图片尺寸

    public final static String DELIVERY_TITLE = "delivery_title"; //派送页面标题文字

    public final static String EXPIRE_DATE = "expire_date"; //优惠券过期提醒的天数(如5,表示提前5天通知即将过去)

    // 中国地区编号
    public final static Integer CHINA_AREA_ID = 5588;

    // 会员密码长度限制
    public final static Integer MIN_PASSWORD_LEGNTH = 6;
    public final static Integer MAX_PASSWORD_LENGTH = 15;

    // 积分兑换梦想币键
    public final static String PointsToMoneyKey = "points_to_money_key";
    // 积分兑换比率
    public final static Integer PointsRatio = 1000;
    // 签到规则键
    public final static String SignInRulesKey = "sign_in_rules_key";


    public final static String CacheKeyArea = "cache_key_area";


    public final static String CUSTOMER_SERVICES_PHONE = "400-8272-399";

    public static boolean IsIOS(String platform) {
        if (platform.equalsIgnoreCase("ios")) {
            return true;
        }
        return false;
    }

    public static boolean IsAndroid(String platform) {
        if (platform.equalsIgnoreCase("android")) {
            return true;
        }
        return false;
    }

    public static boolean IsWeixin(String platform) {
        if (platform.equalsIgnoreCase("weixin")) {
            return true;
        }
        return false;
    }

}
