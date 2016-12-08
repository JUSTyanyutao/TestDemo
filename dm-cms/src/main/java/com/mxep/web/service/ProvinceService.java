package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.common.City;
import com.mxep.model.common.Province;
import com.mxep.model.repositories.common.CityDao;
import com.mxep.model.repositories.common.ProvinceDao;
import com.mxep.service.CommonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Service - 城市
 */
@Service
public class ProvinceService extends BaseService<Province, Integer> {

	private ProvinceDao provinceDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<Province, Integer> baseDao) {
		this.baseDao = baseDao;
		provinceDao =( ProvinceDao)baseDao;
	}

	public List<Province> findAllProvince()
	{
		return provinceDao.findAllProvince();
	}




}
