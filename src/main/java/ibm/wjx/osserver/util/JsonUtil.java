package ibm.wjx.osserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Create Date: 12/29/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String convertToJson(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
