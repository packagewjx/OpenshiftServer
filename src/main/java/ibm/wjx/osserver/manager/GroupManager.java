package ibm.wjx.osserver.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.constant.ResultCode;
import ibm.wjx.osserver.pojo.Group;
import ibm.wjx.osserver.pojo.ListResult;
import ibm.wjx.osserver.pojo.Result;
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
    public Result<Boolean> addUser(String groupName, String username) {
        Result<Group> getResult = get(groupName);
        if (getResult.getResultCode() != ResultCode.SUCCESS || getResult.getData() == null) {
            logger.error("Cannot find group {}", groupName);
            return Result.newFailResult(false, getResult.getResultCode(), getResult.getMessage());
        }
        Group group = getResult.getData();
        group.getUsers().add(username);
        Result<Group> updateResult = update(group);
        if (updateResult.getResultCode() == ResultCode.SUCCESS) {
            logger.info("Add user {} to group {} succeed.", username, groupName);
            return Result.newSuccessResult(true);
        } else {
            logger.error("Add user {} to group {} failed, message is {}", username, groupName, updateResult.getMessage());
            return Result.newFailResult(false, updateResult.getResultCode(), updateResult.getMessage());
        }
    }

    /**
     * A wrapper method to remove user from a group
     *
     * @param groupName group name
     * @param username  username
     */
    public Result<Boolean> removeUser(String groupName, String username) {
        Result<Group> getResult = get(groupName);
        if (getResult.getResultCode() != ResultCode.SUCCESS) {
            logger.error("Cannot find group {}", groupName);
            return Result.convertFromOther(false, getResult);
        }
        Group group = getResult.getData();
        group.getUsers().remove(username);
        Result<Group> updateResult = update(group);
        if (updateResult.getResultCode() == ResultCode.SUCCESS) {
            logger.info("Delete user {} from group {} succeed.", username, groupName);
            return Result.newSuccessResult(true);
        } else {
            logger.error("Delete user {} from group {} failed, message is {}", username, groupName, updateResult.getMessage());
            return Result.convertFromOther(false, updateResult);
        }
    }

    /**
     * Group Pojo stores username, not object. This method use to get objects.
     *
     * @param groupName group name
     * @return user objects from group
     */
    public Result<Set<User>> getGroupUsers(String groupName) {
        Set<User> groupUsers = new HashSet<>();
        Result<Group> getResult = get(groupName);
        if (getResult.getResultCode() != ResultCode.SUCCESS) {
            logger.error("Cannot find group {}", groupName);
            return Result.convertFromOther(new HashSet<>(), getResult);
        }
        Group group = getResult.getData();
        if (group.getUsers().size() > 1) {
            GetResourceObjectsCommand<User> command = new GetResourceObjectsCommand<>(new ApiTypeReference<>(), CmdKind.USER, group.getUsers());
            ShellCommandResult<ListResult<User>> commandResult = command.execute();
            if (commandResult.getReturnCode() == BaseShellCommand.PROCESS_OK && commandResult.getData().isPresent()) {
                groupUsers.addAll(commandResult.getData().get().getItems());
            } else {
                return Result.newFailResult(new HashSet<>(), commandResult.getReturnCode(), commandResult.getRawResult());
            }
        } else if (group.getUsers().size() == 1) {
            GetResourceObjectCommand<User> command = new GetResourceObjectCommand<>(new TypeReference<User>() {
            }, CmdKind.USER, group.getUsers().iterator().next());
            ShellCommandResult<User> commandResult = command.execute();
            if (commandResult.getReturnCode() == BaseShellCommand.PROCESS_OK && commandResult.getData().isPresent()) {
                groupUsers.add(commandResult.getData().get());
            } else {
                return Result.newFailResult(new HashSet<>(), commandResult.getReturnCode(), commandResult.getRawResult());
            }
        }
        return Result.newSuccessResult(groupUsers);

    }
}
