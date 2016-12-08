package com.mxep.web.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 订单打包请求参数实体对象
 *
 * @author ranfi
 * @since 5/3/16
 */
public class OrderPackageParamDto implements Serializable {

    private static final long serialVersionUID = -69601387375509797L;

    private Integer orderId;

    private List<OrderBizGoodsDto> orderBizGoodsList;

    private Integer orderSequence;

    private Map<String, Object> searchParams;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<OrderBizGoodsDto> getOrderBizGoodsList() {
        return orderBizGoodsList;
    }

    public void setOrderBizGoodsList(List<OrderBizGoodsDto> orderBizGoodsList) {
        this.orderBizGoodsList = orderBizGoodsList;
    }

    public Integer getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(Integer orderSequence) {
        this.orderSequence = orderSequence;
    }

    public Map<String, Object> getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(Map<String, Object> searchParams) {
        this.searchParams = searchParams;
    }
}
