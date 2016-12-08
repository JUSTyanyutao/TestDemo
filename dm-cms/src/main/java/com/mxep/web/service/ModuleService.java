package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.FilmBrand;
import com.mxep.model.goods.Module;
import com.mxep.model.repositories.goods.FilmBrandDao;
import com.mxep.model.repositories.goods.ModuleDao;
import com.mxep.service.CommonService;
import com.mxep.web.web.JsonMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;


/**
 * Service - 按钮
 */
@Service
public class ModuleService extends BaseService<Module, Integer> {

	private ModuleDao moduleDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<Module, Integer> baseDao) {
		this.baseDao = baseDao;
		moduleDao =( ModuleDao) baseDao;
	}
	/**
	 * 查询按钮信息
	 * 
	 * @param id
	 * @return 信息
	 */
	@Transactional(readOnly = true)
	public Module findModule(Integer id) {
		if (id == null) {
			return null;
		}
		return moduleDao.findOne(id);
	}

	/**
	 * 保存按钮
	 */
	@Override
	@Transactional
	public Module save(Module module) {
		// 从数据库总查询QA信息
		Timestamp currentTime = commonService.getCurrentTime();
		Module entity = findModule(module.getId());
		if (entity == null) {
			// 新建品牌
			entity = module;
			entity.setCreateTime(currentTime);
			entity.setUpdateTime(currentTime);
		} else {
			// 保存品牌
			Date createTime = entity.getCreateTime();
			entity = module;
			entity.setCreateTime(createTime);
			entity.setUpdateTime(currentTime);
		}

		return moduleDao.save(entity);
	}

	/**
	 * 删除按钮
	 */
	@Transactional
	public JsonMap deleteModule(Integer[] ids) {
		Module module;
		for (Integer id : ids) {
			module = moduleDao.findOne(id);
			if (module == null) {
				continue;
			}
			module.setFlag((byte) -1);
			moduleDao.save(module);
		}
		return new JsonMap(0,"删除成功");
	}

	/**
	 * 启用 按钮
	 */
	@Transactional
	public JsonMap enable(Integer[] ids) {
		Module module;
		for (Integer id : ids) {
			module = moduleDao.findOne(id);
			if (module == null) {
				continue;
			}
			module.setStatus( (byte)1);
			save(module);
		}
		return new JsonMap(0, "启用成功");
	}

	/**
	 *  禁用 按钮
	 */
	@Transactional
	public JsonMap disable(Integer[] ids) {
		Module module;
		for (Integer id : ids) {
			module = moduleDao.findOne(id);
			if (module == null) {
				continue;
			}
			module.setStatus( (byte) 0);
			save(module);
		}
		return new JsonMap(0, "禁用成功");
	}

}
