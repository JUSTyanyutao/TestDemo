package com.mxep.web.test;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jersey.repackaged.com.google.common.collect.Maps;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = Maps.newHashMap();

		map.put("appVersion", "testBase");
		map.put("phoneModel", "eclipse");
		map.put("platformType", "Java");

		Map<String, Object> param = Maps.newHashMap();
		param.put("scenicId", "5");

		map.put("param", param);

		String result = post("http://192.168.1.180:8080/sanmao-api/scenic/getMapinfo", map, String.class);
		System.out.println(result);
	}
	
	protected static <T> T post(String url, Object data, Class<T> cls) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = request.post(Entity.entity(data, MediaType.APPLICATION_JSON));
		return response.readEntity(cls);
	}
}
