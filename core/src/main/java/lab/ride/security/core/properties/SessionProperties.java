package lab.ride.security.core.properties;

/**
 * @author cwz
 * @date 2018/12/03
 */
public class SessionProperties {
    private String invalidUrl = SecurityConstants.DEFAULT_SESSION_INVALID_URL;

    private int maximumSession = 1;

    private boolean maxSessionsPreventsLogin = false;

    public String getInvalidUrl() {
        return invalidUrl;
    }

    public void setInvalidUrl(String invalidUrl) {
        this.invalidUrl = invalidUrl;
    }

    public int getMaximumSession() {
        return maximumSession;
    }

    public void setMaximumSession(int maximumSession) {
        this.maximumSession = maximumSession;
    }

    public boolean isMaxSessionsPreventsLogin() {
        return maxSessionsPreventsLogin;
    }

    public void setMaxSessionsPreventsLogin(boolean maxSessionsPreventsLogin) {
        this.maxSessionsPreventsLogin = maxSessionsPreventsLogin;
    }
}
