package ibm.wjx.osserver.shell.oc.get;

import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.pojo.BaseApiResult;
import ibm.wjx.osserver.pojo.Group;
import ibm.wjx.osserver.shell.resultparser.JsonParser;
import ibm.wjx.osserver.shell.resultparser.ResultParser;

import java.util.List;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class GetGroupCommand extends BaseGetCommand<BaseApiResult<Group>> {

    public GetGroupCommand() {
        super(new JsonParser<>(new TypeReference<BaseApiResult<Group>>() {
        }));
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("group");
        return cmdArray;
    }
}
