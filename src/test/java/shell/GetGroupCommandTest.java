package shell;

import ibm.wjx.osserver.pojo.BaseApiResult;
import ibm.wjx.osserver.pojo.Group;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.oc.get.GetGroupCommand;
import org.junit.Test;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class GetGroupCommandTest {
    @Test
    public void testGetGroupTest() {
        ShellCommandResult<BaseApiResult<Group>> result = new GetGroupCommand().execute();
        System.out.println(result);
    }
}
