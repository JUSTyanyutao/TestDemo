package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.Goods;
import com.mxep.model.goods.GoodsInfo;
import com.mxep.model.repositories.goods.GoodsDao;
import com.mxep.model.repositories.goods.GoodsInfoDao;
import com.mxep.service.CommonService;
import com.mxep.web.web.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;


/**
 * Service - 商品信息管理
 */
@Service
@Transactional
public class GoodsInfoService extends BaseService<GoodsInfo, Integer> {

	private final Logger logger = LoggerFactory.getLogger(GoodsInfoService.class);

	private GoodsInfoDao goodsInfoDao;

	@Resource
	private GoodsDao goodsDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<GoodsInfo, Integer> baseDao) {
		this.baseDao = baseDao;
		goodsInfoDao = (GoodsInfoDao) baseDao;
	}





}
