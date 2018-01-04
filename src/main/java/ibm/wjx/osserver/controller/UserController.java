package ibm.wjx.osserver.controller;

import ibm.wjx.osserver.manager.ResourceManager;
import ibm.wjx.osserver.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create Date: 1/4/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */

@RestController
@RequestMapping(path = "/user")
public class UserController extends BaseController<User> {

    @Autowired
    public UserController(ResourceManager<User> manager) {
        super(manager);
    }
}
