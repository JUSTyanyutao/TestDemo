package com.mxep.model.repositories.order;

import com.mxep.model.BaseDao;
import com.mxep.model.order.OrderLog;

public interface OrderLogDao extends BaseDao<OrderLog, Integer> {

//	@Query("select a from OrderLog a where a.order.id=?1 and a.type=?2 order by a.id desc")
//	List<OrderLog> findOrderLog(Integer orderId,Integer type);
}
