package lab.ride.security.core.social.qq.config;

import lab.ride.security.core.properties.QQProperties;
import lab.ride.security.core.properties.SecurityProperties;
import lab.ride.security.core.social.ConnectView;
import lab.ride.security.core.social.ConnectionStatusView;
import lab.ride.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * @author cwz
 * @date 2018/12/02
 */
@Configuration
@ConditionalOnProperty(prefix = "self.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }

    @Bean({"connect/qqConnected", "connect/qqConnect"})
    @ConditionalOnMissingBean(name = "qqConnectedView")
    public View qqConnectedView(){
        return new ConnectView();
    }
}
