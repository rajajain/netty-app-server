package netty.controller;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import netty.common.ResponseType;
import netty.handlers.IBaseRequestHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by raja on 30/11/15.
 */
@Controller(value = "/v1/search/.*")
public class TestController implements IBaseRequestHandler{

    private final String jsonContentType = "application/json";

    @netty.annotation.RequestMapping(method = RequestMethod.GET, value = "/v1/search/test-method1", responseType = ResponseType.JSON)
    public FullHttpResponse testMethod1(String requestUri, FullHttpRequest request) {

        return createResponse("test method 1", jsonContentType, OK);
    }

    @netty.annotation.RequestMapping(method = RequestMethod.POST, value = "/v1/search/test-method2", responseType = ResponseType.JSON)
    public FullHttpResponse testMethod2(String requestUri, String requestPayload, FullHttpRequest request) {

        return createResponse("test method 2", jsonContentType, OK);
    }

    public static FullHttpResponse createResponse(String responseStr, String contentType, HttpResponseStatus status) {
        FullHttpResponse response = null;
        if (StringUtils.isNotBlank(responseStr)) {
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(responseStr.getBytes()));
        } else {
            response = new DefaultFullHttpResponse(HTTP_1_1, OK);
        }
        response.headers().set("Content-Type", contentType);
        return response;
    }
}
