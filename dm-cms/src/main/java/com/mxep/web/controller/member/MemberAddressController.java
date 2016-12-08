package com.mxep.web.controller.member;

import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.MemberAddressService;
import com.mxep.web.web.Servlets;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Controller - 会员管理
 */
@RequestMapping("/member")
@Controller
public class MemberAddressController extends WebController {

    @Resource
    private MemberAddressService memberAddressService;

    /**
     * 会员列表
     */
    @RequestMapping(value = "/addresses", method = RequestMethod.GET)
    public String list(Pagination pagination, Integer uid, Model model) {
        model.addAttribute("pagination", pagination);
        model.addAttribute("uid", uid);
        return "member/address/list";
    }

    /**
     * 会员列表
     */
    @RequestMapping(value = "/addresses", method = RequestMethod.POST)
    public String members(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        model.addAttribute("page", memberAddressService.list(
                pagination, searchParams,
                new Sort(Sort.Direction.DESC, "id")));
        return "member/address/nested";
    }


}
