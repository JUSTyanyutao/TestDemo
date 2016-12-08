package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.Constant;
import com.mxep.model.base.Area;
import com.mxep.model.repositories.base.AreaDao;
import com.mxep.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service - 地区
 */
@Service
public class AreaService extends BaseService<Area, Integer> {

	private final Logger logger = LoggerFactory.getLogger(AreaService.class);

	private AreaDao areaDao;
	
	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<Area, Integer> baseDao) {
		this.baseDao = baseDao;
		areaDao = (AreaDao)baseDao;
	}
	
	
	/**
	 * 查询地区
	 *
	 * @param id 编号
	 * @return 地区信息
	 */
	@Transactional(readOnly = true)
	public Area findArea(Integer id) {
		if (id == null) {
			return null;
		}
		return areaDao.findOne(id);
	}
	
	/**
	 * 删除地区
	 *
	 * @param ids 编号数组
	 */
	@Override
	@Transactional
	public void delete(Integer[] ids) {
		Area area;
		for (Integer id : ids) {
			area = findArea(id);
			if (area == null) {
				continue;
			}
			//member.s(Member.MemberStatus.disable.value);

			areaDao.save(area);
		}
	}

	/**
	 * 根据上级编号查询地区信息
	 *
	 * @param pid 上级编号
	 * @return 地区信息
     */
	public List<Area> findByPid(Integer pid) {
		return areaDao.findByPid(pid);
	}
	
	/**
	 * 查找国家下的所有省份
	 * @param countryId
	 * @return
	 */
	public List<Area> getProvinces(Integer countryId){
		return areaDao.findProvinces(countryId);
	}

	/**
	 * 查询中国省份
	 *
	 * @return 中国省份
     */
	public List<Area> findChinaProvinces() {
		return getProvinces(Constant.CHINA_AREA_ID);
	}
	
	/**
	 * 查找省份下的所有城市
	 * @param provinceId
	 * @return
	 */
	public List<Area> getCitys(Integer provinceId){
		return areaDao.findCitys(provinceId);
	}
	
	/**
	 * 查找城市下的所有县区
	 * @param cityId
	 * @return
	 */
	public List<Area> getCountys(Integer cityId){
		return areaDao.findCountys(cityId);
	}
	
	
	
	
	


}
