package ibm.wjx.osserver.shell.oc.create;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;
import ibm.wjx.osserver.shell.resultparser.ResultParser;

import java.util.List;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class CreateIdentityCommand extends BaseCreateCommand<String> {
    private String identityName;

    public CreateIdentityCommand(String identityName) {
        super(RawStringParser.getInstance());
        this.identityName = identityName;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("identity");
        cmdArray.add(identityName);
        return cmdArray;
    }
}
