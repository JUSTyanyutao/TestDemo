package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.GoodsShow;
import com.mxep.model.repositories.goods.GoodsShowDao;
import com.mxep.service.CommonService;
import com.mxep.web.web.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;


/**
 * Service - 大家说管理
 */
@Service
@Transactional
public class GoodsShowService extends BaseService<GoodsShow, Integer> {

	private final Logger logger = LoggerFactory.getLogger(GoodsShowService.class);

	private GoodsShowDao goodsShowDao;

	@Resource
	private CommonService commonService;



	@Resource
	@Override
	public void setBaseDao(BaseDao<GoodsShow, Integer> baseDao) {
		this.baseDao = baseDao;
		goodsShowDao = (GoodsShowDao) baseDao;
	}



	/**
	 * 删除 大家说
	 */
	@Transactional
	public JsonMap deleteGoodsShow(Integer[] ids) {
		GoodsShow goodsComment;
		for (Integer id : ids) {
			goodsComment = goodsShowDao.findOne(id);
			if (goodsComment == null) {
				continue;
			}
			goodsComment.setFlag((byte) -1);
			goodsShowDao.save(goodsComment);
		}
		return new JsonMap(0,"删除成功");
	}

	/**
	 * 审核 大家说
	 */
	@Transactional
	public JsonMap audit(Integer[] ids,byte status) {
		Timestamp currentTime = commonService.getCurrentTime();
		for(Integer id:ids)
		{
			GoodsShow goodsComment = goodsShowDao.findOne(id);
			goodsComment.setStatus(status);
			goodsComment.setUpdateTime(currentTime);
			goodsShowDao.save(goodsComment);
		}
		return new JsonMap(0, "审核成功");
	}

	/**
	 *  置顶 操作
	 */
	@Transactional
	public JsonMap recommend(Integer[] ids) {
		GoodsShow goodsComment;
		for (Integer id : ids) {
			goodsComment = goodsShowDao.findOne(id);
			if (goodsComment == null) {
				continue;
			}
			goodsComment.setRecommend(1);
			goodsShowDao.save(goodsComment);
		}
		return new JsonMap(0, "置顶成功");
	}


	/**
	 *   取消 置顶 操作
	 */
	@Transactional
	public JsonMap disRecommend(Integer[] ids) {
		GoodsShow goodsComment;
		for (Integer id : ids) {
			goodsComment = goodsShowDao.findOne(id);
			if (goodsComment == null) {
				continue;
			}
			goodsComment.setRecommend(0);
			goodsShowDao.save(goodsComment);
		}
		return new JsonMap(0, "取消置顶成功");
	}






}
