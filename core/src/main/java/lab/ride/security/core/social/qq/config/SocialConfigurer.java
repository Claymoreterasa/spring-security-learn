package lab.ride.security.core.social.qq.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author cwz
 * @date 2018/12/02
 */
public class SocialConfigurer extends SpringSocialConfigurer {
    private String socialProcessUrl;

    private SocialAuthenticationFilterPostProcessor postProcessor;

    public SocialAuthenticationFilterPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(SocialAuthenticationFilterPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    public SocialConfigurer(String socialProcessUrl) {
        this.socialProcessUrl = socialProcessUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter)super.postProcess(object);
        filter.setFilterProcessesUrl(socialProcessUrl);
        if(postProcessor != null){
            postProcessor.process(filter);
        }
        return (T)filter;
    }
}
