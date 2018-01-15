package manager;

import ibm.wjx.osserver.constant.ResultCode;
import ibm.wjx.osserver.manager.GroupManager;
import ibm.wjx.osserver.pojo.Result;
import ibm.wjx.osserver.pojo.User;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
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
        String testUserName = "test";
        String testGroupName = "test";
        manager.addUser(testGroupName, testUserName);
        Result<Set<User>> getGroupResult = manager.getGroupUsers(testGroupName);
        assertEquals(ResultCode.SUCCESS, getGroupResult.getResultCode());
        Set<User> groupUsers = getGroupResult.getData();
        boolean found = false;
        for (User groupUser : groupUsers) {
            if (groupUser.getMetadata().getName().equals(testUserName)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
        manager.removeUser(testGroupName, testUserName);
    }
}
