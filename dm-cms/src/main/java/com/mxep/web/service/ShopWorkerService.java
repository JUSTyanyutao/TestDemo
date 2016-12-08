package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.member.ShopWorker;
import com.mxep.model.repositories.member.ShopWorkerDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/11/9 0009.
 *
 *  技师 和 场地的 关系
 */
@Service
@Transactional
public class ShopWorkerService extends BaseService<ShopWorker,Integer> {

    private ShopWorkerDao shopWorkerDao;

    @Resource
    @Override
    public void setBaseDao(BaseDao<ShopWorker, Integer> baseDao) {
        this.baseDao = baseDao;
        shopWorkerDao = (ShopWorkerDao)baseDao;
    }
}
