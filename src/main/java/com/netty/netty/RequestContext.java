package com.netty.netty;

import org.json.simple.JSONAware;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dhruva
 * Date: 10/12/14
 * Time: 9:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class RequestContext {

    private String requestUriWithoutParams;
    private Map<String, List<String>> urlParameters;
    private byte[] requestPayloadBytes;
    private String requestPayload;
    private JSONAware payloadJsonObj;

    private String msisdnInHeader;
    private String msisdnParam;
    private String msisdn;
    private String tenDigMsisdn;
    private String beneficiaryId; //10dig

    private String circleUsingMsidnSeriesMapping;
    private String circleParam;

    private boolean forceUpdateNDSInfo;
    private boolean overwriteCircleWithNDS;


    public String getRequestUriWithoutParams() {
        return requestUriWithoutParams;
    }

    public void setRequestUriWithoutParams(String requestUriWithoutParams) {
        this.requestUriWithoutParams = requestUriWithoutParams;
    }

    public Map<String, List<String>> getUrlParameters() {
        return urlParameters;
    }

    public void setUrlParameters(Map<String, List<String>> urlParameters) {
        this.urlParameters = urlParameters;
    }

    public byte[] getRequestPayloadBytes() {
        return requestPayloadBytes;
    }

    public void setRequestPayloadBytes(byte[] requestPayloadBytes) {
        this.requestPayloadBytes = requestPayloadBytes;
    }

    public String getRequestPayload() {
        return requestPayload;
    }

    public void setRequestPayload(String requestPayload) {
        this.requestPayload = requestPayload;
    }

    public JSONAware getPayloadJsonObj() {
        return payloadJsonObj;
    }

    public void setPayloadJsonObj(JSONAware payloadJsonObj) {
        this.payloadJsonObj = payloadJsonObj;
    }

    public String getMsisdnInHeader() {
        return msisdnInHeader;
    }

    public void setMsisdnInHeader(String msisdnInHeader) {
        this.msisdnInHeader = msisdnInHeader;
    }

    public String getMsisdnParam() {
        return msisdnParam;
    }

    public void setMsisdnParam(String msisdnParam) {
        this.msisdnParam = msisdnParam;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getTenDigMsisdn() {
        return tenDigMsisdn;
    }

    public void setTenDigMsisdn(String tenDigMsisdn) {
        this.tenDigMsisdn = tenDigMsisdn;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getCircleUsingMsidnSeriesMapping() {
        return circleUsingMsidnSeriesMapping;
    }

    public void setCircleUsingMsidnSeriesMapping(String circleUsingMsidnSeriesMapping) {
        this.circleUsingMsidnSeriesMapping = circleUsingMsidnSeriesMapping;
    }

    public String getCircleParam() {
        return circleParam;
    }

    public void setCircleParam(String circleParam) {
        this.circleParam = circleParam;
    }

    public boolean isForceUpdateNDSInfo() {
        return forceUpdateNDSInfo;
    }

    public void setForceUpdateNDSInfo(boolean forceUpdateNDSInfo) {
        this.forceUpdateNDSInfo = forceUpdateNDSInfo;
    }

    public boolean isOverwriteCircleWithNDS() {
        return overwriteCircleWithNDS;
    }

    public void setOverwriteCircleWithNDS(boolean overwriteCircleWithNDS) {
        this.overwriteCircleWithNDS = overwriteCircleWithNDS;
    }

}
