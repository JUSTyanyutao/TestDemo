package com.mxep.model.repositories.member;

import com.mxep.model.BaseDao;
import com.mxep.model.member.ShopWorker;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
public interface ShopWorkerDao extends BaseDao<ShopWorker,Integer> {

    @Query("select a from ShopWorker a  where  a.shopId = ?1")
    List<ShopWorker> findShopWorkerByShopId(Integer id);

}
