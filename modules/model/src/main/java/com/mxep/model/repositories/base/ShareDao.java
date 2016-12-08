package com.mxep.model.repositories.base;

import com.mxep.model.BaseDao;
import com.mxep.model.share.Share;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by lq on 2016/9/12.
 */
public interface ShareDao extends BaseDao<Share,Integer> {


    /**
     * 分页查询晒单列表数据
     *
     * @param pageable 分页信息
     * @return 晒单列表
     */
    @Query("SELECT a FROM Share a WHERE a.flag = 1 and a.checkStatus = 1")
    Page<Share> findPage(Pageable pageable);

    /**
     * 根据竞拍品编号分页查询晒单信息
     *
     * @param auctionId 竞拍品编号
     * @param pageable 分页信息
     * @return 分页晒单信息
     */
//    @Query("SELECT a FROM Share a WHERE a.auctionId = ?1 AND a.flag = 1 AND a.checkStatus = 1")
//    Page<Share> findPageByAuctionId(Integer auctionId, Pageable pageable);

}
