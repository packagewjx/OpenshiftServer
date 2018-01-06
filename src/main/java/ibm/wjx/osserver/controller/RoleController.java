package ibm.wjx.osserver.controller;

import ibm.wjx.osserver.manager.ResourceManager;
import ibm.wjx.osserver.manager.RoleManager;
import ibm.wjx.osserver.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create Date: 1/4/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
@RestController
@RequestMapping(path = "/role")
public class RoleController extends BaseController<Role> {
    private RoleManager roleManager = (RoleManager) manager;

    @Autowired
    public RoleController(ResourceManager<Role> manager) {
        super(manager);
    }

    @RequestMapping(value = "/{name}", params = "username", method = RequestMethod.PUT)
    public boolean addRoleToUser(@PathVariable(name = "name") String role, @RequestParam("username") String username) {
        return roleManager.addRoleToUser(role, username);
    }

    @RequestMapping(value = "/{name}", params = "groupName", method = RequestMethod.PUT)
    public boolean addRoleToGroup(@PathVariable(name = "name") String role, @RequestParam("groupName") String groupName) {
        return roleManager.addRoleToGroup(role, groupName);
    }

    @RequestMapping(value = "/{name}/user/{username}", method = RequestMethod.DELETE)
    public boolean removeRoleFromUser(@PathVariable("name") String role, @PathVariable("username") String username) {
        return roleManager.removeRoleFromUser(role, username);
    }

    @RequestMapping(value = "/{name}/group/{groupName}", method = RequestMethod.DELETE)
    public boolean removeRoleFromGroup(@PathVariable("name") String role, @PathVariable("groupName") String groupName) {
        return roleManager.removeRoleFromGroup(role, groupName);
    }

    @RequestMapping(value = "/all/user/{username}", method = RequestMethod.DELETE)
    public boolean removeRolesFromUser(@PathVariable("username") String username) {
        return roleManager.removeUserAllRole(username);
    }

    @RequestMapping(value = "/all/group/{groupName}", method = RequestMethod.DELETE)
    public boolean removeUsersFromUser(@PathVariable("groupName") String groupName) {
        return roleManager.removeGroupAllRole(groupName);
    }



}
