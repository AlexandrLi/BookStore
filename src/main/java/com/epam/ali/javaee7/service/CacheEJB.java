package com.epam.ali.javaee7.service;

import javax.ejb.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Singleton
@Lock(LockType.WRITE)
@AccessTimeout(value = 20, unit = TimeUnit.SECONDS)
@Startup
public class CacheEJB {

    private Map<Long, Object> cache = new HashMap<>();

    public void addToCache(Long id, Object object) {
        if (!cache.containsKey(id)) {
            cache.put(id, object);
        }
    }

    public void removeFromCache(Long id) {
        if (cache.containsKey(id)) {
            cache.remove(id);
        }
    }

    @Lock(LockType.READ)
    public Object getFromCache(Long id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        } else {
            return null;
        }
    }
}
