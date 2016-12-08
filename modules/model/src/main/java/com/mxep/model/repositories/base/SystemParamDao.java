package com.mxep.model.repositories.base;

import com.mxep.model.BaseDao;
import com.mxep.model.sys.Param;

public interface SystemParamDao extends BaseDao<Param, Integer> {

	Param findSysParamByKey(String key);
}
