package com.mxep.web.controller.base;

import com.mxep.model.Constant;
import com.mxep.model.common.Faq;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.FaqService;
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
 * Controller - 商品分类
 */
@Controller
public class FaqController extends WebController {

	@Autowired
	private FaqService faqService;

	/** 积分问题列表 */
	@RequestMapping(value="/faq/pointlist", method = RequestMethod.GET)
	public String pointlist(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		model.addAttribute("parent", faqService.findAllParent());
		return "qa/pointlist";
	}

	/** 积分问题列表 */
	@RequestMapping(value = "/faq/pointlist", method = RequestMethod.POST)
	public String pointlist(Pagination pagination, HttpServletRequest request, Model model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_type", String.valueOf(2));
		searchParams.put("EQ_flag", 1);
		model.addAttribute("page", faqService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "sort")));
		return "qa/pointnested";
	}

	/** 创建积分问题*/
	@RequestMapping(value = "/faq/ponint", method = RequestMethod.GET)
	public String addpoint(Model model,HttpServletRequest request,Pagination pagination) {
//		model.addAttribute("qaParents", faqService.findQAParent());
//		model.addAttribute("pagination", pagination);
		return "qa/pointedit";
	}





	/** 编辑积分问题 */
	@RequestMapping(value = "/faq/ponint/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model,HttpServletRequest request,Pagination pagination) {
		Faq faq = faqService.findFaq(id);
		model.addAttribute("faq", faq);
//		model.addAttribute("image_url", Constant.IMAGE_DOMAIN);
//		model.addAttribute("jsessionid", request.getSession().getId());
//		model.addAttribute("pagination", pagination);
		return "qa/pointedit";
	}

	/** 保存积分问题*/
	@ResponseBody
	@RequestMapping(value = "/faq/ponint/save", method = RequestMethod.POST)
	public JsonMap save(@Valid Faq faq, BindingResult result,HttpServletRequest request) {


		faqService.save(faq);
		return new JsonMap(0,"保存成功");
	}

	/** 删除积分问题 */
	@ResponseBody
	@RequestMapping(value = "/faq/ponint/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		faqService.delete(ids);
		return new JsonMap(0, "删除成功");
	}
	
	
	/** 常见问题列表 */
	@RequestMapping(value="/faq/list", method = RequestMethod.GET)
	public String qalist(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		model.addAttribute("parent", faqService.findAllParent());
		return "qa/list";
	}

	/** 常见问题列表 */
	@RequestMapping(value = "/faq/list", method = RequestMethod.POST)
	public String qalist(Pagination pagination, HttpServletRequest request, Model model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_type", String.valueOf(1));
		searchParams.put("EQ_flag", 1);
		model.addAttribute("page", faqService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "sort")));
		return "qa/nested";
	}

	/** 创建常见问题 */
	@RequestMapping(value = "/faq", method = RequestMethod.GET)
	public String addqa(Model model,HttpServletRequest request,Pagination pagination) {
//		model.addAttribute("qaParents", faqService.findQAParent());
//		model.addAttribute("pagination", pagination);
		return "qa/edit";
	}

	/** 编辑常见问题 */
	@RequestMapping(value = "/faq/{id}", method = RequestMethod.GET)
	public String editqa(@PathVariable("id") Integer id, Model model,HttpServletRequest request,Pagination pagination) {
		Faq faq = faqService.findFaq(id);
		model.addAttribute("faq", faq);
//		model.addAttribute("qaParents", faqService.findQAParent());
//		model.addAttribute("pagination", pagination);
		return "qa/edit";
	}

	/**保存常见问题*/
	@ResponseBody
	@RequestMapping(value = "/faq", method = RequestMethod.POST)
	public JsonMap savefaq(@Valid Faq faq){


		faqService.save(faq);
		return new JsonMap(0,"保存成功");

	}


	/** 删除常见问题 */
	@ResponseBody
	@RequestMapping(value = "/faq/delete", method = RequestMethod.POST)
	public JsonMap deleteqa(@RequestParam("id[]") Integer[] ids) {
		faqService.delete(ids);
		return new JsonMap(0, "删除成功");
	}
	
}
