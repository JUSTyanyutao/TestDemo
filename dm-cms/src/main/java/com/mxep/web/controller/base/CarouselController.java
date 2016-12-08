package com.mxep.web.controller.base;

import com.mxep.model.Constant;
import com.mxep.model.SysConstant;
import com.mxep.model.common.Carousel;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.CarouselService;
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
 * Controller - 轮播图
 */
@Controller
public class CarouselController extends WebController {

	@Autowired
	private CarouselService carouselService;

	/** 轮播图列表 */
	@RequestMapping(value="/carousels", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "carousel/list";
	}

	/** 轮播图列表 */
	@RequestMapping(value = "/carousels", method = RequestMethod.POST)
	public String list(Pagination pagination, HttpServletRequest request, Model model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		if(searchParams.containsKey("EQ_marketId")){
    		if(searchParams.get("EQ_marketId").toString().equals("-1")){
    			searchParams.remove("EQ_marketId");
    		}
    	}
		searchParams.put("EQ_flag", 1);
		model.addAttribute("page", carouselService.list(pagination, searchParams, new Sort(Sort.Direction.ASC, "sort")));
		model.addAttribute("image_url", Constant.IMAGE_DOMAIN);
		return "carousel/nested";
	}

	/** 创建轮播图 */
	@RequestMapping(value = "/carousel", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request,Pagination pagination) {
		model.addAttribute("pagination", pagination);
		model.addAttribute("image_url", SysConstant.IMAGE_DOMAIN);
		model.addAttribute("jsessionid", request.getSession().getId());
//		model.addAttribute("carouselPlatformList", carouselService.getCarouselPlatformList(""));
		return "carousel/edit";
	}

	/** 编辑轮播图 */
	@RequestMapping(value = "/carousel/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model,HttpServletRequest request,Pagination pagination) {
		Carousel carousel = carouselService.findCarousel(id);
//		model.addAttribute("carouselPlatformList", carouselService.getCarouselPlatformList(carousel.getPlatform()+""));
		model.addAttribute("carousel", carousel);
		model.addAttribute("pagination", pagination);
		model.addAttribute("jsessionid", request.getSession().getId());
		model.addAttribute("image_url", SysConstant.IMAGE_DOMAIN);
		return "carousel/edit";
	}
	
	/** 保存轮播图 */
	@ResponseBody
	@RequestMapping(value = "/carousel", method = RequestMethod.POST)
	public JsonMap save(@Valid Carousel carousel, BindingResult result,HttpServletRequest request) {
		JsonMap ret;
		if (result.hasErrors()) {
			ret = parseErrorResult(result, "保存失败");
		} else {
			carousel.setFlag( (byte)1);
			carouselService.save(carousel);
			ret = new JsonMap(0, "保存成功");
		}
		return ret;
	}

	/** 删除轮播图 */
	@ResponseBody
	@RequestMapping(value = "/carousel/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		carouselService.delete(ids);
		return new JsonMap(0, "删除成功");
	}
}
