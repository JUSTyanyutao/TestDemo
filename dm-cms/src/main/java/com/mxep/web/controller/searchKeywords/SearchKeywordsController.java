package com.mxep.web.controller.searchKeywords;

import com.mxep.model.SysConstant;
import com.mxep.model.search.SearchKeywords;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.SearchKeywordsService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by lq on 2016/9/8.
 *
 * controller    搜索关键字
 */
@Controller
public class SearchKeywordsController extends WebController {

    @Resource
    private SearchKeywordsService searchKeywordsService;

    /*搜索关键字列表*/
    @RequestMapping(value="/searchKeywords/list", method = RequestMethod.GET)
    public String list(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "searchKeywords/list";
    }

    /** 搜索关键字列表 */
    @RequestMapping(value = "/searchKeywords/list", method = RequestMethod.POST)
    public String list(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        if(searchParams.containsKey("EQ_marketId")){
            if(searchParams.get("EQ_marketId").toString().equals("-1")){
                searchParams.remove("EQ_marketId");
            }
        }
        searchParams.put("EQ_flag", 1);
        model.addAttribute("page", searchKeywordsService.list(pagination, searchParams, new Sort(Sort.Direction.ASC, "sort")));
        model.addAttribute("image_url", SysConstant.IMAGE_DOMAIN);
        return "searchKeywords/nested";
    }

    /** 创建关键字 */
    @RequestMapping(value = "/searchKeywords", method = RequestMethod.GET)
    public String add(Model model,HttpServletRequest request,Pagination pagination) {
        model.addAttribute("pagination", pagination);
        model.addAttribute("image_url", SysConstant.IMAGE_DOMAIN);
        model.addAttribute("jsessionid", request.getSession().getId());
//		model.addAttribute("carouselPlatformList", carouselService.getCarouselPlatformList(""));
        return "searchKeywords/edit";
    }

    /** 编辑关键字*/
    @RequestMapping(value = "/searchKeywords/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model,HttpServletRequest request,Pagination pagination) {
        SearchKeywords searchKeywords = searchKeywordsService.findSearchKeywords(id);
//		model.addAttribute("carouselPlatformList", carouselService.getCarouselPlatformList(carousel.getPlatform()+""));
        model.addAttribute("searchKeywords", searchKeywords);
        model.addAttribute("pagination", pagination);
        model.addAttribute("jsessionid", request.getSession().getId());
        model.addAttribute("image_url", SysConstant.IMAGE_DOMAIN);
        return "searchKeywords/edit";
    }

    /** 保存关键字 */
    @ResponseBody
    @RequestMapping(value = "/searchKeywords", method = RequestMethod.POST)
    public JsonMap save(@Valid SearchKeywords searchKeywords, BindingResult result,HttpServletRequest request) {
        JsonMap ret;
        if (result.hasErrors()) {
            ret = parseErrorResult(result, "保存失败");
        } else {
            searchKeywords.setFlag((byte) 1);
            searchKeywordsService.save(searchKeywords);
            ret = new JsonMap(0, "保存成功");
        }
        return ret;
    }

    /** 删除关键字*/
    @ResponseBody
    @RequestMapping(value = "/searchKeywords/delete", method = RequestMethod.POST)
    public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
        searchKeywordsService.delete(ids);
        return new JsonMap(0, "删除成功");
    }
}
