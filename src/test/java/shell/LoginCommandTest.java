package shell;

import ibm.wjx.osserver.shell.oc.LoginCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;
import org.junit.Assert;
import org.junit.Test;

/**
 * Create Date: 12/20/17
 * Author: <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class LoginCommandTest {
    @Test
    public void testLogin() {
        ShellCommandResult<Boolean> result = new LoginCommand().execute();
        Assert.assertTrue(result.getData().isPresent());
        Assert.assertTrue(result.getData().get());
    }
}