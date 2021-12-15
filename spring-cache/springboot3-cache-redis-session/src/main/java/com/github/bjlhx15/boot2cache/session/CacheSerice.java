package com.github.bjlhx15.boot2cache.session;

import com.github.bjlhx15.boot2cache.session.tool.MapSessionExt;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public interface CacheSerice {
    void set(String id, MapSessionExt session, int expiredTime, TimeUnit seconds);

    Serializable get(String id);

    void del(String id);
}
