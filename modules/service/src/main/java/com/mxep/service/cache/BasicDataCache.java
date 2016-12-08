package com.mxep.service.cache;

import com.mxep.model.RedisKeys;
import com.mxep.model.base.Area;
import com.mxep.model.repositories.base.AreaDao;
import com.mxep.model.repositories.base.SystemParamDao;
import com.mxep.model.sys.Param;
import com.mxep.service.QueryCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Cache - 缓存服务
 */
@Service
public class BasicDataCache extends BaseCache {

	@Resource
	private SystemParamDao systemParamDao;

	@Resource
	private AreaDao areaDao;

	/**
	 * 查询系统参数
	 *
	 * @param key 键
	 * @return 值
	 */
	public Param findSystemParam(final String key) {

		return redisService.findEntityForCacheOrDb(RedisKeys.SYSTEM_PARAM.key + key,
				new QueryCallback<Param>() {
					@Override
					public Param doInDb() {
						return systemParamDao.findSysParamByKey(key);
					}
				}, RedisKeys.LiveTime.HOURS_1.time);
	}

	/**
	 * 查询所有地区数据
	 *
	 * @return 值
	 */
	public List<Area> findAreaList() {
		return  redisService.findEntityForCacheOrDb(RedisKeys.AREA.key,
				new QueryCallback<List<Area>>() {
					@Override
					public List<Area> doInDb() {
						return areaDao.findAreaList();
					}
		}, RedisKeys.LiveTime.HOURS_2.time);
	}


	/**
	 * 缓存数据
	 *
	 * @param key 键
	 * @param data 值
	 * @param mseconds 时间
	 */
	public void push(String key, Object data, Long mseconds) {
		redisService.setEntity(key, data, mseconds);
	}

	/**
	 * 查询缓存数据
	 *
	 * @param key 键
	 * @return 值
	 */
	public Object pull(String key) {
		return redisService.getEntity(key);
	}

	/**
	 * 删除缓存
	 *
	 * @param key 键
	 */
	public void delete(String key) {
		redisService.del(key);
	}
}
