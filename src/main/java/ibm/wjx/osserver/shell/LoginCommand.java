package ibm.wjx.osserver.shell;

import ibm.wjx.osserver.shell.resultparser.ResultParser;
import ibm.wjx.osserver.util.ConfigurationUtils;

import java.util.List;
import java.util.Set;

/**
 * Create Date: 12/20/17
 * Author: <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: ${Description}
 */
public class LoginCommand extends BasicCommand<Boolean> {
    private static final Parser parser = new Parser();

    public LoginCommand() {
        super(parser);
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
