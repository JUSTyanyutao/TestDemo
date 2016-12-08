package com.mxep.web.controller.goods;

import com.mxep.model.SysConstant;
import com.mxep.model.goods.CityFreightPrice;
import com.mxep.model.goods.FilmBrand;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.CityFreightPriceService;
import com.mxep.web.service.FilmBrandService;
import com.mxep.web.utils.ExcelReader;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Controller - 城市运费
 */
@Controller
@RequestMapping("/city/freight/price")
public class CityFreightPriceController extends WebController {

	@Autowired
	private CityFreightPriceService cityFreightPriceService;

	
	/** 城市运费列表 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "cityFreightPrice/list";
	}


	/** 城市运费列表 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public String agreements(Pagination pagination,
							 HttpServletRequest request, Model model){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		model.addAttribute("page", cityFreightPriceService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
		return "cityFreightPrice/nested";
	}

	/** 删除城市运费*/
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		return cityFreightPriceService.deleteCityFreightPrice(ids);
	}

	/** 新建城市运费*/
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "cityFreightPrice/edit";
	}

	/** 导入城市运费*/
	@RequestMapping(value = "/import", method = RequestMethod.GET)
	public String importDate(Model model) {
		return "cityFreightPrice/batchEdit";
	}

	/** 批量导入数据 */
	@ResponseBody
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public List importDate(HttpServletRequest request) throws IOException {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
		MultipartFile file = multiRequest.getFile("file");
		ExcelReader ex = new ExcelReader();
		List list= ex.read(file.getInputStream());
		return list;
	}

	/**
	 *  批量增加城市运费
	 * @return
	 */
	@RequestMapping(value = "/adds", method = RequestMethod.POST)
	@ResponseBody
	public JsonMap addMembers(@RequestParam("provinces")String[] provinces,
							  @RequestParam("cities") String[] cities,
							  @RequestParam("prices") String[] prices) {
		return cityFreightPriceService.addCityFreightPrices(provinces,cities,prices);
	}


	/** 编辑城市运费*/
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id")Integer id, Model model) {
		CityFreightPrice cityFreightPrice = cityFreightPriceService.get(id);
		model.addAttribute("cityFreightPrice",cityFreightPrice);
		model.addAttribute("url", SysConstant.IMAGE_DOMAIN);
		return "cityFreightPrice/edit";
	}

	/** 保存城市运费*/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonMap save(CityFreightPrice cityFreightPrice) {
		cityFreightPriceService.save(cityFreightPrice);
		return new JsonMap(0, "保存成功");
	}



}

