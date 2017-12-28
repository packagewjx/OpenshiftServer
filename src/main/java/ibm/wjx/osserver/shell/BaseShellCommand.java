package ibm.wjx.osserver.shell;


import ibm.wjx.osserver.shell.resultparser.ResultParser;
import ibm.wjx.osserver.util.ExceptionLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Abstract Base Class for a shell command, use template method to execute a command.
 * @param <DataType> The Return Object Type.
 */
public abstract class BaseShellCommand<DataType> {
    public static final int RETURN_CODE_IO_EXCEPTION = -1000;
    public static final int RETURN_CODE_INTERRUPTED_EXCEPTION = -1001;
    public static final int RETURN_CODE_NO_CMD = -1002;
    public static final int EXEC_TIMEOUT = 300;
    public static final int RETURN_CODE_TIMEOUT = -1002;
    public static final int PROCESS_OK = 0;
    /**
     * Subclass Should Replace this Logger to its own logger in order to have a more meaningful log.
     */
    protected static Logger logger = LoggerFactory.getLogger(BaseShellCommand.class);


    private ResultParser<DataType> resultParser;

    public BaseShellCommand(ResultParser<DataType> resultParser) {
        this.resultParser = resultParser;
    }

    protected abstract List<String> getCmdArray();

    protected abstract Set<String> getEnvs();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ShellCommand {");
        Set<String> envs = getEnvs();
        if (envs != null && envs.size() != 0) {
            builder.append("Envs: ");
            for (String env : getEnvs()) {
                builder.append(env).append(", ");
            }
            builder.delete(builder.length() - 2, builder.length());
            builder.append("; ");
        }
        List<String> cmdArray = getCmdArray();
        if (cmdArray != null && cmdArray.size() != 0) {
            builder.append("Command String: \"");
            for (String cmd : getCmdArray()) {
                builder.append(cmd).append(' ');
            }
            builder.deleteCharAt(builder.length() - 1).append('\"');
        } else {
            logger.warn("No Command Input");
        }
        builder.append('}');
        return builder.toString();
    }

    /**
     * Execute this command synchronously, waiting for the command to end and return the result.
     * If the command exit successfully, the result object's return code should be 0, the data object contains the
     * parsed object and the rawString should contain the un-parsed result. Should the command failed, rawString will be
     * error output and return code will be the process error return code.
     * Attention:
     * <ul>
     *     <li>Should not execute a long command using this function, this will run out of StringBuilder buffer space potentially.</li>
     *     <li>Should not execute a command that requires stdin, or user input, just input command and output result.</li>
     * </ul>
     *
     *
     * @return command result object, including return code, raw result string and parsed data object.
     */
    public final ShellCommandResult<DataType> execute() {
        //get parameters from subclass
        List<String> cmdArray = getCmdArray();
        Set<String> envs = getEnvs();

        ShellCommandResult<DataType> result = new ShellCommandResult<>();
        //check the parameters
        if (cmdArray == null || cmdArray.size() == 0) {
            logger.error("No Command Input, Exiting");
            result.setReturnCode(RETURN_CODE_NO_CMD);
            result.setRawResult("No Command Input");
            return result;
        }
        if (envs == null) {
            logger.info("No Environment Variables Input");
            envs = new HashSet<>(2);
        }
        //convert them
        String[] cmdStringArray = new String[cmdArray.size()];
        cmdArray.toArray(cmdStringArray);
        String[] envStringArray = new String[envs.size()];
        envs.toArray(envStringArray);

        try {
            logger.info("Executing {}", toString());
            Process process = Runtime.getRuntime().exec(cmdStringArray, envStringArray);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder okStringBuilder = new StringBuilder();
            StringBuilder errStringBuilder = new StringBuilder();
            String line = null;
            String errLine = null;
            //read stdout and stderr at the same time
            boolean eof = false;
            while (!eof) {
                eof = true;
                if (null != (line = reader.readLine())) {
                    logger.debug("stdout: {}", line);
                    okStringBuilder.append(line).append('\n');
                    eof = false;
                }
                if (null != (errLine = errReader.readLine())) {
                    logger.debug("stderr: {}", errLine);
                    errStringBuilder.append(errLine).append('\n');
                    eof = false;
                }
            }
            boolean finished = process.waitFor(EXEC_TIMEOUT, TimeUnit.SECONDS);
            if (finished) {
                if (process.exitValue() == PROCESS_OK) {
                    logger.info("Command Executed Successfully");
                    //remove the last line separator
                    okStringBuilder.deleteCharAt(okStringBuilder.length() - 1);
                    String rawResult = okStringBuilder.toString();
                    result.setReturnCode(PROCESS_OK);
                    result.setRawResult(rawResult);
                    DataType data = resultParser.parse(rawResult);
                    result.setData(data);
                } else {
                    logger.error("Command exited with code {}. Error Message is: {}", process.exitValue(), errStringBuilder.toString());
                    result.setData(null);
                    //remove the last line separator
                    errStringBuilder.deleteCharAt(errStringBuilder.length() - 1);
                    result.setRawResult(errStringBuilder.toString());
                    result.setReturnCode(process.exitValue());
                }
            } else {
                logger.error("The Command did not finished in {} second, exit waiting.", EXEC_TIMEOUT);
                result.setRawResult("The Command did not finished in " + EXEC_TIMEOUT + " second");
                result.setReturnCode(RETURN_CODE_TIMEOUT);
                result.setData(null);
            }
        } catch (IOException e) {
            String trace = ExceptionLogUtil.getStacktrace(e);
            logger.error("Caught IO Exception:\n {}", trace);
            result.setReturnCode(RETURN_CODE_IO_EXCEPTION);
            result.setRawResult(e.getMessage());
            result.setData(null);
        } catch (InterruptedException e) {
            String trace = ExceptionLogUtil.getStacktrace(e);
            logger.error("Caught InterruptedException:\n {}", trace);
            result.setReturnCode(RETURN_CODE_INTERRUPTED_EXCEPTION);
            result.setRawResult(e.getMessage());
            result.setData(null);
        }
        return result;
    }
}
