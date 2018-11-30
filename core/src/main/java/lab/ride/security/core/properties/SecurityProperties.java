package lab.ride.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author cwz
 * @date 2018/11/27
 */
@ConfigurationProperties(prefix = "self.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private LoginType loginType = LoginType.JSON; // default json

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
