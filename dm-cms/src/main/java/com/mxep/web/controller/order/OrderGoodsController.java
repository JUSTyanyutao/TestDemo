package com.mxep.web.controller.order;

import com.mxep.model.enums.EnumOrderStatus;
import com.mxep.model.enums.EnumRewardStatus;
import com.mxep.model.order.Order;
import com.mxep.model.order.OrderGoods;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.OrderGoodsService;
import com.mxep.web.service.OrderService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zhuming
 */
@Controller
@RequestMapping(value = "/order/goods")
public class OrderGoodsController extends WebController {

    @Autowired
    private OrderGoodsService orderGoodsService;

    @Autowired
    private OrderService orderService;

//    /** 商品订单列表 */
//    @RequestMapping(value="/list", method = RequestMethod.GET)
//    public String list(Pagination pagination, Model model) {
//        model.addAttribute("pagination", pagination);
//        return "order/goods/list";
//    }
//
//
//    /** 商品订单列表 */
//    @RequestMapping(value="/list",method = RequestMethod.POST)
//    public String orders(Pagination pagination,
//                             HttpServletRequest request, Model model){
//        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
//        searchParams.put("EQ_flag",1);
//        model.addAttribute("page", orderGoodsService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
//        return "order/goods/nested";
//    }






//    //导出Excel
//    @RequestMapping(value = "/export")
//    public ModelAndView export(HttpServletRequest request) {
//        ModelAndView model = new ModelAndView();
//        try {
//            model = getExcelView(request);
//            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
//            searchParams.put("EQ_flag",1);
//            model.addObject("result", orderGoodsService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return model;
//    }


//    /**
//     * 查看某一 商品 订单
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
//    public String showGoodsOrder(@PathVariable("id")Integer id,Model model) {
//        OrderGoods orderGoods = orderGoodsService.get(id);
//        model.addAttribute("orderGoods",orderGoods);
//        model.addAttribute("order",orderGoods.getOrder());
//
//        return "order/goods/show";
//    }


//    /**
//     * 更新订单 备注
//     * @param oid
//     * @param remark
//     * @return
//     */
//    @RequestMapping(value = "/updateRemark", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonMap updateRemark(@RequestParam("oid")Integer oid,@RequestParam("remark")String remark)
//    {
//        return orderService.updateRemark(oid,remark);
//    }
//
//

//
//    /**
//     * 完成订单
//     * @param orderId
//     * @return
//     */
//    @RequestMapping(value = "complete", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonMap orderComplete(@RequestParam("orderId")Integer orderId)
//    {
//        return orderService.orderComplete(orderId);
//    }
//
//
//    /**
//     * 流水 页面
//     * @param pagination
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/sales", method = RequestMethod.GET)
//    public String dayTotalIndex(Pagination pagination, Model model) {
//        model.addAttribute("pagination", pagination);
//        return "order/sales/list";
//    }
//
//
//    /**
//     * 流水页面
//     * @param startDate
//     * @param endDate
//     * @param pageNum
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/sales", method = RequestMethod.POST)
//    public String dayTotal(@RequestParam(value = "startDate",required = false)String startDate,
//                           @RequestParam(value = "endDate", required = false)String endDate,
//                           @RequestParam(value = "page", defaultValue = "1")Integer pageNum,
//                           Model model)
//    {
//        model.addAttribute("total",orderService.findTotal(startDate,endDate));
//        model.addAttribute("page",orderService.findDayTotal(pageNum,startDate,endDate));
//        return "order/sales/nested";
//    }
//
//
//    /**
//     * chart报表
//     * @param startDate
//     * @param endDate
//     * @param pageNum
//     * @return
//     */
//    @RequestMapping(value = "/chart", method = RequestMethod.POST)
//    @ResponseBody
//    public Page chart(@RequestParam(value = "startDate",required = false)String startDate,
//                         @RequestParam(value = "endDate", required = false)String endDate,
//                         @RequestParam(value = "page", defaultValue = "1")Integer pageNum)
//    {
//        return orderService.findDayTotal(pageNum,startDate,endDate);
//    }



}
