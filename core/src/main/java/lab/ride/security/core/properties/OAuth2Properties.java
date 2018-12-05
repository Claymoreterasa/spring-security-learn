package lab.ride.security.core.properties;

/**
 * @author cwz
 * @date 2018/12/04
 */
public class OAuth2Properties {
    private OAuth2ClientProperties[] clients = {};

    private String jwtSigningKey = "ride";

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }
}
