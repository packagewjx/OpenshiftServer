package manager;

import ibm.wjx.osserver.manager.RoleManager;
import ibm.wjx.osserver.manager.UserManager;
import ibm.wjx.osserver.pojo.Role;
import ibm.wjx.osserver.pojo.User;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
        Role testRole = manager.add(roleName, verbs, resources, null);
        assertNotNull(testRole);
        assertTrue(roleName.equals(testRole.getMetadata().getName()));
        //bind role to user test
        assertTrue(manager.addRoleToUser(roleName, "test"));
        //remove role form user test
        assertTrue(manager.removeRoleFromUser(roleName, "test"));
        //remove role
        assertTrue(manager.delete(roleName));
    }

    @Test
    public void addUserAndRoleTest() {
        //add a user
        UserManager userManager = new UserManager();
        User user1 = userManager.add("user1");
        assertNotNull(user1);
        //add a role
        RoleManager manager = new RoleManager();
        String roleName = "testRole";
        Set<String> verbs = new HashSet<>();
        verbs.add("get");
        Set<String> resources = new HashSet<>();
        resources.add("project");
        Role testRole = manager.add(roleName, verbs, resources, null);
        assertNotNull(testRole);
        assertTrue(roleName.equals(testRole.getMetadata().getName()));
        //bind role to user test
        assertTrue(manager.addRoleToUser(roleName, user1.getMetadata().getName()));
        //remove user's role
        manager.removeUserAllRole(user1.getMetadata().getName());
        userManager.delete(user1.getMetadata().getName());
        //remove role
        assertTrue(manager.delete(roleName));
    }
}
