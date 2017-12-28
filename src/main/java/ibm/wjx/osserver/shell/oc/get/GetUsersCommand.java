package ibm.wjx.osserver.shell.oc.get;

import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.pojo.BaseApiResult;
import ibm.wjx.osserver.pojo.User;
import ibm.wjx.osserver.shell.resultparser.JsonParser;

import java.util.List;

/**
 * Create Date: 12/27/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class GetUsersCommand extends BaseGetCommand<BaseApiResult<User>> {

    public GetUsersCommand() {
        super(new JsonParser<>(new TypeReference<BaseApiResult<User>>() {
        }));
    }

    /**
     * Build the command string like "oc get -o json user [username]"
     *
     * @return command string.
     */
    @Override
    protected List<String> getCmdArray() {
        List<String> cmds = super.getCmdArray();
        cmds.add("user");
        return cmds;
    }
}
