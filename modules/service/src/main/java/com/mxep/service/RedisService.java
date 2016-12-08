package com.mxep.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;

public interface RedisService {

    /**
     * 通过key删除
     *
     * @param keys
     */
    long del(String... keys);

    /**
     * 通过key删除
     *
     * @param keys
     */
    long del(final Set<byte[]> keys);

    /**
     * 添加key value 并且设置存活时间(byte)
     *
     * @param key
     * @param value
     * @param liveTime
     */
    void set(byte[] key, byte[] value, long liveTime);

    /**
     * 添加key List 并且设置存活时间(byte)
     *
     * @param key
     * @param values
     * @param liveTime
     */
    void setList(String key, List<? extends Object> values, long liveTime);


    /**
     * 添加可排序的结果集
     *
     * @param key
     * @param value
     * @param score
     */
    void addSortSet(String key, Object value, Long score);

    /**
     * 获取排序结果集
     *
     * @param key
     * @param start
     * @param end
     * @param <T>
     * @return
     */
    <T> Set<T> getSortSet(String key, int start, int end);


    /**
     * 从排序结果队列中移除元素
     *
     * @param key
     * @param start
     * @param end
     */
    void removeSortSet(String key, int start, int end);

    /**
     * 添加实体到List,并且设置存活时间(byte)
     *
     * @param key
     * @param entity
     * @param liveTime
     */
    void setList(String key, Object entity, long liveTime);

    /**
     * 添加实体到List的最右边,并且设置存活时间(byte)
     *
     * @param key
     * @param entity
     * @param liveTime
     */
    void setRightList(String key, Object entity, long liveTime);

    /**
     * 获取List对象
     *
     * @param key
     * @return
     */
    <T> List<T> getList(String key);

    /**
     * 获取区间List对象
     *
     * @param key
     * @return
     */
    <T> List<T> getRangeList(String key, int startIndex, int endIndex);

    /**
     * 添加key, entity, 并且设置存活时间(byte)
     *
     * @param key
     * @param entity
     * @param liveTime
     */
    void setEntity(String key, Object entity, long liveTime);

    /**
     * 获取实体
     *
     * @param key
     * @return
     */
    <T> T getEntity(String key);

    /**
     * 添加key value 并且设置存活时间
     *
     * @param key
     * @param value
     * @param liveTime 单位秒
     */
    void set(String key, String value, long liveTime);

    String get(String key);

    /**
     * 添加key value
     *
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * 添加key value (字节)(序列化)
     *
     * @param key
     * @param value
     */
    void set(byte[] key, byte[] value);

    /**
     * 使用hash添加key,value
     *
     * @param key
     * @param paramKey
     * @param paramValue
     */
    void set(String key, Object paramKey, Object paramValue);

    /**
     * 使用hash添加key,Map
     *
     * @param key
     * @param paramMap
     */
    void setMap(String key, Map<? extends Object, ? extends Object> paramMap, long liveTime);

    /**
     * 获取Map对象
     *
     * @param key
     * @return
     */
    <K, V> Map<K, V> getMap(String key);

    /**
     * 从hash里面获取缓存键值
     *
     * @param key
     * @param paramKey
     * @return
     */
    <T> T get(String key, String paramKey);

    <T> T leftPop(String key);

    /**
     * @param key
     * @param queryCallback
     * @param liveTime
     * @return
     */
    <T> T findEntityForCacheOrDb(final String key, QueryCallback<T> queryCallback, long liveTime);

    /**
     * @param key
     * @param queryCallback
     * @param liveTime
     * @return
     */
    <K, V> Map<K, V> findMapForCacheOrDb(final String key, QueryCallback<Map<K, V>> queryCallback, long liveTime);

    /**
     * @param key
     * @param queryCallback
     * @param liveTime
     * @return
     */
    <T> List<T> findListForCacheOrDb(final String key, QueryCallback<List<T>> queryCallback, long liveTime);


    /**
     * @param key
     * @param queryCallback
     * @param liveTime
     * @return
     */
    <T> Set<T> findSetForCacheOrDb(final String key, QueryCallback<Set<T>> queryCallback, long liveTime);

    /**
     * 通过正则匹配keys
     *
     * @param pattern
     * @return
     */
    Set<byte[]> keys(String pattern);

    ListOperations<Serializable, Object> getOpsForList();

    SetOperations<Serializable, Object> getOpsForSet();

    ZSetOperations<Serializable, Object> getOpsForZset();

    /**
     * 检查key是否已经存在
     *
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * 清空redis 所有数据
     *
     * @return
     */
    String flushDB();

    /**
     * 查看redis里有多少数据
     */
    long dbSize();

    /**
     * 检查是否连接成功
     *
     * @return
     */
    String ping();

}
