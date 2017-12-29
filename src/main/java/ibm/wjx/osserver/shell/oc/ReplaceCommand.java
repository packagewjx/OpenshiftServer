package ibm.wjx.osserver.shell.oc;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;
import ibm.wjx.osserver.util.ExceptionLogUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Create Date: 12/29/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class ReplaceCommand extends BaseOcCommand<String> {
    private String resourceKind;
    private String resourceObject;

    public ReplaceCommand(String resourceKind, String resourceObject) {
        super(RawStringParser.getInstance());
        this.resourceKind = resourceKind;
        this.resourceObject = resourceObject;
    }

    @Override
    protected void beforeExecute() {
        try {
            FileWriter writer = new FileWriter("replaceCmdFile.json");
            writer.write(resourceObject);
            writer.close();
            logger.debug("Created replaceCmdFile.json used to execute replace command");
        } catch (IOException e) {
            logger.error("Cannot write object json file, io exception occurred.");
            logger.error(ExceptionLogUtil.getStacktrace(e));
        }

    }

    @Override
    protected void afterError() {
        afterExecute();
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("replace");
        cmdArray.add(resourceKind);
        cmdArray.add("-f");
        cmdArray.add("replaceCmdFile.json");
        return cmdArray;
    }

    @Override
    protected void afterExecute() {
        File file = new File("replaceCmdFile.json");
        boolean deleted = file.delete();
        if (deleted) {
            logger.debug("Deleted file replaceCmdFile.json");
        } else {
            logger.error("Failed to delete file replaceCmdFile.json");
        }
    }
}
