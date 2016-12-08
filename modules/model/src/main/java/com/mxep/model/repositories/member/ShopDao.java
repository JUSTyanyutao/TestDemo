package com.mxep.model.repositories.member;

import com.mxep.model.BaseDao;
import com.mxep.model.common.Coupon;
import com.mxep.model.member.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Dao - 场地
 *
 *
 */
public interface ShopDao extends BaseDao<Shop, Integer> {

    @Query("select a from Shop a where a.status = 1 and a.type = 1 and a.flag= 1")
    List<Shop> findAll4sShop();

}
