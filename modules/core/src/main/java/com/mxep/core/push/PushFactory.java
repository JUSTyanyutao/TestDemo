package com.mxep.core.push;

import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;

/**
 * 基于第三方推送的工厂实现类
 * 
 * @author ranfi
 * 
 */
public class PushFactory {

	// 默认运维app的推送key
	private static final String baiduApiKey = "xEG3LbYlQVBbNz92xrpEIF33";
	private static final String baiduSecretKey = "AyQGd5nlRkec0mw7w3hz3BUYdFB6MHpc";

	// 洗洗app的推送key
	private static final String appIOSBaiduApiKey = "xEG3LbYlQVBbNz92xrpEIF33";
	private static final String appIOSBaiduSecretKey = "AyQGd5nlRkec0mw7w3hz3BUYdFB6MHpc";

	private static final String appAndroidBaiduApiKey = "yj43BN25tALptmx67T3tmwN6";
	private static final String appAndroidBaiduSecretKey = "VbBjtG8dXifT8ATePVsDIkToNoLHCmEl";

	private static final Push<PushUnicastMessageRequest, PushUnicastMessageResponse> baiduPush = new BaiduPush(
			baiduApiKey, baiduSecretKey);

	/**
	 * baidu IOS证书
	 */
	private static final Push<PushUnicastMessageRequest, PushUnicastMessageResponse> appIOSBaiduPush = new BaiduPush(
			appIOSBaiduApiKey, appIOSBaiduSecretKey);

	/**
	 * baidu Android证书
	 */
	private static final Push<PushUnicastMessageRequest, PushUnicastMessageResponse> appAndroidBaiduPush = new BaiduPush(
			appAndroidBaiduApiKey, appAndroidBaiduSecretKey);

	public enum PushType {

		BAIDU, JPUSH;

	}

	public static Push<PushUnicastMessageRequest, PushUnicastMessageResponse> getBaiduPush() {
		return baiduPush;
	}

	public static Push<PushUnicastMessageRequest, PushUnicastMessageResponse> getAppIOSBaiduPush() {
		return appIOSBaiduPush;
	}

	public static Push<PushUnicastMessageRequest, PushUnicastMessageResponse> getAppAndroidBaiduPush() {
		return appAndroidBaiduPush;
	}

	public static void main(String[] args) {
		PushUnicastMessageRequest request = new PushUnicastMessageRequest();
		request.setChannelId(5528042658893310923l);
		request.setDeviceType(4); // device_type => 1: web 2: pc 3:android 4:ios
									// 5:wp
		request.setDeployStatus(2);
		request.setUserId("630695248868245014");
		request.setMessageType(1);
		request.setMessage("{\"title\":\"中文\",\"description\":\"中文问\",\"aps\":{\"alert\":\"你好\"，\"sound\":\"default\",\"badge\":1}}");

		PushUnicastMessageResponse messageResponse = getBaiduPush().sendNotifaction(request);
		System.out.println(messageResponse.getSuccessAmount());
	}
}
