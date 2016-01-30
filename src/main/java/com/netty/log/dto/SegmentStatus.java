package com.netty.log.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by raja .
 */
public enum SegmentStatus {

    ACTIVE, INACTIVE;

    private static final Map<String, SegmentStatus> cacheMap = new HashMap<>();

    static {
        for (SegmentStatus campaignStatus : values()) {
            cacheMap.put(campaignStatus.name(), campaignStatus);
        }
    }

    public static SegmentStatus findByName(String name) {
        return cacheMap.get(name);
    }

}
