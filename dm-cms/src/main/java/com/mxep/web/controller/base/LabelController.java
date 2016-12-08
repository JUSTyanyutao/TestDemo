package com.mxep.web.controller.base;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.base.Label;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.LabelService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Controller - 标签
 */
@Controller
public class LabelController extends WebController {

	@Autowired
	private LabelService labelService;

	/**
	 *获取所有 类型的饿 label
	 * @return
	 */
	@RequestMapping(value = "/getLabel/{type}", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getGoods(@PathVariable Integer type) {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择");
		list.add(objTemp);
		List<Label> labelList = labelService.getLabelListByType(type);
		for (Label a : labelList) {
			Map<String, Object> obj = Maps.newHashMap();
			obj.put("id", a.getValue());
			obj.put("text", a.getValue());
			list.add(obj);
		}
		return list;
	}





	/** 标签列表 */
	@RequestMapping(value="/labels", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "label/list";
	}

	/** 标签列表 */
	@RequestMapping(value = "/labels", method = RequestMethod.POST)
	public String list(Pagination pagination, HttpServletRequest request, Model model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_flag",1);
		model.addAttribute("page", labelService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "id")));
		return "label/nested";
	}

	/** 创建标签 */
	@RequestMapping(value = "/label", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request,Pagination pagination) {
		model.addAttribute("pagination", pagination);
		return "label/edit";
	}

	/** 编辑标签 */
	@RequestMapping(value = "/label/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model,HttpServletRequest request,Pagination pagination) {
		Label label = labelService.findLabel(id);
		model.addAttribute("label", label);
		model.addAttribute("pagination", pagination);
		return "label/edit";
	}
	
	/** 保存标签 */
	@ResponseBody
	@RequestMapping(value = "/label", method = RequestMethod.POST)
	public JsonMap save(@Valid Label label, BindingResult result,HttpServletRequest request) {
		JsonMap ret;
		if (result.hasErrors()) {
			ret = parseErrorResult(result, "保存失败");
		} else {
			labelService.save(label);
			ret = new JsonMap(0, "保存成功");
		}
		return ret;
	}

	/** 删除标签 */
	@ResponseBody
	@RequestMapping(value = "/label/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		labelService.delete(ids);
		return new JsonMap(0, "删除成功");
	}
}
