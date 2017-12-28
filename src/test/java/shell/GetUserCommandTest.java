package shell;

import ibm.wjx.osserver.pojo.BaseApiResult;
import ibm.wjx.osserver.pojo.User;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.oc.get.GetUsersCommand;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Create Date: 12/27/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class GetUserCommandTest {
    @Test
    public void testGetUser() {
        GetUsersCommand command = new GetUsersCommand();
        ShellCommandResult<BaseApiResult<User>> result = command.execute();
        System.out.println(result);
        assertNotNull(result);
    }
}
