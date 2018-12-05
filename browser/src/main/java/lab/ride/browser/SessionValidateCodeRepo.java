package lab.ride.browser;

import lab.ride.security.core.validate.ValidateCode;
import lab.ride.security.core.validate.ValidateCodeRepo;
import lab.ride.security.core.validate.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author cwz
 * @date 2018/12/04
 */
@Component
public class SessionValidateCodeRepo implements ValidateCodeRepo {
    private final String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, ValidateCode validateCode, ValidateCodeType codeType) {
        ValidateCode savedCode = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + codeType.name(), savedCode);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType codeType) {
        return (ValidateCode) sessionStrategy.getAttribute(request, SESSION_KEY_PREFIX + codeType.name());
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        sessionStrategy.removeAttribute(request, SESSION_KEY_PREFIX + codeType.name());
    }

}
