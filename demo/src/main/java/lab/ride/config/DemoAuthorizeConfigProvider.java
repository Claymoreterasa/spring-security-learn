package lab.ride.config;

import lab.ride.security.core.authorization.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author cwz
 * @date 2018/12/05
 */
@Component
@Order(Integer.MAX_VALUE)
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry http) {
        // 要放到最后读
        http.anyRequest().access("@rbacService.hasPermission(request, authentication)");
        http.antMatchers(HttpMethod.GET, "/user/*").hasRole("ADMIN");
    }
}
