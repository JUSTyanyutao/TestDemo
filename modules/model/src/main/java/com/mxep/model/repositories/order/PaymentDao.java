package com.mxep.model.repositories.order;

import com.mxep.model.log.LogPayment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentDao extends CrudRepository<LogPayment, Integer> {


//    /**
//     * 根据年份查询付款金额统计数据
//     *
//     * @param year 年份
//     * @return 付款金额统计数据
//     */
//    @Query(value = "select date_format(pay_time, '%c') as `name`, sum(total) as `value` from sw_payment where date_format(pay_time, '%Y') = ?1 group by date_format(pay_time, '%Y-%m')", nativeQuery = true)
//    public List<Object> getPaymentStatisticsByYear(String year);
//
//    /**
//     * 根据月份查询付款金额统计数据
//     *
//     * @param month 月份
//     * @return 付款金额统计数据
//     */
//    @Query(value = " select date_format(pay_time, '%e') as `name`, sum(total) as `value` from sw_payment where date_format(pay_time, '%Y-%m') = ?1 group by date_format(pay_time, '%Y-%m-%d')", nativeQuery = true)
//    public List<Object> getPaymentStatisticsByMonth(String month);
//
//    /**
//     *
//     * @param orderId
//     * @param memberId
//     * @return
//     */
//    @Query("select a from Payment a where a.order.id=?1 and a.member.id = ?2")
//    public LogPayment findPaymentByOrderIdAndUid (Integer orderId, Integer memberId);
}
