package com.industry.util;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUnit;
import org.springframework.stereotype.Component;

/**
 * @author lc
 * @date 2022/6/27
 */
@Component
public class LocalCacheUtil {
    public static final Long EXPIRATION_TIME = 1000 * 60 * 60 * 2L;
    private static TimedCache<String, String> timedCache = CacheUtil.newTimedCache(EXPIRATION_TIME);

    static {
        timedCache.schedulePrune(1000 * 60 * 15);
    }

    /**
     * 根据key获取缓存数据
     *
     * @param key key
     * @return string
     */
    public String get(String key) {
        return timedCache.get(key);
    }

    /**
     * 往 timedCache 插入元素（固定过期时间）
     *
     * @param key   key
     * @param value value
     */
    public void put(String key, String value) {
        timedCache.put(key, value);
    }

    /**
     * 往 timedCache 插入元素（自定义过期时间）
     *
     * @param key     key
     * @param value   value
     * @param timeout timeout
     */
    public void put(String key, String value, Long timeout) {
        timedCache.put(key, value, timeout);
    }

    /**
     * 获取长度
     *
     * @return timeCache长度
     */
    public Integer size() {
        return timedCache.size();
    }

}
