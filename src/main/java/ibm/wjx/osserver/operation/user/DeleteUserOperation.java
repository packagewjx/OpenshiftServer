package ibm.wjx.osserver.operation.user;

import ibm.wjx.osserver.operation.BaseOperation;
import ibm.wjx.osserver.shell.oc.delete.DeleteResourceCommand;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class DeleteUserOperation extends BaseOperation<String>{
    public DeleteUserOperation(String username, String identityName) {
        addCommand(new DeleteResourceCommand("user", username));
        addCommand(new DeleteResourceCommand("identity", identityName));
    }

    @Override
    protected String getResult() {
        return "";
    }
}
