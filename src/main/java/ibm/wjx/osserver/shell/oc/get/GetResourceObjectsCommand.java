package ibm.wjx.osserver.shell.oc.get;

import ibm.wjx.osserver.pojo.BaseApiResult;
import ibm.wjx.osserver.shell.resultparser.JsonParser;

import java.util.List;

/**
 * Create Date: 12/29/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class GetResourceObjectsCommand<T> extends BaseGetCommand<BaseApiResult<T>> {
    private final String resourceKind;

    public GetResourceObjectsCommand(ApiTypeReference<T> typeReference, String resourceKind) {
        super(new JsonParser<>(typeReference));
        this.resourceKind = resourceKind;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add(resourceKind);
        return cmdArray;
    }
}
