package com.netty;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.netty.annotation.RequestMapping;
import com.netty.common.PortalException;
import com.netty.common.ResponseType;
import com.netty.handlers.IBaseRequestHandler;
import com.netty.util.PortalConfig;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by raja on 30/11/15.
 */
@Component
public class ServerBaseHandler extends BaseHttpServerHandler {

    private static final Logger logger = LoggerFactory.getLogger(ServerBaseHandler.class.getCanonicalName());


    @Autowired
    private PortalConfig portalConfig;

    private ApplicationContext applicationContext;
    private Map<String, IBaseRequestHandler> handlerClassMap = new ConcurrentHashMap<>();
    private Table<String, String, MethodMetaData> methodTable = HashBasedTable.create();

    public FullHttpResponse handleRequest(String requestUri, String requestPayload, FullHttpRequest request) throws Exception {

        int i = requestUri.indexOf("?");
        String requestUriWoutParams = requestUri;
        if (i > 0) {
            requestUriWoutParams = requestUri.substring(0, i);
        }

        IBaseRequestHandler baseRequestHandler = lookupUrlHandler(requestUriWoutParams);
        if (baseRequestHandler == null) {
            return new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
        }

        FullHttpResponse response = null;
        String requestMethod = request.getMethod().name();
        MethodMetaData methodMetaData = lookupRequestMapping(baseRequestHandler, requestUriWoutParams, requestMethod);
        if (methodMetaData == null) {
            throw new PortalException("");
        }
        logger.warn("WARN");
        logger.info("handleRequest");
        logger.debug("Method found :: [{}]", methodMetaData);
        Method method = methodMetaData.getMethod();

        if (requestMethod.equals("GET")) {
            response = (FullHttpResponse) method.invoke(baseRequestHandler, requestUri, request);
        } else if (requestMethod.equals("POST")) {
            response = (FullHttpResponse) method.invoke(baseRequestHandler, requestUri, requestPayload, request);
        } else {
            response = (FullHttpResponse) method.invoke(baseRequestHandler, requestUri, requestPayload, request);
        }
        return response;
    }

    public MethodMetaData lookupRequestMapping(IBaseRequestHandler baseRequestHandler, String uri, String requestMethod) {
        MethodMetaData methodMetaData = methodTable.get(uri, requestMethod);
        if (methodMetaData == null) {
            Method[] methods = baseRequestHandler.getClass().getDeclaredMethods();
            for (Method method : methods) {
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if (requestMapping != null && uri.matches(requestMapping.value()) && requestMapping.method().toString().equals(requestMethod)) {
                    Method lookedUpMethod = method;
                    methodMetaData = new MethodMetaData(requestMethod, lookedUpMethod, requestMapping.responseType());
                    methodTable.put(uri, requestMethod, methodMetaData);
                    logger.info("Uri: [{}] requestMethod: [{}] method: [{}] mapping added to cache", uri, requestMethod, lookedUpMethod.getName());
                    break;
                }
            }
        }
        return methodMetaData;
    }

    private class MethodMetaData {

        private String requestMethod;
        private Method method;
        private ResponseType responseType;

        public MethodMetaData(String requestMethod, Method method, ResponseType responseType) {
            this.requestMethod = requestMethod;
            this.method = method;
            this.responseType = responseType;
        }

        public String getRequestMethod() {
            return requestMethod;
        }

        public Method getMethod() {
            return method;
        }

        public ResponseType getResponseType() {
            return responseType;
        }

        @Override
        public String toString() {
            return "requestMethod=" + getRequestMethod() + ", method=" + getMethod() + ", responseType=" + getResponseType();
        }

    }

    public IBaseRequestHandler lookupUrlHandler(String uri) {
        IBaseRequestHandler handler = handlerClassMap.get(uri);
        if (handler == null) {
            Map<String, Object> controllerBeanMap = applicationContext.getBeansWithAnnotation(Controller.class);
            Collection<Object> controllerBeans = controllerBeanMap.values();
            for (Object controllerBean : controllerBeans) {
                Controller controller = controllerBean.getClass().getAnnotation(Controller.class);
                if (uri.matches(controller.value())) {
                    handler = (IBaseRequestHandler) controllerBean;
                    handlerClassMap.put(uri, handler);
                    logger.info("Handler: [{}] uri: [{}] mapping added to cache", handler.getClass().getCanonicalName(), uri);
                    break;
                }
            }
        }
        return handler;
    }


    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
