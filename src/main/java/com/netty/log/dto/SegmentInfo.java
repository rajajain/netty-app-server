package com.netty.log.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by raja.
 */
public class SegmentInfo implements Serializable {

    private UUID id;
    private boolean include;
    private long durationFrom;
    private long durationTo;
    private List<EventSegment> events;
    private List<AttributeSegment> userAttributes;
    private List<AttributeSegment> deviceAttributes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isInclude() {
        return include;
    }

    public void setInclude(boolean include) {
        this.include = include;
    }

    public long getDurationFrom() {
        return durationFrom;
    }

    public void setDurationFrom(long durationFrom) {
        this.durationFrom = durationFrom;
    }

    public long getDurationTo() {
        return durationTo;
    }

    public void setDurationTo(long durationTo) {
        this.durationTo = durationTo;
    }

    public List<EventSegment> getEvents() {
        return events;
    }

    public void setEvents(List<EventSegment> events) {
        this.events = events;
    }

    public List<AttributeSegment> getUserAttributes() {
        return userAttributes;
    }

    public void setUserAttributes(List<AttributeSegment> userAttributes) {
        this.userAttributes = userAttributes;
    }

    public List<AttributeSegment> getDeviceAttributes() {
        return deviceAttributes;
    }

    public void setDeviceAttributes(List<AttributeSegment> deviceAttributes) {
        this.deviceAttributes = deviceAttributes;
    }
}
