package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.order.Order;
import com.mxep.model.order.OrderGoods;
import com.mxep.model.order.OrderWorkerStatistics;
import com.mxep.model.repositories.JpaDao;
import com.mxep.model.repositories.order.OrderGoodsDao;
import com.mxep.model.repositories.order.OrderLogDao;
import com.mxep.model.repositories.order.OrderWorkerStatisticsDao;
import com.mxep.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Service -  技师结算表
 */
@Service
public class OrderWorkerStatisticsService extends BaseService<OrderWorkerStatistics, Integer> {

    private final Logger logger = LoggerFactory.getLogger(OrderWorkerStatisticsService.class);

    private OrderWorkerStatisticsDao orderWorkerStatisticsDao;

    @Resource
    private CommonService commonService;

    @Resource
    private OrderLogDao orderLogDao;

    @Resource
    private JpaDao jpaDao;


    @Resource
    @Override
    public void setBaseDao(BaseDao<OrderWorkerStatistics, Integer> baseDao) {
        this.baseDao = baseDao;
        orderWorkerStatisticsDao = (OrderWorkerStatisticsDao) baseDao;
    }


}
