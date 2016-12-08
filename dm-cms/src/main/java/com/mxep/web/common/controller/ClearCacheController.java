package com.mxep.web.common.controller;

import com.mxep.core.utils.http.HttpUtils;
import com.mxep.web.web.JsonMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
@Controller
@RequestMapping("/clear/cache")
public class ClearCacheController {

    private static String ip = "121.42.159.144";

    private static String port = "8082";

    @RequestMapping("")
    public String clearCache(Model model)
    {
        model.addAttribute("action","http://"+ip+":"+port+"/dmtm-api/cache/flush");
        return "cache/list";
    }

    @ResponseBody
    @RequestMapping( value = "/solve", method = RequestMethod.POST)
    public Map solve()throws IOException
    {
        Map map = new HashMap<>();
//        StringBuffer buffer = new StringBuffer(); //用来拼接参数
        StringBuffer result = new StringBuffer(); //用来接受返回值
        String url = "http://"+ip+":"+port+"/dmtm-api/cache/flush";
        String data = HttpUtils.get(url,"utf-8");
        String[] s= data.replace("\"","").split(":");
        map.put("code",s[1].charAt(0));
        map.put("msg","清除成功");
        return map;
    }


}
