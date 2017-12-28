package ibm.wjx.osserver.shell.oc.create;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class CreateResourceCommand extends BaseCreateCommand<String>{
    private String resourceKind;
    private String[] names;

    public CreateResourceCommand(String resourceKind, String... name) {
        super(RawStringParser.getInstance());
        this.resourceKind = resourceKind;
        this.names = name;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add(resourceKind);
        cmdArray.addAll(Arrays.stream(names).collect(Collectors.toList()));
        return cmdArray;
    }
}
