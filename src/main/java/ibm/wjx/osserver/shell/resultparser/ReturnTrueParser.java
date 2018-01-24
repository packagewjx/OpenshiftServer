package ibm.wjx.osserver.shell.resultparser;

/**
 * Create Date: 1/24/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: In some cases, return code 0 indicates a success, no need to parse the string.
 */
public class ReturnTrueParser implements ResultParser<Boolean> {
    private static ReturnTrueParser instance = new ReturnTrueParser();

    private ReturnTrueParser() {

    }

    public static ReturnTrueParser getInstance() {
        return instance;
    }

    @Override
    public Boolean parse(String rawResult) {
        return true;
    }
}
