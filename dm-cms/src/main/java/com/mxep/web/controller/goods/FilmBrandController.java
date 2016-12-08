package com.mxep.web.controller.goods;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.SysConstant;
import com.mxep.model.goods.FilmBrand;
import com.mxep.model.goods.Goods;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.FilmBrandService;
import com.mxep.web.service.GoodsService;
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
 * Controller - 品牌
 */
@Controller
@RequestMapping("/brand")
public class FilmBrandController extends WebController {

	@Autowired
	private FilmBrandService filmBrandService;


	/**
	 *获取所有品牌
	 * @return
	 */
	@RequestMapping(value = "/getFilmBrand", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getGoods() {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择");
		list.add(objTemp);
		List<FilmBrand> filmBrandList = filmBrandService.findAll();
		for (FilmBrand g : filmBrandList) {
			Map<String, Object> obj = Maps.newHashMap();
			obj.put("id", g.getId());
			obj.put("text", g.getName());
			list.add(obj);
		}
		return list;
	}


	/** 品牌列表 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "brand/list";
	}


	/** 品牌列表 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public String agreements(Pagination pagination,
							 HttpServletRequest request, Model model,
							 @RequestParam(value = "search_EQ_status" , required = false)Integer[] status){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_flag",1);
		searchParams.remove("EQ_status");
		if(null != status && status.length > 0)
		{
			searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
		}
		model.addAttribute("page", filmBrandService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
		return "brand/nested";
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
			model.addObject("result", filmBrandService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	/** 删除品牌*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		return filmBrandService.deleteBrand(ids);
	}

	/** 置顶品牌*/
	@ResponseBody
	@RequestMapping(value = "/recommend", method = RequestMethod.POST)
	public JsonMap recommendFlag(@RequestParam("id[]") Integer[] ids) {
		return filmBrandService.recommendFlag(ids);
	}

	/** 置顶品牌*/
	@ResponseBody
	@RequestMapping(value = "/disRecommend", method = RequestMethod.POST)
	public JsonMap disRecommendFlag(@RequestParam("id[]") Integer[] ids) {
		return filmBrandService.disRecommendFlag(ids);
	}

	/** 新建品牌*/
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "brand/edit";
	}



	/** 编辑品牌*/
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id")Integer id, Model model) {
		FilmBrand filmBrand = filmBrandService.get(id);
		model.addAttribute("brand",filmBrand);
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "brand/edit";
	}

	/**
	 * 上架品牌
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id[]")Integer[] ids){
		return filmBrandService.enable(ids);
	}

	/**
	 * 下架品牌
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public JsonMap disable(@RequestParam("id[]")Integer[] ids){
		return filmBrandService.disable(ids);
	}

	/** 保存物品*/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonMap save(FilmBrand filmBrand) {
//		filmBrand.setFlag((byte) 1);
		filmBrandService.save(filmBrand);
		return new JsonMap(0, "保存成功");
	}



}

