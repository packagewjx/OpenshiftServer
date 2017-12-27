package ibm.wjx.osserver.shell.oc;

import ibm.wjx.osserver.pojo.BaseApiResult;
import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.resultparser.ResultParser;
import ibm.wjx.osserver.util.ConfigurationUtils;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create Date: 12/20/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: Basic Command, to insert system-wide environment variables and options
 */
public abstract class BaseOcCommand<T> extends BaseShellCommand<T> {
    static {
        logger = LoggerFactory.getLogger(BaseOcCommand.class);
    }

    private String namespace;

    public BaseOcCommand(ResultParser<T> resultParser) {
        super(resultParser);
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * Just 'oc'. Subclass Should use this method and add more things.
     *
     * @return list contains one element oc
     */
    @Override
    protected List<String> getCmdArray() {
        List<String> cmds = new ArrayList<>();
        cmds.add("oc");
        if (namespace != null && !"".equals(namespace)) {
            cmds.add("-n");
            cmds.add(namespace);
        }
        return cmds;
    }

    /**
     * Return System-wide default environment variables.
     *
     * @return set contains system-wide environment vars.
     */
    @Override
    protected Set<String> getEnvs() {
        Set<String> envs = new HashSet<>(16);
        envs.add("KUBECONFIG=" + ConfigurationUtils.getConfig(ConfigurationUtils.Keys.KUBECONFIG_FILE));
        return envs;
    }
}
