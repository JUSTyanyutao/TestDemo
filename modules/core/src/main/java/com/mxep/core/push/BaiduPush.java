package com.mxep.core.push;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

public class BaiduPush implements Push<PushUnicastMessageRequest, PushUnicastMessageResponse> {

	private final String apiKey;
	private final String secretKey;

	public BaiduPush(String apiKey, String secretKey) {
		super();
		this.apiKey = apiKey;
		this.secretKey = secretKey;
	}

	private BaiduChannelClient buildClient() {
		// 1. 设置developer平台的ApiKey/SecretKey
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

		// 2. 创建BaiduChannelClient对象实例
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);

		// 3. 若要了解交互细节，请注册YunLogHandler类
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});
		return channelClient;
	}

	@Override
	public PushUnicastMessageResponse sendNotifaction(PushUnicastMessageRequest p) {
		try {
			return buildClient().pushUnicastMessage(p);
		} catch (ChannelClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PushUnicastMessageResponse sendBatchNotifaction(PushUnicastMessageRequest p) {
		// TODO Auto-generated method stub
		return null;
	}
}
