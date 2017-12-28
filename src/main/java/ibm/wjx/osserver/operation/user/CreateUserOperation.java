package ibm.wjx.osserver.operation.user;

import ibm.wjx.osserver.operation.BaseOperation;
import ibm.wjx.osserver.shell.oc.create.CreateResourceCommand;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: This class do something to create the user and identity in one operation.
 */
public class CreateUserOperation extends BaseOperation<String> {
    public CreateUserOperation(String username, String identityName) {
        //TODO handle errors and roll back
        addCommand(new CreateResourceCommand("user", username));
        addCommand(new CreateResourceCommand("identity", identityName));
        addCommand(new CreateResourceCommand("useridentitymapping", identityName, username));
    }

    @Override
    protected String getResult() {
        return "";
    }
}
