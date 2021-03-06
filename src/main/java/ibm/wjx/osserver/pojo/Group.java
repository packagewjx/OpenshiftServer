package ibm.wjx.osserver.pojo;

import ibm.wjx.osserver.constant.Kind;

import java.util.Set;

/**
 * Create Date: 12/27/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class Group extends BasePojo {
    private Set<String> users;

    public Group() {
        super(Kind.GROUP);
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Group{" +
                "users=" + users +
                ", apiVersion='" + apiVersion + '\'' +
                ", kind='" + kind + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}
