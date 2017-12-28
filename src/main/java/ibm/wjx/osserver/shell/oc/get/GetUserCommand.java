package ibm.wjx.osserver.shell.oc.get;

import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.pojo.User;
import ibm.wjx.osserver.shell.resultparser.JsonParser;

import java.util.List;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class GetUserCommand extends BaseGetCommand<User> {
    private String user;

    public GetUserCommand(String user) {
        super(new JsonParser<>(new TypeReference<User>() {
        }));
        this.user = user;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("user");
        cmdArray.add(user);
        return cmdArray;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
