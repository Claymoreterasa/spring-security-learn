package lab.ride.security.core.validate;

import lab.ride.security.core.properties.SecurityConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author cwz
 * @date 2018/11/29
 */
public abstract class AbstractValidateCodeProcessor<T extends ValidateCode> implements ValidateCodeProcessor {

    @Autowired
    private ValidateCodeRepo validateCodeRepo;

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        T validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    private T generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator generator = validateCodeGenerators.get(type + "CodeGenerator");
        return (T)generator.generate(request);
    }

    private void save(ServletWebRequest request, T validateCode) {
        ValidateCode savedCode = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        ValidateCodeType codeType = getValidateCodeType();
        validateCodeRepo.save(request, savedCode, codeType);
    }

    protected abstract void send(ServletWebRequest request, T validateCode) throws Exception;

    private String getProcessorType(ServletWebRequest request){
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/");
    }


    /**
     * 实现父类的验证方法
     */
    @SuppressWarnings("unchecked")
    @Override
    public void validate(ServletWebRequest request) {

        ValidateCodeType codeType = getValidateCodeType();
        String sessionKey = getSessionKey();

        T code = (T)validateCodeRepo.get(request, codeType);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码的值不能为空");
        }

        if (code == null) {
            throw new ValidateCodeException(codeType + "验证码不存在");
        }

        if (code.isExpried()) {
            validateCodeRepo.remove(request, codeType);
            throw new ValidateCodeException(codeType + "验证码已过期");
        }

        if (!StringUtils.equals(code.getCode(), codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码不匹配");
        }

        validateCodeRepo.remove(request, codeType);
    }

    /**
     * 根据请求的url获取校验码的类型
     * @return
     */
    private ValidateCodeType getValidateCodeType() {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "ValidateCodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * 构建验证码放入session时的key
     * @return
     */
    private String getSessionKey() {
        return SESSION_KEY_PREFIX + getValidateCodeType().toString().toUpperCase();
    }


}
