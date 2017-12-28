package ibm.wjx.osserver.shell.oc.create;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;

import java.util.List;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class CreateUserCommand extends BaseCreateCommand<String> {
    private String username;


    public CreateUserCommand(String username) {
        super(RawStringParser.getInstance());
        this.username = username;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("user");
        cmdArray.add(username);
        return cmdArray;
    }
}
