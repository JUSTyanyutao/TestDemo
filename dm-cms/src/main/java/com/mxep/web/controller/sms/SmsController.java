package com.mxep.web.controller.sms;

import com.mxep.model.log.Sms;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.SmsService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by lq on 2016/9/10.
 */
@Controller
@RequestMapping("/sms")
public class SmsController extends WebController {

    @Resource
    private SmsService smsService;

    /**短信列表*/
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String list(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "sms/list";
    }

    /** 短信列表 */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("page", smsService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
        return "sms/nested";
    }

    /** 删除短信*/
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
        smsService.delete(ids);
        return new JsonMap(0, "删除成功");
    }

    /** 增加短信*/
    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public String edit() {
        return "sms/edit";
    }

    /** 增加短信*/
    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id")Integer id, Model model) {
        model.addAttribute("sms",smsService.get(id));
        return "sms/edit";
    }

    /** 保存短信*/
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMap save(Sms sms) {
        smsService.save(sms);
        return new JsonMap(0, "保存成功");
    }

}
