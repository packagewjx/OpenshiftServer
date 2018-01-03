package ibm.wjx.osserver.shell.resultparser;


/**
 * Create Date: 1/3/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: a simple class using regular expression to see if the result is success.
 */
public class RegexpTrueParser implements ResultParser<Boolean> {
    private String regexp;

    public RegexpTrueParser(String regexp) {
        this.regexp = regexp;
    }

    /**
     * parse the result compare it against the regular expression.
     * @param rawResult stdout output, in string
     * @return if the rawResult matches that regular expression, then return true, otherwise false.
     */
    @Override
    public Boolean parse(String rawResult) {
        return rawResult.matches(regexp);
    }
}