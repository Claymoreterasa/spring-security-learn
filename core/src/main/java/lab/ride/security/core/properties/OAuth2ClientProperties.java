package lab.ride.security.core.properties;

/**
 * @author cwz
 * @date 2018/12/04
 */
public class OAuth2ClientProperties {
    private String clientId;

    private String clientSecret;

    private int accessTokenValidTime;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public int getAccessTokenValidTime() {
        return accessTokenValidTime;
    }

    public void setAccessTokenValidTime(int accessTokenValidTime) {
        this.accessTokenValidTime = accessTokenValidTime;
    }
}
