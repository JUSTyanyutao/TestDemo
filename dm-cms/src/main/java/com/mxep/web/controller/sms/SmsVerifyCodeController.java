package com.mxep.web.controller.sms;

import com.mxep.model.SysConstant;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.SmsVerifyCodeService;
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
@RequestMapping("/sms/verify/code")
public class SmsVerifyCodeController extends WebController {

    @Resource
    private SmsVerifyCodeService smsVerifyCodeService;

    /*短信列表*/
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String list(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "sms/verifyCode/list";
    }

    /** 短信列表 */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("page", smsVerifyCodeService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
        return "sms/verifyCode/nested";
    }

    /** 删除短信*/
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
        smsVerifyCodeService.delete(ids);
        return new JsonMap(0, "删除成功");
    }

}
