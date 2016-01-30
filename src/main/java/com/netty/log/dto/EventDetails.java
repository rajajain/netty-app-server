package com.netty.log.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by raja.
 */
public class EventDetails implements Serializable {

    private String clientId;
    private String id;
    private String segmentName;
    private String segmentRelationship;

    private String segmentsJsonPayload;

    private long creationTime;

    private String status;

    private List<SegmentInfo> segments;

    public String getSegmentsJsonPayload() {
        return segmentsJsonPayload;
    }

    public void setSegmentsJsonPayload(String segmentsJsonPayload) {
        this.segmentsJsonPayload = segmentsJsonPayload;
    }

    public List<SegmentInfo> getSegments() {
        return segments;
    }

    public void setSegments(List<SegmentInfo> segments) {
        this.segments = segments;
    }


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    public String getSegmentRelationship() {
        return segmentRelationship;
    }

    public void setSegmentRelationship(String segmentRelationship) {
        this.segmentRelationship = segmentRelationship;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }


    public SegmentStatus getStatus() {
        return SegmentStatus.valueOf(this.status);
    }

    public void setStatus(SegmentStatus status) {
        this.status = status.name();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("clientId", clientId)
                .append("id", id)
                .append("segmentName", segmentName)
                .append("segmentRelationship", segmentRelationship)
                .append("segmentsJsonPayload", segmentsJsonPayload)
                .append("creationTime", creationTime)
                .append("status", status)
                .append("segments", segments)
                .toString();
    }
}
