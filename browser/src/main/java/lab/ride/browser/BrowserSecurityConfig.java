package lab.ride.browser;

import lab.ride.browser.logout.MyLogoutSuccessHandler;
import lab.ride.browser.session.ExpiredSessionStrategy;
import lab.ride.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import lab.ride.security.core.authorization.AuthorizeConfigManager;
import lab.ride.security.core.properties.AbstractChannelSecurityConfig;
import lab.ride.security.core.properties.SecurityConstants;
import lab.ride.security.core.properties.SecurityProperties;
import lab.ride.security.core.properties.ValidateCodeSecurityConfig;
import lab.ride.browser.session.InvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author cwz
 * @date 2018/11/27
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer socialSecuriryConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;


    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Bean
    @ConditionalOnMissingBean(org.springframework.security.web.session.InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new InvalidSessionStrategy(securityProperties.getBrowser().getSession().getInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public ExpiredSessionStrategy expiredSessionStrategy(){
        return new ExpiredSessionStrategy(securityProperties.getBrowser().getSession().getInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new MyLogoutSuccessHandler(securityProperties.getBrowser().getLogoutUrl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);

        http
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(socialSecuriryConfig)
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                    .and()
                .sessionManagement()
                    .invalidSessionStrategy(invalidSessionStrategy())
                    .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSession())
                    .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                    .expiredSessionStrategy(expiredSessionStrategy())
                    .and()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(logoutSuccessHandler())
                    .deleteCookies("JSESSIONID")
                    .and()
//                .authorizeRequests()
//                    .antMatchers(
//                            SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
//                            SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
//                            securityProperties.getBrowser().getLoginPage(),
//                            SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
//                            securityProperties.getBrowser().getSignUpUrl(),
//                            "/user/regist",
//                            securityProperties.getBrowser().getSession().getInvalidUrl() + ".html")
//                    .permitAll()
//                    .antMatchers(HttpMethod.GET, "/user/*").hasAuthority("write")
////                    .antMatchers(HttpMethod.GET, "/user/*").access("hasAuthority(\"write\") and hasRole(\"ADMIN\")")
//                    .anyRequest()
//                    .authenticated()
//                    .and()
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }
}
