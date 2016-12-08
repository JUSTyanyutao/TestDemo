package com.mxep.web.controller.member;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mxep.model.common.City;
import com.mxep.model.common.Coupon;
import com.mxep.model.enums.EnumMemberStatus;
import com.mxep.model.enums.EnumPlatform;
import com.mxep.model.enums.EnumPlatformType;
import com.mxep.model.member.Member;
import com.mxep.model.member.Shop;
import com.mxep.service.CommonService;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.CityService;
import com.mxep.web.service.MemberCouponService;
import com.mxep.web.service.MemberService;
import com.mxep.web.service.ShopService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller - 会员管理
 */
@RequestMapping("/member")
@Controller
public class MemberController extends WebController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private MemberCouponService memberCouponService;

    private static String orderName = "registerTime";
    private static Direction orderDirection = Sort.Direction.DESC;


    /**
     *  根据类型获取所有 会员
     * @return
     */
    @RequestMapping(value = "/getMemberByRole/{role}", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getMemberByRole(@PathVariable("role")byte role) {
        List<Map<String, Object>> list = Lists.newArrayList();
        Map<String, Object> objTemp = Maps.newHashMap();
        objTemp.put("id", -1);
        objTemp.put("text", "请选择");
        list.add(objTemp);
        List<Member> goodsList = memberService.getMemberByRole(role);
        for (Member g : goodsList) {
            Map<String, Object> obj = Maps.newHashMap();
            obj.put("id", g.getId());
            obj.put("text", g.getMobile()+"("+g.getProfile().getNickname()+")");
            list.add(obj);
        }
        return list;
    }

    /**
     *  获取所有 会员
     * @return
     */
    @RequestMapping(value = "/getMember", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getMember() {
        List<Map<String, Object>> list = Lists.newArrayList();
        Map<String, Object> objTemp = Maps.newHashMap();
        objTemp.put("id", -1);
        objTemp.put("text", "请选择");
        list.add(objTemp);
        List<Member> goodsList = memberService.findAllEffectiveMembers();
        for (Member g : goodsList) {
            Map<String, Object> obj = Maps.newHashMap();
            obj.put("id", g.getId());
            obj.put("text", g.getMobile());
//            obj.put("text", g.getMobile()+"("+ g.getProfile() == null ?"":g.getProfile().getNickname()+")");
            list.add(obj);
        }
        return list;
    }


    /**
     *  普通 会员列表
     */
    @RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
    public String list(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "member/list";
    }

    /**
     *   普通 会员列表
     */
    @RequestMapping(value = {"", "/list"}, method = RequestMethod.POST)
    public String members(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        searchParams.put("EQ_role",1);
        model.addAttribute("page", memberService.queryAsPage(pagination, searchParams, new Sort(orderDirection, orderName)));
        return "member/nested";
    }

    /**
     *   区域 经理 列表
     */
    @RequestMapping(value =  "/manager/list", method = RequestMethod.GET)
    public String managerList(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "member/manager/list";
    }

    /**
     * 区域 经理 列表
     */
    @RequestMapping(value =  "/manager/list", method = RequestMethod.POST)
    public String managerList(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        searchParams.put("EQ_role",4);
        model.addAttribute("page", memberService.queryAsPage(pagination, searchParams, new Sort(orderDirection, orderName)));
        return "member/manager/nested";
    }

    /**
     * 创建 区域经理
     */
    @RequestMapping(value = "/editManagerMember", method = RequestMethod.GET)
    public String editManagerMember(Pagination pagination, Model model) {
        List<Map<String, Object>> sourceList = new ArrayList<Map<String, Object>>();
        for (EnumPlatformType temp : EnumPlatformType.values()) {
            Map<String, Object> sourceMap = new HashMap<String, Object>();
            sourceMap.put("key", temp.value);
            sourceMap.put("value", temp.name);
            sourceList.add(sourceMap);
        }
        model.addAttribute("sourceMap", sourceList);
        model.addAttribute("pagination", pagination);
        return "member/manager/edit";
    }


    /**
     * 编辑 区域经理
     */
    @RequestMapping(value = "/editManagerMember/{id}", method = RequestMethod.GET)
    public String editManagerMember(@PathVariable("id") Integer id,
                             Pagination pagination, Model model) {
        Member member = memberService.findMember(id);
        model.addAttribute("member", member);
        model.addAttribute("cityList",memberService.getCityList(member));
//        List<Map<String, Object>> sourceList = new ArrayList<Map<String, Object>>();
//        for (EnumPlatform temp : EnumPlatform.values()) {
//            Map<String, Object> sourceMap = new HashMap<String, Object>();
//            sourceMap.put("key", temp.getValue());
//            sourceMap.put("value", temp.getName());
//            sourceList.add(sourceMap);
//        }
//        model.addAttribute("sourceMap", sourceList);
        model.addAttribute("pagination", pagination);
        return "member/manager/edit";
    }

    /**
     * 保存 区域 经理
     */
    @ResponseBody
    @RequestMapping(value = "/saveManagerMember", method = RequestMethod.POST)
    public JsonMap saveManagerMember(@Valid Member member, BindingResult result,HttpServletRequest request) {
        JsonMap ret;
        String[] city = request.getParameterValues("city");
        if (result.hasErrors()) {
            ret = parseErrorResult(result, "保存失败");
        } else {
            List<City> cityList = new ArrayList<>();
            if(city!=null) {
                for (String s : city) {
                    cityList.add(cityService.get(Integer.parseInt(s)));
                }
            }
            member.setCityList(cityList);
            memberService.save(member);
            ret = new JsonMap(0, "保存成功");
        }
        return ret;
    }


    /**
     *   企业用户  4s店 列表
     */
    @RequestMapping(value =  "/shoper/list", method = RequestMethod.GET)
    public String shoperList(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "member/shoper/list";
    }

    /**
     * 企业用户 列表
     */
    @RequestMapping(value =  "/shoper/list", method = RequestMethod.POST)
    public String shoperList(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        searchParams.put("EQ_role",2);
        model.addAttribute("page", memberService.queryAsPage(pagination, searchParams, new Sort(orderDirection, orderName)));
        return "member/shoper/nested";
    }

    /**
     * 创建 企业用户
     */
    @RequestMapping(value = "/editShoperMember", method = RequestMethod.GET)
    public String editShoperMember(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "member/shoper/edit";
    }


    /**
     * 编辑 企业用户
     */
    @RequestMapping(value = "/editShoperMember/{id}", method = RequestMethod.GET)
    public String editShoperMember(@PathVariable("id") Integer id,
                                    Pagination pagination, Model model) {
        Member member = memberService.findMember(id);
        model.addAttribute("member", member);
        model.addAttribute("shopList",memberService.getShopList(member));
        model.addAttribute("pagination", pagination);
        return "member/shoper/edit";
    }

    /**
     * 保存 企业 用户
     */
    @ResponseBody
    @RequestMapping(value = "/saveShoperMember", method = RequestMethod.POST)
    public JsonMap saveShoperMember(@Valid Member member, BindingResult result,HttpServletRequest request) {
        JsonMap ret;
        String[] shops = request.getParameterValues("shop");
        if (result.hasErrors()) {
            ret = parseErrorResult(result, "保存失败");
        } else {
            List<Shop> shopList = new ArrayList<>();
            if(shops!=null) {
                for (String s : shops) {
                    shopList.add(shopService.get(Integer.parseInt(s)));
                }
            }
            member.setShopList(shopList);
            memberService.save(member);
            ret = new JsonMap(0, "保存成功");
        }
        return ret;
    }


    /**
     * 转变为 区域 经理
     */
    @ResponseBody
    @RequestMapping(value = "/changeManager", method = RequestMethod.POST)
    public JsonMap changeManager(@RequestParam("managerIds")Integer[] ids,@RequestParam("city")Integer[] city) {
        JsonMap ret;
        memberService.changeManager(city,ids);
        ret = new JsonMap(0, "保存成功");
        return ret;
    }

    /**
     * 转变为 区域 经理
     */
    @ResponseBody
    @RequestMapping(value = "/changeShoper", method = RequestMethod.POST)
    public JsonMap changeShop(@RequestParam("shoperIds")Integer[] ids,@RequestParam("shop")Integer[] shop) {
        JsonMap ret;
        memberService.changeShoper(shop,ids);
        ret = new JsonMap(0, "保存成功");
        return ret;
    }


    // 导出Excel
    @RequestMapping(value = "/export")
    public ModelAndView export(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        try {
            model = getExcelView(request);
            Map<String, Object> searchParams = Servlets
                    .getParametersStartingWith(request, "search_");
            model.addObject("result", memberService.list(searchParams,
                    new Sort(orderDirection, orderName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    /**
     * 创建会员
     */
    @RequestMapping(value = "/addMember", method = RequestMethod.GET)
    public String addMember(Pagination pagination, Model model) {
        List<Map<String, Object>> sourceList = new ArrayList<Map<String, Object>>();
        for (EnumPlatformType temp : EnumPlatformType.values()) {
            Map<String, Object> sourceMap = new HashMap<String, Object>();
            sourceMap.put("key", temp.value);
            sourceMap.put("value", temp.name);
            sourceList.add(sourceMap);
        }
        model.addAttribute("sourceMap", sourceList);
        model.addAttribute("pagination", pagination);
        return "member/edit";
    }

    /**
     * 编辑会员
     */
    @RequestMapping(value = "/editMember/{id}", method = RequestMethod.GET)
    public String editMember(@PathVariable("id") Integer id,
                             Pagination pagination, Model model) {
        model.addAttribute("member", memberService.findMember(id));
        List<Map<String, Object>> sourceList = new ArrayList<Map<String, Object>>();
        for (EnumPlatform temp : EnumPlatform.values()) {
            Map<String, Object> sourceMap = new HashMap<String, Object>();
            sourceMap.put("key", temp.getValue());
            sourceMap.put("value", temp.getName());
            sourceList.add(sourceMap);
        }
        model.addAttribute("sourceMap", sourceList);
        model.addAttribute("pagination", pagination);
        return "member/edit";
    }

    /**
     * 保存会员
     */
    @ResponseBody
    @RequestMapping(value = "/saveMember", method = RequestMethod.POST)
    public JsonMap save(@Valid Member member, BindingResult result) {
        JsonMap ret;
        if (result.hasErrors()) {
            ret = parseErrorResult(result, "保存失败");
        } else {
            memberService.save(member);
            ret = new JsonMap(0, "保存成功");
        }
        return ret;
    }

    /**
     * 删除会员
     */
    @ResponseBody
    @RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
    public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
        memberService.delete(ids);
        return new JsonMap(0, "删除成功");
    }

    /**
     * 会员禁用
     */
    @ResponseBody
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public JsonMap disable(@RequestParam("id[]") Integer[] ids) {
        memberService.updateStatus(ids, EnumMemberStatus.Disable.getValue());
        return new JsonMap(0, "禁用成功");
    }

    /**
     * 会员解禁
     */
    @ResponseBody
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    public JsonMap enable(@RequestParam("id[]") Integer[] ids) {
        memberService.updateStatus(ids, EnumMemberStatus.Enable.getValue());
        return new JsonMap(0, "解禁成功");
    }

    /**
     * 会员优惠券列表
     */
    @RequestMapping(value = "/coupon/{memberId}", method = RequestMethod.GET)
    public String couponList(Pagination pagination, Model model,
                             @PathVariable("memberId") Integer memberId) {
        model.addAttribute("pagination", pagination);
        model.addAttribute("memberId", memberId);
        return "coupon/list";
    }

    /**
     * 会员优惠券列表
     */
    @RequestMapping(value = "/coupon/{memberId}", method = RequestMethod.POST)
    public String couponLists(Pagination pagination,
                              HttpServletRequest request, Model model,
                              @PathVariable("memberId") Integer memberId) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(
                request, "search_");
        searchParams.put("EQ_member.id", memberId);
        searchParams.put("EQ_flag", 1);
        model.addAttribute("memberId", memberId);
        model.addAttribute("page", memberCouponService.list(pagination,
                searchParams, new Sort(Sort.Direction.DESC, "id")));
        return "coupon/nested";
    }

    /**
     * 创建优惠券
     */
    @RequestMapping(value = "/coupon/add/{memberId}", method = RequestMethod.GET)
    public String add(Model model, HttpServletRequest request,
                      Pagination pagination, @PathVariable("memberId") Integer memberId) {
        model.addAttribute("pagination", pagination);
        if (memberId != null) {
            Member member = memberService.findMember(memberId);
            if (member != null) {
                model.addAttribute("mobile", member.getMobile());
            }
        }
        model.addAttribute("memberId", memberId);
        return "coupon/edit";
    }

    /**
     * 编辑优惠券
     */
    @RequestMapping(value = "/coupon/edit/{id}", method = RequestMethod.GET)
    public String editCoupon(@PathVariable("id") Integer id,
                             Pagination pagination, Model model) {
        Coupon coupon = memberCouponService.findCoupon(id);
        model.addAttribute("coupon", coupon);
        model.addAttribute("pagination", pagination);
        Integer memberId = coupon.getMember().getId();
        if (memberId != null) {
            Member member = memberService.findMember(memberId);
            if (member != null) {
                model.addAttribute("mobile", member.getMobile());
            }
        }
        model.addAttribute("memberId", memberId);
//        model.addAttribute("statusList",
//                memberCouponService.getStatusList(coupon.getStatus() + ""));
        return "coupon/edit";
    }

    /** 批量保存优惠券界面 */
    @RequestMapping(value = "/addBatch", method = RequestMethod.GET)
    public String addBatch(Pagination pagination, Model model) {
        return "coupon/batchEdit";
    }

    /**
     * 批量保存优惠券
     */
    @ResponseBody
    @RequestMapping(value = "/coupon/saveBatch", method = RequestMethod.POST)
    public JsonMap saveBatchCoupon(@RequestParam(value="memberPhone") String memberPhone, @Valid Coupon coupon, BindingResult result,
                              HttpServletRequest request) {
        JsonMap ret;
        if (result.hasErrors()) {
            ret = parseErrorResult(result, "保存失败");
        }else if(memberPhone.length()==0){
            ret = parseErrorResult(result, "请先导入数据");
        }
        else {
            try {
                String[] memberPhones = memberPhone.split(",");
                memberCouponService.saveBatch(coupon,memberPhones, request);
                ret = new JsonMap(0, "保存成功");

            } catch (Exception e) {
                ret = parseErrorResult(result, "保存失败");
            }
        }
        return ret;
    }

    /**
     * 保存优惠券
     */
    @ResponseBody
    @RequestMapping(value = "/coupon/save", method = RequestMethod.POST)
    public JsonMap saveCoupon(@Valid Coupon coupon, BindingResult result,
                              HttpServletRequest request) {
        JsonMap ret;
        if (result.hasErrors()) {
            ret = parseErrorResult(result, "保存失败");
        } else {
            try {
                memberCouponService.save(coupon, request);
                ret = new JsonMap(0, "保存成功");
            } catch (Exception e) {
                ret = parseErrorResult(result, "保存失败");
            }
        }
        return ret;
    }

    /**
     * 优惠券设为无效
     */
    @ResponseBody
    @RequestMapping(value = "/coupon/update", method = RequestMethod.POST)
    public JsonMap update(@RequestParam("id[]") Integer[] ids) {
        memberCouponService.delete(ids);
        return new JsonMap(0, "设为无效成功");
    }

    /**
     * 跳转至添加积分页面
     */
    @RequestMapping(value = "/addPoints", method = RequestMethod.GET)
    public String addPoints(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "member/addPoints";
    }


    /**
     * 获取所有会员手机号
     */
    @RequestMapping(value = "/getMobileList", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getMobileList() {
        List<Map<String, Object>> list = Lists.newArrayList();
        List<Member> mobileList = memberService.findAllEffectiveMembers();
        for (Member member : mobileList) {
            Map<String, Object> obj = Maps.newHashMap();
            obj.put("id", member.getId());
            obj.put("text", member.getMobile() + "(" + member.getPoints() + ")");
            list.add(obj);
        }
        return list;
    }

    /**
     * 保存积分
     */
    @ResponseBody
    @RequestMapping(value = "/savePoints", method = RequestMethod.POST)
    public JsonMap savePoints(HttpServletRequest request) {
        memberService.savePoints(request);
        return new JsonMap(0, "添加成功");
    }


    /**
     *  会员增长页面
     * @param pagination
     * @param model
     * @return
     */
    @RequestMapping(value = "/increase", method = RequestMethod.GET)
    public String dayTotalIndex(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "member/increase/list";
    }


    /**
     * 会员增长页面
     * @param startDate
     * @param endDate
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping(value = "/increase", method = RequestMethod.POST)
    public String dayTotal(@RequestParam(value = "startDate",required = false)String startDate,
                           @RequestParam(value = "endDate", required = false)String endDate,
                           @RequestParam(value = "page", defaultValue = "1")Integer pageNum,
                           Model model)
    {
        model.addAttribute("total",memberService.findTotal(startDate,endDate));
        model.addAttribute("page",memberService.findMemberTotal(pageNum,startDate,endDate));
        return "member/increase/nested";
    }
}
