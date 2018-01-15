package ibm.wjx.osserver.operation.user;

import ibm.wjx.osserver.operation.BaseOperation;
import ibm.wjx.osserver.operation.OperationResult;
import ibm.wjx.osserver.pojo.Result;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.oc.delete.DeleteResourceCommand;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class DeleteUserOperation extends BaseOperation<Boolean>{
    public DeleteUserOperation(String username, String identityName) {
        addCommand(new DeleteResourceCommand("user", username));
        addCommand(new DeleteResourceCommand("identity", identityName));
    }

    @Override
    protected Result<Boolean> getResult(OperationResult<Boolean> result) {
        if (result.isAllOk()) {
            return Result.newSuccessResult(true);
        } else {
            ShellCommandResult errorResult = result.getCommandResults().get(result.getCommandResults().size() - 1);
            return Result.newFailResult(false, errorResult.getReturnCode(), errorResult.getRawResult());
        }
    }
}
