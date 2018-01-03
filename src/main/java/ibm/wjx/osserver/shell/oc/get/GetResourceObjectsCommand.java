package ibm.wjx.osserver.shell.oc.get;

import ibm.wjx.osserver.pojo.ListResult;
import ibm.wjx.osserver.shell.resultparser.JsonParser;

import java.util.Collection;
import java.util.List;

/**
 * Create Date: 12/29/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class GetResourceObjectsCommand<T> extends BaseGetCommand<ListResult<T>> {
    private final String resourceKind;
    private Collection<String> names;

    public GetResourceObjectsCommand(ApiTypeReference<T> typeReference, String resourceKind) {
        super(new JsonParser<>(typeReference));
        this.resourceKind = resourceKind;
    }

    public GetResourceObjectsCommand(ApiTypeReference<T> typeReference, String resourceKind, Collection<String> names) {
        super(new JsonParser<>(typeReference));
        this.resourceKind = resourceKind;
        this.names = names;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add(resourceKind);
        cmdArray.addAll(names);
        return cmdArray;
    }
}
