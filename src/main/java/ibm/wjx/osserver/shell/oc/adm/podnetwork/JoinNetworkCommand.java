package ibm.wjx.osserver.shell.oc.adm.podnetwork;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;

import java.util.List;

/**
 * Create Date: 1/3/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class JoinNetworkCommand extends BasePodNetworkCommand<String> {
    private String toProject;
    private String selector;
    private List<String> projects;

    public JoinNetworkCommand(String toProject, List<String> projects) {
        super(RawStringParser.getInstance());
        this.toProject = toProject;
        this.projects = projects;
    }

    public JoinNetworkCommand(String toProject, String selector) {
        super(RawStringParser.getInstance());
        this.toProject = toProject;
        this.selector = selector;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("join-project");
        cmdArray.add("--to=" + toProject);
        if (selector != null) {
            cmdArray.add("--selector='" + selector + "'");
        } else {
            cmdArray.addAll(projects);
        }
        return cmdArray;
    }
}
