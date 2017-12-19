package ibm.wjx.osserver.shell.resultparser;

import ibm.wjx.osserver.shell.ShellCommandResult;

/**
 * @author wjx
 */
public interface ResultParser<T> {
    /**
     * Parse a raw stdout result, and convert it into domain object, type T.
     * @param rawResult stdout output, in string
     * @return domain object parsed.
     */
    T parse(String rawResult);
}