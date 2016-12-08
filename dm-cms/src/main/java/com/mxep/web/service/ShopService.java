package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.common.City;
import com.mxep.model.member.Shop;
import com.mxep.model.member.ShopWorker;
import com.mxep.model.repositories.common.CityDao;
import com.mxep.model.repositories.member.ShopDao;
import com.mxep.model.repositories.member.ShopWorkerDao;
import com.mxep.service.CommonService;
import com.mxep.web.common.exception.WebRequestException;
import com.mxep.web.web.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;


/**
 * Service - 场地管理
 */
@Service
@Transactional
public class ShopService extends BaseService<Shop, Integer> {

	private final Logger logger = LoggerFactory.getLogger(ShopService.class);

	private ShopDao shopDao;

	@Resource
	private CityDao cityDao;

	@Resource
	private ShopWorkerDao shopWorkerDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<Shop, Integer> baseDao) {
		this.baseDao = baseDao;
		shopDao = (ShopDao) baseDao;
	}


	/**
	 * 查找所有 可用 的 4s场地
	 * @return
     */
	public List<Shop> findAll4sShop()
	{
		return shopDao.findAll4sShop();
	}


	public Shop findShop(Integer id)
	{
		if(null == id)
		{
			return null;
		}
		return shopDao.findOne(id);
	}


	/**
	 * 删除场地
	 */
	@Transactional
	public JsonMap deleteShop(Integer[] ids) {
		Shop shop;
		for (Integer id : ids) {
			shop = shopDao.findOne(id);
			if (shop == null) {
				continue;
			}
//			if(shop.getMemberShopList().size() !=0)
//			{
//				new JsonMap(1,"该场地下有企业用户，无法删除！");
//			}
			shop.setFlag((byte) -1);
			shopDao.save(shop);
		}
		return new JsonMap(0,"删除成功");
	}

	/**
	 * 审核场地
	 */
	@Transactional
	public JsonMap audit(Integer id,byte status,String remark) {
		Shop shop = shopDao.findOne(id);
		shop.setStatus(status);
		shop.setRemark(remark);
		shopDao.save(shop);
		return new JsonMap(0, "审核成功");
	}

	/**
	 * 禁用场地
	 */
	@Transactional
	public JsonMap disable(Integer[] ids) {
		Shop shop;
		for (Integer id : ids) {
			shop = shopDao.findOne(id);
			if (shop == null) {
				continue;
			}
			shop.setStatus( (byte)0);
			save(shop);
		}
		return new JsonMap(0, "禁用成功");
	}


	/**
	 * 保存 4s场地  并且保存对应的 技师
	 */
	@Transactional
	public Shop save(Shop shop,String name) {

		City city = cityDao.getCityByName(name);
		shop.setCityId(city.getId());
		Timestamp currentTime = commonService.getCurrentTime();

		Shop entity = findShop(shop.getId());
		if(entity == null)
		{
			entity = new Shop();
			entity.setCreateTime(currentTime);
			entity.setStatus( (byte)1);
		}
		entity.setCityId(shop.getCityId());
		entity.setName(shop.getName());
		entity.setContact(shop.getContact());
		entity.setPhone(shop.getPhone());
		entity.setServiceTimeAm(shop.getServiceTimeAm());
		entity.setServiceTimePm(shop.getServiceTimePm());
		entity.setCarBrandId(shop.getCarBrandId());
		entity.setAddress(shop.getAddress());
		entity.setLng(shop.getLng());
		entity.setLat(shop.getLat());
		entity.setPictures(shop.getPictures());
		entity.setUpdateTime(currentTime);
		entity.setType( (byte)1);     //设为 4s店  场地
		Shop temp = shopDao.save(entity);

		List<ShopWorker> oldShopWorkerList = shopWorkerDao.findShopWorkerByShopId(temp.getId());
		shopWorkerDao.delete(oldShopWorkerList);

		List<ShopWorker> shopWorkerList = shop.getShopWorkerList();
		for(ShopWorker sw:shopWorkerList)
		{
			if(sw.getMemberId() == null)
			{
				continue;
			}
			sw.setShopId(temp.getId());
			shopWorkerDao.save(sw);
		}
		return entity;
	}


	/**
	 * 编辑  保存 街边场地
	 */
	@Transactional
	public Shop saveStreet(Shop shop,String name) {

		City city = cityDao.getCityByName(name);
		shop.setCityId(city.getId());
		Timestamp currentTime = commonService.getCurrentTime();

		Shop entity = findShop(shop.getId());
		if(entity == null)
		{
//			entity = new Shop();
//			entity.setCreateTime(currentTime);
//			entity.setStatus( (byte)1);
			throw new WebRequestException("不存在的场地");
		}
		entity.setCityId(shop.getCityId());
		entity.setName(shop.getName());
		entity.setContact(shop.getContact());
		entity.setPhone(shop.getPhone());
//		entity.setCarBrandId(shop.getCarBrandId());
		entity.setAddress(shop.getAddress());
		entity.setLng(shop.getLng());
		entity.setLat(shop.getLat());
		entity.setPictures(shop.getPictures());
		entity.setUpdateTime(currentTime);
//		entity.setType( (byte)2);     //设为 4s店  场地
		shopDao.save(entity);

		return entity;
	}




}
