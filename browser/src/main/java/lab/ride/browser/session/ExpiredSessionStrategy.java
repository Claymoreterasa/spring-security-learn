package lab.ride.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author cwz
 * @date 2018/12/03
 */
public class ExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy{

    public ExpiredSessionStrategy(String invalidUrl){
        super(invalidUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent eventØ) throws IOException, ServletException {
        onSessionInvalid(eventØ.getRequest(), eventØ.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
