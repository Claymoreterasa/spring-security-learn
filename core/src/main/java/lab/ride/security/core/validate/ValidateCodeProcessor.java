package lab.ride.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author cwz
 * @date 2018/11/29
 */
public interface ValidateCodeProcessor {
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    void create(ServletWebRequest request) throws Exception;

    void validate(ServletWebRequest request);
}
