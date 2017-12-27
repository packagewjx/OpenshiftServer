package ibm.wjx.osserver.shell.oc;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * Create Date: 12/20/17
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: Execute 'oc whoami' to confirm that current user is system:admin
 */
public class WhoAmICommand extends BaseOcCommand<String> {
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
