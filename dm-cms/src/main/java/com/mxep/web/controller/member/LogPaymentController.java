package com.mxep.web.controller.member;

import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.LogPaymentService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 
 */
@Controller
@RequestMapping("/logPayment")
public class LogPaymentController extends WebController {

    @Resource
    private LogPaymentService logPaymentService;

    /**支付记录列表*/
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String list(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "logPayment/list";
    }

    /** 支付记录列表 */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("page", logPaymentService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "payTime")));
        return "logPayment/nested";
    }

    /** 删除支付记录*/
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
        logPaymentService.delete(ids);
        return new JsonMap(0, "删除成功");
    }

}
