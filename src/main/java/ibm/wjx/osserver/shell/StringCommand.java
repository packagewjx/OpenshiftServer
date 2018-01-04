package ibm.wjx.osserver.shell;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Create Date: 12/21/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: This Class use to execute any string command with provided environment variables.
 */
public class StringCommand extends BaseShellCommand<String> {
    static {
        logger = LoggerFactory.getLogger(StringCommand.class);
    }

    private String command;
    private Map<String, String> envs;

    public StringCommand() {
        this("");
    }

    public StringCommand(String command) {
        super(RawStringParser.getInstance());
        this.command = command;
        this.envs = new HashMap<>();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Set the environment variables used while running this command
     *
     * @param name  Env Name, often ALL UPPERCASE ALPHABET, eg. KUBECONFIG.
     * @param value Env Value, any value you use
     */
    public void setEnvs(String name, String value) {
        if (envs.containsKey(name)) {
            logger.info("Replacing {} env value from {} to {}", name, envs.get(name), value);
        }
        envs.put(name, value);
    }


    @Override
    protected List<String> getCmdArray() {
        //TODO split the string so that it did not split "" and '\ '('\<space>')
        return Arrays.stream(command.split(" ")).collect(Collectors.toList());
    }

    @Override
    protected Set<String> getEnvs() {
        Set<String> envSet = new HashSet<>();
        for (Map.Entry<String, String> entry : envs.entrySet()) {
            envSet.add(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }
        return envSet;
    }
}
