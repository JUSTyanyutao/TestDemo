package com.mxep.web.controller.goods;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.SysConstant;
import com.mxep.model.goods.FilmBrand;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.FilmBrandService;
import com.mxep.web.service.OrderWorkerStatisticsService;
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
 * Controller -  技师结算
 */
@Controller
@RequestMapping("/order/worker/statistics")
public class OrderWorkerStatisticsController extends WebController {

	@Autowired
	private OrderWorkerStatisticsService orderWorkerStatisticsService;
	


	/** 技师结算列表 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "workerStatistics/list";
	}


	/** 技师结算列表 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public String agreements(Pagination pagination,
							 HttpServletRequest request, Model model,
							 @RequestParam(value = "search_EQ_status" , required = false)Integer[] status){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.remove("EQ_status");
//		if(null != status && status.length > 0)
//		{
//			searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
//		}
		model.addAttribute("page", orderWorkerStatisticsService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
		return "workerStatistics/nested";
	}

//	//导出Excel
//	@RequestMapping(value = "/export")
//	public ModelAndView export(HttpServletRequest request,@RequestParam(value = "search_EQ_status" , required = false)Integer[] status) {
//		ModelAndView model = new ModelAndView();
//		try {
//			model = getExcelView(request);
//			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
//			searchParams.remove("EQ_status");
//			if(null != status && status.length > 0)
//			{
//				searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
//			}
//			searchParams.put("EQ_flag",1);
//			model.addObject("result", filmBrandService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

//	/** 删除技师结算*/
//	@ResponseBody
//	@RequestMapping(value = "/delete", method = RequestMethod.POST)
//	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
//		return filmBrandService.deleteBrand(ids);
//	}


//	/**
//	 * 上架技师结算
//	 * @param ids
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/enable", method = RequestMethod.POST)
//	public JsonMap enable(@RequestParam("id[]")Integer[] ids){
//		return filmBrandService.enable(ids);
//	}
//
//	/**
//	 * 下架技师结算
//	 * @param ids
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/disable", method = RequestMethod.POST)
//	public JsonMap disable(@RequestParam("id[]")Integer[] ids){
//		return filmBrandService.disable(ids);
//	}


}

