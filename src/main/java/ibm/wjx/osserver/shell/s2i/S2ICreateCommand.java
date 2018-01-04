package ibm.wjx.osserver.shell.s2i;

import ibm.wjx.osserver.shell.resultparser.RawStringParser;

import java.util.List;

/**
 * Create Date: 1/3/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class S2ICreateCommand extends BaseS2ICommand<String> {
    private String builderImageName;
    private String destination;

    /**
     * This command creates a directory containing a default Dockerfile, which defines how builder image is build, a s2i
     * directory containing scripts including assemble, run, save-artifacts and usage, a Makefile which use to make the
     * builder image.
     *
     * @param builderImageName the builder image name that is going to be built the sources on, which is what the Dockerfile defines.
     * @param destination      the places where the generated sources will be placed.
     */
    public S2ICreateCommand(String builderImageName, String destination) {
        super(RawStringParser.getInstance());
        this.builderImageName = builderImageName;
        this.destination = destination;
    }

    @Override
    protected List<String> getCmdArray() {
        List<String> cmdArray = super.getCmdArray();
        cmdArray.add("create");
        cmdArray.add(builderImageName);
        cmdArray.add(destination);
        return cmdArray;
    }
}
