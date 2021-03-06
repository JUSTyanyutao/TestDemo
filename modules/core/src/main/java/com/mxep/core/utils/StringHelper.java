package com.mxep.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class StringHelper {


    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String objectToString(Object value, String defaultValue) {
        if (null == value) {
            return defaultValue;
        }
        return value.toString();
    }

    /**
     * 转Int
     *
     * @param str
     * @param defaultValue
     * @return 相应的值 或 零
     */
    public static int objectToInt(Object obj, int defaultValue) {
        String str = objectToString(obj, defaultValue + "");
        int i = 0;
        try {
            i = Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            i = 0;
        }
        return i;
    }

    /**
     * 转short
     *
     * @param obj
     * @return 相应的值 或 零
     */
    public static short objectToShort(Object obj, short defaultValue) {
        String str = objectToString(obj, defaultValue + "");
        short i = 0;
        try {
            i = Short.parseShort(str.trim());
        } catch (NumberFormatException e) {
            i = 0;
        }
        return i;
    }

    /**
     * 转Double
     *
     * @param obj
     * @return 相应的值 或 零
     */
    public static double objectToDouble(Object obj, double defaultValue) {
        String str = objectToString(obj, defaultValue + "");
        double i = 0;
        try {
            i = Double.parseDouble(str.trim());
        } catch (NumberFormatException e) {
            i = 0;
        }
        return i;
    }

    /**
     * 转Long
     *
     * @param obj
     * @return 相应的值 或 零
     */
    public static long objectToLong(Object obj, long defaultValue) {
        String str = objectToString(obj, defaultValue + "");
        Long li = new Long(0);
        try {
            li = Long.valueOf(str);
        } catch (NumberFormatException e) {
        }
        return li.longValue();
    }

    // ***************** 其他类型相互转换 *****************

    /**
     * double转long
     *
     * @param d
     * @return 只截取前面的整数
     */
    public static long doubleToLong(double d) {
        long l = 0;
        try {
            // double转换成long前要过滤掉double类型小数点后数据
            l = Long.parseLong(String.valueOf(d).substring(0, String.valueOf(d).lastIndexOf(".")));
        } catch (Exception e) {
            l = 0;
        }
        return l;
    }

    /**
     * double转int
     *
     * @param d
     * @return 只截取前面的整数
     */
    public static int doubleToInt(double d) {
        int i = 0;
        try {
            // double转换成Int前要过滤掉double类型小数点后数据
            i = Integer.parseInt(String.valueOf(d).substring(0, String.valueOf(d).lastIndexOf(".")));
        } catch (Exception e) {
            i = 0;
        }
        return i;
    }

    /**
     * double转long(四舍五入)
     *
     * @param d
     * @return 只截取前面的整数 (四舍五入)
     */
    public static long doubleToLongWhithRound(double d) {
        long l = 0;
        try {
            l = Math.round(d);
        } catch (Exception e) {
            l = 0;
        }
        return l;
    }

    /**
     * double转int(四舍五入)
     *
     * @param d
     * @return 只截取前面的整数 (四舍五入)
     */
    public static int doubleToIntWhithRound(double d) {
        int i = 0;
        try {
            i = (int) Math.round(d);
        } catch (Exception e) {
            i = 0;
        }
        return i;
    }

    public static double longToDouble(long d) {
        double l = 0;
        try {
            l = Double.parseDouble(String.valueOf(d));
        } catch (Exception e) {
            l = 0;
        }
        return l;
    }

    public static int longToInt(long d) {
        int l = 0;
        try {
            l = Integer.parseInt(String.valueOf(d));
        } catch (Exception e) {
            l = 0;
        }
        return l;
    }

    /**
     * 获取随机验证码
     *
     * @param length
     * @return
     */
    public static String newRadomCode(int length) {
        final int maxNum = 36;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < length) {
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    /**
     * 获取非0的随机验证码
     *
     * @param length
     * @return
     */
    public static String newRadomCodeNotZero(int length) {
        final int maxNum = 36;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < length) {
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    /**
     * 操作平台
     *
     * @param length
     * @return
     */
    public static String newRadomCodePlat(int length) {
        final int maxNum = 36;
        int i; // 生成的随机数
        int count = 0; // 生成的长度
        char[] str = {'1', '2', '3'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < length) {
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    /**
     * 13位条形码生成规则，临时方案，偶尔会出现重复（2015-08-31停止使用）
     *
     * @param uid
     * @param ctime
     * @return
     */
    public static String createBarCode(Integer uid, Date ctime) {
        int r1 = (int) (Math.random() * (9));
        String barCode = StringUtils.rightPad("" + uid, 7, "0") + DateUtils.date2Str(ctime, "mmss") + r1;
        Integer i1 = 0;
        Integer i2 = 0;
        for (int i = 0; i < barCode.length(); i++) {
            if (i % 2 == 0) {
                i1 += Integer.parseInt(barCode.substring(i, i + 1));
            } else {
                i2 += Integer.parseInt(barCode.substring(i, i + 1));
            }
        }
        Integer ret = i2 * 3 + i1;
        Integer w = 10 - (ret % 10);
        if (w == 10) {
            w = 0;
        }
        return barCode + w;
    }

    /**
     * 生成条形码序列到数据库
     *
     * @param codeFlag 6表示接口  9表示收衣批量条码
     * @return
     */
    public static synchronized String createBarCode(String codeFlag) {
        String barCode = codeFlag + StringHelper.newRadomCode(11);
        Integer i1 = 0;
        Integer i2 = 0;
        for (int i = 0; i < barCode.length(); i++) {
            if (i % 2 == 0) {
                i1 += Integer.parseInt(barCode.substring(i, i + 1));
            } else {
                i2 += Integer.parseInt(barCode.substring(i, i + 1));
            }
        }
        Integer ret = i2 * 3 + i1;
        Integer w = 10 - (ret % 10);
        if (w == 10) {
            w = 0;
        }
        return barCode + w;
    }

    public static Integer seq = 0;

    /**
     * 生成订单号
     *
     * @param uid  用户编号
     * @param time 时间
     * @return 订单号
     */
    public static synchronized String createOrderSn(Integer uid, Date time) {
        if (seq++ >= 1000000) {
            seq = 1;
        }
        String date = DateUtils.date2Str(time, "yyMMddHHmmss");
        String id = StringUtils.leftPad("" + seq, 6, "0");
        return date + id;
    }



    public static void main(String[] args) {
        System.out.println(createBarCode("6"));
        System.out.println(createBarCode(87, new Date()));
        System.out.println(org.apache.commons.lang3.time.DateUtils.addDays(new Date(), -4));
        //System.out.println(getUUID());
    }
}
