package lab.ride.security.app;

import lab.ride.security.core.social.qq.config.SocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author cwz
 * @date 2018/12/04
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if(StringUtils.equals(s, "socialSecurityConfig")){
            SocialConfigurer configurer = (SocialConfigurer)o;
            configurer.signupUrl("/social/signup");
            return configurer;
        }
        return o;
    }
}
