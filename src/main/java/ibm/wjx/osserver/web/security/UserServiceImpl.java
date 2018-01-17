package ibm.wjx.osserver.web.security;

import ibm.wjx.osserver.util.ConfigurationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Create Date: 1/16/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
@Component("userDetailService")
public class UserServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static RestUser admin;

    static {
        Authority adminAuthority = new Authority("ROLE_ADMIN");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(adminAuthority);
        String adminUserName = "system:admin";
        admin = new RestUser(authorities, ConfigurationUtils.getConfig(ConfigurationUtils.Keys.ADMIN_PASSWORD), adminUserName);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.debug("Trying to retrieve user {}", s);
        if (admin.getUsername().equals(s)) {
            return admin;
        } else {
            logger.debug("User {} not exist", s);
            throw new UsernameNotFoundException("RestUser " + s + " does not exist");
        }
    }
}
