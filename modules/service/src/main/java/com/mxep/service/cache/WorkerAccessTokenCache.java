package com.mxep.service.cache;

import com.mxep.model.RedisKeys;
import org.springframework.stereotype.Service;

/**
 * 物流工人access_token
 *
 * @author ranfi
 */
@Service
public class WorkerAccessTokenCache extends BaseCache {

    // 保存5个小时
    public static final RedisKeys.LiveTime expireTime = RedisKeys.LiveTime.HOURS_5;

    /**
     * 把token放入到缓存中
     *
     * @param workerId    物流人员ID
     * @param accessToken token
     */
    public void pushToken(Integer workerId, String accessToken) {
        redisService.setEntity(accessToken, workerId, expireTime.time);
    }

    /**
     * 从缓存中删除token信息
     *
     * @param accessToken token
     */
    public void removeToken(String accessToken) {
        redisService.del(accessToken);
    }

    /**
     * 判断token是否存在
     *
     * @param accessToken token
     * @return 是否存在
     */
    public boolean isTokenExists(String accessToken) {
        return redisService.exists(accessToken);
    }

    /**
     * 根据token查询物流工人编号
     *
     * @param accessToken token
     * @return 物流工人ID
     */
    public Integer getWorkerId(String accessToken) {
        return redisService.getEntity(accessToken);
    }


}
