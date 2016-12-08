package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.common.City;
import com.mxep.model.common.Province;
import com.mxep.model.goods.CityFreightPrice;
import com.mxep.model.repositories.common.CityDao;
import com.mxep.model.repositories.common.ProvinceDao;
import com.mxep.model.repositories.goods.CityFreightPriceDao;
import com.mxep.service.CommonService;
import com.mxep.web.common.exception.WebRequestException;
import com.mxep.web.web.JsonMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * Service - 城市运费
 */
@Service
public class CityFreightPriceService extends BaseService<CityFreightPrice, Integer> {

	private CityFreightPriceDao cityFreightPriceDao;

	@Resource
	private CommonService commonService;

	@Resource
	private ProvinceDao provinceDao;

	@Resource
	private CityDao cityDao;

	@Resource
	@Override
	public void setBaseDao(BaseDao<CityFreightPrice, Integer> baseDao) {
		this.baseDao = baseDao;
		cityFreightPriceDao =( CityFreightPriceDao) baseDao;
	}
	/**
	 * 查询城市运费信息
	 * 
	 * @param id
	 * @return 信息
	 */
	@Transactional(readOnly = true)
	public CityFreightPrice findCityFreightPrice(Integer id) {
		if (id == null) {
			return null;
		}
		return cityFreightPriceDao.findOne(id);
	}

	/**
	 * 保存城市运费
	 */
	@Override
	@Transactional
	public CityFreightPrice save(CityFreightPrice cityFreightPrice) {
		// 从数据库总查询QA信息
		Timestamp currentTime = commonService.getCurrentTime();
		CityFreightPrice entity = findCityFreightPrice(cityFreightPrice.getId());
		if (entity == null) {
			// 新建城市运费
			entity = cityFreightPrice;
			entity.setCreateTime(currentTime);
		} else {
			// 保存城市运费
			Date createTime = entity.getCreateTime();
			entity = cityFreightPrice;
			entity.setCreateTime(createTime);
		}

		return cityFreightPriceDao.save(entity);
	}

	/**
	 * 删除城市运费
	 */
	@Transactional
	public JsonMap deleteCityFreightPrice(Integer[] ids) {
		CityFreightPrice cityFreightPrice;
		for (Integer id : ids) {
			cityFreightPrice = cityFreightPriceDao.findOne(id);
			if (cityFreightPrice == null) {
				continue;
			}
			cityFreightPriceDao.delete(cityFreightPrice);
		}
		return new JsonMap(0,"删除成功");
	}


	/**
	 * 批量 增加  会员
	 * @param provinces
	 * @param cities
	 * @param prices
	 * @return
	 */
	public JsonMap addCityFreightPrices(String[] provinces,String[] cities,String[] prices)
	{

		for(int i =0; i<provinces.length; i++) {
			Province province = provinceDao.getProvinceByName(provinces[i]);
			if(null == province){
				continue;
			}
			City city = cityDao.findCityByName(cities[i]);
			if(null == city){
				continue;
			}
			if(city.getPid() != province.getId()) {
				continue;
			}
			BigDecimal price = null;
			try {
				price = new BigDecimal(prices[i]);
				CityFreightPrice cityFreightPrice = new CityFreightPrice();
				cityFreightPrice.setProvinceId(province.getId());
				cityFreightPrice.setCityId(city.getId());
				cityFreightPrice.setFreightPrice(price);
				cityFreightPrice.setCreateTime(commonService.getCurrentTime());
				cityFreightPriceDao.save(cityFreightPrice);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				throw new WebRequestException("价格只能是数字");
			}
		}
		return  new JsonMap(0,"保存成功");
	}








}
