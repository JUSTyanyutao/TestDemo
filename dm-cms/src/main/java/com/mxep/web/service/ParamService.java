package com.mxep.web.service;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mxep.model.BaseDao;
import com.mxep.model.repositories.base.SystemParamDao;
import com.mxep.model.sys.Param;
import com.mxep.service.CommonService;

/**
 * Service - 系统参数
 */
@Service
public class ParamService extends BaseService<Param, Integer> {

	private SystemParamDao systemParamDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<Param, Integer> baseDao) {
		this.baseDao = baseDao;
		systemParamDao =( SystemParamDao)baseDao;
	}
	/**
	 * 查询系统参数信息
	 * 
	 * @param id
	 *            系统参数编号
	 * @return 系统参数信息
	 */
	@Transactional(readOnly = true)
	public Param findParam(Integer id) {
		if (id == null) {
			return null;
		}
		return systemParamDao.findOne(id);
	}

	/**
	 * 保存系统参数
	 * 
	 * @param param
	 *            系统参数
	 * @return 系统参数
	 */
	@Override
	@Transactional
	public Param save(Param param) {
		return systemParamDao.save(param);
	}


	/**
	 * 删除参数
	 */

	@Transactional
	public void delete(Integer[] ids){
		Param param;
		for (Integer id:ids){
			param=systemParamDao.findOne(id);
			if (param==null){
				continue;
			}
			systemParamDao.delete(id);
		}
	}
	
}
