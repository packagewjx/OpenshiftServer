package ibm.wjx.osserver.shell.oc;

import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.resultparser.ResultParser;
import ibm.wjx.osserver.util.ConfigurationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Create Date: 1/24/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class UserLoginCommand extends BaseShellCommand<String> {
    private static Parser parser = new Parser();
    private String username;
    private String password;

    public UserLoginCommand(String username, String password) {
        super(parser);
        this.username = username;
        this.password = password;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = new ArrayList<>();
        //can not use && in Runtime.exec(), so use bash
        cmdArray.add("/bin/bash");
        cmdArray.add("-c");
        cmdArray.add(String.format("oc login -u %s -p %s --insecure-skip-tls-verify %s && oc whoami -t", username, password, ConfigurationUtils.getConfig(ConfigurationUtils.Keys.OPENSHIFT_SERVER_ADDRESS)));
        return cmdArray;
    }

    @Override
    protected Set<String> getEnvs() {
        return getSystemEnv();
    }

    private static class Parser implements ResultParser<String> {
        @Override
        public String parse(String rawResult) {
            return rawResult.substring(rawResult.lastIndexOf('\n') + 1);
        }
    }

}
