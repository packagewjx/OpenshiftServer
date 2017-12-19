package ibm.wjx.osserver.shell;


import ibm.wjx.osserver.shell.resultparser.ResultParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class BaseShellCommand<DataType> {
    public static final int RETURN_CODE_IO_EXCEPTION = -1000;
    public static final int RETURN_CODE_INTERRUPTED_EXCEPTION = -1001;
    public static final int EXEC_TIMEOUT = 300;
    public static final int RETURN_CODE_TIMEOUT = -1002;
    private static final int PROCESS_OK = 0;

    private ResultParser<DataType> resultParser;

    public BaseShellCommand(ResultParser<DataType> resultParser) {
        this.resultParser = resultParser;
    }

    protected abstract List<String> getCmdArray();

    protected abstract Set<String> getEnvs();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Shell Command: Envs: ");
        for (String env : getEnvs()) {
            builder.append(env).append(", ");
        }
        builder.append(" Command String: ");
        for (String cmd : getCmdArray()) {
            builder.append(cmd).append(' ');
        }
        return builder.toString();
    }

    /**
     * Execute this command synchronously, waiting for the command to end and return the result.
     * If the command exit successfully, the result object's return code should be 0, the data object contains the
     * parsed object and the rawString should contain the un-parsed result. Should the command failed, rawString will be
     * error output and return code will be the process error return code.
     * Attention: Should not execute a long command using this function, this will run out of StringBuilder buffer space potentially.
     *
     * @return command result object, including return code, raw result string and parsed data object.
     */
    public final ShellCommandResult<DataType> execute() {
        //get parameters from subclass
        List<String> cmdArray = getCmdArray();
        Set<String> envs = getEnvs();
        //check the parameters
        if (cmdArray == null || cmdArray.size() == 0) {
            //TODO log err output
            return null;
        }
        if (envs == null) {
            envs = new HashSet<>(2);
        }
        //convert them
        String[] cmdStringArray = new String[cmdArray.size()];
        cmdArray.toArray(cmdStringArray);
        String[] envStringArray = new String[envs.size()];
        envs.toArray(envStringArray);

        ShellCommandResult<DataType> result = new ShellCommandResult<>();
        try {
            //TODO should output process stdout to log
            Process process = Runtime.getRuntime().exec(cmdStringArray, envStringArray);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder okStringBuilder = new StringBuilder();
            StringBuilder errStringBuilder = new StringBuilder();
            String line = null;
            String errLine = null;
            //TODO should output to log
            //read stdout and stderr at the same time
            boolean eof = false;
            while (!eof) {
                eof = true;
                if (null != (line = reader.readLine())) {
                    okStringBuilder.append(line).append('\n');
                    eof = false;
                }
                if (null != (errLine = errReader.readLine())) {
                    errStringBuilder.append(errLine).append('\n');
                    eof = false;
                }
            }
            boolean finished = process.waitFor(EXEC_TIMEOUT, TimeUnit.SECONDS);
            if (finished) {
                if (process.exitValue() == PROCESS_OK) {
                    String rawResult = okStringBuilder.toString();
                    DataType data = resultParser.parse(rawResult);
                    result.setData(data);
                    result.setReturnCode(PROCESS_OK);
                    result.setRawResult(rawResult);
                } else {
                    //TODO should output error log
                    result.setData(null);
                    result.setRawResult(errStringBuilder.toString());
                    result.setReturnCode(process.exitValue());
                }
            } else {
                //TODO should output error log
                result.setRawResult("The Command did not finished in " + EXEC_TIMEOUT + " second");
                result.setReturnCode(RETURN_CODE_TIMEOUT);
                result.setData(null);
            }
            //TODO should output error log
        } catch (IOException e) {
            e.printStackTrace();
            result.setReturnCode(RETURN_CODE_IO_EXCEPTION);
            result.setRawResult(e.getMessage());
            result.setData(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
            result.setReturnCode(RETURN_CODE_INTERRUPTED_EXCEPTION);
            result.setRawResult(e.getMessage());
            result.setData(null);
        }
        return result;
    }
}
