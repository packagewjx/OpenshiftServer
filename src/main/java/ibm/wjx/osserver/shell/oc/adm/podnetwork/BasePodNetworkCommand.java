package ibm.wjx.osserver.shell.oc.adm.podnetwork;

import ibm.wjx.osserver.shell.oc.adm.BaseAdmCommand;
import ibm.wjx.osserver.shell.resultparser.ResultParser;

import java.util.List;

/**
 * Create Date: 1/3/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class BasePodNetworkCommand<T> extends BaseAdmCommand<T>{
    protected BasePodNetworkCommand(ResultParser<T> resultParser) {
        super(resultParser);
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("pod-network");
        return cmdArray;
    }
}
