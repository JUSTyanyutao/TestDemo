package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.common.Faq;
import com.mxep.model.goods.FilmBrand;
import com.mxep.model.repositories.common.FaqDao;
import com.mxep.model.repositories.goods.FilmBrandDao;
import com.mxep.service.CommonService;
import com.mxep.web.common.persistence.DynamicSpecifications;
import com.mxep.web.common.persistence.SearchFilter;
import com.mxep.web.web.JsonMap;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;


/**
 * Service - 品牌
 */
@Service
public class FilmBrandService extends BaseService<FilmBrand, Integer> {

	private FilmBrandDao filmBrandDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<FilmBrand, Integer> baseDao) {
		this.baseDao = baseDao;
		filmBrandDao =( FilmBrandDao) baseDao;
	}


	/**
	 * 查询所有 品牌
	 *
	 * @param
	 * @return 信息
	 */
	@Transactional(readOnly = true)
	public List<FilmBrand> findAll() {
		return filmBrandDao.findAll();
	}


	/**
	 * 查询品牌信息
	 * 
	 * @param id
	 * @return 信息
	 */
	@Transactional(readOnly = true)
	public FilmBrand findBrand(Integer id) {
		if (id == null) {
			return null;
		}
		return filmBrandDao.findOne(id);
	}

	/**
	 * 保存品牌
	 */
	@Override
	@Transactional
	public FilmBrand save(FilmBrand filmBrand) {
		// 从数据库总查询QA信息
		Timestamp currentTime = commonService.getCurrentTime();
		FilmBrand entity = findBrand(filmBrand.getId());
		if (entity == null) {
			// 新建品牌
			entity = filmBrand;
			entity.setCreateTime(currentTime);
			entity.setUpdateTime(currentTime);
		} else {
			// 保存品牌
			Date createTime = entity.getCreateTime();
			entity = filmBrand;
			entity.setCreateTime(createTime);
			entity.setUpdateTime(currentTime);
		}

		return filmBrandDao.save(entity);
	}

	/**
	 * 置顶品牌
	 */
	@Transactional
	public JsonMap recommendFlag(Integer[] ids) {
		FilmBrand filmBrand;
		for (Integer id : ids) {
			filmBrand = filmBrandDao.findOne(id);
			if (filmBrand == null) {
				continue;
			}
			filmBrand.setRecommendFlag( (byte)1);
			filmBrandDao.save(filmBrand);
		}
		return new JsonMap(0,"置顶成功");
	}

	/**
	 * 取消置顶品牌
	 */
	@Transactional
	public JsonMap disRecommendFlag(Integer[] ids) {
		FilmBrand filmBrand;
		for (Integer id : ids) {
			filmBrand = filmBrandDao.findOne(id);
			if (filmBrand == null) {
				continue;
			}
			filmBrand.setRecommendFlag( (byte)0);
			filmBrandDao.save(filmBrand);
		}
		return new JsonMap(0,"取消成功");
	}

	/**
	 * 删除品牌
	 */
	@Transactional
	public JsonMap deleteBrand(Integer[] ids) {
		FilmBrand filmBrand;
		for (Integer id : ids) {
			filmBrand = filmBrandDao.findOne(id);
			if (filmBrand == null) {
				continue;
			}
			filmBrand.setFlag((byte) -1);
			filmBrandDao.save(filmBrand);
		}
		return new JsonMap(0,"删除成功");
	}

	/**
	 * 上架品牌
	 */
	@Transactional
	public JsonMap enable(Integer[] ids) {
		FilmBrand filmBrand;
		for (Integer id : ids) {
			filmBrand = filmBrandDao.findOne(id);
			if (filmBrand == null) {
				continue;
			}
			filmBrand.setStatus( (byte)1);
			save(filmBrand);
		}
		return new JsonMap(0, "上架成功");
	}

	/**
	 * 下架品牌
	 */
	@Transactional
	public JsonMap disable(Integer[] ids) {
		FilmBrand filmBrand;
		for (Integer id : ids) {
			filmBrand = filmBrandDao.findOne(id);
			if (filmBrand == null) {
				continue;
			}
			filmBrand.setStatus( (byte)0);
			save(filmBrand);
		}
		return new JsonMap(0, "下架成功");
	}
}
