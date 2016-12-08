package com.mxep.web.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mxep.model.BaseDao;
import com.mxep.model.log.LogRecharge;
import com.mxep.model.repositories.order.LogRechargeDao;
import com.mxep.service.CommonService;
import com.mxep.web.common.persistence.DynamicSpecifications;
import com.mxep.web.common.persistence.SearchFilter;

/**
 * Service - 充值记录管理
 */
@Service
public class MemberRechargeService extends BaseService<LogRecharge, Integer> {

	private final Logger logger = LoggerFactory.getLogger(MemberRechargeService.class);

	private LogRechargeDao rechargeDao;
	
	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<LogRecharge, Integer> baseDao) {
		this.baseDao = baseDao;
		rechargeDao = (LogRechargeDao)baseDao;
	}
	
	/**
	 * 查询充值记录
	 *
	 * @param id 充值记录编号
	 * @return 充值记录信息
	 */
	@Transactional(readOnly = true)
	public LogRecharge findRecharge(Integer id) {
		if (id == null) {
			return null;
		}
		return rechargeDao.findOne(id);
	}
	
	/**
     * 根据搜索条件查找所有的充值记录
     *
     * @param params
     * @param sort
     * @return
     */
    @Transactional(readOnly = true)
    public List<LogRecharge> list(Map<String, Object> params, Sort sort) {

        Map<String, SearchFilter> filters = SearchFilter.parse(params);
        Specification<LogRecharge> spec = DynamicSpecifications.bySearchFilter(filters.values(),
                (Class<LogRecharge>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);

        return baseDao.findAll(spec, sort);
    }
}
