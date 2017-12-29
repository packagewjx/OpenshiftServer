package ibm.wjx.osserver.operation;

import ibm.wjx.osserver.shell.ShellCommandResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Create Date: 12/29/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class OperationResult<T> {
    private T result;
    private List<ShellCommandResult> commandResults;
    private int code;

    public OperationResult() {
        commandResults = new ArrayList<>();
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public List<ShellCommandResult> getCommandResults() {
        return commandResults;
    }

    public void setCommandResults(List<ShellCommandResult> commandResults) {
        this.commandResults = commandResults;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isAllOk() {
        for (ShellCommandResult commandResult : commandResults) {
            if (commandResult.getReturnCode() != 0) {
                return false;
            }
        }
        return true;
    }
}
