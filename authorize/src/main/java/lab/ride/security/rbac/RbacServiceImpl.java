package lab.ride.security.rbac;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @author cwz
 * @date 2018/12/05
 */
@Component
public class RbacServiceImpl implements RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if(principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            // 读取用户所拥有权限的所有URL,
            // 再加上允许请求方法？
            Set<String> urls = new HashSet<>();

            boolean hasPermission = false;

            for(String url : urls){
                if(antPathMatcher.match(url, request.getRequestURI())){
                    hasPermission = true;
                    break;
                }
            }
            return hasPermission;
        }

        return false;
    }
}
