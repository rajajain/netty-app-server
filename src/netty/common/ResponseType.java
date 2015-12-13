package netty.common;

import io.netty.handler.codec.http.FullHttpResponse;
import netty.service.ICustomResponseService;

/**
 * The Enum ResponseType.
 */
public enum ResponseType {

    /**
     * The redirect url.
     */
    REDIRECT_URL {
        /*
         * (non-Javadoc)
         * 
         * @see com.bsb.portal.common.ResponseType#createHttpResponse(com.bsb.portal.service.
         * ICustomResponseService)
         */
        @Override
        public FullHttpResponse createHttpResponse(ICustomResponseService customResponseService) {
            return customResponseService.createRedirectResponse();
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.bsb.portal.common.ResponseType#createHttpResponse(com.bsb.portal.service.
         * ICustomResponseService, java.lang.Exception)
         */
        @Override
        public FullHttpResponse createHttpResponse(ICustomResponseService customResponseService, Throwable exception) {
            return customResponseService.createErrorRedirectResponse(exception);
        }
    },

    /**
     * The json.
     */
    JSON {
        /*
         * (non-Javadoc)
         * 
         * @see com.bsb.portal.common.ResponseType#createHttpResponse(com.bsb.portal.service.
         * ICustomResponseService)
         */
        @Override
        public FullHttpResponse createHttpResponse(ICustomResponseService customResponseService) {
            return customResponseService.createJsonResponse();
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.bsb.portal.common.ResponseType#createHttpResponse(com.bsb.portal.service.
         * ICustomResponseService, java.lang.Exception)
         */
        @Override
        public FullHttpResponse createHttpResponse(ICustomResponseService customResponseService, Throwable exception) {
            return customResponseService.createErrorJsonResponse(exception);
        }
    };

    /**
     * Creates the http response.
     *
     * @param customResponseService the response service
     * @return the http response
     */
    public abstract FullHttpResponse createHttpResponse(ICustomResponseService customResponseService);

    /**
     * Creates the http response.
     *
     * @param customResponseService the custom response service
     * @param exception             the exception
     * @return the http response
     */
    public abstract FullHttpResponse createHttpResponse(ICustomResponseService customResponseService, Throwable exception);
}
