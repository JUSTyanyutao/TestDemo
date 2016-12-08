package com.mxep.web.controller.goods;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxep.model.GlobalConstant;
import com.mxep.model.common.Promotion;
import com.mxep.service.CommonService;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.PromotionService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;

/**
 * Controller - 优惠活动
 */
@RequestMapping("/promotion")
@Controller
public class PromotionController extends WebController {

	@Autowired
	private PromotionService promotionService;
	
    @Autowired
    private CommonService commonService;
    
    /** 优惠活动列表 */
	@RequestMapping(value={"","/list"}, method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "promotion/list";
	}
    
    
    /** 优惠活动列表 */
    @RequestMapping(value={"","/list"},method = RequestMethod.POST)
    public String promotions(Pagination pagination,
			   					HttpServletRequest request, Model model){
    	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
    	
		searchParams.put("NEQ_status", ""+GlobalConstant.Status.Deleted.value);
    	model.addAttribute("page", promotionService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "sort")));
    	//model.addAttribute("image_url", EhFarmConstant.IMAGE_DOMAIN);
		return "promotion/nested";
    }
    
    /** 创建优惠活动 */
	@RequestMapping(value = "/addPromotion", method = RequestMethod.GET)
	public String addPromotion(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "promotion/edit";
	}
	
	/** 编辑优惠活动 */
	@RequestMapping(value = "/editPromotion/{id}", method = RequestMethod.GET)
	public String editPromotion(@PathVariable("id") Integer id, Pagination pagination, Model model) {
		model.addAttribute("promotion", promotionService.findPromotion(id));
		model.addAttribute("pagination", pagination);
		return "promotion/edit";
	}
	
	/** 保存优惠活动 */
	@ResponseBody
	@RequestMapping(value = "/savePromotion", method = RequestMethod.POST)
	public JsonMap save(@Valid Promotion promotion, BindingResult result) {
		JsonMap ret;
		if (result.hasErrors()) {
			ret = parseErrorResult(result, "保存失败");
		} else {
			promotionService.save(promotion);
			ret = new JsonMap(0, "保存成功");
		}
		return ret;
	}

	/**
     * 优惠活动下架
     */
    @ResponseBody
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public JsonMap disable(@RequestParam("id[]") Integer[] ids) {
    	
    	promotionService.updateStatus(ids, GlobalConstant.Status.Disable.value);
    	return new JsonMap(0, "下架成功");
    }

    /**
     * 优惠活动上架
     */
    @ResponseBody
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    public JsonMap enable(@RequestParam("id[]") Integer[] ids) {
    	
    	promotionService.updateStatus(ids, GlobalConstant.Status.Enable.value);
    	return new JsonMap(0, "上架成功");
    }
    
	/** 删除优惠活动 */
	@ResponseBody
	@RequestMapping(value = "/deletepromotion", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		promotionService.delete(ids);
		return new JsonMap(0, "删除成功");
	}
	
}
