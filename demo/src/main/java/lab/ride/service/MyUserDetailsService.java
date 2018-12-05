package lab.ride.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author cwz
 * @date 2018/11/27
 */
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService{
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
        logger.info("表单登录用户名: " + username);

        return buildUser(username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("设计用户名Id: " + userId);
        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String userId){
        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码: " + password);
        return new SocialUser(userId, password, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN, ROLE_USER"));
    }
}
