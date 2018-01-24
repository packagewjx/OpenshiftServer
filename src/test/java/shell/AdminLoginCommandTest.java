package shell;

import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.oc.AdminLoginCommand;
import org.junit.Assert;
import org.junit.Test;

/**
 * Create Date: 12/20/17
 * Author: <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class AdminLoginCommandTest {
    @Test
    public void testLogin() {
        ShellCommandResult<Boolean> result = new AdminLoginCommand().execute();
        Assert.assertTrue(result.getData().isPresent());
        Assert.assertTrue(result.getData().get());
    }
}