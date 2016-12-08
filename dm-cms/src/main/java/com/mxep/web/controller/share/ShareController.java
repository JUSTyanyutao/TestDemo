package com.mxep.web.controller.share;

import com.mxep.model.SysConstant;
import com.mxep.model.share.Share;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.ShareService;
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
 * Created by lq on 2016/9/12.
 * 晒单  controller
 */
@Controller
public class ShareController extends WebController {

    @Autowired
    private ShareService shareService;

    /** 晒单列表 */
    @RequestMapping(value="/shares", method = RequestMethod.GET)
    public String list(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "share/list";
    }

    /** 晒单列表 */
    @RequestMapping(value = "/shares", method = RequestMethod.POST)
    public String list(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        if(searchParams.containsKey("EQ_marketId")){
            if(searchParams.get("EQ_marketId").toString().equals("-1")){
                searchParams.remove("EQ_marketId");
            }
        }
        searchParams.put("EQ_flag", 1);
        model.addAttribute("page", shareService.list(pagination, searchParams, new Sort(Sort.Direction.ASC, "sort")));
//        model.addAttribute("image_url", HowEatConstant.IMAGE_DOMAIN);
        return "share/nested";
    }

    /** 晒单 */
    @RequestMapping(value = "/share", method = RequestMethod.GET)
    public String add(Model model,HttpServletRequest request,Pagination pagination) {
        model.addAttribute("pagination", pagination);
        model.addAttribute("image_url", SysConstant.IMAGE_DOMAIN);
        model.addAttribute("jsessionid", request.getSession().getId());
//		model.addAttribute("carouselPlatformList", carouselService.getCarouselPlatformList(""));
        return "share/edit";
    }

    /** 编辑晒单 */
    @RequestMapping(value = "/share/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model,HttpServletRequest request,Pagination pagination) {
        Share share = shareService.findShare(id);
//		model.addAttribute("carouselPlatformList", carouselService.getCarouselPlatformList(carousel.getPlatform()+""));
        model.addAttribute("carousel", share);
        model.addAttribute("pagination", pagination);
        model.addAttribute("jsessionid", request.getSession().getId());
        model.addAttribute("image_url", SysConstant.IMAGE_DOMAIN);
        return "share/edit";
    }

    /** 保存晒单 */
    @ResponseBody
    @RequestMapping(value = "/share", method = RequestMethod.POST)
    public JsonMap save(@Valid Share share, BindingResult result,HttpServletRequest request) {
        JsonMap ret;
        if (result.hasErrors()) {
            ret = parseErrorResult(result, "保存失败");
        } else {
            share.setFlag( (byte)1);
            shareService.save(share);
            ret = new JsonMap(0, "保存成功");
        }
        return ret;
    }

    /** 删除晒单 */
    @ResponseBody
    @RequestMapping(value = "/share/delete", method = RequestMethod.POST)
    public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
        shareService.delete(ids);
        return new JsonMap(0, "删除成功");
    }

    /**
     * 审核操作
     *
     * @param id
     * @param status
     * @param comment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/share/check",method = RequestMethod.POST)
    public JsonMap check(@RequestParam("id") Integer id,
                         @RequestParam("status")byte status,
                         @RequestParam("comment")String comment,
                         @RequestParam("sort")Integer sort) {
        shareService.check(id,status,comment,sort);
        return new JsonMap(0,"审核成功");
    }


}
