package com.netty.log.handler;

import com.netty.annotation.RequestMapping;
import com.netty.common.APIResponse;
import com.netty.common.ResponseType;
import com.netty.handlers.IBaseRequestHandler;
import com.netty.util.Utils;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by raja .
 */
@Controller(value = "/v1/log/events.*")
public class LogHandler implements IBaseRequestHandler {

    private static final Logger LOGGER2 = LoggerFactory.getLogger("eventlog");

    @RequestMapping(method = RequestMethod.POST, value = "/v1/log/events", responseType = ResponseType.JSON)
    public FullHttpResponse logEvents(String requestUri, String requestPayload, FullHttpRequest request) {

        // or implement logger factory for multiple hosts

        LOGGER2.info(requestPayload);
//        return null;
        return Utils.createJsonResponse(GSON.toJson(APIResponse.getErrorResponse("Logged events.")), HttpResponseStatus.UNAUTHORIZED);
    }

}
