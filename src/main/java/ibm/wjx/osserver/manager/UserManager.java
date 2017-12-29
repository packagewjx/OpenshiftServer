package ibm.wjx.osserver.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.operation.user.CreateUserOperation;
import ibm.wjx.osserver.operation.user.DeleteUserOperation;
import ibm.wjx.osserver.pojo.User;
import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.constant.CmdKind;
import ibm.wjx.osserver.shell.oc.ReplaceCommand;
import ibm.wjx.osserver.shell.oc.get.GetResourceObjectCommand;
import ibm.wjx.osserver.util.ExceptionLogUtil;
import ibm.wjx.osserver.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create Date: 12/29/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: class to manage user.
 */
public class UserManager {
    private static final String IDENTITY_PROVIDER = "anypassword";
    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);


    /**
     * use the simplest identity provider to create a user. Identity Provider should be configured before openshift start.
     * @param username username
     */
    public void addUser(String username) {
        CreateUserOperation operation = new CreateUserOperation(username, IDENTITY_PROVIDER + ":" + username);
        if (operation.operate()) {
            logger.info("successfully created user {}", username);
        } else {
            logger.error("create user {} failed", username);
        }
    }


    public void deleteUser(String username) {
        DeleteUserOperation operation = new DeleteUserOperation(username, IDENTITY_PROVIDER + ":" + username);
        if (operation.operate()) {
            logger.info("successfully deleted user {}", username);
        } else {
            logger.error("delete user {} failed", username);
        }
    }

    public User getUser(String username) {
        logger.info("Getting user {}", username);
        GetResourceObjectCommand<User> command = new GetResourceObjectCommand<>(new TypeReference<User>() {
        }, CmdKind.USER, username);
        ShellCommandResult<User> result = command.execute();
        if (result.getReturnCode() == BaseShellCommand.PROCESS_OK && result.getData().isPresent()) {
            logger.info("get user {} success", username);
            return result.getData().get();
        } else {
            logger.error("failed to get user {}", username);
            return null;
        }
    }

    public User updateUser(User user) {
        logger.info("updating user");
        try {
            ReplaceCommand command = new ReplaceCommand(CmdKind.USER, JsonUtil.convertToJson(user));
            ShellCommandResult<String> result = command.execute();
            if (result.getReturnCode() == BaseShellCommand.PROCESS_OK) {
                //return the new user, with info and metadata changed
                return getUser(user.getMetadata().getName());
            }
        } catch (JsonProcessingException e) {
            logger.error("Convert user into json failed");
            logger.error(ExceptionLogUtil.getStacktrace(e));
        }
        return user;
    }
}
