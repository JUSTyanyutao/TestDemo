package com.mxep.web.controller.system;

import com.mxep.model.sys.Param;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.ParamService;
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
import java.util.Map;

/**
 * Controller - 系统参数
 */
@Controller
public class ParamController extends WebController {

	@Autowired
	private ParamService paramService;
	

	/** 系统参数列表 */
	@RequestMapping(value="/param/list", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "param/list";
	}

	/** 系统参数列表 */
	@RequestMapping(value = "/param/list", method = RequestMethod.POST)
	public String list(Pagination pagination, HttpServletRequest request, Model model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		model.addAttribute("page", paramService.list(pagination, searchParams, new Sort(Sort.Direction.ASC, "id")));
		return "param/nested";
	}


	/**删除参数*/
	@ResponseBody
	@RequestMapping(value = "param/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids){
		paramService.delete(ids);
		return new JsonMap(0, "删除成功");
	}



	/**新建参数*/
	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public String edit() {
		return "param/edit";
	}



	/** 编辑系统参数 */
	@RequestMapping(value = "/param/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model,Pagination pagination) {
		Param params = paramService.findParam(id);
		model.addAttribute("params", params);
		model.addAttribute("pagination", pagination);
		return "param/edit";
	}
	
	/** 保存系统参数 */
	@ResponseBody
	@RequestMapping(value = "/param/save", method = RequestMethod.POST)
	public JsonMap save(@Valid Param param, BindingResult result,HttpServletRequest request) {
		JsonMap ret;
		if (result.hasErrors()) {
			ret = parseErrorResult(result, "保存失败");
		} else {
			paramService.save(param);
			ret = new JsonMap(0, "保存成功");
		}
		return ret;
	}

}
