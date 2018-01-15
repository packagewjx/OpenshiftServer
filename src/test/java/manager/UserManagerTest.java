package manager;

import ibm.wjx.osserver.constant.ResultCode;
import ibm.wjx.osserver.manager.UserManager;
import ibm.wjx.osserver.pojo.Result;
import ibm.wjx.osserver.pojo.User;
import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.constant.CmdKind;
import ibm.wjx.osserver.shell.oc.create.CreateResourceCommand;
import ibm.wjx.osserver.shell.oc.delete.DeleteResourceCommand;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Create Date: 12/30/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class UserManagerTest {

    @Test
    public void userManagerTest() {
        UserManager manager = new UserManager();
        //test add
        String username = "testuser";
        Result<User> addResult = manager.add(username);
        assertEquals(ResultCode.SUCCESS, addResult.getResultCode());
        User user = addResult.getData();
        assertEquals(username, user.getMetadata().getName());
        //test update, update the new identity
        String anIdentity = "anypassword:anIdentity";
        CreateResourceCommand createIdentityCommand = new CreateResourceCommand(CmdKind.IDENTITY, anIdentity);
        ShellCommandResult<String> commandResult = createIdentityCommand.execute();
        assertEquals(BaseShellCommand.PROCESS_OK, commandResult.getReturnCode());
        Set<String> identities = user.getIdentities();
        identities.clear();
        identities.add(anIdentity);
        user.setIdentities(identities);
        Result<User> updateResult = manager.update(user);
        user = updateResult.getData();
        assertEquals(ResultCode.SUCCESS, updateResult.getResultCode());
        assertNotNull(user.getIdentities());
        assertNotNull(user.getIdentities().iterator().next());
        assertEquals(anIdentity, user.getIdentities().iterator().next());
        //test get
        Result<User> getResult = manager.get(username);
        assertEquals(ResultCode.SUCCESS, getResult.getResultCode());
        user = getResult.getData();
        assertTrue(username.equals(user.getMetadata().getName()));
        //test delete
        assertTrue(manager.delete(username).getData());
        DeleteResourceCommand command = new DeleteResourceCommand(CmdKind.IDENTITY, anIdentity);
        ShellCommandResult<String> commandResult1 = command.execute();
        assertEquals(BaseShellCommand.PROCESS_OK, commandResult1.getReturnCode());
    }

    @Test
    public void testGetAll() {
        UserManager manager = new UserManager();
        Result<List<User>> getAllResult = manager.getAllInAllProjects();
        assertEquals(ResultCode.SUCCESS, getAllResult.getResultCode());
        List<User> users = getAllResult.getData();
        assertNotNull(users);
        getAllResult = manager.getAll();
        assertEquals(ResultCode.SUCCESS, getAllResult.getResultCode());
        users = getAllResult.getData();
        assertNotNull(users);
    }
}
