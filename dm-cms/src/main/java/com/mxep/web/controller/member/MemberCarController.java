package com.mxep.web.controller.member;

import com.mxep.model.member.MemberCar;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.MemberCarService;
import com.mxep.web.web.Servlets;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
@Controller
@RequestMapping("/member/car/")
public class MemberCarController extends WebController {

    @Resource
    private MemberCarService memberCarService;

    /**
     * 车 列表
     * @param pagination
     * @param model
     * @return
     */
    @RequestMapping(value="/list/{id}", method = RequestMethod.GET)
    public String list(@PathVariable("id")Integer id, Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        model.addAttribute("id",id);
        return "member/car/list";
    }

    /**
     * 车 列表
     * @param pagination
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/list/{id}", method = RequestMethod.POST)
    public String list(Pagination pagination, HttpServletRequest request, Model model,
                       @PathVariable("id")Integer id) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_memberId",id);
        model.addAttribute("page", memberCarService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
        return "member/car/nested";
    }




}
