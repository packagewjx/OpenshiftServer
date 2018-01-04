package ibm.wjx.osserver.manager;

import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.oc.adm.podnetwork.IsolateNetworkCommand;
import ibm.wjx.osserver.shell.oc.adm.podnetwork.JoinNetworkCommand;
import ibm.wjx.osserver.shell.oc.adm.podnetwork.MakeNetworkGlobalCommand;

import java.util.List;

/**
 * Create Date: 1/3/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 * TODO test this manager
 */
public class PodNetworkManager {

    public boolean joinProjects(String toProject, String selector) {
        return new JoinNetworkCommand(toProject, selector).execute().getReturnCode() == BaseShellCommand.PROCESS_OK;
    }

    public boolean joinProjects(String toProject, List<String> projects) {
        return new JoinNetworkCommand(toProject, projects).execute().getReturnCode() == BaseShellCommand.PROCESS_OK;
    }

    public boolean isolateProjects(String selector) {
        return new IsolateNetworkCommand(selector).execute().getReturnCode() == BaseShellCommand.PROCESS_OK;
    }

    public boolean isolateProjects(List<String> projects) {
        return new IsolateNetworkCommand(projects).execute().getReturnCode() == BaseShellCommand.PROCESS_OK;
    }

    public boolean makeProjectsGlobal(String selector) {
        return new MakeNetworkGlobalCommand(selector).execute().getReturnCode() == BaseShellCommand.PROCESS_OK;
    }

    public boolean makeProjectsGlobal(List<String> projects) {
        return new MakeNetworkGlobalCommand(projects).execute().getReturnCode() == BaseShellCommand.PROCESS_OK;
    }
}
