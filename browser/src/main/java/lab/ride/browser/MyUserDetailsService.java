package lab.ride.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author cwz
 * @date 2018/11/27
 */
@Component
public class MyUserDetailsService implements UserDetailsService{
//    @Autowired
//    private SomeDAO userDao


    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查找用户信息
        // 根据查找到的用户信息判断用户是否被冻结
        // 自定义实现UserDetails
        logger.info("登录用户名: " + username);
//        return new User(username, "123456", true, true, true, false, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        return new User(username, passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
