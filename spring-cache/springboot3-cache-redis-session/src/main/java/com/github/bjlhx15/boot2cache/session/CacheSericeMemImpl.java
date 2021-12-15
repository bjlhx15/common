package com.github.bjlhx15.boot2cache.session;

import com.github.bjlhx15.boot2cache.session.tool.MapSessionExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CacheSericeMemImpl implements CacheSerice {
    Map<String, Serializable> store = new HashMap<>();

    @Override
    public void set(String id, MapSessionExt session, int expiredTime, TimeUnit seconds) {
        store.put(id, session);
    }

    @Override
    public Serializable get(String id) {
        return store.get(id);
    }

    @Override
    public void del(String id) {
        store.remove(id);
    }
}
