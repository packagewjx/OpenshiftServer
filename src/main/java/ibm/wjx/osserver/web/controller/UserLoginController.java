package ibm.wjx.osserver.web.controller;

import ibm.wjx.osserver.constant.ResultCode;
import ibm.wjx.osserver.constant.ResultMessage;
import ibm.wjx.osserver.pojo.Result;
import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.oc.UserLoginCommand;
import ibm.wjx.osserver.shell.oc.UserLogoutCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * Create Date: 1/24/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: To provide a rest login api for ordinary users.
 */
@RestController

public class UserLoginController {
    private static Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @RequestMapping("/login")
    public Result<String> login(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
        logger.info("Logging in using username {}, password {}, from {}", username, password, request.getRemoteAddr());
        UserLoginCommand command = new UserLoginCommand(username, password);
        ShellCommandResult<String> loginResult = command.execute();
        if (loginResult.getReturnCode() == BaseShellCommand.PROCESS_OK && loginResult.getData().isPresent()) {
            logger.info("Login Success");
            return Result.newSuccessResult(loginResult.getData().get());
        } else {
            logger.info("Login failed with username {}, password {}", username, password);
            return Result.newFailResult("", ResultCode.BAD_CREDENTIAL, ResultMessage.BAD_CREDENTIAL);
        }
    }

    @RequestMapping("/user-logout")
    public Result<Boolean> logout(@RequestParam String token) {
        logger.info("User logging out using token {}", token);
        UserLogoutCommand command = new UserLogoutCommand(token);
        ShellCommandResult<Boolean> result = command.execute();
        if (result.getReturnCode() == BaseShellCommand.PROCESS_OK && result.getData().isPresent()) {
            return Result.newSuccessResult(result.getData().get());
        } else {
            return Result.newFailResult(false, ResultCode.OTHER, result.getRawResult());
        }
    }


}
