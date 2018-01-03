package ibm.wjx.osserver.shell.oc.adm.policy;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;

import java.util.List;

/**
 * Create Date: 1/2/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class AddAndRemoveRoleBindingCommand extends BasePolicyCommand<String> {
    public static final int ADD_TO_USER = 1;
    public static final int ADD_TO_GROUP = 2;
    public static final int REMOVE_FROM_USER = 3;
    public static final int REMOVE_FROM_GROUP = 4;

    private int action;
    private String roleName;
    private String name;

    /**
     * construct a command like "oc adm policy add-role-to-[group|user]"
     *
     * @param action   values from ADD_TO_USER, ADD_TO_GROUP, REMOVE_FROM_USER, REMOVE_FROM_GROUP, default ADD_TO_USER
     * @param roleName name of the role want to add
     * @param name     name of the user|group
     */
    public AddAndRemoveRoleBindingCommand(int action, String roleName, String name) {
        super(RawStringParser.getInstance());
        this.action = action;
        this.roleName = roleName;
        this.name = name;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        switch (action) {
            default:
            case ADD_TO_USER:
                cmdArray.add("add-role-to-user");
                break;
            case ADD_TO_GROUP:
                cmdArray.add("add-role-to-group");
                break;
            case REMOVE_FROM_USER:
                cmdArray.add("remove-role-from-user");
                break;
            case REMOVE_FROM_GROUP:
                cmdArray.add("remove-role-from-group");
                break;
        }
        cmdArray.add(roleName);
        cmdArray.add(name);
        return cmdArray;
    }
}
