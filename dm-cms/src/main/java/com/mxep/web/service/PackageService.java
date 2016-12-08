package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.Package;
import com.mxep.model.repositories.goods.PackageDao;
import com.mxep.service.CommonService;
import com.mxep.web.web.JsonMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * Service - 服务
 */
@Service
public class PackageService extends BaseService<Package, Integer> {

	private PackageDao packageDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<Package, Integer> baseDao) {
		this.baseDao = baseDao;
		packageDao =(PackageDao) baseDao;
	}

	/**
	 * 查询所有 可用服务
	 * @return 信息
	 */
	@Transactional(readOnly = true)
	public List<Package> findAllPackage() {
		return  packageDao.findAllPackage();
	}

	/**
	 * 查询服务信息
	 * 
	 * @param id
	 * @return 信息
	 */
	@Transactional(readOnly = true)
	public Package findServiceItem(Integer id) {
		if (id == null) {
			return null;
		}
		return packageDao.findOne(id);
	}

	/**
	 * 保存服务
	 */
	@Override
	@Transactional
	public Package save(Package package1) {
		// 从数据库总查询QA信息
		if(null == package1.getCategoryId())
		{
			package1.setCategoryId(0);
		}
		Timestamp currentTime = commonService.getCurrentTime();
		Package entity = findServiceItem(package1.getId());
		if (entity == null) {
			// 新建品牌
			entity = package1;
			entity.setCreateTime(currentTime);
			entity.setUpdateTime(currentTime);
		} else {
			// 保存品牌
			Date createTime = entity.getCreateTime();
			entity = package1;
			entity.setCreateTime(createTime);
			entity.setUpdateTime(currentTime);
		}

		return packageDao.save(entity);
	}

	/**
	 * 删除服务
	 */
	@Transactional
	public JsonMap deleteServiceItem(Integer[] ids) {
		Package package1;
		for (Integer id : ids) {
			package1 = packageDao.findOne(id);
			if (package1 == null) {
				continue;
			}
			package1.setFlag((byte) -1);
			packageDao.save(package1);
		}
		return new JsonMap(0,"删除成功");
	}

	/**
	 * 启用 服务
	 */
	@Transactional
	public JsonMap enable(Integer[] ids) {
		Package package1;
		for (Integer id : ids) {
			package1 = packageDao.findOne(id);
			if (package1 == null) {
				continue;
			}
			package1.setStatus( (byte)1);
			save(package1);
		}
		return new JsonMap(0, "启用成功");
	}

	/**
	 *  禁用 服务
	 */
	@Transactional
	public JsonMap disable(Integer[] ids) {
		Package package1;
		for (Integer id : ids) {
			package1 = packageDao.findOne(id);
			if (package1 == null) {
				continue;
			}
			package1.setStatus( (byte) 0);
			save(package1);
		}
		return new JsonMap(0, "禁用成功");
	}
}
