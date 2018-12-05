package lab.ride.security.app.social;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.UsersConnectionRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cwz
 * @date 2018/11/29
 */
public class OpenIdCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OpenIdAuthenticationToken token = (OpenIdAuthenticationToken)authentication;
        Set<String> providerUserIds = new HashSet();
        providerUserIds.add((String)token.getPrincipal());
        Set<String> userIds = usersConnectionRepository.findUserIdsConnectedTo(token.getProviderId(), providerUserIds);
        if(CollectionUtils.isEmpty(userIds) || userIds.size() != 1){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        String userId = userIds.iterator().next();

        UserDetails user = userDetailsService.loadUserByUsername(userId);

        if(user == null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        OpenIdAuthenticationToken authenticatedResult = new OpenIdAuthenticationToken(user, user.getAuthorities());
        authenticatedResult.setDetails(token.getDetails());

        return authenticatedResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
