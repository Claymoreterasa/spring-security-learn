package lab.ride.security.core.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author cwz
 * @date 2018/11/30
 */
@Component
public class ValidateCodeProcessorHolder {
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type){
        return findValidateCodeProcessor(type.name());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type){
        String beanName = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(beanName);
        if(processor == null){
            throw new ValidateCodeException("没有" + type + "类型的验证码处理器");
        }
        return validateCodeProcessors.get(beanName);
    }
}
