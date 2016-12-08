package com.mxep.web.controller.base;

import com.mxep.model.SysConstant;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.SignInService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by lq on 2016/9/13.
 * controller 签到
 */

@Controller
public class SignInController extends WebController {

    @Resource
    private SignInService signInService;

    /**
     * 签到列表
     * @param pagination
     * @param model
     * @return
     */
    @RequestMapping(value="/signIn/list", method = RequestMethod.GET)
    public String list(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "signIn/list";
    }

    /**
     * 签到列表
     * @param pagination
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/signIn/list", method = RequestMethod.POST)
    public String list(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
//        if(searchParams.containsKey("EQ_marketId")){
//            if(searchParams.get("EQ_marketId").toString().equals("-1")){
//                searchParams.remove("EQ_marketId");
//            }
//        }
        searchParams.put("EQ_flag", 1);
        model.addAttribute("page", signInService.list(pagination, searchParams, new Sort(Sort.Direction.ASC, "createTime")));
//        model.addAttribute("image_url", SysConstant.IMAGE_DOMAIN);
        return "signIn/nested";
    }


    /**
     * 删除 签到
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/signIn/delete", method = RequestMethod.POST)
    public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
        signInService.delete(ids);
        return new JsonMap(0, "删除成功");
    }
}
