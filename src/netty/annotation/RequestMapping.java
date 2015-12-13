package netty.annotation;

import netty.common.ResponseType;
import org.springframework.web.bind.annotation.RequestMethod;
import java.lang.annotation.*;

/**
 * Annotation for mapping web requests onto specific handler methods. Provides a consistent style
 * with the semantics adapting to the concrete environment.
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

    /**
     * Url Value.
     *
     * @return the string
     */
    String value();

    /**
     * Request Method.
     *
     * @return the request method
     */
    RequestMethod method();

    /**
     * Response type.
     *
     * @return the response type
     */
    ResponseType responseType();

}
