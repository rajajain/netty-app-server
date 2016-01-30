package com.netty.service;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * The Custom interface ICustomResponseService for creating HttpResponse based upon the type of
 * method invoked.
 */
public interface ICustomResponseService {

    /**
     * Creates the redirect response to client provided url.
     *
     * @return the http response
     */
    FullHttpResponse createRedirectResponse();

    /**
     * Creates the error redirect response.
     *
     * @param exception the exception
     * @return the http response
     */
    FullHttpResponse createErrorRedirectResponse(Throwable exception);

    /**
     * Creates the json response.
     *
     * @return the http response
     */
    FullHttpResponse createJsonResponse();

    /**
     * Creates the error json response.
     *
     * @param exception the exception
     * @return the http response
     */
    FullHttpResponse createErrorJsonResponse(Throwable exception);

}
