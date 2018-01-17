package ibm.wjx.osserver.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create Date: 1/16/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
@Component
public class TokenBasedAuthenticationFilter extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(TokenBasedAuthenticationFilter.class);
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Header must contain the Token field.
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = httpServletRequest.getHeader("Token");
        if (token != null) {
            logger.debug("Got token {}", token);
            if (tokenManager.validateToken(token)) {
                logger.debug("Token {} is a valid token", token);
                UserDetails user = tokenManager.getUserFromTokenString(token);
                Authentication authentication = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
                authenticationManager.authenticate(authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                //update the token
                tokenManager.updateToken(token);
            } else {
                logger.debug("Token {} invalid, sending error message", token);
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Your token is expired or invalid");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


}
