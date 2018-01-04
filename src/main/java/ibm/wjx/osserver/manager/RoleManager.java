package ibm.wjx.osserver.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.pojo.Role;
import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.constant.CmdKind;
import ibm.wjx.osserver.shell.oc.adm.policy.AddAndRemoveRoleBindingCommand;
import ibm.wjx.osserver.shell.oc.adm.policy.RemoveUserGroupCommand;
import ibm.wjx.osserver.shell.oc.create.CreateRoleCommand;
import ibm.wjx.osserver.shell.oc.get.ApiTypeReference;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Create Date: 1/2/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */

@Component
public class RoleManager extends BaseResourceManager<Role> {
    public RoleManager() {
        super(CmdKind.ROLE, new TypeReference<Role>() {
        }, new ApiTypeReference<>());
    }

    /**
     * Add a role to a user
     *
     * @param role     role name
     * @param username username
     * @return true success, false fail
     */
    public boolean addRoleToUser(String role, String username) {
        return addOrRemove(AddAndRemoveRoleBindingCommand.ADD_TO_USER, role, username);
    }

    /**
     * Can not use this method to add a role. Use the other method instead
     *
     * @param name rolename
     * @return no
     * @see RoleManager#add(String, Set, Set, Set)
     */
    @Override
    public Role add(String name) {
        throw new UnsupportedOperationException("Can not add role by its name");
    }

    /**
     * Create a role.
     *
     * @param name         Role name
     * @param verbs        what action this role can do, in Verb class
     * @param resources    what resources can this role manipulate on
     * @param resourceNames nullable. the specific resource name that this role can manipulate on.
     * @return new role added
     */
    public Role add(String name, Set<String> verbs, Set<String> resources, Set<String> resourceNames) {
        CreateRoleCommand createRoleCommand = new CreateRoleCommand(name, verbs, resources, resourceNames == null ? new HashSet<>() : resourceNames);
        ShellCommandResult<String> result = createRoleCommand.execute();
        if (result.getReturnCode() == BaseShellCommand.PROCESS_OK) {
            return get(name);
        } else {
            logger.error("Create role {} failed", name);
            return null;
        }
    }

    /**
     * Add a role to a group
     *
     * @param role      role name
     * @param groupName group name
     * @return true success, false fail
     */
    public boolean addRoleToGroup(String role, String groupName) {
        return addOrRemove(AddAndRemoveRoleBindingCommand.ADD_TO_GROUP, role, groupName);
    }

    /**
     * Remove a role from a user
     *
     * @param role     role name
     * @param username username
     * @return true success, false fail
     */
    public boolean removeRoleFromUser(String role, String username) {
        return addOrRemove(AddAndRemoveRoleBindingCommand.REMOVE_FROM_USER, role, username);
    }

    /**
     * Remove a role from a group
     *
     * @param role      role name
     * @param groupName group name
     * @return true success, false fail
     */
    public boolean removeRoleFromGroup(String role, String groupName) {
        return addOrRemove(AddAndRemoveRoleBindingCommand.REMOVE_FROM_GROUP, role, groupName);
    }

    /**
     * Remove all roles of a user
     *
     * @param username the user want to remove
     * @return true success, false fail
     */
    public boolean removeUserAllRole(String username) {
        return removeGroupOrUser(true, username);
    }

    /**
     * Remove all roles bind to a group
     *
     * @param groupName the group you want to remove
     * @return true success, false fail
     */
    public boolean removeGroupAllRole(String groupName) {
        return removeGroupOrUser(false, groupName);
    }

    /**
     * wrapper method to add/remove a role to a user/group.
     *
     * @param action action to do, in AddAndRemoveRoleBindingCommand
     * @param role   role name
     * @param name   user/group name
     * @return true success, false fail
     */
    private boolean addOrRemove(int action, String role, String name) {
        AddAndRemoveRoleBindingCommand command = new AddAndRemoveRoleBindingCommand(action, role, name);
        ShellCommandResult<String> result = command.execute();
        if (result.getReturnCode() == BaseShellCommand.PROCESS_OK) {
            logger.info("Successfully {} role {} to {} {}",
                    action == AddAndRemoveRoleBindingCommand.ADD_TO_GROUP || action == AddAndRemoveRoleBindingCommand.ADD_TO_USER ? "added" : "removed",
                    role,
                    action == AddAndRemoveRoleBindingCommand.ADD_TO_USER || action == AddAndRemoveRoleBindingCommand.REMOVE_FROM_USER ? "user" : "group",
                    name);
            return true;
        } else {
            logger.info("{} role {} to {} {} failed",
                    action == AddAndRemoveRoleBindingCommand.ADD_TO_GROUP || action == AddAndRemoveRoleBindingCommand.ADD_TO_USER ? "Add" : "Remove",
                    role,
                    action == AddAndRemoveRoleBindingCommand.ADD_TO_USER || action == AddAndRemoveRoleBindingCommand.REMOVE_FROM_USER ? "user" : "group",
                    name);
            return false;
        }
    }

    /**
     * wrapper method to remove user/group
     *
     * @param isUser true to remove user, false to remove group
     * @param name   user or group name
     * @return true success, false fail
     */
    private boolean removeGroupOrUser(boolean isUser, String name) {
        RemoveUserGroupCommand command = new RemoveUserGroupCommand(isUser ? RemoveUserGroupCommand.USER : RemoveUserGroupCommand.GROUP, name);
        ShellCommandResult<String> result = command.execute();
        if (result.getReturnCode() == BaseShellCommand.PROCESS_OK) {
            logger.info("Successfully removed {} {} and its role", isUser ? "user" : "group", name);
            return true;
        } else {
            logger.error("Failed to remove {} {} and its role", isUser ? "user" : "group", name);
            return false;
        }
    }
}
