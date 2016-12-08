package com.mxep.web.controller.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.common.Province;
import com.mxep.web.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
@Controller
@RequestMapping(value = "/province")
public class ProvinceController extends WebController {


    @Autowired
    private ProvinceService provinceService;

    /**
     * 获取所有省
     * @return
     */
	@RequestMapping(value = "/getProvince", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getProvince() {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择");
		list.add(objTemp);
		List<Province> provinceList = provinceService.findAllProvince();
		for (Province province : provinceList) {
			Map<String, Object> obj = Maps.newHashMap();
			obj.put("id", province.getId());
			obj.put("text", province.getName());
			list.add(obj);
		}
		return list;
	}



}
