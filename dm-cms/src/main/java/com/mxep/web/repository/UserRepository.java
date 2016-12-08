/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.mxep.web.repository;

import com.mxep.model.BaseDao;
import com.mxep.model.sys.User;

/**
 * Dao - 系统用户
 */
public interface UserRepository extends BaseDao<User, Integer> {

	/**
	 * 根据登陆帐号查询用户
	 *
	 * @param account 登陆帐号
	 * @return 用户信息
	 */
	User findByAccount(String account);

}
