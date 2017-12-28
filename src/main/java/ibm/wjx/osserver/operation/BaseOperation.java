package ibm.wjx.osserver.operation;

import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: Operation base class, use template method to execute a series of shell command, handling output and error.
 */
public abstract class BaseOperation<T> {
    protected static Logger logger = LoggerFactory.getLogger(BaseOperation.class);

    private List<BaseShellCommand> commands;
    private List<CommandCompeteHandler> handlers;

    protected void addCommand(BaseShellCommand command) {
        addCommand(command, DoNothingHandler.getInstance());
    }

    protected void addCommand(BaseShellCommand command, CommandCompeteHandler handler) {
        commands.add(command);
        handlers.add(handler);
    }

    /**
     * After all command executed successfully or failed in the middle, it should do the output.
     * @return result that this command produce.
     */
    protected abstract T getResult();

    /**
     * Execute the whole operation, success when all command executed successfully. When one command failed, stop the
     * operation and handle the error.
     */
    public T operate() {
        logger.info("Starting Operation");
        for (int i = 0; i < commands.size(); i++) {
            logger.info("Executing Command No.{}", i + 1);
            BaseShellCommand command = commands.get(i);
            CommandCompeteHandler handler = handlers.get(i);
            ShellCommandResult result = command.execute();
            boolean proceed = handler.handle(result, i == commands.size() - 1 ? null : commands.get(i + 1));
            if (!proceed) {
                logger.info("Error occurred, breaking");
                break;
            }
        }
        return getResult();
    }
}
