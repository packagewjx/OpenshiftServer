package ibm.wjx.osserver.web.controller;

import ibm.wjx.osserver.manager.GroupManager;
import ibm.wjx.osserver.manager.ResourceManager;
import ibm.wjx.osserver.pojo.Group;
import ibm.wjx.osserver.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Create Date: 1/4/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
@RestController
@RequestMapping(path = "/group")
public class GroupController extends BaseController<Group> {
    private GroupManager groupManager = (GroupManager) manager;

    public GroupController(ResourceManager<Group> manager) {
        super(manager);
    }

    @RequestMapping(path = "/{name}/user", method = RequestMethod.GET)
    public Set<User> getGroupUser(@PathVariable(value = "name") String groupName) {
        return groupManager.getGroupUsers(groupName);
    }

    @RequestMapping(value = "/{name}/user", method = RequestMethod.PUT)
    public boolean addUserToGroup(@PathVariable(name = "name") String groupName, @RequestParam(name = "username") String username) {
        return groupManager.addUser(groupName, username);
    }

    @RequestMapping(value = "/{name}/user/{username}", method = RequestMethod.DELETE)
    public boolean deleteUserFromGroup(@PathVariable(name = "name") String groupName, @PathVariable("username") String username) {
        return groupManager.removeUser(groupName, username);
    }


}
