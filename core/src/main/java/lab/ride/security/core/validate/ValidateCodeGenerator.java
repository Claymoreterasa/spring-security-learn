package lab.ride.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author cwz
 * @date 2018/11/28
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}
