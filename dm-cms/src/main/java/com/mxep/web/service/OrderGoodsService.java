package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.enums.EnumOrderStatus;
import com.mxep.model.order.Order;
import com.mxep.model.order.OrderGoods;
import com.mxep.model.order.OrderLog;
import com.mxep.model.repositories.JpaDao;
import com.mxep.model.repositories.order.OrderDao;
import com.mxep.model.repositories.order.OrderGoodsDao;
import com.mxep.model.repositories.order.OrderLogDao;
import com.mxep.service.CommonService;
import com.mxep.service.member.BaseMemberService;
import com.mxep.service.order.BaseOrderService;
import com.mxep.service.push.PushService;
import com.mxep.web.web.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Service - 订单
 */
@Service
public class OrderGoodsService extends BaseService<OrderGoods, Integer> {

    private final Logger logger = LoggerFactory.getLogger(OrderGoodsService.class);

    private OrderGoodsDao orderGoodsDao;

    @Resource
    private CommonService commonService;

    @Resource
    private OrderLogDao orderLogDao;

    @Resource
    private JpaDao jpaDao;


    @Resource
    @Override
    public void setBaseDao(BaseDao<OrderGoods, Integer> baseDao) {
        this.baseDao = baseDao;
        orderGoodsDao = (OrderGoodsDao) baseDao;
    }

//    /**
//     * 更新 order 备注
//     * @param id
//     * @param remark
//     * @return
//     */
//    public JsonMap updateRemark(Integer id, String remark)
//    {
//        Timestamp currentTime = commonService.getCurrentTime();
//        Order order = orderDao.findOne(id);
//        if(null == order)
//        {
//            return new JsonMap(1,"不存在的订单");
//        }
//        order.setRemark(remark);
//        order.setUpdateTime(currentTime);
//        Date date = new Date(order.getUpdateTime().getTime());
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        String time = dateFormat.format(date);
//        orderDao.save(order);
//
//        //插入订单日志表
//        OrderLog orderLog = new OrderLog();
//        orderLog.setOrderId(order.getId());
//        orderLog.setFlag( (byte)1);
//        orderLog.setStatus(order.getStatus());
//        orderLog.setCreateTime(currentTime);
//        orderLog.setUpdateTime(currentTime);
//        orderLogDao.save(orderLog);
//        return new JsonMap(0,"更新备注成功","data",time);
//    }

    /**
     *  完成 订单
     */
//    public JsonMap orderComplete(Integer id)
//    {
//        Timestamp currentTime = commonService.getCurrentTime();
//        Order order = orderDao.findOne(id);
//        if(null == order)
//        {
//            return new JsonMap(1,"不存在的订单");
//        }
//        order.setStatus(EnumOrderStatus.Completed.getValue());
//        order.setUpdateTime(currentTime);
//        orderDao.save(order);
//
//        //插入订单日志表
//        OrderLog orderLog = new OrderLog();
//        orderLog.setOrderId(order.getId());
//        orderLog.setFlag( (byte)1);
//        orderLog.setStatus(order.getStatus());
//        orderLog.setCreateTime(currentTime);
//        orderLog.setUpdateTime(currentTime);
//        orderLogDao.save(orderLog);
//        return new JsonMap(0,"订单已完成");
//    }


//    /**
//     * 统计流水
//     * @param pageNum
//     * @param startDate
//     * @param endDate
//     * @return
//     */
//    public Page findDayTotal(int pageNum, String startDate, String endDate)
//    {
//        String sql = "select  COUNT(*) as num ,sum(balance) as totalMoney ,DATE_FORMAT(create_time,\"%Y-%m-%d\") as day1 from mx_log_payment where status=2";
//        if(!"".equals(startDate) && "".equals(endDate))
//        {
//            startDate = "\""+startDate+"\"";
//            sql += " AND DATE_FORMAT(create_time,\"%Y-%m-%d\") >= str_to_date("+startDate+",\"%Y-%m-%d\")";
//        }
//        if("".equals(startDate) && !"".equals(endDate))
//        {
//            endDate = "\""+endDate+"\"";
//            sql += " AND DATE_FORMAT(create_time,\"%Y-%m-%d\") <= str_to_date("+endDate +",\"%Y-%m-%d\")";
//        }
//        if( !"".equals(startDate) && !"".equals(endDate))
//        {
//            startDate = "\""+startDate+"\"";
//            endDate = "\""+endDate+"\"";
//            sql += " AND DATE_FORMAT(create_time,\"%Y-%m-%d\") >= str_to_date("+startDate+",\"%Y-%m-%d\")"
//                    +" AND DATE_FORMAT(create_time,\"%Y-%m-%d\") <= str_to_date("+endDate +",\"%Y-%m-%d\")";
//        }
//        sql += " GROUP BY DATE_FORMAT(create_time,\"%Y-%m-%d\") ORDER BY DATE_FORMAT(create_time,\"%Y-%m-%d\") DESC";
//
//        Page page=jpaDao.queryAsPage(pageNum,sql,Map.class);
//
//        return page;
//    }
//
//
//    /**
//     * 统计流水
//     * @param startDate
//     * @param endDate
//     * @return
//     */
//    public String findTotal(String startDate, String endDate)
//    {
//        String sql = "select sum(balance) as totalMoney from mx_log_payment where status=2";
//        if(!"".equals(startDate) && "".equals(endDate))
//        {
//            startDate = "\""+startDate+"\"";
//            sql += " AND DATE_FORMAT(create_time,\"%Y-%m-%d\") >= str_to_date("+startDate+",\"%Y-%m-%d\")";
//        }
//        if("".equals(startDate) && !"".equals(endDate))
//        {
//            endDate = "\""+endDate+"\"";
//            sql += " AND DATE_FORMAT(create_time,\"%Y-%m-%d\") <= str_to_date("+endDate +",\"%Y-%m-%d\")";
//        }
//        if( !"".equals(startDate) && !"".equals(endDate))
//        {
//            startDate = "\""+startDate+"\"";
//            endDate = "\""+endDate+"\"";
//            sql += " AND DATE_FORMAT(create_time,\"%Y-%m-%d\") >= str_to_date("+startDate+",\"%Y-%m-%d\")"
//                    +" AND DATE_FORMAT(create_time,\"%Y-%m-%d\") <= str_to_date("+endDate +",\"%Y-%m-%d\")";
//        }
//
//        List<Map> list =jpaDao.queryAsList(sql,Map.class);
//        String totalMoney= null == list.get(0).get("totalMoney")?"无":list.get(0).get("totalMoney").toString();
//
//        return totalMoney;
//    }



}
