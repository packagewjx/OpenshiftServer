package ibm.wjx.osserver.shell.oc.create;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;

import java.util.List;

/**
 * Create Date: 12/28/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: Create from file, like "oc create -f <filename>"
 */
public class CreateFileCommand extends BaseCreateCommand<String> {

    private String path;

    protected CreateFileCommand(String path) {
        super(RawStringParser.getInstance());
        this.path = path;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("-f");
        cmdArray.add(path);
        return cmdArray;
    }
}
