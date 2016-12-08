package com.mxep.web.controller.member;

import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.MemberAddressService;
import com.mxep.web.service.ShoppingCarService;
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
 * Controller - 会员管理
 */
@RequestMapping("/member")
@Controller
public class MemberShoppingCarsController extends WebController {

    @Resource
    private ShoppingCarService shoppingCarService;

    /**
     * 购物车列表
     */
    @RequestMapping(value = "/shoppingCar", method = RequestMethod.GET)
    public String list(Pagination pagination, Integer uid, Model model) {
        model.addAttribute("pagination", pagination);
        model.addAttribute("uid", uid);
        return "member/shopping/list";
    }

    /**
     * 购物车列表
     */
    @RequestMapping(value = "/shoppingCar", method = RequestMethod.POST)
    public String members(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        model.addAttribute("page", shoppingCarService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
        return "member/shopping/nested";
    }

    /** 删除购物车中商品*/
    @ResponseBody
    @RequestMapping(value = "/shopping/delete", method = RequestMethod.POST)
    public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
        shoppingCarService.delete(ids);
        return new JsonMap(0, "删除成功");
    }


}
