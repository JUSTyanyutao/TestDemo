package com.mxep.web.service;


import com.mxep.model.BaseDao;
import com.mxep.model.common.Carousel;
import com.mxep.model.repositories.base.CarouselDao;
import com.mxep.service.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;


/**
 * Service - 轮播图
 */
@Service
public class CarouselService extends BaseService<Carousel, Integer> {

	private CarouselDao carouselDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<Carousel, Integer> baseDao) {
		this.baseDao = baseDao;
		carouselDao =( CarouselDao)baseDao;
	}
	/**
	 * 查询轮播图信息
	 * 
	 * @param id
	 *            轮播图编号
	 * @return 轮播图信息
	 */
	@Transactional(readOnly = true)
	public Carousel findCarousel(Integer id) {
		if (id == null) {
			return null;
		}
		return carouselDao.findOne(id);
	}

	/**
	 * 保存轮播图
	 * 
	 * @param carousel 轮播图
	 * @return 轮播图
	 */
	@Override
	@Transactional
	public Carousel save(Carousel carousel) {
		Timestamp currentTime = commonService.getCurrentTime();
		// 从数据库总查询轮播图信息
		Carousel entity = findCarousel(carousel.getId());
		if (entity == null) {
			// 新建轮播图
			entity = carousel;
			entity.setCreateTime(currentTime);
		} else {
			// 保存轮播图
			Date createTime = carousel.getCreateTime();
			entity = carousel;
			entity.setCreateTime(createTime);
			entity.setUpdateTime(currentTime);
		}
		return carouselDao.save(entity);
	}

	/**
	 * 删除轮播图
	 * 
	 * @param ids
	 *            编号数组
	 */
	@Override
	@Transactional
	public void delete(Integer[] ids) {
		Carousel carousel;
		for (Integer id : ids) {
			carousel = findCarousel(id);
			if (carousel == null) {
				continue;
			}
			carousel.setFlag((byte)-1);
			carouselDao.save(carousel);
		}
	}

}
