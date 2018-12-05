package lab.ride.browser.session;

/**
 * @author cwz
 * @date 2018/12/03
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * session失效时的跳转
 * @author zhailiang
 *
 */
public class InvalidSessionStrategy extends AbstractSessionStrategy implements org.springframework.security.web.session.InvalidSessionStrategy {

    public InvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        onSessionInvalid(request, response);
    }
}

