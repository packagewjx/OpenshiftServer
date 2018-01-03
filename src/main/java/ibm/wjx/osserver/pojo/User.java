package ibm.wjx.osserver.pojo;

import ibm.wjx.osserver.constant.Kind;

import java.util.Set;

/**
 * Create Date: 12/20/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class User extends BasePojo {
    private Set<String> identities;
    private Set<String> groups;

    public User() {
        super(Kind.USER);
    }

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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
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
