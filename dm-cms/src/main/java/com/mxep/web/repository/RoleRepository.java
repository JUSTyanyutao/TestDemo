/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.mxep.web.repository;

import java.util.List;

import com.mxep.model.BaseDao;
import org.springframework.data.jpa.repository.Query;

import com.mxep.model.sys.Role;

/**
 * Dao - 角色
 */
public interface RoleRepository extends BaseDao<Role, Integer> {

	@Query(value = "from Role where createUid = ?1 ")
	List<Role> findRoleListByUid(int uid);

	/**
	 * 查询所有角色
	 *
	 * @return 查询所有角色
	 */
	@Query(value = "from Role")
	List<Role> findRoleList();

}
