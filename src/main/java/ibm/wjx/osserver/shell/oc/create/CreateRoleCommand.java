package ibm.wjx.osserver.shell.oc.create;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create Date: 1/3/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class CreateRoleCommand extends BaseCreateCommand<String> {
    protected CreateRoleCommand() {
        super(RawStringParser.getInstance());
    }

    private String roleName;
    private Set<String> verbs;
    private Set<String> resources;
    private Set<String> resourceNames;

    public CreateRoleCommand(String roleName, Set<String> verbs, Set<String> resources) {
        this(roleName, verbs, resources, new HashSet<>());
    }

    public CreateRoleCommand(String roleName, Set<String> verbs, Set<String> resources, Set<String> resourceNames) {
        super(RawStringParser.getInstance());
        this.roleName = roleName;
        this.verbs = verbs;
        this.resources = resources;
        this.resourceNames = resourceNames;
    }

    public boolean addVerb(String verb) {
        return verbs.add(verb);
    }

    public boolean addResources(String resource) {
        return resources.add(resource);
    }

    public boolean addResourceNames(String resourceName) {
        return resourceNames.add(resourceName);
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<String> getVerbs() {
        return verbs;
    }

    public void setVerbs(Set<String> verbs) {
        this.verbs = verbs;
    }

    public Set<String> getResources() {
        return resources;
    }

    public void setResources(Set<String> resources) {
        this.resources = resources;
    }

    public Set<String> getResourceNames() {
        return resourceNames;
    }

    public void setResourceNames(Set<String> resourceNames) {
        this.resourceNames = resourceNames;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("role");
        cmdArray.add(roleName);
        cmdArray.add("--verb=" + toCommaSplitString(verbs));
        cmdArray.add("--resource=" + toCommaSplitString(resources));
        if (resourceNames.size() > 0) {
            cmdArray.add("--resourceName=" + toCommaSplitString(resourceNames));
        }
        return cmdArray;
    }

    private String toCommaSplitString(Set<String> strings) {
        StringBuilder builder = new StringBuilder(30);
        for (String resource : strings) {
            builder.append(resource);
            builder.append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
