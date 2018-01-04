package ibm.wjx.osserver.shell.s2i;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;

import java.util.List;

/**
 * Create Date: 1/3/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class S2IBuildCommand extends BaseS2ICommand<String> {
    private String sourceDir;
    private String buildImageName;
    private String imageName;

    /**
     * @param sourceDir      the directory where sources are placed, including folder s2i/bin and scripts.
     * @param buildImageName the builder image that is going to use to build the sources
     * @param imageName      the image name we are building
     */
    public S2IBuildCommand(String sourceDir, String buildImageName, String imageName) {
        super(RawStringParser.getInstance());
        this.sourceDir = sourceDir;
        this.buildImageName = buildImageName;
        this.imageName = imageName;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("build");
        cmdArray.add(sourceDir);
        cmdArray.add(buildImageName);
        cmdArray.add(imageName);
        return cmdArray;
    }
}
