package ibm.wjx.osserver.shell;




import java.util.Optional;

/**
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 */
public class ShellCommandResult<DataType> {
    private int returnCode;
    private String rawResult;
    private DataType data;

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

    public void setData(DataType data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ShellCommandResult{" +
                "returnCode=" + returnCode +
                ", rawResult='" + rawResult + '\'' +
                ", data=" + data +
                '}';
    }
}