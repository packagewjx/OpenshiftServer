package ibm.wjx.osserver.shell.oc.adm.policy;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;

import java.util.List;

/**
 * Create Date: 1/2/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class RemoveUserGroupCommand extends BasePolicyCommand<String> {
    public static final int GROUP = 1;
    public static final int USER = 2;

    private int remove;
    private String name;

    /**
     * build a command like "oc adm policy remove-[user|group]"
     * @param removes value from GROUP and USER
     * @param name name of the group|user
     */
    public RemoveUserGroupCommand(int removes, String name) {
        super(RawStringParser.getInstance());
        this.remove = removes;
        this.name = name;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add(remove == GROUP ? "remove-group" : "remove-user");
        cmdArray.add(name);
        return cmdArray;
    }
}
