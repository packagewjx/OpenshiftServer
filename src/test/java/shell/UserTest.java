package shell;

import ibm.wjx.osserver.operation.user.CreateUserOperation;
import ibm.wjx.osserver.operation.user.DeleteUserOperation;
import org.junit.Test;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class UserTest {

    @Test
    public void createUserOperationTest() {
        CreateUserOperation operation = new CreateUserOperation("wujunxian", "anypassword:wujunxian");
        operation.operate();
    }

    @Test
    public void deleteUserOperationTest() {
        DeleteUserOperation operation = new DeleteUserOperation("wujunxian", "anypassword:wujunxian");
        operation.operate();
    }
}
