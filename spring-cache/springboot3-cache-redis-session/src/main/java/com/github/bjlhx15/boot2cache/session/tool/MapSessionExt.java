package com.github.bjlhx15.boot2cache.session.tool;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.DurationDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.DurationSerializer;
import org.springframework.session.Session;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class MapSessionExt implements Session, Serializable {

    public static final int DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS = 1800;
    private String id;
    private final String originalId;
    private Map<String, Object> sessionAttrs = new HashMap<>();


    @JsonSerialize(using = InstantConfig.InstantSerializer.class)
    @JsonDeserialize(using = InstantConfig.InstantDeserializer.class)
    private Instant careationTime = Instant.now();

    @JsonSerialize(using = InstantConfig.InstantSerializer.class)
    @JsonDeserialize(using = InstantConfig.InstantDeserializer.class)
    private Instant lastAccessedTime = this.careationTime;

    private Set<String> attributeNames = new HashSet<>();
    private boolean expired = false;

    @JsonSerialize(using = DurationSerializer.class)
    @JsonDeserialize(using = DurationDeserializer.class)
    private Duration maxInactiveInterval = Duration.ofSeconds(DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS);


    public MapSessionExt() {
        this(generatedId());
    }

    public MapSessionExt(String id) {
        this.id = id;
        this.originalId = id;
    }

    public MapSessionExt(Session session) {
        if (null == session) {
            throw new IllegalArgumentException("session cannot be null");
        }
        this.id = session.getId();
        this.originalId = this.id;
        this.sessionAttrs = new HashMap<>(session.getAttributeNames().size());
        for (String attributeName : session.getAttributeNames()) {
            Object attribute = session.getAttribute(attributeName);
            if (null != attribute) {
                this.sessionAttrs.put(attributeName, attribute);
            }
        }
        this.lastAccessedTime = session.getLastAccessedTime();
        this.careationTime = session.getCreationTime();
        this.maxInactiveInterval = session.getMaxInactiveInterval();
    }

    private static String generatedId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalId() {
        return this.originalId;
    }

    @Override
    public String changeSessionId() {
        String changeId = this.generatedId();
        this.setId(changeId);
        return changeId;
    }

    @Override
    public <T> T getAttribute(String s) {
        return (T) this.sessionAttrs.get(s);
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(this.sessionAttrs.keySet());
    }

    @Override
    public void setAttribute(String name, Object value) {
        if (null == value) {
            removeAttribute(name);
        } else {
            this.sessionAttrs.put(name, value);
        }
    }

    @Override
    public void removeAttribute(String s) {
        this.sessionAttrs.remove(s);
    }

    @Override
    public Instant getCreationTime() {
        return this.careationTime;
    }

    @Override
    public void setLastAccessedTime(Instant instant) {
        this.lastAccessedTime = instant;
    }

    @Override
    public Instant getLastAccessedTime() {
        return this.lastAccessedTime;
    }

    @Override
    public void setMaxInactiveInterval(Duration duration) {
        this.maxInactiveInterval = duration;
    }

    @Override
    public Duration getMaxInactiveInterval() {
        return this.maxInactiveInterval;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    private boolean isExpired(Instant now) {
        if (this.maxInactiveInterval.isNegative()) {
            return false;
        }
        return now.minus(this.maxInactiveInterval).compareTo(this.lastAccessedTime) > 0;
    }


    public void setCareationTime(Instant careationTime) {
        this.careationTime = careationTime;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Session && this.id.equals(((Session) o).getId());
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
