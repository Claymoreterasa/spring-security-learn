package lab.ride.browser.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab.ride.security.core.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cwz
 * @date 2018/12/03
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private String logoutUrl;

    public MyLogoutSuccessHandler(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("退出成功");

        if(StringUtils.isNotBlank(logoutUrl)){
            response.sendRedirect(logoutUrl);
        }else{
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
        }
    }
}
