package com.mxep.model.repositories.order;

import com.mxep.model.BaseDao;
import com.mxep.model.order.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

/**
 * Dao - 订单
 */
public interface OrderDao extends BaseDao<Order, Integer> {

}
