package ibm.wjx.osserver.constant;

/**
 * Create Date: 1/15/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: Store default message for results.
 */
public class ResultMessage {
    public static final String SUCCESS = "success";
    public static final String UNAUTHORIZED = "Current user is not authorized to do this";
    public static final String UNAUTHENTICATED = "You are not authenticated as a user in this system";
    public static final String WRONG_ARGUMENT = "Wrong argument";
    public static final String CONVERT_TO_JSON_ERROR = "Error while converting object to json";
    public static final String CONVERT_FROM_JSON_ERROR = "Error while converting json to object";
    public static final String BAD_CREDENTIAL = "Your credential was wrong";
    public static final String BAD_TOKEN = "Your token was invalid or expired";
    public static final String OTHER = "Unknown reason";
}
