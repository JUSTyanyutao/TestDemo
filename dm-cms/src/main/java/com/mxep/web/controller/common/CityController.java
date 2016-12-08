package com.mxep.web.controller.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.common.City;
import com.mxep.web.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
@Controller
@RequestMapping(value = "/city")
public class CityController extends WebController {

    @Autowired
    private CityService cityService;


    /**
     * 根据省 获取所有省下 的市
     * @return
     */
	@RequestMapping(value = "/getCityByProvinceId/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getCityByProvinceId(@PathVariable("id")Integer id) {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择");
		list.add(objTemp);
		List<City> cityList = cityService.findCityByProvinceId(id);
		for (City city : cityList) {
			Map<String, Object> obj = Maps.newHashMap();
			obj.put("id", city.getId());
			obj.put("text", city.getName());
			list.add(obj);
		}
		return list;
	}



	/**
	 * 所有的 市
	 * @return
	 */
	@RequestMapping(value = "/getCity", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getCity() {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择");
		list.add(objTemp);
		List<City> cityList = cityService.findAll();
		for (City city : cityList) {
			Map<String, Object> obj = new HashMap<>();
			obj.put("id", city.getId());
			obj.put("text", city.getName());
			list.add(obj);
		}
		return list;
	}


}
