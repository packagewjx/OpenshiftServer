package ibm.wjx.osserver.operation;

import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class DoNothingHandler implements CommandCompeteHandler {
    private static DoNothingHandler instance = new DoNothingHandler();

    private DoNothingHandler() {
    }

    public static DoNothingHandler getInstance() {
        return instance;
    }


    @Override
    public boolean handle(ShellCommandResult result, BaseShellCommand nextCommand) {
        return true;
    }
}
