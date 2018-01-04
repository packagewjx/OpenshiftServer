package ibm.wjx.osserver.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.pojo.Group;
import ibm.wjx.osserver.pojo.ListResult;
import ibm.wjx.osserver.pojo.User;
import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.constant.CmdKind;
import ibm.wjx.osserver.shell.oc.get.ApiTypeReference;
import ibm.wjx.osserver.shell.oc.get.GetResourceObjectCommand;
import ibm.wjx.osserver.shell.oc.get.GetResourceObjectsCommand;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Create Date: 12/29/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */

@Component
public class GroupManager extends BaseResourceManager<Group> {
    public GroupManager() {
        super(CmdKind.GROUP, new TypeReference<Group>() {
        }, new ApiTypeReference<>());
    }

    /**
     * A wrapper method just to add a user to a group
     *
     * @param groupName name of the group
     * @param username  user you want to add
     */
    public void addUser(String groupName, String username) {
        Group group = get(groupName);
        if (group == null) {
            logger.error("Cannot find group {}", groupName);
            return;
        }
        group.getUsers().add(username);
        Group updated = update(group);
        if (!updated.getMetadata().getResourceVersion().equals(group.getMetadata().getResourceVersion())) {
            logger.info("Add user {} to group {} succeed.", username, groupName);
        }
    }

    /**
     * A wrapper method to remove user from a group
     * @param groupName group name
     * @param username username
     */
    public void removeUser(String groupName, String username) {
        Group group = get(groupName);
        if (group == null) {
            logger.error("Cannot find group {}", groupName);
            return;
        }
        group.getUsers().remove(username);
        Group updated = update(group);
        if (!updated.getMetadata().getResourceVersion().equals(group.getMetadata().getResourceVersion())) {
            logger.info("Delete user {} from group {} succeed.", username, groupName);
        }
    }

    /**
     * Group Pojo stores username, not object. This method use to get objects.
     * @param groupName group name
     * @return user objects from group
     */
    public Set<User> getGroupUsers(String groupName) {
        Set<User> result = new HashSet<>();
        Group group = get(groupName);
        if (group == null) {
            logger.error("Cannot find group {}", groupName);
            return result;
        }
        if (group.getUsers().size() > 1) {
            GetResourceObjectsCommand<User> command = new GetResourceObjectsCommand<>(new ApiTypeReference<>(), CmdKind.USER, group.getUsers());
            ShellCommandResult<ListResult<User>> commandResult = command.execute();
            if (commandResult.getReturnCode() == BaseShellCommand.PROCESS_OK && commandResult.getData().isPresent()) {
                result.addAll(commandResult.getData().get().getItems());
            }
        } else if (group.getUsers().size() == 1) {
            GetResourceObjectCommand<User> command = new GetResourceObjectCommand<>(new TypeReference<User>() {
            }, CmdKind.USER, group.getUsers().iterator().next());
            ShellCommandResult<User> commandResult = command.execute();
            if (commandResult.getReturnCode() == BaseShellCommand.PROCESS_OK && commandResult.getData().isPresent()) {
                result.add(commandResult.getData().get());
            }
        }
        return result;

    }
}
