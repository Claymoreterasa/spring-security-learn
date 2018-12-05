package lab.ride.security.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cwz
 * @date 2018/12/05
 */
public interface RbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
