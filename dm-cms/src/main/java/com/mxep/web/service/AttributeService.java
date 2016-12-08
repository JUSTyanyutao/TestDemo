package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.Attribute;
import com.mxep.model.goods.Attribute;
import com.mxep.model.repositories.goods.AttributeDao;
import com.mxep.model.repositories.goods.AttributeDao;
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
 * Service -   特性 管理
 */
@Service
@Transactional
public class AttributeService extends BaseService<Attribute, Integer> {

	private final Logger logger = LoggerFactory.getLogger(AttributeService.class);

	private AttributeDao attributeDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<Attribute, Integer> baseDao) {
		this.baseDao = baseDao;
		attributeDao = (AttributeDao) baseDao;
	}


	/**
	 * 查找所有  属性
	 * @return
     */
	public List<Attribute> findAllAttribute()
	{
		return attributeDao.findAllAttribute();
	}


	/**
	 * 删除  特性
	 */
	@Transactional
	public JsonMap deleteattribute(Integer[] ids) {
		Attribute attributeValue;
		for (Integer id : ids) {
			attributeValue = attributeDao.findOne(id);
			if (attributeValue == null) {
				continue;
			}
			attributeValue.setFlag((byte) -1);
			attributeDao.save(attributeValue);
		}
		return new JsonMap(0,"删除成功");
	}

	/**
	 * 启用 特性
	 */
	@Transactional
	public JsonMap enable(Integer[] ids) {
		Attribute attributeValue;
		for (Integer id : ids) {
			attributeValue = attributeDao.findOne(id);
			if (attributeValue == null) {
				continue;
			}
			attributeValue.setStatus( (byte)1);
			attributeDao.save(attributeValue);
		}
		return new JsonMap(0, "启用成功");
	}

	/**
	 * 禁用 特性
	 */
	@Transactional
	public JsonMap disable(Integer[] ids) {
		Attribute attributeValue;
		for (Integer id : ids) {
			attributeValue = attributeDao.findOne(id);
			if (attributeValue == null) {
				continue;
			}
			attributeValue.setStatus( (byte)0);
			attributeDao.save(attributeValue);
		}
		return new JsonMap(0, "禁用成功");
	}


	/**
	 * 保存特性
	 */
	@Transactional
	public Attribute save(Attribute attributeValue) {
		Timestamp currentTime = commonService.getCurrentTime();
		if(null != attributeValue.getId())
		{
			attributeValue.setUpdateTime(currentTime);
		}
		else
		{
			attributeValue.setCreateTime(currentTime);
			attributeValue.setUpdateTime(currentTime);
		}
		attributeDao.save(attributeValue);
		return attributeValue;
	}





}
