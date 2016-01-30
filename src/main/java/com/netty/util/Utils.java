package com.netty.util;

import com.netty.handlers.IBaseRequestHandler;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by raja on 30/11/15.
 */
public class Utils {

    public static Map<String, List<String>> getUrlParameters(String url) {
        try {
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String pair[] = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }
                    List<String> values = params.get(key);
                    if (values == null) {
                        values = new ArrayList<String>();
                        params.put(key, values);
                    }
                    values.add(value);
                }
            }
            return params;
        } catch (UnsupportedEncodingException e) {
        }

        return null;
    }

    public static String getStringParameter(Map<String, List<String>> urlParameters, String paramName) {
        if (urlParameters == null)
            return null;
        if (urlParameters.get(paramName) != null && urlParameters.get(paramName).size() > 0) {
            String nresultsParam = urlParameters.get(paramName).get(0);
            if (nresultsParam != null) {
                return nresultsParam.trim();
            }
        }
        return null;
    }

    public static FullHttpResponse createSuccessJsonResponse(String responseStr) {
        return createJsonResponse(responseStr, HttpResponseStatus.OK);
    }

    public static FullHttpResponse createJsonResponse(String responseStr, HttpResponseStatus status) {
        return createResponse(responseStr, IBaseRequestHandler.JSON_CONTENT_TYPE, status);
    }

    public static FullHttpResponse createResponse(String responseStr, String contentType, HttpResponseStatus status) {
        FullHttpResponse response = null;
        if (org.apache.commons.lang3.StringUtils.isNotBlank(responseStr)) {
            response = new DefaultFullHttpResponse(HTTP_1_1, status, Unpooled.wrappedBuffer(responseStr.getBytes()));
        } else {
            response = new DefaultFullHttpResponse(HTTP_1_1, status);
        }
        response.headers().set("Content-Type", contentType);
        return response;
    }
}
