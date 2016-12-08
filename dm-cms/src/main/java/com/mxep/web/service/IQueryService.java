package com.mxep.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

public interface IQueryService<T> {

	public Page<T> findAsPage(int pageNum, Map<String, Object> params);

	public String findAsSql(Map<String, Object> params);

	public List<T> findAsList(Map<String, Object> params);

}
