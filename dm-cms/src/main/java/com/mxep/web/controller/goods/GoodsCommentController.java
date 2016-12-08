package com.mxep.web.controller.goods;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.SysConstant;
import com.mxep.model.goods.GoodsComment;
import com.mxep.model.member.Worker;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.GoodsCommentService;
import com.mxep.web.service.WorkerService;
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
 * Controller -  商品 评价
 */
@Controller
@RequestMapping("/goods/comment")
public class GoodsCommentController extends WebController {

	@Autowired
	private GoodsCommentService goodsCommentService;


//	/**
//	 *获取所有 商品 评论
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

	
	/**  商品 评论列表 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "goodsComment/list";
	}


	/** 商品 评论 列表 */
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
		model.addAttribute("page", goodsCommentService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
		return "goodsComment/nested";
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
			model.addObject("result", goodsCommentService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	/** 删除技师*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		return goodsCommentService.deleteGoodsComment(ids);
	}



	/** 编辑 商品评论*/
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editShop(@PathVariable("id")Integer id, Model model) {
		GoodsComment goodsComment = goodsCommentService.get(id);
		model.addAttribute("goodsComment",goodsComment);
		return "goodsComment/edit";
	}


	/**
	 * 审核  商品 评论
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id")Integer[] id,
						  @RequestParam("status")byte status){
		return goodsCommentService.audit(id,status);
	}


	/**
	 *  置顶 操作
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/recommend", method = RequestMethod.POST)
	public JsonMap recommend(@RequestParam("id[]")Integer[] id){
		return goodsCommentService.recommend(id);
	}

	/**
	 *  取消 置顶 操作
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/disRecommend", method = RequestMethod.POST)
	public JsonMap disRecommend(@RequestParam("id[]")Integer[] id){
		return goodsCommentService.disRecommend(id);
	}






}

