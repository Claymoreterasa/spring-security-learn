package lab.ride.security.core.validate;

import lab.ride.security.core.properties.SecurityConstants;
import lab.ride.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author cwz
 * @date 2018/11/28
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeProcessorHolder processorHolder;

    private Map<String, ValidateCodeType> urlsToCodeType = new HashMap<>();


    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        fillUrlMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);
        urlsToCodeType.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        fillUrlMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);
        urlsToCodeType.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
    }

    private void fillUrlMap(String urls, ValidateCodeType type){
        if(StringUtils.isNotBlank(securityProperties.getCode().getImage().getUrl())) {
            String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ";");
            for (String configUrl : configUrls) {
                urlsToCodeType.put(configUrl, type);
            }
        }
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
       ValidateCodeType type = getValidateCodeType(httpServletRequest);
        if(type != null){
            logger.info("校验请求(" +  httpServletRequest.getRequestURI() + ")中的验证码，验证码类型: " + type.name());
            try {
                processorHolder.findValidateCodeProcessor(type).validate(new ServletWebRequest(httpServletRequest, httpServletResponse));
                logger.info("验证码校验通过");
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if (StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
            Set<String> urls = urlsToCodeType.keySet();
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    result = urlsToCodeType.get(url);
                }
            }
        }
        return result;
    }
}
