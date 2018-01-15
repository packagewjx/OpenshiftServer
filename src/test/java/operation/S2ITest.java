package operation;

import ibm.wjx.osserver.operation.s2i.S2IOperation;
import ibm.wjx.osserver.pojo.Result;
import org.junit.Test;

/**
 * Create Date: 1/4/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class S2ITest {
    @Test
    public void testS2IOperation() {
        //language=Bash
        String assemble = "#!/usr/bin/env bash\n" +
                "echo \"Building sources\"\n" +
                "sleep 1\n" +
                "echo \"Installing binarys\"\n" +
                "sleep 1";
        //language=Bash
        String run = "echo \"running\"";
        S2IOperation operation = new S2IOperation(assemble, run, "tests2i");
        Result<Boolean> operate = operation.operate();
        assert operate.getData();
    }
}
