package ibm.wjx.osserver.pojo;

import java.util.Set;

/**
 * Create Date: 12/20/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class User extends BaseResult {
    private Set<String> identities;
    private Set<String> groups;

    @Override
    public String toString() {
        return "User{" +
                "identities=" + identities +
                ", groups=" + groups +
                ", apiVersion='" + apiVersion + '\'' +
                ", kind='" + kind + '\'' +
                ", metadata=" + metadata +
                '}';
    }

    public Set<String> getIdentities() {
        return identities;
    }

    public void setIdentities(Set<String> identities) {
        this.identities = identities;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }
}
