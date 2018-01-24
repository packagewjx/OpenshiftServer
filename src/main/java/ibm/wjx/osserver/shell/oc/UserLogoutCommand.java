package ibm.wjx.osserver.shell.oc;

import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.resultparser.ReturnTrueParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Create Date: 1/24/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: Used to logout a user, invalidate its token
 */
public class UserLogoutCommand extends BaseShellCommand<Boolean> {
    private String token;

    public UserLogoutCommand(String token) {
        super(ReturnTrueParser.getInstance());
        this.token = token;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = new ArrayList<>();
        cmdArray.add("/bin/bash");
        cmdArray.add("-c");
        cmdArray.add(String.format("oc login --token %s && oc logout", token));
        return cmdArray;
    }

    @Override
    protected Set<String> getEnvs() {
        return getSystemEnv();
    }
}
