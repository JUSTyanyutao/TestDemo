package com.mxep.web.controller.member;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.SysConstant;
import com.mxep.model.member.Member;
import com.mxep.model.member.Shop;
import com.mxep.model.member.Worker;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.ShopService;
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
 * Controller - 技师
 */
@Controller
@RequestMapping("/worker")
public class WorkerController extends WebController {

	@Autowired
	private WorkerService workerService;


	/**
	 *获取所有 技师
	 * @return
	 */
	@RequestMapping(value = "/getAllWorker", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getGoods() {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> objTemp = Maps.newHashMap();
		objTemp.put("id", -1);
		objTemp.put("text", "请选择");
		list.add(objTemp);
		List<Worker> workerList = workerService.findAllWorker();
		for (Worker g : workerList) {
			Map<String, Object> obj = Maps.newHashMap();
			obj.put("id", g.getId());
			obj.put("text", g.getMember().getMobile()+g.getName());
			list.add(obj);
		}
		return list;
	}

	
	/** 技师列表 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "worker/list";
	}


	/** 技师列表 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public String list(Pagination pagination,
							 HttpServletRequest request, Model model,
					   @RequestParam(value = "search_EQ_applyStatus" , required = false)Integer[] status){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_flag",1);
		searchParams.remove("EQ_applyStatus");
		if(null != status && status.length > 0)
		{
			searchParams.put("IN_applyStatus", org.apache.commons.lang3.StringUtils.join(status, ","));
		}
		model.addAttribute("page", workerService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
		return "worker/nested";
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
			model.addObject("result", workerService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	/** 删除技师*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		return workerService.deleteWorker(ids);
	}


	/** 新建技师*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String editShop( Model model) {
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "worker/add";
	}

	/** 编辑技师*/
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editShop(@PathVariable("id")Integer id, Model model) {
		Worker worker = workerService.get(id);
		model.addAttribute("worker",worker);
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "worker/edit";
	}


	/**
	 * 审核技师
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public JsonMap enable(@RequestParam("id")Integer id,
						  @RequestParam("status")byte status,
						  @RequestParam("remark")String remark,
						  @RequestParam("categoryId")String categoryId
	){
		return workerService.audit(id,status,remark,categoryId);
	}



	/** 保存技师*/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonMap save(Worker worker) {
		workerService.save(worker);
		return new JsonMap(0, "保存成功");
	}

	/** 新加 技师*/
	@ResponseBody
	@RequestMapping(value = "/saveWorker", method = RequestMethod.POST)
	public JsonMap saveWorker(Worker worker,Member member) {
		workerService.saveWorker(worker,member);
		return new JsonMap(0, "保存成功");
	}



}

