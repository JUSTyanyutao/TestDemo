package com.mxep.model.repositories.base;


import java.util.List;

import com.mxep.model.BaseDao;
import org.springframework.data.jpa.repository.Query;

import com.mxep.model.base.Label;

public interface LabelDao extends BaseDao<Label, Integer> {
	
	@Query(value = "select a from Label a where a.flag = 1")
	List<Label> findAllLabelList();
	
	@Query(value = "select a from Label a where a.type = ?1 and a.flag = 1")
	List<Label> findLabelListByType(Integer type);
	
}
