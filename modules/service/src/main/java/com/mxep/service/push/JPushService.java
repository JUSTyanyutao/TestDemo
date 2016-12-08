package com.mxep.service.push;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;

/**
 * 极光推送Service
 * @author zlj
 *
 */
@Service
public class JPushService {
    
	protected static final Logger logger = LoggerFactory.getLogger(JPushService.class);

	private static final String appKey ="9e924902ec0f464e6870dc92";
	private static final String masterSecret = "4458310d0dd43857fe393b2e";
	
	/**
	 * ios极光推送
	 */
	@Async
    public void sendIosAlert(String title,String registrationID) {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        try {
            PushResult result = jpushClient.sendIosNotificationWithRegistrationID(title, new HashMap<String, String>(), registrationID);
            logger.info("Get result - " + result);
        } catch (APIConnectionException e) {
        	logger.error("Connection error.", e);
        } catch (APIRequestException e) {
        	logger.error("Error response from JPush server.", e);
        	logger.info("HTTP Status: " + e.getStatus());
        	logger.info("Error Code: " + e.getErrorCode());
        	logger.info("Error Message: " + e.getErrorMessage());
        }
    }

//	/**
//	 * JPUSH广播推送
//	 *
//	 * @param title
//	 * @param messgae
//	 * @param param
//	 */
//	@Async
//	public void send(String title, String messgae, Map param) {
//		JPushUtils.getInstance(masterSecret, appKey).broadcastPush(title, messgae, param);
//	}

}

