package com.mxep.web.test;

import java.util.List;
import java.util.Map;

import com.mxep.web.utils.freemarkerUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class FreemarkTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("title","双11活动页面");
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> param = Maps.newHashMap();
		param.put("name", "小米手机");
		param.put("shortIntro", "1999元");
		list.add(param);
		objTemp.put("templateList", list);

		String templateFile = "template1.html";
		String targetHtmlPath = "D:/WNPHP/nginx/html/data/html/template1.html";
		boolean result =  freemarkerUtils.createHtml(templateFile, objTemp, targetHtmlPath);
		System.out.println("result:"+ result );
	}

}
