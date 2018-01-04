package ibm.wjx.osserver.shell.oc;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;

import java.util.List;

/**
 * Create Date: 1/4/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: This command get current project name.
 */
public class ProjectCommand extends BaseOcCommand<String> {

    private String projectName;

    public ProjectCommand() {
        super(RawStringParser.getInstance());
    }

    public ProjectCommand(String projectName) {
        super(RawStringParser.getInstance());
        this.projectName = projectName;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("project");
        cmdArray.add("-q");
        if (projectName != null && !"".equals(projectName)) {
            cmdArray.add(projectName);
        }
        return cmdArray;
    }
}
