package com.netty.handlers;


import com.google.gson.Gson;
import com.netty.util.GsonUtils;

/**
 * The base interface IBaseUrlRequestHandler.
 */
public interface IBaseRequestHandler {

    String JSON_CONTENT_TYPE = "application/json";
    String TEXT_CONTENT_TYPE = "text/plain";
    Gson GSON = GsonUtils.getInstanceWithExclusion();

}
