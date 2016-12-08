/**
 * 2014-12-8
 * model
 */
package com.mxep.model;


/**
 * @author hxj
 */
public class SysConstant {

    public static final Integer PAGE = 1;
    public static final Integer PAGE_SIZE = 15;

    public static final String OK = "OK";
    public static final String FAIL = "FAIL";

    public static final Integer SYSTEM_ADMIN_USER = 1;//系统管理员ID

    public static final String DOAMIN_SUFFIX = ".hexianhui.cn";

    public static final String WEIXIN_DOMAIN = "http://weixin" + DOAMIN_SUFFIX;

    public static final String IMAGE_DOMAIN = "http://image" + DOAMIN_SUFFIX;

    public static final String STATIC_DOMAIN = "http://static" + DOAMIN_SUFFIX;

    public static final String IMAGE_UPLOAD_URL = "http://image" + DOAMIN_SUFFIX + "/upload?ftype=image";

    public static final String DESC_HTML_PATH = "/opt/pickorder/html";

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

    // 大厨上门间隔时间
    public final static Integer COOK_INTERVAL = 1;


    public final static String CUSTOMER_SERVICES_PHONE = "400-8272-399";

}
