package manager;

import ibm.wjx.osserver.manager.GroupManager;
import ibm.wjx.osserver.pojo.User;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Create Date: 1/2/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class GroupManagerTest {

    @Test
    public void testAddAndDelete() {
        GroupManager manager = new GroupManager();
        manager.addUser("test", "test");
        manager.removeUser("test", "test");
    }

    @Test
    public void testGetUsers() {
        GroupManager manager = new GroupManager();
        manager.addUser("test", "test");
        Set<User> groupUsers = manager.getGroupUsers("test");
        boolean found = false;
        for (User groupUser : groupUsers) {
            if (groupUser.getMetadata().getName().equals("test")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
        manager.removeUser("test", "test");
    }
}
