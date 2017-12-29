package ibm.wjx.osserver.shell.oc.get;

import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.shell.resultparser.JsonParser;

import java.util.List;

/**
 * Create Date: 12/29/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class GetResourceObjectCommand<T> extends BaseGetCommand<T>{

    /**
     * Generic class to get a resource object by its type T, parsed by Jackson.
     * @param typeReference its type
     * @param resourceKind the kind of this object, stored in CmdKind
     * @param name object name.
     */
    public GetResourceObjectCommand(TypeReference<T> typeReference, String resourceKind, String name) {
        super(new JsonParser<>(typeReference));
        this.resourceKind = resourceKind;
        this.resourceName = name;
    }

    private String resourceKind;
    private String resourceName;

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add(resourceKind);
        cmdArray.add(resourceName);
        return cmdArray;
    }
}
