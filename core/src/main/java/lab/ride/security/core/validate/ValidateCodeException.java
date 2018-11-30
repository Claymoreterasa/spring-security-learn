package lab.ride.security.core.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * @author cwz
 * @date 2018/11/28
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
