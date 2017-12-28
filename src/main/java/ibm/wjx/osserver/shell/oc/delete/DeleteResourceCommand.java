package ibm.wjx.osserver.shell.oc.delete;

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
public class DeleteResourceCommand extends BaseDeleteCommand<String> {
    private String resourceKind;
    private String[] names;

    public DeleteResourceCommand(String resourceKind, String... names) {
        super(RawStringParser.getInstance());
        this.resourceKind = resourceKind;
        this.names = names;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add(resourceKind);
        cmdArray.addAll(Arrays.stream(names).collect(Collectors.toList()));
        return cmdArray;
    }
}
