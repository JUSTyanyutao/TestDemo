package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.GoodsComment;
import com.mxep.model.member.Worker;
import com.mxep.model.repositories.goods.GoodsCommentDao;
import com.mxep.model.repositories.member.WorkerDao;
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
 * Service - 评价管理
 */
@Service
@Transactional
public class GoodsCommentService extends BaseService<GoodsComment, Integer> {

	private final Logger logger = LoggerFactory.getLogger(GoodsCommentService.class);

	private GoodsCommentDao goodsCommentDao;

	@Resource
	private CommonService commonService;



	@Resource
	@Override
	public void setBaseDao(BaseDao<GoodsComment, Integer> baseDao) {
		this.baseDao = baseDao;
		goodsCommentDao = (GoodsCommentDao) baseDao;
	}


	/**
	 * 查找所有评价
	 * @return
     */
//	public List<Worker> findAllWorker()
//	{
//		return workerDao.findAll();
//	}
//

	/**
	 * 删除评价
	 */
	@Transactional
	public JsonMap deleteGoodsComment(Integer[] ids) {
		GoodsComment goodsComment;
		for (Integer id : ids) {
			goodsComment = goodsCommentDao.findOne(id);
			if (goodsComment == null) {
				continue;
			}
			goodsComment.setFlag((byte) -1);
			goodsCommentDao.save(goodsComment);
		}
		return new JsonMap(0,"删除成功");
	}

	/**
	 * 审核 评价
	 */
	@Transactional
	public JsonMap audit(Integer[] ids,byte status) {
		Timestamp currentTime = commonService.getCurrentTime();
		for(Integer id:ids)
		{
			GoodsComment goodsComment = goodsCommentDao.findOne(id);
			goodsComment.setStatus(status);
			goodsComment.setUpdateTime(currentTime);
			goodsCommentDao.save(goodsComment);
		}
		return new JsonMap(0, "审核成功");
	}

	/**
	 *  置顶 操作
	 */
	@Transactional
	public JsonMap recommend(Integer[] ids) {
		GoodsComment goodsComment;
		for (Integer id : ids) {
			goodsComment = goodsCommentDao.findOne(id);
			if (goodsComment == null) {
				continue;
			}
			goodsComment.setRecommend( (byte)1);
			goodsCommentDao.save(goodsComment);
		}
		return new JsonMap(0, "置顶成功");
	}


	/**
	 *   取消 置顶 操作
	 */
	@Transactional
	public JsonMap disRecommend(Integer[] ids) {
		GoodsComment goodsComment;
		for (Integer id : ids) {
			goodsComment = goodsCommentDao.findOne(id);
			if (goodsComment == null) {
				continue;
			}
			goodsComment.setRecommend( (byte)0);
			goodsCommentDao.save(goodsComment);
		}
		return new JsonMap(0, "取消置顶成功");
	}






}
