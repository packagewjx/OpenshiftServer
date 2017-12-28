package ibm.wjx.osserver.operation;

import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public interface CommandCompleteHandler {
    /**
     * This method should handle both success and fail situation when a command completed.
     *
     * @param result      command result.
     * @param nextCommand next command after this command, null if this is the last command. To add or change param based
     *                    on last results.
     * @return return true, to execute, false to stop and return.
     */
    boolean handle(ShellCommandResult result, BaseShellCommand nextCommand);
}
