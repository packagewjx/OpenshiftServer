package ibm.wjx.osserver.operation.s2i;

import ibm.wjx.osserver.operation.BaseOperation;
import ibm.wjx.osserver.operation.CommandCompleteHandler;
import ibm.wjx.osserver.operation.OperationResult;
import ibm.wjx.osserver.pojo.Result;
import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.StringCommand;
import ibm.wjx.osserver.shell.s2i.S2IBuildCommand;
import ibm.wjx.osserver.shell.s2i.S2ICreateCommand;
import ibm.wjx.osserver.util.ExceptionLogUtil;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Create Date: 1/3/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: This operation first create necessery files using "s2i create" command. Sources-To-Image process requires
 * assemble script, used to build the sources, and run script, used to run the program in the image. The sources is built
 * on the builder image, which is defined by Dockerfile. You can customize Dockerfile using the set method, and you can
 * also set the builder image name, whose default value is DEFAULT_BUILD_IMAGE_NAME. Then it will invoke a "make" command,
 * to construct the builder image. At last, it will perform "s2i build" command to build the image we need.
 * @see S2IOperation#DEFAULT_BUILD_IMAGE_NAME
 */
public class S2IOperation extends BaseOperation<Boolean> {

    private static final String DEFAULT_BUILD_IMAGE_NAME = "base-image";
    private static final int COMMAND_COUNT = 3;
    /**
     * Optional script. Defines how the builder image are build.
     */
    private String dockerFileScript;
    /**
     * Required script. Define how to build the sources upon the builder image.
     */
    private String assembleScript;
    /**
     * When the sources were built, this script is used to run the binary program.
     */
    private String runScript;
    /**
     * The image that is used to build the sources we developed, defined by Dockerfile which is created by "s2i create" command.
     */
    private String builderImageName = DEFAULT_BUILD_IMAGE_NAME;
    /**
     * The image this Source-To-Image process will build.
     */
    private String imageName;
    /**
     * Optional test/run script, to test building the image and run it.
     */
    private String testRunScript;
    /**
     * Optional save-artifact script, to save the last build and then rebuild the sources.
     */
    private String saveArtifactScript;
    /**
     * Optional usage script, to tell user how to use this image
     */
    private String usageScript;


    /**
     * gather the required scripts and set image name
     *
     * @param assembleScript assemble command script
     * @param runScript      run command script
     * @param imageName      the image name we are going to build, use the builder image to build it.
     */
    public S2IOperation(String assembleScript, String runScript, String imageName) {
        this.assembleScript = assembleScript;
        this.runScript = runScript;
        this.imageName = imageName;
    }

    public static String getDefaultBuildImageName() {
        return DEFAULT_BUILD_IMAGE_NAME;
    }

    public String getTestRunScript() {
        return testRunScript;
    }

    public void setTestRunScript(String testRunScript) {
        this.testRunScript = testRunScript;
    }

    public String getSaveArtifactScript() {
        return saveArtifactScript;
    }

    public void setSaveArtifactScript(String saveArtifactScript) {
        this.saveArtifactScript = saveArtifactScript;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    protected Result<Boolean> getResult(OperationResult<Boolean> result) {
        //delete those files created by s2i create
        if (new StringCommand("rm -rf " + imageName).execute().getReturnCode() != StringCommand.PROCESS_OK) {
            logger.error("Error Deleting S2I scripts");
        }
        logger.debug("Deleted S2I scripts");

        if (result.getCommandResults().size() == COMMAND_COUNT) {
            return Result.newSuccessResult(true);
        } else {
            ShellCommandResult lastResult = result.getCommandResults().get(result.getCommandResults().size() - 1);
            return Result.newFailResult(false, lastResult.getReturnCode(), lastResult.getRawResult());
        }
    }

    public String getDockerFileScript() {
        return dockerFileScript;
    }

    public void setDockerFileScript(String dockerFileScript) {
        this.dockerFileScript = dockerFileScript;
    }

    public String getUsageScript() {
        return usageScript;
    }

    public void setUsageScript(String usageScript) {
        this.usageScript = usageScript;
    }

    @Override
    protected void prepare() {
        if (assembleScript == null) {
            //TODO add default script here
            assembleScript = "";
        }
        if (runScript == null) {
            runScript = "";
        }
        //I use imageName to be the name of the directory
        String dirName = this.imageName;
        addCommand(new S2ICreateCommand(builderImageName, dirName), (CommandCompleteHandler<String>) (result, nextCommand) -> {
            //when command ends, write the files.
            if (result.getReturnCode() != BaseShellCommand.PROCESS_OK) {
                logger.error("s2i create command failed, returning");
                return false;
            }
            try {
                logger.debug("Saving scripts to files");
                saveToFile(this.imageName + "/s2i/bin/assemble", assembleScript);
                saveToFile(this.imageName + "/s2i/bin/run", runScript);
                if (dockerFileScript != null && !"".equals(dockerFileScript)) {
                    saveToFile(this.imageName + "/Dockerfile", dockerFileScript);
                }
                if (saveArtifactScript != null && !"".equals(saveArtifactScript)) {
                    saveToFile(this.imageName + "/s2i/bin/save-artifact", saveArtifactScript);
                }
                if (testRunScript != null && !"".equals(testRunScript)) {
                    saveToFile(this.imageName + "/test/run", testRunScript);
                }
                if (usageScript != null && !"".equals(usageScript)) {
                    saveToFile(this.imageName + "/s2i/bin/usage", usageScript);
                }

            } catch (IOException e) {
                logger.error("Exception occurred while writing s2i files");
                logger.error(ExceptionLogUtil.getStacktrace(e));
                return false;
            }
            return true;
        });
        //build the builder image
        addCommand(new StringCommand("make -C " + this.imageName));
        addCommand(new S2IBuildCommand(this.imageName, builderImageName, this.imageName));
    }

    public String getAssembleScript() {
        return assembleScript;
    }

    public void setAssembleScript(String assembleScript) {
        this.assembleScript = assembleScript;
    }

    public String getRunScript() {
        return runScript;
    }

    public void setRunScript(String runScript) {
        this.runScript = runScript;
    }

    public String getBuilderImageName() {
        return builderImageName;
    }

    public void setBuilderImageName(String builderImageName) {
        this.builderImageName = builderImageName;
    }

    private void saveToFile(String fileName, String content) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        }
    }
}
