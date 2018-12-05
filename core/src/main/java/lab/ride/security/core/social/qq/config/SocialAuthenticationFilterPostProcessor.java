package lab.ride.security.core.social.qq.config;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @author cwz
 * @date 2018/12/04
 */
public interface SocialAuthenticationFilterPostProcessor {
    void process(SocialAuthenticationFilter filter);
}
