package shell;

import ibm.wjx.osserver.shell.constant.CmdKind;
import ibm.wjx.osserver.shell.oc.ReplaceCommand;
import org.junit.Test;

/**
 * Create Date: 12/29/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class TestReplace {
    @Test
    public void testReplace() {
        String o = "";
        ReplaceCommand command = new ReplaceCommand(CmdKind.USER, o);
        command.execute();
    }
}
