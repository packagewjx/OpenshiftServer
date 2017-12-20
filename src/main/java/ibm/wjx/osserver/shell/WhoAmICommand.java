package ibm.wjx.osserver.shell;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;
import ibm.wjx.osserver.shell.resultparser.ResultParser;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Create Date: 12/20/17
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class WhoAmICommand extends BasicCommand<String>{
    static {
        logger = LoggerFactory.getLogger(WhoAmICommand.class);
    }
    public WhoAmICommand() {
        super(new RawStringParser());
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("whoami");
        return cmdArray;
    }

    @Override
    protected Set<String> getEnvs() {
        return super.getEnvs();
    }
}
