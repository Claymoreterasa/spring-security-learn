package lab.ride.security.app;

import lab.ride.security.app.social.OpenIdAuthenticationSecurityConfig;
import lab.ride.security.app.social.OpenIdCodeAuthenticationProvider;
import lab.ride.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import lab.ride.security.core.authorization.AuthorizeConfigManager;
import lab.ride.security.core.properties.SecurityConstants;
import lab.ride.security.core.properties.SecurityProperties;
import lab.ride.security.core.properties.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author cwz
 * @date 2018/12/03
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    @Autowired
    protected AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler myAuthenticationFailureHandler;


    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer socialSecuriryConfig;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);

        http
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(socialSecuriryConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
//                .authorizeRequests()
//                .antMatchers(
//                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
//                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
//                        securityProperties.getBrowser().getLoginPage(),
//                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
//                        securityProperties.getBrowser().getSignUpUrl(),
//                        "/user/regist", "/social/signUp",
//                        securityProperties.getBrowser().getSession().getInvalidUrl() + ".html")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }
}
