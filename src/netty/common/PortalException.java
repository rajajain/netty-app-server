package netty.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Marker;

/**
 * The Class PortalException.
 */
public class PortalException extends Exception {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -9013187864093076005L;

    /**
     * The error code.
     */
    private String errorCode;

    /**
     * The error title.
     */
    private String errorTitle;

    /**
     * Instantiates a new portal exception.
     *
     * @param message the message
     */
    public PortalException(String message) {
        super(message);
    }

    /**
     * Instantiates a new portal exception.
     *
     * @param errorCode  the error code
     * @param message    the message
     * @param errorTitle the error title
     * @param th         the throwable
     */
    public PortalException(String errorCode, String message, String errorTitle, Throwable th) {
        super(message, th);
        this.errorTitle = errorTitle;
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new portal exception.
     *
     * @param errorCode  the error code
     * @param message    the message
     * @param errorTitle the error title
     */
    public PortalException(String errorCode, String message, String errorTitle) {
        super(message);
        this.errorTitle = errorTitle;
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new portal exception.
     *
     * @param th the throwable
     */
    public PortalException(Throwable th) {
        super(th);
    }

    /**
     * Instantiates a new portal exception.
     *
     * @param errorMsg the error msg
     * @param th       the throwable
     */
    public PortalException(String errorMsg, Throwable th) {
        super(errorMsg, th);
    }

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the error code.
     *
     * @param errorCode the new error code
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the error title.
     *
     * @return the error title
     */
    public String getErrorTitle() {
        return errorTitle;
    }

    /**
     * Sets the error title.
     *
     * @param errorTitle the new error title
     */
    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }


}
