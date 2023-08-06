package com.industry.util;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import com.industry.bean.entity.LoginUserInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lc
 * @date 2022/6/27
 */
@Component
public class LocalCacheUtil {
    public static final Long EXPIRATION_TIME = 1000 * 60 * 60 * 2L;
    private static final TimedCache<String, Object> TIMED_CACHE = CacheUtil.newTimedCache(EXPIRATION_TIME);

    static {
        TIMED_CACHE.schedulePrune(1000 * 60 * 15);
    }

    /**
     * 根据key获取缓存数据
     *
     * @param key key
     * @return string
     */
    public Object get(String key) {
        return TIMED_CACHE.get(key);
    }

    /**
     * 往 timedCache 插入元素（固定过期时间）
     *
     * @param key   key
     * @param value value
     */
    public void put(String key, Object value) {
        TIMED_CACHE.put(key, value);
    }

    /**
     * 往 timedCache 插入元素（自定义过期时间）
     *
     * @param key     key
     * @param value   value
     * @param timeout timeout
     */
    public void put(String key, Object value, Long timeout) {
        TIMED_CACHE.put(key, value, timeout);
    }

    /**
     * 获取长度
     *
     * @return timeCache长度
     */
    public Integer size() {
        return TIMED_CACHE.size();
    }

    public static List<LoginUserInfo> getListLoginUsers() {
        List<LoginUserInfo> list = new ArrayList<>();
        for (Object o : TIMED_CACHE) {
            LoginUserInfo userInfo = (LoginUserInfo) o;
            list.add(userInfo);
        }
        return list;
    }

    public static void deleteUser(String username) {
        TIMED_CACHE.remove(username);
    }

}
