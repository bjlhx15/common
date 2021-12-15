package com.github.bjlhx15.boot2cache.session.tool;


import com.github.bjlhx15.boot2cache.session.CacheSerice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.session.SessionRepository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class AuthSessionRespository implements SessionRepository<MapSessionExt> {

    private SessionProperties sessionProperties;
    private static final String sessionProfix = "auht-";

    @Resource
    private CacheSerice cacheSerice;

    public AuthSessionRespository(SessionProperties sessionProperties) {
        this.sessionProperties = sessionProperties;
    }

    @Override
    public MapSessionExt createSession() {
        String sessionId = sessionProfix + System.currentTimeMillis() + "-" + UUID.randomUUID().toString();
        MapSessionExt mapSessionExt = new MapSessionExt(sessionId);
        mapSessionExt.setMaxInactiveInterval(sessionProperties.getTimeout());
        return mapSessionExt;
    }

    @Override
    public void save(MapSessionExt session) {
        if (null == session) {
            return;
        }
        int expiredTime = (int) (session.getMaxInactiveInterval().toMinutes() * 60);
        cacheSerice.set(session.getId(), session, expiredTime, TimeUnit.SECONDS);
    }

    @Override
    public MapSessionExt findById(String id) {
        MapSessionExt session = (MapSessionExt) cacheSerice.get(id);
        if (null == session || session.isExpired()) {
            return null;
        }
        session.setLastAccessedTime(Instant.now());
        return session;
    }

    @Override
    public void deleteById(String id) {
        MapSessionExt session = this.findById(id);
        if(session==null){
            return;
        }
        cacheSerice.del(id);

    }
}
