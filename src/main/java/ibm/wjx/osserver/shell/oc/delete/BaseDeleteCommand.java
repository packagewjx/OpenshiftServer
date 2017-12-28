package ibm.wjx.osserver.shell.oc.delete;

import ibm.wjx.osserver.shell.oc.BaseOcCommand;
import ibm.wjx.osserver.shell.resultparser.ResultParser;

import java.util.List;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class BaseDeleteCommand<T> extends BaseOcCommand<T> {


    protected BaseDeleteCommand(ResultParser<T> resultParser) {
        super(resultParser);
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("delete");
        return cmdArray;
    }
}
