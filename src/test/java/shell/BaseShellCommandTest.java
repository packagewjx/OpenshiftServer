package shell;

import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.resultparser.ResultParser;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Create Date: 12/19/17
 * Author: <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: ${Description}
 */
public class BaseShellCommandTest {

    @Test
    public void testCommand() {
        ShellCommandResult<String> result = new TestCommand("ls -l").execute();
        assertNotNull(result);
        assertEquals(0, result.getReturnCode());
        assertNotEquals(0, result.getData().length());
        System.out.println(result.getData());
    }

    @Test
    public void testWrongCommand() {
        ShellCommandResult<String> result = new TestCommand("this is a wrong command").execute();
        assertNotNull(result);
        assertNotEquals(0, result.getReturnCode());
        assertNotEquals(0, result.getRawResult().length());
        System.out.println(result.getRawResult());
    }

    @Test
    public void testWrongCommand2() {
        ShellCommandResult<String> result = new TestCommand("ls help").execute();
        assertNotNull(result);
        assertNotEquals(0, result.getReturnCode());
        assertNotEquals(0, result.getRawResult().length());
        System.out.println(result.getRawResult());
    }
}

class TestCommand extends BaseShellCommand<String> {
    private String command;
    static {
        logger = LoggerFactory.getLogger(TestCommand.class);
    }

    public TestCommand(String command) {
        super(new TestParser());
        this.command = command;
    }


    @Override
    protected List<String> getCmdArray() {
        return Arrays.stream(command.split(" ")).collect(Collectors.toList());
    }

    @Override
    protected Set<String> getEnvs() {
        return null;
    }
}

class TestParser implements ResultParser<String> {
    @Override
    public String parse(String rawResult) {
        return rawResult;
    }
}