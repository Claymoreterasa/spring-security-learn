package lab.ride.security.core.properties;

/**
 * @author cwz
 * @date 2018/12/02
 */
public class SocialProperties {
    private String filterProcessUrl = "/login";

    private QQProperties qq = new QQProperties();

    public String getFilterProcessUrl() {
        return filterProcessUrl;
    }

    public void setFilterProcessUrl(String filterProcessUrl) {
        this.filterProcessUrl = filterProcessUrl;
    }

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
