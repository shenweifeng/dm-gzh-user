package com.guozhengood.user.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: xu.dm
 * @Date: 2018/11/4 20:55
 * @Description:实现cache共享
 */
@SuppressWarnings("unchecked")
public class ShiroCache<K, V> implements Cache<K, V> {

    private static final String REDIS_SHIRO_CACHE = "dmerp:shiro:cache:";
    private String cacheKey;
    private RedisTemplate<K, V> redisTemplate;

    // shiro-session-timeout
    private long globExpire = 43200;

    @SuppressWarnings("rawtypes")
    public ShiroCache(String name, RedisTemplate client) {
        if(client==null)
            throw new NullPointerException();

        this.cacheKey = REDIS_SHIRO_CACHE + name + ":";
        this.redisTemplate = client;
    }

    @Override
    public V get(K key) throws CacheException {
        redisTemplate.expire(getCacheKey(key),globExpire, TimeUnit.SECONDS);
        return redisTemplate.opsForValue().get(getCacheKey(key));
    }

    @Override
    public V put(K key, V value) throws CacheException {
        V old = get(key);
        redisTemplate.opsForValue().set(getCacheKey(key),value,globExpire,TimeUnit.SECONDS);
        return old;
    }

    @Override
    public V remove(K key) throws CacheException {
        V old = get(key);
        redisTemplate.delete(getCacheKey(key));
        return old;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(keys());
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.keys(getCacheKey("*"));
    }

    @Override
    public Collection<V> values() {
        Set<K> set = keys();
        List<V> list = new ArrayList<>();
        for (K s : set) {
            list.add(get(s));
        }
        return list;
    }

    private K getCacheKey(Object k) {
        return (K) (this.cacheKey + k);
    }
}