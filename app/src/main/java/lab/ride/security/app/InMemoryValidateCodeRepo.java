package lab.ride.security.app;

import lab.ride.security.core.validate.ValidateCode;
import lab.ride.security.core.validate.ValidateCodeRepo;
import lab.ride.security.core.validate.ValidateCodeType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cwz
 * @date 2018/12/04
 */
@Component
public class InMemoryValidateCodeRepo implements ValidateCodeRepo {
    private final String CODE_HEADER_NAME = "deviceId";

    private ConcurrentHashMap<String, ValidateCode> values = new ConcurrentHashMap<>();

    @Override
    public void save(ServletWebRequest request, ValidateCode validateCode, ValidateCodeType codeType) {
        values.put(getKey(request, codeType), validateCode);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType codeType) {
       return values.get(getKey(request, codeType));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        values.remove(getKey(request, codeType));
    }

    private String getKey(ServletWebRequest request, ValidateCodeType codeType){
        String deviceId = request.getHeader(CODE_HEADER_NAME);
        return deviceId + "_" + codeType.name();
    }
}
