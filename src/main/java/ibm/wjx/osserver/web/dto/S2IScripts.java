package ibm.wjx.osserver.web.dto;

/**
 * Create Date: 1/11/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class S2IScripts {
    /**
     * Required script. Define how to build the sources upon the builder image.
     */
    private String assembleScript;
    /**
     * Required script. When the sources were built, this script is used to run the binary program.
     */
    private String runScript;
    /**
     * The image that is used to build the sources we developed, defined by Dockerfile which is created by "s2i create" command.
     */
    private String builderImageName;
    /**
     * The image this Source-To-Image process will build.
     */
    private String imageName;
    /**
     * Optional script. Defines how the builder image are build.
     */
    private String dockerFileScript;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDockerFileScript() {
        return dockerFileScript;
    }

    public void setDockerFileScript(String dockerFileScript) {
        this.dockerFileScript = dockerFileScript;
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

    public String getUsageScript() {
        return usageScript;
    }

    public void setUsageScript(String usageScript) {
        this.usageScript = usageScript;
    }
}
