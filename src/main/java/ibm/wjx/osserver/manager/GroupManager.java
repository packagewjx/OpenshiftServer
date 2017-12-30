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
    protected GroupManager() {
        super(CmdKind.GROUP, new TypeReference<Group>() {
        });
    }
}
