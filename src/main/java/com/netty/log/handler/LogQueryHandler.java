package com.netty.log.handler;

import com.netty.annotation.RequestMapping;
import com.netty.common.APIResponse;
import com.netty.common.ResponseType;
import com.netty.handlers.IBaseRequestHandler;
import com.netty.log.dto.EventDetails;
import com.netty.util.Utils;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Created by raja .
 */
@Controller(value = "/v1/log/query-events.*")
public class LogQueryHandler implements IBaseRequestHandler {

    private static final Logger LOGGER2 = LoggerFactory.getLogger(LogQueryHandler.class.getCanonicalName());

    private static Map<String, Map<Long, EventDetails>> logEventsMap = new HashMap<>();

    public static Map<String, Map<Long, EventDetails>> getLogEventsMap() {
        return logEventsMap;
    }

    public static void setLogEventsMap(Map<String, Map<Long, EventDetails>> logEventsMap) {
        LogQueryHandler.logEventsMap = logEventsMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/v1/log/query-events", responseType = ResponseType.JSON)
    public FullHttpResponse getQueryResults(String requestUri, FullHttpRequest request) {

        Map<String, List<String>> urlParameterMap = Utils.getUrlParameters(requestUri);
        String clientId = Utils.getStringParameter(urlParameterMap, "clientId");
        String fromCreationTimeStamp = Utils.getStringParameter(urlParameterMap, "fromCreationTimeStamp");
        String toCreationTimeStamp = Utils.getStringParameter(urlParameterMap, "toCreationTimeStamp");
        if (StringUtils.isBlank(clientId)) {
            return Utils.createResponse(GSON.toJson(APIResponse.getSuccessResponse(logEventsMap.get("DUMMY_CLIENT_ID"))), JSON_CONTENT_TYPE, HttpResponseStatus.OK);
        }
// need to improve following logic
        Map<Long, EventDetails> event = logEventsMap.get(clientId);
        if (event == null || event.isEmpty()) {
            return Utils.createResponse(GSON.toJson(APIResponse.getSuccessResponse("")), JSON_CONTENT_TYPE, HttpResponseStatus.OK);
        }
        if (StringUtils.isBlank((fromCreationTimeStamp)) && StringUtils.isBlank((toCreationTimeStamp))) {
            return Utils.createResponse(GSON.toJson(APIResponse.getSuccessResponse(event)), JSON_CONTENT_TYPE, HttpResponseStatus.OK);
        }
        List<EventDetails> events = new ArrayList<>();
        if (StringUtils.isNotBlank((fromCreationTimeStamp))) {
            for (Map.Entry<Long, EventDetails> entry : event.entrySet()) {
                if (entry.getKey() >= Long.valueOf(fromCreationTimeStamp)) {
                    events.add(entry.getValue());
                }
            }
        }

        if (StringUtils.isNotBlank((toCreationTimeStamp))) {
            Iterator<EventDetails> itr = events.iterator();
            while (itr.hasNext()) {
                if (itr.next().getCreationTime() > Long.valueOf(toCreationTimeStamp)) {
                    itr.remove();
                }
            }
        }
        return Utils.createResponse(GSON.toJson(APIResponse.getSuccessResponse(events)), JSON_CONTENT_TYPE, HttpResponseStatus.OK);
    }

}
