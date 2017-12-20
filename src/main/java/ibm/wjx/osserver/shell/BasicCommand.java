package ibm.wjx.osserver.shell;

import ibm.wjx.osserver.shell.resultparser.ResultParser;
import ibm.wjx.osserver.util.ConfigurationUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create Date: 12/20/17
 * Author: <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: ${Description}
 */
public class BasicCommand<T> extends BaseShellCommand<T>{
    public BasicCommand(ResultParser<T> resultParser) {
        super(resultParser);
    }

    /**
     * Just oc. Subclass Should Override this method to add more things.
     * @return list contains one element oc
     */
    @Override
    protected List<String> getCmdArray() {
        List<String> cmds = new ArrayList<>();
        cmds.add("oc");
        return cmds;
    }

    /**
     * Return System-wide default environment variables.
     * @return set contains system-wide environment vars.
     */
    @Override
    protected Set<String> getEnvs() {
        Set<String> envs = new HashSet<>(16);
        envs.add("KUBECONFIG=" + ConfigurationUtils.getConfig(ConfigurationUtils.Keys.KUBECONFIG_FILE));
        return envs;
    }
}
