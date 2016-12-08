package com.mxep.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * com.ydd.web.dto
 *
 * @author ranfi
 * @since 6/6/16
 */
public class OrderBizGoodsDto implements Serializable {


    private static final long serialVersionUID = -195090531756110474L;

    private Integer id;
    private Integer actualWeight;
    private Integer merchantId;
    private BigDecimal actualPrice;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(Integer actualWeight) {
        this.actualWeight = actualWeight;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }
}
