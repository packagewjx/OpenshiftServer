package ibm.wjx.osserver.web.controller;

import ibm.wjx.osserver.constant.ResultCode;
import ibm.wjx.osserver.constant.ResultMessage;
import ibm.wjx.osserver.pojo.Result;
import ibm.wjx.osserver.web.security.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Create Date: 1/16/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
@RestController
@RequestMapping("/token")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserDetailsService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result<String> getToken(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
        UserDetails user = userService.loadUserByUsername(username);
        logger.debug("User {} using password {} from {} trying to login", username, password, request.getRemoteAddr());
        if (password.equals(user.getPassword())) {
            logger.debug("User {} from {} login success", username, request.getRemoteAddr());
            return Result.newSuccessResult(tokenManager.newToken(user));
        } else {
            logger.debug("User {} from {} login failed, invalid credential", username, request.getRemoteAddr());
            return Result.newFailResult(null, ResultCode.BAD_CREDENTIAL, ResultMessage.BAD_CREDENTIAL);
        }
    }

    @RequestMapping(value = "{token}", method = RequestMethod.DELETE)
    public Result<Boolean> deleteToken(HttpServletRequest request, @PathVariable String token) {
        UserDetails user = tokenManager.getUserFromTokenString(token);
        if (tokenManager.removeToken(token)) {
            logger.debug("User {} from {} logged out", user.getUsername(), request.getRemoteAddr());
            return Result.newSuccessResult(true);
        } else {
            return Result.newFailResult(false, ResultCode.BAD_CREDENTIAL, ResultMessage.BAD_CREDENTIAL);
        }
    }

    @RequestMapping(value = "{token}", method = RequestMethod.GET)
    public Result<Boolean> update(HttpServletRequest request, @PathVariable String token) {
        UserDetails user = tokenManager.getUserFromTokenString(token);
        if (tokenManager.updateToken(token)) {
            logger.debug("User {} from {} update token", user.getUsername(), request.getRemoteAddr());
            return Result.newSuccessResult(true);
        } else {
            return Result.newFailResult(false, ResultCode.BAD_CREDENTIAL, ResultMessage.BAD_CREDENTIAL);
        }
    }
}
