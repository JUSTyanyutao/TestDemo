package com.mxep.web.controller.sms;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.SysConstant;
import com.mxep.model.log.SmsTemplate;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.SmsTemplateService;
import com.mxep.web.service.SmsVerifyCodeService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by lq on 2016/9/10.
 *  短信模版
 *
 */
@Controller
@RequestMapping("/sms/template")
public class SmsTemplateController extends WebController {

    @Resource
    private SmsTemplateService smsTemplateService;


    /**
     * 获取所有可用的短信模版
     * @return
     */
    @RequestMapping(value = "/getSmsTemplate", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getCategory() {
        List<Map<String, Object>> list = Lists.newArrayList();
        Map<String, Object> objTemp = Maps.newHashMap();
        objTemp.put("id", -1);
        objTemp.put("text", "请选择模版");
        list.add(objTemp);
        List<SmsTemplate> smsTemplateList = smsTemplateService.findAll();
        for(SmsTemplate a: smsTemplateList)
        {
            Map<String, Object> temp = Maps.newHashMap();
            temp.put("id",a.getId());
            temp.put("text",a.getCode());
            list.add(temp);
        }
        return list;
    }


    /**短信列表*/
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String list(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "sms/template/list";
    }

    /** 短信列表 */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("page", smsTemplateService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
        return "sms/template/nested";
    }

    /** 删除短信*/
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
        smsTemplateService.delete(ids);
        return new JsonMap(0, "删除成功");
    }

    /** 增加短信*/
    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public String edit() {
        return "sms/template/edit";
    }

    /** 增加短信*/
    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id")Integer id, Model model) {
        model.addAttribute("smsTemplate",smsTemplateService.get(id));
        return "sms/template/edit";
    }

    /** 保存短信模版*/
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMap save(SmsTemplate smsTemplate) {
        smsTemplateService.save(smsTemplate);
        return new JsonMap(0, "保存成功");
    }

}
