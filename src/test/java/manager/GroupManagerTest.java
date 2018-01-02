package manager;

import ibm.wjx.osserver.manager.GroupManager;
import org.junit.Test;

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


}
