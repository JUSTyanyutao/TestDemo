package com.mxep.web.controller.goods;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.zxing.common.StringUtils;
import com.mxep.model.SysConstant;
import com.mxep.model.goods.Goods;
import com.mxep.model.goods.Package;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.GoodsService;
import com.mxep.web.service.PackageService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controller - 商品
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends WebController {

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private PackageService packageService;



	/**
	 *	获取  分类 为 漆面贴膜  和 玻璃贴膜的 商品
	 * @return
	 */
	@RequestMapping(value = "/getServiceGoods", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getServiceGoods() {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择");
		list.add(objTemp);
		List<Goods> goodsList = goodsService.findServiceGoods();
		for (Goods g : goodsList) {
			Map<String, Object> obj = Maps.newHashMap();
			obj.put("id", g.getId());
			obj.put("text", g.getName());
			list.add(obj);
		}
		return list;
	}


	/**
	 *获取所有商品
	 * @return
	 */
	@RequestMapping(value = "/getGoods", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getGoods() {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择");
		list.add(objTemp);
		List<Goods> goodsList = goodsService.findAllGoods();
		for (Goods g : goodsList) {
			Map<String, Object> obj = Maps.newHashMap();
			obj.put("id", g.getId());
			obj.put("text", g.getName());
			list.add(obj);
		}
		return list;
	}


	/**
	 * 获取商品状态
	 * @return
	 */
	@RequestMapping(value = "/getStatus", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getStatus() {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		Map<String, Object> objTemp1 = Maps.newHashMap();
		Map<String, Object> objTemp2 = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择商品状态");
		list.add(objTemp);
		objTemp1.put("id",0);
		objTemp1.put("text","下架");
		list.add(objTemp1);
		objTemp2.put("id",1);
		objTemp2.put("text", "上架");
		list.add(objTemp2);
		return list;
	}

	/** 普通 商品列表 */
	@RequestMapping(value="/common/list", method = RequestMethod.GET)
	public String commonList(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "goods/common/list";
	}


	/** 普通 商品列表 */
	@RequestMapping(value="/common/list",method = RequestMethod.POST)
	public String commonList(Pagination pagination,
							 HttpServletRequest request, Model model,
							 @RequestParam(value = "search_EQ_status" , required = false)Integer[] status){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_flag",1);
		searchParams.put("EQ_type",1);
		searchParams.remove("EQ_status");
		if(null != status && status.length > 0)
		{
			searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
		}
		model.addAttribute("page", goodsService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
		return "goods/common/nested";
	}

	/** 新建普通商品*/
	@RequestMapping(value = "/common/edit", method = RequestMethod.GET)
	public String editCommon(Model model) {
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "goods/common/edit";
	}

	/** 新建 其他 商品*/
	@RequestMapping(value = "/others/edit", method = RequestMethod.GET)
	public String editOthers(Model model) {
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "goods/common/othersEdit";
	}

	/** 新建 企业 商品*/
	@RequestMapping(value = "/shopGoods/edit", method = RequestMethod.GET)
	public String editShopGoods(Model model) {
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "goods/common/shopGoodsEdit";
	}

	/** 编辑 普通 商品*/
	@RequestMapping(value = "/common/edit/{id}", method = RequestMethod.GET)
	public String editCommon(@PathVariable("id")Integer id, Model model) {
		Goods goods = goodsService.get(id);
		model.addAttribute("goods",goods);
		model.addAttribute("packageList",goodsService.getPackageList(goods));
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		if(goods.getCategory() == null ) {
			return "goods/common/shopGoodsEdit";
		} else if( goods.getCategory().getName().equals("车膜")) {
			return "goods/common/edit";
		}
		return "goods/common/othersEdit";
	}

	/** 服务商品列表 */
	@RequestMapping(value="/service/list", method = RequestMethod.GET)
	public String serviceList(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "goods/service/list";
	}


	/** 服务 商品列表 */
	@RequestMapping(value="/service/list",method = RequestMethod.POST)
	public String serviceList(Pagination pagination,
							 HttpServletRequest request, Model model,
							 @RequestParam(value = "search_EQ_status" , required = false)Integer[] status){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_flag",1);
		searchParams.put("EQ_type",2);
		searchParams.remove("EQ_status");
		if(null != status && status.length > 0)
		{
			searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
		}
		model.addAttribute("page", goodsService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
		return "goods/service/nested";
	}

	/** 新建 服务 商品*/
	@RequestMapping(value = "/service/edit", method = RequestMethod.GET)
	public String editService(Model model) {
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "goods/service/edit";
	}

	/** 编辑 服务 商品*/
	@RequestMapping(value = "/service/edit/{id}", method = RequestMethod.GET)
	public String editService(@PathVariable("id")Integer id, Model model) {
		Goods goods = goodsService.get(id);
		model.addAttribute("goods",goods);
		model.addAttribute("packageList",goodsService.getPackageList(goods));
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "goods/service/edit";
	}

	/** 美容洗车列表 */
	@RequestMapping(value="/cleanCar/list", method = RequestMethod.GET)
	public String cleanCarList(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "goods/cleanCar/list";
	}

	/** 美容洗车 商品列表 */
	@RequestMapping(value="/cleanCar/list",method = RequestMethod.POST)
	public String cleanCarList(Pagination pagination,
							  HttpServletRequest request, Model model,
							  @RequestParam(value = "search_EQ_status" , required = false)Integer[] status){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_flag",1);
		searchParams.put("EQ_type",3);
		searchParams.remove("EQ_status");
		if(null != status && status.length > 0)
		{
			searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
		}
		model.addAttribute("page", goodsService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
		return "goods/cleanCar/nested";
	}

	/** 新建美容洗车商品*/
	@RequestMapping(value = "/cleanCar/edit", method = RequestMethod.GET)
	public String editCar(Model model) {
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "goods/cleanCar/edit";
	}

	/** 编辑 美容洗车 商品*/
	@RequestMapping(value = "/cleanCar/edit/{id}", method = RequestMethod.GET)
	public String editCar(@PathVariable("id")Integer id, Model model) {
		Goods goods = goodsService.get(id);
		model.addAttribute("goods",goods);
		model.addAttribute("packageList",goodsService.getPackageList(goods));
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "goods/cleanCar/edit";
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
			model.addObject("result", goodsService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	/** 删除商品*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {

		return goodsService.deleteGoods(ids);
	}



	/**
	 * 上架商品
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id[]")Integer[] ids){
		return goodsService.enable(ids);
	}

	/**
	 * 下架商品
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public JsonMap disable(@RequestParam("id[]")Integer[] ids){
		return goodsService.disable(ids);
	}


	/** 置顶商品*/
	@ResponseBody
	@RequestMapping(value = "/recommend", method = RequestMethod.POST)
	public JsonMap recommendFlag(@RequestParam("id[]") Integer[] ids) {
		return goodsService.recommendFlag(ids);
	}

	/** 取消置顶商品*/
	@ResponseBody
	@RequestMapping(value = "/disRecommend", method = RequestMethod.POST)
	public JsonMap disRecommendFlag(@RequestParam("id[]") Integer[] ids) {
		return goodsService.disRecommendFlag(ids);
	}

	/** 保存物品*/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonMap save(Goods goods,@RequestParam(value = "packages",required = false)String[] packages) {
		List<Package> packageList = new ArrayList<>();
		if(packages!=null) {
			for (String s : packages) {
				packageList.add(packageService.get(Integer.parseInt(s)));
			}
		}
		goods.setPackageList(packageList);
		goodsService.save(goods);
		return new JsonMap(0, "保存成功");
	}



}

