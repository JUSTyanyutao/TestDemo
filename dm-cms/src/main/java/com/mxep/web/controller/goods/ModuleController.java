package com.mxep.web.controller.goods;

import com.mxep.model.SysConstant;
import com.mxep.model.goods.Module;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.ModuleService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Controller - 按钮
 */
@Controller
@RequestMapping("/module")
public class ModuleController extends WebController {

	@Autowired
	private ModuleService moduleService;


	/**
	 *获取所有按钮
	 * @return
	 */
//	@RequestMapping(value = "/getGoods", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Map<String, Object>> getGoods() {
//		List<Map<String, Object>> list = Lists.newArrayList();
//		Map<String, Object> objTemp = Maps.newHashMap();
//		objTemp.put("id", -1);
//		objTemp.put("text", "请选择");
//		list.add(objTemp);
//		List<Goods> goodsList = goodsService.findAllGoods();
//		for (Goods g : goodsList) {
//			Map<String, Object> obj = Maps.newHashMap();
//			obj.put("id", g.getId());
//			obj.put("text", g.getName());
//			list.add(obj);
//		}
//		return list;
//	}


	/** 按钮列表 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "module/list";
	}


	/** 按钮列表 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public String agreements(Pagination pagination,
							 HttpServletRequest request, Model model,
							 @RequestParam(value = "search_EQ_status" , required = false)Integer[] status){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_flag",1);
		if(null != status && status.length > 0)
		{
			searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
		}
		model.addAttribute("page", moduleService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
		return "module/nested";
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
			model.addObject("result", moduleService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	/** 删除按钮*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		return moduleService.deleteModule(ids);
	}


	/**
	 * 启动服务
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id[]")Integer[] ids){
		return moduleService.enable(ids);
	}

	/**
	 * 禁用服务
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public JsonMap disable(@RequestParam("id[]")Integer[] ids){
		return moduleService.disable(ids);
	}

	/** 新建按钮*/
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "module/edit";
	}

	/** 编辑按钮*/
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id")Integer id, Model model) {
		Module module = moduleService.get(id);
		model.addAttribute("module",module);
		return "module/edit";
	}

	/** 保存按钮*/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonMap save(Module module) {
		moduleService.save(module);
		return new JsonMap(0, "保存成功");
	}



}

