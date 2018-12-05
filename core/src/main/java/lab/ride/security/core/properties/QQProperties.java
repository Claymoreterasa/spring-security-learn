package lab.ride.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author cwz
 * @date 2018/12/02
 */
public class QQProperties extends SocialProperties {
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
