package lab.ride.security.app.social;

import lab.ride.security.core.social.qq.config.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;


/**
 * @author cwz
 * @date 2018/12/04
 */
@Component
public class AppSocialAuthenticationFilterPost implements SocialAuthenticationFilterPostProcessor{
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Override
    public void process(SocialAuthenticationFilter filter) {
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
    }
}
