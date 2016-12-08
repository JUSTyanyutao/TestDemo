package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.common.City;
import com.mxep.model.common.Faq;
import com.mxep.model.repositories.common.CityDao;
import com.mxep.model.repositories.common.FaqDao;
import com.mxep.service.CommonService;
import com.mxep.web.common.persistence.DynamicSpecifications;
import com.mxep.web.common.persistence.SearchFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;


/**
 * Service - 城市
 */
@Service
public class CityService extends BaseService<City, Integer> {

	private CityDao cityDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<City, Integer> baseDao) {
		this.baseDao = baseDao;
		cityDao =( CityDao)baseDao;
	}

	/**
	 *  根据省份 Id  查找 城市
	 * @param id
	 * @return
     */
	public List<City> findCityByProvinceId(Integer id)
	{
		return  cityDao.findCityByProvinceId(id);
	}


	/**
	 *  查找所有的 城市
	 * @return
     */
	public List<City> findAll()
	{
		return  cityDao.findCity();
	}

	/**
	 *  根据名字 查找  城市
	 * @return
	 */
	public City getCityByName(String name)
	{
		return  cityDao.getCityByName(name);
	}


}
