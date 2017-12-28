package ibm.wjx.osserver.shell.oc.create;

import ibm.wjx.osserver.shell.oc.BaseOcCommand;
import ibm.wjx.osserver.shell.resultparser.ResultParser;

import java.util.List;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class BaseCreateCommand<T> extends BaseOcCommand<T>{
    protected BaseCreateCommand(ResultParser<T> resultParser) {
        super(resultParser);
    }

    /**
     * Construct a string like "oc create"
     * @return cmd string array
     */
    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("create");
        return cmdArray;
    }
}
