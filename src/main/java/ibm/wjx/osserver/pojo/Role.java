package ibm.wjx.osserver.pojo;

import ibm.wjx.osserver.constant.Kind;

import java.util.Set;

/**
 * Create Date: 12/20/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class Role extends BasePojo {
    private Set<RoleRule> rules;


    protected Role() {
        super(Kind.ROLE);
    }
}
