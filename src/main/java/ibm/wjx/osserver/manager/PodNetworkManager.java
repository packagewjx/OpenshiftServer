package ibm.wjx.osserver.manager;

import ibm.wjx.osserver.pojo.Result;
import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.oc.adm.podnetwork.IsolateNetworkCommand;
import ibm.wjx.osserver.shell.oc.adm.podnetwork.JoinNetworkCommand;
import ibm.wjx.osserver.shell.oc.adm.podnetwork.MakeNetworkGlobalCommand;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Create Date: 1/3/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 * TODO test this manager
 */
@Component
public class PodNetworkManager {

    public Result<Boolean> joinProjects(String toProject, String selector) {
        return execute(new JoinNetworkCommand(toProject, selector));
    }

    public Result<Boolean> joinProjects(String toProject, List<String> projects) {
        return execute(new JoinNetworkCommand(toProject, projects));
    }

    public Result<Boolean> isolateProjects(String selector) {
        return execute(new IsolateNetworkCommand(selector));
    }

    public Result<Boolean> isolateProjects(List<String> projects) {
        return execute(new IsolateNetworkCommand(projects));
    }

    public Result<Boolean> makeProjectsGlobal(String selector) {
        return execute(new MakeNetworkGlobalCommand(selector));
    }

    public Result<Boolean> makeProjectsGlobal(List<String> projects) {
        return execute(new MakeNetworkGlobalCommand(projects));
    }

    private Result<Boolean> execute(BaseShellCommand command) {
        ShellCommandResult commandResult = command.execute();
        if (commandResult.getReturnCode() == BaseShellCommand.PROCESS_OK) {
            return Result.newSuccessResult(true);
        } else {
            return Result.newFailResult(false, commandResult.getReturnCode(), commandResult.getRawResult());
        }
    }
}
