package ibm.wjx.osserver.operation;

import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class DoNothingHandler implements CommandCompleteHandler {
    private static DoNothingHandler instance = new DoNothingHandler();

    private DoNothingHandler() {
    }

    public static DoNothingHandler getInstance() {
        return instance;
    }


    /**
     * Basic Implementation of CommandCompleteHandler
     * @param result      command result.
     * @param nextCommand next command after this command, null if this is the last command. To add or change param based
     *                    on last results.
     * @return when the result return code is 0, return true, otherwise false.
     */
    @Override
    public boolean handle(ShellCommandResult result, BaseShellCommand nextCommand) {
        return result.getReturnCode() == 0;
    }
}
