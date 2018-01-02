package ibm.wjx.osserver.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.pojo.Group;
import ibm.wjx.osserver.shell.constant.CmdKind;

/**
 * Create Date: 12/29/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class GroupManager extends BaseManager<Group> {
    public GroupManager() {
        super(CmdKind.GROUP, new TypeReference<Group>() {
        });
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
}
