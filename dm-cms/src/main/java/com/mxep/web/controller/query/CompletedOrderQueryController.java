package com.mxep.web.controller.query;

import com.mxep.service.CommonService;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.web.Servlets;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//import com.mxep.web.service.OrderGoodsService;

/**
 * 
 * @author 订单统计
 * 
 */
@Controller
@RequestMapping(value = "/completeQuery")
public class CompletedOrderQueryController extends WebController {


	@Resource
	private CommonService commonService;
	
	private static String orderName = "createTime";
	private static Direction orderDirection = Sort.Direction.DESC;
	
	/***************************** 订单 ****************************/
	
	/** 订单列表 */
	@RequestMapping(value={"","/list"}, method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		//model.addAttribute("siteStations", siteStationService.findAllSiteStation());
		model.addAttribute("currentDate",commonService.getCurrentTime());
		return "query/completlist";
	}
    
    /** 订单列表 */
    @RequestMapping(value={"","/list"}, method = RequestMethod.POST)
    public String orders(Pagination pagination,
			   					HttpServletRequest request, Model model){
    	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
    	
//    	model.addAttribute("orderGoodsQuerys", orderGoodsService.countOrderGoods(searchParams));
//
		return "query/completnested";
    }
    
   //导出Excel
  	@RequestMapping(value = "/export")
  	public ModelAndView export(HttpServletRequest request) {
  		ModelAndView model = new ModelAndView();
  		try {
  			model = getExcelView(request);
  			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
//  			model.addObject("result", orderGoodsService.countOrderGoods(searchParams));
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return model;

  	}
    
   

}
