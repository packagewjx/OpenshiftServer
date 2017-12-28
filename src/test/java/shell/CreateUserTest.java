package shell;

import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.oc.create.CreateIdentityCommand;
import ibm.wjx.osserver.shell.oc.create.CreateUserCommand;
import ibm.wjx.osserver.shell.oc.create.CreateUserIdentityCommand;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class CreateUserTest {
    @Test
    public void createUserTest() {
        CreateUserCommand createUserCommand = new CreateUserCommand("wujunxian");
        CreateIdentityCommand createIdentityCommand = new CreateIdentityCommand("anypassword:wujunxian");
        CreateUserIdentityCommand createUserIdentityCommand = new CreateUserIdentityCommand("wujunxian", "anypassword:wujunxian");
        ShellCommandResult<String> r1 = createUserCommand.execute();
        assertEquals(0, r1.getReturnCode());
        ShellCommandResult<String> r2 = createIdentityCommand.execute();
        assertEquals(0, r2.getReturnCode());
        ShellCommandResult<String> r3 = createUserIdentityCommand.execute();
        assertEquals(0, r3.getReturnCode());
    }
}
