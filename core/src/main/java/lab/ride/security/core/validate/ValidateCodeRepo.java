package lab.ride.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author cwz
 * @date 2018/12/04
 */
public interface ValidateCodeRepo {
    void save(ServletWebRequest request, ValidateCode validateCode, ValidateCodeType codeType);

    ValidateCode get(ServletWebRequest request, ValidateCodeType codeType);

    void remove(ServletWebRequest request, ValidateCodeType codeType);
}
