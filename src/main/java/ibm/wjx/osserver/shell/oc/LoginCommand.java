package ibm.wjx.osserver.shell.oc;

import ibm.wjx.osserver.shell.resultparser.ResultParser;
import ibm.wjx.osserver.util.ConfigurationUtils;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Create Date: 12/20/17
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: ${Description}
 */
public class LoginCommand extends BaseOcCommand<Boolean> {
    private static final Parser PARSER = new Parser();
    static {
        logger = LoggerFactory.getLogger(LoginCommand.class);
    }

    public LoginCommand() {
        super(PARSER);
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmds = super.getCmdArray();
        cmds.add("login");
        cmds.add("-u");
        cmds.add("system:admin");
        cmds.add(ConfigurationUtils.getConfig(ConfigurationUtils.Keys.OPENSHIFT_SERVER_ADDRESS));
        return cmds;
    }

    private static class Parser implements ResultParser<Boolean> {
        @Override
        public Boolean parse(String rawResult) {
            //just return true, because this method will only be called when login command succeeded.
            return true;
        }
    }

}
