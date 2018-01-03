package ibm.wjx.osserver.shell.oc.get;

import ibm.wjx.osserver.shell.oc.BaseOcCommand;
import ibm.wjx.osserver.shell.resultparser.ResultParser;

import java.util.List;

/**
 * Create Date: 12/20/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: oc get command base class
 */
public abstract class BaseGetCommand<T> extends BaseOcCommand<T> {
    protected BaseGetCommand(ResultParser<T> resultParser) {
        super(resultParser);
    }

    /**
     * Build A Command String Like "oc get -o json"
     * @return List of string containing oc, get, -o, json
     */
    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("get");
        //make output format to json
        cmdArray.add("-o");
        cmdArray.add("json");
        return cmdArray;
    }
}
