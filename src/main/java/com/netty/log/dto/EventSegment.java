package com.netty.log.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by raja on.
 */
public class EventSegment implements Serializable {

    private String eventType;

    private List<AttributeSegment> eventAttributes;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public List<AttributeSegment> getEventAttributes() {
        return eventAttributes;
    }

    public void setEventAttributes(List<AttributeSegment> eventAttributes) {
        this.eventAttributes = eventAttributes;
    }
}
