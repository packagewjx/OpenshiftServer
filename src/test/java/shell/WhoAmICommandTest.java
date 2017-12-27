package shell;

import ibm.wjx.osserver.shell.oc.WhoAmICommand;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Create Date: 12/20/17
 * Author: <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: ${Description}
 */
public class WhoAmICommandTest {

    @Test
    public void testCommand() {
        Optional<String> result = new WhoAmICommand().execute().getData();
        Assert.assertTrue(result.isPresent());
        Assert.assertTrue("system:admin".equals(result.get()));
    }

}
