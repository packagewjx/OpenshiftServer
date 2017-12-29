package shell;

import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.pojo.BaseApiResult;
import ibm.wjx.osserver.pojo.User;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.constant.CmdKind;
import ibm.wjx.osserver.shell.oc.get.ApiTypeReference;
import ibm.wjx.osserver.shell.oc.get.GetResourceObjectCommand;
import ibm.wjx.osserver.shell.oc.get.GetResourceObjectsCommand;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Create Date: 12/27/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class GetUserCommandTest {
    @Test
    public void testGetUsers() {
        GetResourceObjectsCommand<User> command = new GetResourceObjectsCommand<>(new ApiTypeReference<User>(), CmdKind.USER);
        ShellCommandResult<BaseApiResult<User>> result = command.execute();
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    public void testGetUserCommand() {
        GetResourceObjectCommand<User> command = new GetResourceObjectCommand<>(new TypeReference<User>() {
        }, CmdKind.USER, "test");
        ShellCommandResult<User> result = command.execute();
        System.out.println(result);
    }
}
