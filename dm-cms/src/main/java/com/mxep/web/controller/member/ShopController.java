package com.mxep.web.controller.member;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.SysConstant;
import com.mxep.model.member.Shop;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.ShopService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Controller - 场地
 */
@Controller
@RequestMapping("/shop")
public class ShopController extends WebController {

	@Autowired
	private ShopService shopService;


	/**
	 *获取所有 4s场地
	 * @return
	 */
	@RequestMapping(value = "/get4sShop", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getGoods() {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择");
		list.add(objTemp);
		List<Shop> shopList = shopService.findAll4sShop();
		for (Shop g : shopList) {
			Map<String, Object> obj = Maps.newHashMap();
			obj.put("id", g.getId());
			obj.put("text", g.getName());
			list.add(obj);
		}
		return list;
	}


//	/**
//	 * 获取场地状态
//	 * @return
//	 */
//	@RequestMapping(value = "/getStatus", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Map<String, Object>> getStatus() {
//		List<Map<String, Object>> list = Lists.newArrayList();
//		Map<String, Object> objTemp = Maps.newHashMap();
//		Map<String, Object> objTemp1 = Maps.newHashMap();
//		Map<String, Object> objTemp2 = Maps.newHashMap();
//		objTemp.put("id", -1);
//		objTemp.put("text", "请选择场地状态");
//		list.add(objTemp);
//		objTemp1.put("id",0);
//		objTemp1.put("text","下架");
//		list.add(objTemp1);
//		objTemp2.put("id",1);
//		objTemp2.put("text", "上架");
//		list.add(objTemp2);
//		return list;
//	}

	/** 4s 场地列表 */
	@RequestMapping(value="/4s/list", method = RequestMethod.GET)
	public String list1(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "shop/4s/list";
	}


	/** 4s 场地列表 */
	@RequestMapping(value="/4s/list",method = RequestMethod.POST)
	public String list1(Pagination pagination,
							 HttpServletRequest request, Model model){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_flag",1);
		searchParams.put("EQ_type" ,1);
		model.addAttribute("page", shopService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
		return "shop/4s/nested";
	}


	/** 街边店铺 场地列表 */
	@RequestMapping(value="/street/list", method = RequestMethod.GET)
	public String list2(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "shop/street/list";
	}


	/** 街边店铺 场地列表 */
	@RequestMapping(value="/street/list",method = RequestMethod.POST)
	public String list2(Pagination pagination,
							 HttpServletRequest request, Model model){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_flag",1);
		searchParams.put("EQ_type" ,2);
		model.addAttribute("page", shopService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
		return "shop/street/nested";
	}



	//导出Excel
	@RequestMapping(value = "/export")
	public ModelAndView export(HttpServletRequest request,@RequestParam(value = "search_EQ_status" , required = false)Integer[] status) {
		ModelAndView model = new ModelAndView();
		try {
			model = getExcelView(request);
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
			searchParams.remove("EQ_status");
			if(null != status && status.length > 0)
			{
				searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
			}
			searchParams.put("EQ_flag",1);
			model.addObject("result", shopService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	/** 删除场地*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		return shopService.deleteShop(ids);
	}



	/** 新建4s场地*/
	@RequestMapping(value = "/4s/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "shop/4s/edit";
	}



	/** 编辑4s场地*/
	@RequestMapping(value = "/4s/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id")Integer id, Model model) {
		Shop shop = shopService.get(id);
		model.addAttribute("shop",shop);
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "shop/4s/edit";
	}


	/** 编辑 街边街道 场地*/
	@RequestMapping(value = "/street/edit/{id}", method = RequestMethod.GET)
	public String editShop(@PathVariable("id")Integer id, Model model) {
		Shop shop = shopService.get(id);
		model.addAttribute("shop",shop);
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "shop/street/edit";
	}


	/**
	 * 审核场地
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id")Integer id,
						  @RequestParam("status")byte status,
						  @RequestParam("remark")String remark){
		return shopService.audit(id,status,remark);
	}



	/** 保存 4s 场地*/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonMap save(Shop shop,@RequestParam("cityName")String name) {
		shopService.save(shop,name);
		return new JsonMap(0, "保存成功");
	}

	/** 保存 街边 场地*/
	@ResponseBody
	@RequestMapping(value = "/save/street", method = RequestMethod.POST)
	public JsonMap saveStreet(Shop shop,@RequestParam("cityName")String name) {
		shopService.saveStreet(shop,name);
		return new JsonMap(0, "保存成功");
	}



}

