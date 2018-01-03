package ibm.wjx.osserver.pojo;

import java.util.Set;

/**
 * Create Date: 1/2/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class RoleRule {
    private Set<String> apiGroups;
    private Set<String> attributeRestrictions;
    private Set<String> resources;
    private Set<String> verbs;

    public Set<String> getApiGroups() {
        return apiGroups;
    }

    public void setApiGroups(Set<String> apiGroups) {
        this.apiGroups = apiGroups;
    }

    public Set<String> getAttributeRestrictions() {
        return attributeRestrictions;
    }

    public void setAttributeRestrictions(Set<String> attributeRestrictions) {
        this.attributeRestrictions = attributeRestrictions;
    }

    public Set<String> getResources() {
        return resources;
    }

    public void setResources(Set<String> resources) {
        this.resources = resources;
    }

    public Set<String> getVerbs() {
        return verbs;
    }

    public void setVerbs(Set<String> verbs) {
        this.verbs = verbs;
    }

    @Override
    public int hashCode() {
        return apiGroups.hashCode() + attributeRestrictions.hashCode() + resources.hashCode() + verbs.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RoleRule) {
            RoleRule rule = (RoleRule) obj;
            return apiGroups.equals(rule.apiGroups) && attributeRestrictions.equals(rule.apiGroups)
                    && resources.equals(rule.apiGroups) && verbs.equals(rule.verbs);
        }
        return false;
    }
}
