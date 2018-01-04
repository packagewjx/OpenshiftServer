package ibm.wjx.osserver.shell.s2i;

import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.resultparser.ResultParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create Date: 1/3/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class BaseS2ICommand<T> extends BaseShellCommand<T> {
    protected BaseS2ICommand(ResultParser<T> resultParser) {
        super(resultParser);
    }

    @Override
    protected List<String> getCmdArray() {
        ArrayList<String> cmdArray = new ArrayList<>();
        cmdArray.add("s2i");
        return cmdArray;
    }

    @Override
    protected Set<String> getEnvs() {
        return new HashSet<>();
    }
}
