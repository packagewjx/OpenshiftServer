package ibm.wjx.osserver.shell;



import jdk.internal.jline.internal.Nullable;

import java.util.Optional;

public class ShellCommandResult<DataType> {
    private int returnCode;
    private String rawResult;
    @Nullable private DataType data;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getRawResult() {
        return rawResult;
    }

    public void setRawResult(String rawResult) {
        this.rawResult = rawResult;
    }

    /**
     * data might be null due to parse error or exception, so return optional object. Required jdk 1.8 or later.
     * @return Optional object contain nullable data.
     */
    public Optional<DataType> getData() {
        return Optional.ofNullable(data);
    }

    public void setData(@Nullable DataType data) {
        this.data = data;
    }
}