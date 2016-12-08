package com.mxep.web.controller.goods;

import com.mxep.model.goods.GoodsComment;
import com.mxep.model.goods.GoodsShow;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.GoodsCommentService;
import com.mxep.web.service.GoodsShowService;
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
 * Controller -  大家说
 */
@Controller
@RequestMapping("/goods/show")
public class GoodsShowController extends WebController {

	@Autowired
	private GoodsShowService goodsShowService;


//	/**
//	 *获取所有 商品 大家说
//	 * @return
//	 */
//	@RequestMapping(value = "/getAllWorker", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Map<String, Object>> getGoods() {
//		List<Map<String, Object>> list = Lists.newArrayList();
//		Map<String, Object> objTemp = Maps.newHashMap();
//		objTemp.put("id", -1);
//		objTemp.put("text", "请选择");
//		list.add(objTemp);
//		List<Worker> workerList = workerService.findAllWorker();
//		for (Worker g : workerList) {
//			Map<String, Object> obj = Maps.newHashMap();
//			obj.put("id", g.getId());
//			obj.put("text", g.getMember().getMobile());
//			list.add(obj);
//		}
//		return list;
//	}

	
	/**  商品 大家说列表 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "goodsShow/list";
	}


	/** 商品 大家说 列表 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public String list(Pagination pagination,
							 HttpServletRequest request, Model model,
					   @RequestParam(value = "search_EQ_status" , required = false)Integer[] status){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_flag",1);
		searchParams.remove("EQ_status");
		if(null != status && status.length > 0)
		{
			searchParams.put("IN_status", org.apache.commons.lang3.StringUtils.join(status, ","));
		}
		model.addAttribute("page", goodsShowService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
		return "goodsShow/nested";
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
			model.addObject("result", goodsShowService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	/** 删除 大家说*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		return goodsShowService.deleteGoodsShow(ids);
	}



	/** 编辑 商品大家说*/
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editShop(@PathVariable("id")Integer id, Model model) {
		GoodsShow goodsShow = goodsShowService.get(id);
		model.addAttribute("goodsShow",goodsShow);
		return "goodsShow/edit";
	}


	/**
	 * 审核  商品 大家说
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id")Integer[] id,
						  @RequestParam("status")byte status){
		return goodsShowService.audit(id,status);
	}


	/**
	 *  置顶 操作
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/recommend", method = RequestMethod.POST)
	public JsonMap recommend(@RequestParam("id[]")Integer[] id){
		return goodsShowService.recommend(id);
	}

	/**
	 *  取消 置顶 操作
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/disRecommend", method = RequestMethod.POST)
	public JsonMap disRecommend(@RequestParam("id[]")Integer[] id){
		return goodsShowService.disRecommend(id);
	}


}

