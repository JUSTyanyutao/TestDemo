package com.mxep.core.sms.ytx;

import com.cloopen.rest.sdk.CCPRestSDK;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * SMS - 云通讯短信平台
 *
 * @author xingkong1221
 * @since 2016-09-07
 */
public class YtxHelper {

    private Logger logger = Logger.getLogger(YtxHelper.class);

    /**
     * 云通讯SDK
     */
    private CCPRestSDK sdk;

    /**
     * 实例化云通讯sdk
     *
     * @param serverIp 服务器ip
     * @param serverPort 服务器端口
     * @param accountSid 账号sid
     * @param accountToken 账号token
     * @param appId 应用id
     */
    public YtxHelper(String serverIp, String serverPort, String accountSid, String accountToken, String appId) {
        sdk = new CCPRestSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSid, accountToken);// 初始化主帐号和主帐号TOKEN
        sdk.setAppId(appId);// 初始化应用ID
    }

    /**
     * 发送短信
     *
     * @param to 接受者手机
     * @param templateId 模板编号
     * @param datas 模板数据
     */
    public Map<String, Object> sendSMS(String to, String templateId, String[] datas) {
        return sdk.sendTemplateSMS(to, templateId, datas);
    }

    /**
     * 解析发送短信请求结果
     *
     * @param result 请求结果
     * @return 请求是否发送成功
     */
    public boolean parse(Map<String, Object> result) {
        // 判断请求是否发送成功
        boolean ret = result != null && "000000".equals(result.get("statusCode"));

        // 记录日志消息
        if (!ret) {
            logger.error("短信发送失败，错误码：" + result.get("statusCode") + " 错误消息：" + result.get("statusMsg"));
        }

        return ret;
    }
    

}
