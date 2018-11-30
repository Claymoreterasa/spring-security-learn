package lab.ride.security.core.validate.sms;

import lab.ride.security.core.properties.SecurityProperties;
import lab.ride.security.core.validate.ValidateCode;
import lab.ride.security.core.validate.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.util.Random;

/**
 * @author cwz
 * @date 2018/11/28
 */
@org.springframework.stereotype.Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getImage().getExpireIn());
    }

    private Color getRandColor(int start, int end) {
        Random random = new Random();
        if(start > 255){
            start = 255;
        }

        if(end > 255){
            end = 255;
        }

        int r = start + random.nextInt(end - start);
        int g = start + random.nextInt(end - start);
        int b = start + random.nextInt(end - start);
        return new Color(r, g, b);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
