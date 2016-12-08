package com.mxep.core.utils.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * http请求工具
 *
 */
public final class HttpUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	private static final String UTF8 = "UTF-8";

	/**
	 * @param postUrl
	 * @param postData
	 * @param contentType
	 * @return
	 * 发送post请求
	 */
	public static final String post(String postUrl, String postData, String contentType) {
		OutputStream out = null;
		InputStream in = null;
		try {
			URL url = new URL(postUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置http请求协议头
            conn.setRequestProperty("Content-Type", contentType);
            conn.setRequestProperty("Content-Length", "" + postData.length());
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			out = conn.getOutputStream();
			out.write(postData.getBytes(UTF8));
			out.flush();
            // 获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "";
            }
            // 获取响应内容
			String line = null;
			StringBuilder appender = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			while((line = reader.readLine()) != null) {
				appender.append(line);
			}
			return appender.toString();
		} catch (IOException e) {
			e.printStackTrace();
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage());
			}
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					if (logger.isErrorEnabled()) {
						logger.error(e.getMessage());
					}
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					if (logger.isErrorEnabled()) {
						logger.error(e.getMessage());
					}
				}
			}
		}
		return "";
	}
	
}
