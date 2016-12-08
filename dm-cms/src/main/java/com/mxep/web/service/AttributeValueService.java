package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.AttributeValue;
import com.mxep.model.repositories.goods.AttributeValueDao;
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
public class AttributeValueService extends BaseService<AttributeValue, Integer> {

	private final Logger logger = LoggerFactory.getLogger(AttributeValueService.class);

	private AttributeValueDao attributeValueDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<AttributeValue, Integer> baseDao) {
		this.baseDao = baseDao;
		attributeValueDao = (AttributeValueDao) baseDao;
	}


	/**
	 *  根据 配置 id  查找  对应的 配置
	 * @return
     */
	public List<AttributeValue> getAttributeValueByAttrbuteId(Integer id)
	{
		return attributeValueDao.getAttributeValueByAttrbuteId(id);
	}


	/**
	 * 删除  特性
	 */
	@Transactional
	public JsonMap deleteattributeValue(Integer[] ids) {
		AttributeValue attributeValue;
		for (Integer id : ids) {
			attributeValue = attributeValueDao.findOne(id);
			if (attributeValue == null) {
				continue;
			}
			attributeValue.setFlag((byte) -1);
			attributeValueDao.save(attributeValue);
		}
		return new JsonMap(0,"删除成功");
	}

	/**
	 * 启用 特性
	 */
	@Transactional
	public JsonMap enable(Integer[] ids) {
		AttributeValue attributeValue;
		for (Integer id : ids) {
			attributeValue = attributeValueDao.findOne(id);
			if (attributeValue == null) {
				continue;
			}
			attributeValue.setStatus( (byte)1);
			attributeValueDao.save(attributeValue);
		}
		return new JsonMap(0, "启用成功");
	}

	/**
	 * 禁用 特性
	 */
	@Transactional
	public JsonMap disable(Integer[] ids) {
		AttributeValue attributeValue;
		for (Integer id : ids) {
			attributeValue = attributeValueDao.findOne(id);
			if (attributeValue == null) {
				continue;
			}
			attributeValue.setStatus( (byte)0);
			attributeValueDao.save(attributeValue);
		}
		return new JsonMap(0, "禁用成功");
	}


	/**
	 * 保存特性
	 */
	@Transactional
	public AttributeValue save(AttributeValue attributeValue) {
		Timestamp currentTime = commonService.getCurrentTime();
		if(null != attributeValue.getId()) {
			attributeValue.setUpdateTime(currentTime);
		} else {
			attributeValue.setCreateTime(currentTime);
			attributeValue.setUpdateTime(currentTime);
		}
		attributeValue.setColor("#"+attributeValue.getColor());
		attributeValue.setPercentum(attributeValue.getPercentum()+"%");
		attributeValueDao.save(attributeValue);
		return attributeValue;
	}





}
