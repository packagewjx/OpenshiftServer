package ibm.wjx.osserver.web.security;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Create Date: 1/16/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class Token {
    private String token;
    private long startTime;
    private UserDetails user;

    Token(String token, long startTime, UserDetails user) {
        this.token = token;
        this.startTime = startTime;
        this.user = user;
    }

    public Token() {
    }

    public String getToken() {

        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }
}
