package ibm.wjx.osserver.shell.oc.adm.podnetwork;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;

import java.util.List;

/**
 * Create Date: 1/3/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class MakeNetworkGlobalCommand extends BasePodNetworkCommand<String> {
    private String selector;
    private List<String> projects;

    public MakeNetworkGlobalCommand(String selector) {
        super(RawStringParser.getInstance());
        this.selector = selector;
    }

    public MakeNetworkGlobalCommand(List<String> projects) {
        super(RawStringParser.getInstance());
        this.projects = projects;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("make-projects-global");
        if (selector != null) {
            cmdArray.add("--selector='" + selector + "'");
        } else {
            cmdArray.addAll(projects);
        }
        return cmdArray;
    }
}
