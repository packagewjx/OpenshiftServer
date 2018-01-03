package ibm.wjx.osserver.shell.oc.adm.policy;

import ibm.wjx.osserver.shell.oc.adm.BaseAdmCommand;
import ibm.wjx.osserver.shell.resultparser.ResultParser;

import java.util.List;

/**
 * Create Date: 1/2/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class BasePolicyCommand<T> extends BaseAdmCommand<T> {
    protected BasePolicyCommand(ResultParser<T> resultParser) {
        super(resultParser);
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("policy");
        return cmdArray;
    }
}
