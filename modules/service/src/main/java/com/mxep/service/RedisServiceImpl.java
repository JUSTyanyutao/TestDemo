package com.mxep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unchecked")
@Service
public class RedisServiceImpl implements RedisService {

    private static String redisCode = "utf-8";

    /**
     * @param keys
     */
    @Override
    public long del(final String... keys) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                Long result = 0l;
                for (int i = 0; i < keys.length; i++) {
                    result = connection.del(keys[i].getBytes());
                }
                return result;
            }
        });
    }

    /**
     * @param keys
     */
    @Override
    public long del(final Set<byte[]> keys) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                Long result = 0l;
                Iterator<byte[]> it = keys.iterator();
                while (it.hasNext()) {
                    result = connection.del(it.next());
                }
                return result;
            }
        });
    }

    /**
     * @param key
     * @param value
     * @param liveTime
     */
    @Override
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }


    /**
     * 添加排序的结果集,排序根据score得分大小
     *
     * @param key
     * @param value
     * @param score
     */
    public void addSortSet(String key, Object value, Long score) {
        redisTemplate.opsForZSet().add(key, value, Double.longBitsToDouble(score));
    }


    public <T> Set<T> getSortSet(String key, int start, int end) {
        return (Set<T>) redisTemplate.opsForZSet().range(key, start, end);
    }


    /**
     * 从排序结果队列中移除元素
     *
     * @param key
     * @param start
     * @param end
     */
    public void removeSortSet(String key, int start, int end) {
        redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    @Override
    public void setList(String key, List<? extends Object> values, long liveTime) {
        redisTemplate.opsForList().leftPushAll(key, values.toArray());
        if (liveTime > 0) {
            redisTemplate.expire(key, liveTime, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void setList(String key, Object entity, long liveTime) {
        redisTemplate.opsForList().leftPush(key, entity);
        if (liveTime > 0) {
            redisTemplate.expire(key, liveTime, TimeUnit.MILLISECONDS);
        }

    }

    @Override
    public void setRightList(String key, Object entity, long liveTime) {
        redisTemplate.opsForList().rightPush(key, entity);
        if (liveTime > 0) {
            redisTemplate.expire(key, liveTime, TimeUnit.MILLISECONDS);
        }

    }


    /**
     * 阻塞获取list对象
     *
     * @param key
     * @return
     */
    public <T> T leftPop(String key) {
        return (T) redisTemplate.opsForList().leftPop(key, 100, TimeUnit.MICROSECONDS);
    }

    @Override
    public <T> List<T> getRangeList(String key, int startIndex, int endIndex) {
        return (List<T>) redisTemplate.opsForList().range(key, startIndex, endIndex);
    }

    @Override
    public <T> List<T> getList(String key) {
        return (List<T>) redisTemplate.opsForList().range(key, 0, -1);

    }

    @Override
    public void setEntity(String key, Object entity, long liveTime) {
        redisTemplate.opsForValue().set(key, entity);
        if (liveTime > 0) {
            redisTemplate.expire(key, liveTime, TimeUnit.MILLISECONDS);
        }

    }

    @Override
    public <T> T getEntity(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * @param key
     * @param value
     * @param liveTime
     */
    @Override
    public void set(String key, String value, long liveTime) {
        this.set(key.getBytes(), value.getBytes(), liveTime);
    }

    /**
     * @param key
     * @param value
     */
    @Override
    public void set(String key, String value) {
        this.set(key, value, 0L);
    }

    /**
     * @param key
     * @param value
     */
    @Override
    public void set(byte[] key, byte[] value) {
        this.set(key, value, 0L);
    }

    @Override
    public void set(String key, Object paramKey, Object paramValue) {
        redisTemplate.opsForHash().put(key, paramKey, paramValue);
    }

    @Override
    public void setMap(String key, Map<? extends Object, ? extends Object> paramMap, long liveTime) {
        redisTemplate.opsForHash().putAll(key, paramMap);
        if (liveTime > 0) {
            redisTemplate.expire(key, liveTime, TimeUnit.MICROSECONDS);
        }

    }

    @Override
    public <K, V> Map<K, V> getMap(String key) {
        return (Map<K, V>) redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取hash缓存键值
     *
     * @param key
     * @param paramKey
     * @return
     */
    @Override
    public <T> T get(String key, String paramKey) {
        return (T) redisTemplate.opsForHash().get(key, paramKey);
    }

    /**
     * @param key
     * @return
     */
    public String get(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    return new String(connection.get(key.getBytes()), redisCode);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return "";
            }
        });
    }

    @Override
    public <T> T findEntityForCacheOrDb(final String key, QueryCallback<T> queryCallback, long liveTime) {
        if (redisTemplate.hasKey(key)) {
            return getEntity(key);
        } else {
            T val = queryCallback.doInDb();
            if (null != val) {
                setEntity(key, val, liveTime);
            }
            return val;
        }
    }

    @Override
    public <K, V> Map<K, V> findMapForCacheOrDb(String key, QueryCallback<Map<K, V>> queryCallback, long liveTime) {
        if (redisTemplate.hasKey(key)) {
            return getMap(key);
        } else {
            Map<K, V> val = queryCallback.doInDb();
            if (null != val && !val.isEmpty()) {
                setMap(key, val, liveTime);
            }
            return val;
        }
    }

    @Override
    public <T> List<T> findListForCacheOrDb(String key, QueryCallback<List<T>> queryCallback, long liveTime) {
        if (redisTemplate.hasKey(key)) {
            return getList(key);
        } else {
            List<T> val = queryCallback.doInDb();
            if (null != val && val.size() > 0) {
                setList(key, val, liveTime);
            }
            return val;
        }
    }

    @Override
    public <T> Set<T> findSetForCacheOrDb(String key, QueryCallback<Set<T>> queryCallback, long liveTime) {
        if (redisTemplate.hasKey(key)) {
            return getSortSet(key, 0, -1);
        }
        if (liveTime > 0) {
            redisTemplate.expire(key, liveTime, TimeUnit.MICROSECONDS);
        }
        return queryCallback.doInDb();
    }

    /**
     * @param pattern
     * @return
     */
    @Override
    public Set<byte[]> keys(String pattern) {
        return redisTemplate.getConnectionFactory().getConnection().keys(pattern.getBytes());

    }

    /**
     * @param key
     * @return
     */
    @Override
    public boolean exists(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }

    /**
     * @return
     */
    @Override
    public String flushDB() {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    /**
     * @return
     */
    @Override
    public long dbSize() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /**
     * @return
     */
    @Override
    public String ping() {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
    }

    @Override
    public ListOperations<Serializable, Object> getOpsForList() {
        return redisTemplate.opsForList();
    }

    @Override
    public SetOperations<Serializable, Object> getOpsForSet() {
        return redisTemplate.opsForSet();
    }

    @Override
    public ZSetOperations<Serializable, Object> getOpsForZset() {
        return redisTemplate.opsForZSet();
    }

    private RedisServiceImpl() {

    }

    @Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;

}
