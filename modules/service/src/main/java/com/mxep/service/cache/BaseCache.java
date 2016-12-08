package com.mxep.service.cache;

import org.springframework.beans.factory.annotation.Autowired;

import com.mxep.service.RedisService;


abstract class BaseCache {

    @Autowired
    protected RedisService redisService;


}
