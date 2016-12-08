package com.mxep.web.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mxep.model.BaseDao;
import com.mxep.model.GlobalConstant;
import com.mxep.model.common.Promotion;
import com.mxep.model.repositories.goods.PromotionDao;
import com.mxep.service.CommonService;

/**
 * Service - 优惠活动
 */
@Service
public class PromotionService extends BaseService<Promotion, Integer> {

	private final Logger logger = LoggerFactory.getLogger(PromotionService.class);

	private PromotionDao promotionDao;
	
	@Resource
	private CommonService commonService;
	

	@Resource
	@Override
	public void setBaseDao(BaseDao<Promotion, Integer> baseDao) {
		this.baseDao = baseDao;
		promotionDao = (PromotionDao)baseDao;
	}
	
	
	/**
	 * 查询优惠活动
	 *
	 * @param id 优惠活动编号
	 * @return 优惠活动信息
	 */
	@Transactional(readOnly = true)
	public Promotion findPromotion(Integer id) {
		if (id == null) {
			return null;
		}
		return promotionDao.findOne(id);
	}
	
	/**
	 * 保存优惠活动
	 *
	 * @param siteStation 站点
	 * @return 站点
	 */
	@Override
	@Transactional
	public Promotion save(Promotion promotion) {
		// 从数据库总查询站点信息
		//SiteStation entity = findSiteStation(siteStation.getSid());
		if(promotion.getId() == null){
			promotion.setCreateTime(commonService.getCurrentTime());
		}
		promotion.setUpdateTime(commonService.getCurrentTime());
		
		return promotionDao.save(promotion);
	}
	
	
	
	/**
	 * 删除优惠活动
	 *
	 * @param ids 编号数组
	 */
	@Override
	@Transactional
	public void delete(Integer[] ids) {
		Promotion promotion;
		for (Integer id : ids) {
			promotion = findPromotion(id);
			if (promotion == null) {
				continue;
			}
			promotion.setStatus(GlobalConstant.Status.Deleted.value);
			
			promotionDao.save(promotion);
		}
	}
	
	
	/**
	 * 更新优惠活动状态
	 *
	 * @param ids 编号
	 * @param status 状态
	 */
	@Transactional
	public void updateStatus(Integer[] ids, Integer status) {
		Promotion promotion;
		if (ids != null && ids.length > 0) {
			for (Integer id : ids) {
				promotion = findPromotion(id);
				if (promotion == null) {
					continue;
				}
				if(promotion.getStatus() == status){
					continue;
				}
				promotion.setStatus(status);
				promotionDao.save(promotion);
			}
		}
	}
	
	
	
	


}
