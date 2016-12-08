package com.mxep.web.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mxep.service.CommonService;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.MemberRechargeService;
import com.mxep.web.web.Servlets;

/**
 * Controller - 充值记录管理
 */
@Controller
public class MemberRechargeController extends WebController {

    @Autowired
    private MemberRechargeService memberRechargeService;

    @Autowired
    private CommonService commonService;
    
    private static String orderName = "id";
	private static Direction orderDirection = Sort.Direction.DESC;
    
    /** 充值记录列表 */
	@RequestMapping(value={"/recharges"}, method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "recharge/list";
	}
    
    /** 充值记录列表 */
    @RequestMapping(value={"/recharges"},method = RequestMethod.POST)
    public String memberPrepaids(Pagination pagination,HttpServletRequest request, Model model){
    	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
    	model.addAttribute("page", memberRechargeService.list(pagination, searchParams, new Sort(orderDirection, orderName)));
		return "recharge/nested";
    }
    
	/** 查看充值记录 */
	@RequestMapping(value = "/recharge/{id}", method = RequestMethod.GET)
	public String editMemberPrepaid(@PathVariable("id") Integer id, Pagination pagination, Model model) {
		model.addAttribute("recharge", memberRechargeService.findRecharge(id));
		model.addAttribute("pagination", pagination);
		return "recharge/view";
	}
	
	//导出Excel
  	@RequestMapping(value = "/recharge/export")
  	public ModelAndView export(HttpServletRequest request) {
  		ModelAndView model = new ModelAndView();
  		try {
  			model = getExcelView(request);
  			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
  			model.addObject("result",memberRechargeService.list( searchParams, new Sort(orderDirection, orderName)));
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return model;
  	}
}
