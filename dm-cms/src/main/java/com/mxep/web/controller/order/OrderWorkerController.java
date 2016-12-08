package com.mxep.web.controller.order;

import com.mxep.model.order.OrderGoods;
import com.mxep.model.order.OrderWorker;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.OrderGoodsService;
import com.mxep.web.service.OrderWorkerService;
import com.mxep.web.web.JsonMap;
import com.mxep.web.web.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/order/worker")
public class OrderWorkerController extends WebController {

    @Autowired
    private OrderWorkerService orderWorkerService;

//    /** 技师订单列表 */
//    @RequestMapping(value="/list", method = RequestMethod.GET)
//    public String list(Pagination pagination, Model model) {
//        model.addAttribute("pagination", pagination);
//        return "order/worker/list";
//    }
//
//
//    /** 技师订单列表 */
//    @RequestMapping(value="/list",method = RequestMethod.POST)
//    public String orders(Pagination pagination,
//                             HttpServletRequest request, Model model){
//        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
//        searchParams.put("EQ_flag",1);
//        model.addAttribute("page", orderWorkerService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime")));
//        return "order/worker/nested";
//    }
//
//
//
//
//
//
//    //导出Excel
//    @RequestMapping(value = "/export")
//    public ModelAndView export(HttpServletRequest request) {
//        ModelAndView model = new ModelAndView();
//        try {
//            model = getExcelView(request);
//            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
//            searchParams.put("EQ_flag",1);
//            model.addObject("result", orderWorkerService.list(searchParams,new Sort(Sort.Direction.DESC,"createTime")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return model;
//    }
//
//
//    /**
//     * 查看某一 技师 订单
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
//    public String showGoodsOrder(@PathVariable("id")Integer id,Model model) {
//        OrderWorker orderWorker = orderWorkerService.get(id);
//        model.addAttribute("orderWorker",orderWorker);
//        model.addAttribute("order",orderWorker.getOrder());
//        return "order/worker/show";
//    }
//
//
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

}
