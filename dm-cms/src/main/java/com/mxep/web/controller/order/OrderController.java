package com.mxep.web.controller.order;

import com.mxep.model.enums.EnumOrderType;
import com.mxep.model.order.Order;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.OrderService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author zhuming
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController extends WebController {

    @Autowired
    private OrderService orderService;

    /** 商品订单列表 */
    @RequestMapping(value="/goods/list", method = RequestMethod.GET)
    public String goodsList(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "order/goods/list";
    }


    /** 商品订单列表 */
    @RequestMapping(value="/goods/list",method = RequestMethod.POST)
    public String goodsList(Pagination pagination,
                             HttpServletRequest request, Model model){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        searchParams.put("EQ_orderType", EnumOrderType.Delivery.getValue());
        model.addAttribute("page", orderService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
        return "order/goods/nested";
    }


    //导出Excel
    @RequestMapping(value = "/goods/export")
    public ModelAndView goodsExport(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        try {
            model = getExcelView(request);
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            searchParams.put("EQ_flag",1);
            searchParams.put("EQ_orderType", EnumOrderType.Delivery.getValue());

            model.addObject("result", orderService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }


    /**
     * 查看某一 发货 订单
     * @param id
     * @return
     */
    @RequestMapping(value = "/showGoodsOrder/{id}", method = RequestMethod.GET)
    public String showGoodsOrder(@PathVariable("id")Integer id,Model model) {
        Order order = orderService.get(id);
        model.addAttribute("order",order);
        return "order/goods/show";
    }

    /** 到店服务 订单列表 */
    @RequestMapping(value="/worker/list", method = RequestMethod.GET)
    public String list(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "order/worker/list";
    }


    /**  到店服务 订单列表 */
    @RequestMapping(value="/worker/list",method = RequestMethod.POST)
    public String orders(Pagination pagination,
                             HttpServletRequest request, Model model){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        searchParams.put("EQ_orderType", EnumOrderType.Service.getValue());
        model.addAttribute("page", orderService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
        return "order/worker/nested";
    }

    //导出Excel
    @RequestMapping(value = "/worker/export")
    public ModelAndView serviceExport(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        try {
            model = getExcelView(request);
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            searchParams.put("EQ_flag",1);
            searchParams.put("EQ_orderType", EnumOrderType.Service.getValue());
            model.addObject("result", orderService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }


    /**
     * 查看某一 服务 订单
     * @param id
     * @return
     */
    @RequestMapping(value = "/showWorkerOrder/{id}", method = RequestMethod.GET)
    public String showWorkerOrder(@PathVariable("id")Integer id,Model model) {
        Order order = orderService.get(id);
        model.addAttribute("order",order);
//        model.addAttribute("workerId",orderService.findWorkerByOrder(order));
        return "order/worker/show";
    }

    /** 企业订单列表 */
    @RequestMapping(value="/shop/list", method = RequestMethod.GET)
    public String shopList(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "order/shop/list";
    }


    /** 企业订单列表 */
    @RequestMapping(value="/shop/list",method = RequestMethod.POST)
    public String shopList(Pagination pagination,
                            HttpServletRequest request, Model model){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        searchParams.put("EQ_orderType", EnumOrderType.Shop.getValue());
        model.addAttribute("page", orderService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
        return "order/shop/nested";
    }

    //导出Excel
    @RequestMapping(value = "/shop/export")
    public ModelAndView shopExport(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        try {
            model = getExcelView(request);
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            searchParams.put("EQ_flag",1);
            searchParams.put("EQ_orderType", EnumOrderType.Shop.getValue());
            model.addObject("result", orderService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    /**
     * 查看某一 企业 订单
     * @param id
     * @return
     */
    @RequestMapping(value = "/showShopOrder/{id}", method = RequestMethod.GET)
    public String showShopOrder(@PathVariable("id")Integer id,Model model) {
        Order order = orderService.get(id);
        model.addAttribute("order",order);
        return "order/shop/show";
    }




//    /**
//     * 指派 技师 订单
//     * @param
//     * @return
//     */
//    @RequestMapping(value = "/updateWorker/{oid}/{wid}", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonMap updateWorker(@PathVariable("oid")Integer oid,@PathVariable("wid")Integer wid) {
//        return orderWorkerService.updateRemark(oid,wid);
//    }


//
//    //导出Excel
//    @RequestMapping(value = "/export")
//    public ModelAndView export(HttpServletRequest request) {
//        ModelAndView model = new ModelAndView();
//        try {
//            model = getExcelView(request);
//            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
//            searchParams.put("EQ_flag",1);
//            model.addObject("result", orderService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return model;
//    }
//

    /**
     *   发货
     * @param oid
     * @return
     */
    @RequestMapping(value = "/updateOrderSn", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap updateOrderSn(@RequestParam("oid")Integer oid)
    {
        return orderService.deliveryOrder(oid);
    }

}
