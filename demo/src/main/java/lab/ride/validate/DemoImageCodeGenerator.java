package lab.ride.validate;

import lab.ride.security.core.validate.image.ImageCode;
import lab.ride.security.core.validate.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author cwz
 * @date 2018/11/28
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }
}
