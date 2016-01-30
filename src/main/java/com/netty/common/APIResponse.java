package com.netty.common;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Ashwin Aditya
 *
 */
public class APIResponse<T> {

    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAILURE = "failure";

    private String             status;
    private String             responseCode;
    private List<String>       errorMessageList;
    private T                  data;

    public APIResponse() {
    }

    public APIResponse(String status, String responseCode, List<String> errorMessageList, T data) {
        this.status = status;
        this.responseCode = responseCode;
        this.errorMessageList = errorMessageList;
        this.data = data;
    }

    public static <T> APIResponse<T> getSuccessResponse(T data) {
        return new APIResponse<T>(STATUS_SUCCESS, "0", null, data);
    }
    
    public static <T> APIResponse<T> getErrorResponse(String message) {
        return new APIResponse<T>(STATUS_FAILURE, "0", Arrays.asList(message), null);
    }
    
    public static <T> APIResponse<T> getErrorResponse(List<String> messageList) {
        return new APIResponse<T>(STATUS_FAILURE, "0", messageList, null);
    }

    public static <T> APIResponse<T> getErrorResponse(String responseCode, String message, T data) {
        return new APIResponse<T>(STATUS_FAILURE, responseCode, Arrays.asList(message), data);
    }

    public static <T> APIResponse<T> getErrorResponse(String responseCode, List<String> messageList, T data) {
        return new APIResponse<T>(STATUS_FAILURE, responseCode, messageList, data);
    }
    
    public boolean isSuccess() {
        return STATUS_SUCCESS.equals(status);
    }

    public boolean isFailure() {
        return STATUS_FAILURE.equals(status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public List<String> getErrorMessageList() {
        return errorMessageList;
    }

    public void setErrorMessageList(List<String> errorMessageList) {
        this.errorMessageList = errorMessageList;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "APIResponse [status=" + status + ", responseCode=" + responseCode + ", errorMessageList=" + errorMessageList + ", data=" + data + "]";
    }

}
