package manager;

import ibm.wjx.osserver.constant.ResultCode;
import ibm.wjx.osserver.manager.RoleManager;
import ibm.wjx.osserver.manager.UserManager;
import ibm.wjx.osserver.pojo.Result;
import ibm.wjx.osserver.pojo.Role;
import ibm.wjx.osserver.pojo.User;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
/**
 * Create Date: 1/2/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class RoleManagerTest {
    @Test
    public void userRoleTest() {
        //add a role
        RoleManager manager = new RoleManager();
        String roleName = "testRole";
        Set<String> verbs = new HashSet<>();
        verbs.add("get");
        Set<String> resources = new HashSet<>();
        resources.add("project");
        Result<Role> addResult = manager.add(roleName, verbs, resources, null);
        assertEquals(ResultCode.SUCCESS, addResult.getResultCode());
        Role testRole = addResult.getData();
        assertNotNull(testRole);
        assertTrue(roleName.equals(testRole.getMetadata().getName()));
        //bind role to user test
        assertTrue(manager.addRoleToUser(roleName, "test").getData());
        //remove role form user test
        assertTrue(manager.removeRoleFromUser(roleName, "test").getData());
        //remove role
        assertTrue(manager.delete(roleName).getData());
    }

    @Test
    public void addUserAndRoleTest() {
        //add a user
        UserManager userManager = new UserManager();
        Result<User> addResult = userManager.add("user1");
        assertEquals(ResultCode.SUCCESS, addResult.getResultCode());
        User user1 = addResult.getData();
        assertNotNull(user1);
        //add a role
        RoleManager manager = new RoleManager();
        String roleName = "testRole";
        Set<String> verbs = new HashSet<>();
        verbs.add("get");
        Set<String> resources = new HashSet<>();
        resources.add("project");
        Result<Role> addRoleResult = manager.add(roleName, verbs, resources, null);
        assertEquals(ResultCode.SUCCESS, addRoleResult.getResultCode());
        Role testRole = addRoleResult.getData();
        assertNotNull(testRole);
        assertTrue(roleName.equals(testRole.getMetadata().getName()));
        //bind role to user test
        assertTrue(manager.addRoleToUser(roleName, user1.getMetadata().getName()).getData());
        //remove user's role
        manager.removeUserAllRole(user1.getMetadata().getName());
        userManager.delete(user1.getMetadata().getName());
        //remove role
        assertTrue(manager.delete(roleName).getData());
    }
}
