package lab.ride.security.core.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author cwz
 * @date 2018/12/05
 */
@Component
public class MyAuthorizeConfigManager implements AuthorizeConfigManager {
    @Autowired
    private List<AuthorizeConfigProvider> providers;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry http) {
        for(AuthorizeConfigProvider provider : providers){
            provider.config(http);
        }
//        http.anyRequest().authenticated();
    }
}
