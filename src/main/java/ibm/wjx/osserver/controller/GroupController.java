package ibm.wjx.osserver.controller;

import ibm.wjx.osserver.manager.ResourceManager;
import ibm.wjx.osserver.pojo.Group;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create Date: 1/4/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
@RestController
@RequestMapping(path = "/group")
public class GroupController extends BaseController<Group> {


    protected GroupController(ResourceManager<Group> manager) {
        super(manager);
    }
}
