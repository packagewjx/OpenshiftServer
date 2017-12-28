package ibm.wjx.osserver.operation;

import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class ReturnTrueHandler implements CommandCompleteHandler {
    private static ReturnTrueHandler instance = new ReturnTrueHandler();

    private ReturnTrueHandler() {
    }

    public static ReturnTrueHandler getInstance() {
        return instance;
    }

    @Override
    public boolean handle(ShellCommandResult result, BaseShellCommand nextCommand) {
        return true;
    }
}
