package ibm.wjx.osserver.shell.resultparser;

/**
 * Create Date: 12/20/17
 * Author: <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: A Simple Class that do nothing, just return rawResult for simple command.
 */
public class RawStringParser implements ResultParser<String> {
    @Override
    public String parse(String rawResult) {
        return rawResult;
    }
}
