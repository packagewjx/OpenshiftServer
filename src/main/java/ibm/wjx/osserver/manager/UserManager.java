package ibm.wjx.osserver.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.operation.user.CreateUserOperation;
import ibm.wjx.osserver.operation.user.DeleteUserOperation;
import ibm.wjx.osserver.pojo.User;
import ibm.wjx.osserver.shell.constant.CmdKind;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create Date: 12/29/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: class to manage user.
 */
public class UserManager extends BaseManager<User> {
    private static final String IDENTITY_PROVIDER = "anypassword";
    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);

    public UserManager() {
        super(CmdKind.USER, new TypeReference<User>() {
        });
    }

    @Override
    public User add(String name) {
        CreateUserOperation operation = new CreateUserOperation(name, IDENTITY_PROVIDER + ":" + name);
        if (operation.operate()) {
            logger.info("successfully created user {}", name);
            return get(name);
        } else {
            logger.error("create user {} failed", name);
            return null;
        }
    }

    @Override
    public boolean delete(String name) {
        DeleteUserOperation operation = new DeleteUserOperation(name, IDENTITY_PROVIDER + ":" + name);
        if (operation.operate()) {
            logger.info("successfully deleted user {}", name);
            return true;
        } else {
            logger.error("delete user {} failed", name);
            return false;
        }
    }
}
