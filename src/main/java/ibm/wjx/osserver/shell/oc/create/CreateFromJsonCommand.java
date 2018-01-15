package ibm.wjx.osserver.shell.oc.create;

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
 * Description: Wrapper class to create a resource from json
 */
public class CreateFromJsonCommand extends BaseCreateCommand<String> {
    private String resourceKind;
    private String json;

    public CreateFromJsonCommand(String resourceKind, String json) {
        super(RawStringParser.getInstance());
        this.resourceKind = resourceKind;
        this.json = json;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        //create -f don't have resourcekind
//        cmdArray.add(resourceKind);
        cmdArray.add("-f");
        cmdArray.add("createCmdFile.json");
        return cmdArray;
    }

    @Override
    protected void beforeExecute() {
        try {
            FileWriter writer = new FileWriter("createCmdFile.json");
            writer.write(json);
            writer.close();
            logger.debug("createCmdFile.json file created");
        } catch (IOException e) {
            logger.error("Error Occurred when creating createCmdFile.json");
            logger.error(ExceptionLogUtil.getStacktrace(e));
        }
    }

    @Override
    protected void afterExecute() {
        File file = new File("createCmdFile.json");
        boolean deleted = file.delete();
        if (deleted) {
            logger.debug("createCmdFile.json deleted");
        } else {
            logger.debug("createCmdFile.json failed to delete");
        }
    }

    @Override
    protected void afterError() {
        afterExecute();
    }
}
