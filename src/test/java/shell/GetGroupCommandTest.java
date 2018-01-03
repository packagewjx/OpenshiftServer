package shell;

import ibm.wjx.osserver.pojo.Group;
import ibm.wjx.osserver.pojo.ListResult;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.constant.CmdKind;
import ibm.wjx.osserver.shell.oc.get.ApiTypeReference;
import ibm.wjx.osserver.shell.oc.get.GetResourceObjectsCommand;
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
        ShellCommandResult<ListResult<Group>> result = new GetResourceObjectsCommand<Group>(new ApiTypeReference<>(), CmdKind.GROUP).execute();
        System.out.println(result);
    }
}
