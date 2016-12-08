package com.mxep.web.service;

import javax.annotation.Resource;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.Goods;
import com.mxep.model.goods.GoodsInfo;
import com.mxep.model.goods.Package;
import com.mxep.model.repositories.goods.GoodsDao;
import com.mxep.model.repositories.goods.GoodsInfoDao;
import com.mxep.service.CommonService;
import com.mxep.web.web.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;


/**
 * Service - 商品管理
 */
@Service
@Transactional
public class GoodsService extends BaseService<Goods, Integer> {

	private final Logger logger = LoggerFactory.getLogger(GoodsService.class);

	private GoodsDao goodsDao;

	@Resource
	private GoodsInfoDao goodsInfoDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<Goods, Integer> baseDao) {
		this.baseDao = baseDao;
		goodsDao = (GoodsDao) baseDao;
	}



	/**
	 * 根据商品 查询 套餐 id
	 *
	 */
	@Transactional(readOnly = true)
	public String getPackageList(Goods goods) {
		List<Package> packageList = goods.getPackageList();
		String s = "";
		for(int i=0; i<packageList.size(); i++)
		{
			if(i != packageList.size()-1)
			{
				s += packageList.get(i).getId()+",";
				continue;
			}
			s += packageList.get(i).getId();
		}
		return  s;
	}


	/**
	 * 查找所有 漆面贴膜 和 玻璃贴膜的 商品
	 * @return
	 */
	public List<Goods> findServiceGoods()
	{
		return goodsDao.findServiceGoods();
	}


	/**
	 * 查找所有商品
	 * @return
     */
	public List<Goods> findAllGoods()
	{
		return goodsDao.findAllGoods();
	}


	/**
	 * 置顶品牌
	 */
	@Transactional
	public JsonMap recommendFlag(Integer[] ids) {
		Goods goods;
		for (Integer id : ids) {
			goods = goodsDao.findOne(id);
			if (goods == null) {
				continue;
			}
			goods.setRecommendFlag( (byte)1);
			goodsDao.save(goods);
		}
		return new JsonMap(0,"置顶成功");
	}

	/**
	 * 取消置顶品牌
	 */
	@Transactional
	public JsonMap disRecommendFlag(Integer[] ids) {
		Goods goods;
		for (Integer id : ids) {
			goods = goodsDao.findOne(id);
			if (goods == null) {
				continue;
			}
			goods.setRecommendFlag( (byte)0);
			goodsDao.save(goods);
		}
		return new JsonMap(0,"取消成功");
	}



	/**
	 * 删除商品
	 */
	@Transactional
	public JsonMap deleteGoods(Integer[] ids) {
		Goods goods;
		for (Integer id : ids) {
			goods = goodsDao.findOne(id);
			if (goods == null) {
				continue;
			}
			goods.setFlag((byte) -1);
			goodsDao.save(goods);
		}
		return new JsonMap(0,"删除成功");
	}

	/**
	 * 上架商品
	 */
	@Transactional
	public JsonMap enable(Integer[] ids) {
		Goods goods;
		for (Integer id : ids) {
			goods = goodsDao.findOne(id);
			if (goods == null) {
				continue;
			}
			goods.setStatus(1);
			goodsDao.save(goods);
		}
		return new JsonMap(0, "上架成功");
	}

	/**
	 * 下架商品
	 */
	@Transactional
	public JsonMap disable(Integer[] ids) {
		Goods goods;
		for (Integer id : ids) {
			goods = goodsDao.findOne(id);
			if (goods == null) {
				continue;
			}
			goods.setStatus(0);
			goodsDao.save(goods);
		}
		return new JsonMap(0, "下架成功");
	}


	/**
	 * 保存商品
	 */
	@Transactional
	public Goods save(Goods goods) {
		Timestamp currentTime = commonService.getCurrentTime();
		GoodsInfo goodsInfo = goods.getGoodsInfo();
		GoodsInfo tempGoodsInfo;
		if(null != goods.getId())
		{
			Goods entity = goodsDao.findOne(goods.getId());
			goods.setCreateTime(entity.getCreateTime());
			goods.setStatus( entity.getStatus() );
			goods.setRecommendFlag(entity.getRecommendFlag());

			tempGoodsInfo = entity.getGoodsInfo();
			tempGoodsInfo.setDetailContent(goodsInfo.getDetailContent());
			tempGoodsInfo.setSpecContent(goodsInfo.getSpecContent());
			tempGoodsInfo.setServiceProcessContent(goodsInfo.getServiceProcessContent());
		}
		else
		{
			goods.setCreateTime(currentTime);
			goods.setStatus( (byte)1);
			goods.setRecommendFlag( (byte)0 );

			tempGoodsInfo = goodsInfo;
		}

		goods.setUpdateTime(currentTime);
		goods.setGoodsInfo(null);
		Goods temp = goodsDao.save(goods);
		tempGoodsInfo.setGoodsId(temp.getId());
		goodsInfoDao.save(tempGoodsInfo);

		return goods;
	}





}
