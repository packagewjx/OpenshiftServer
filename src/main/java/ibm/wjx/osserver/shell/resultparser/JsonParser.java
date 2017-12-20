package ibm.wjx.osserver.shell.resultparser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ibm.wjx.osserver.util.ExceptionLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Create Date: 12/19/17
 * Author: <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: ${Description}
 */
public class JsonParser<T> implements ResultParser<T> {
    private static final Logger logger = LoggerFactory.getLogger(JsonParser.class);
    private TypeReference<T> typeReference;

    public JsonParser(TypeReference<T> typeReference) {
        this.typeReference = typeReference;
    }

    @Override
    public T parse(String rawResult) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(rawResult, typeReference);
        } catch (IOException e) {
            logger.error("Exception caught while parsing type {} json string:\n {}", typeReference.getType().getTypeName(), ExceptionLogUtil.getStacktrace(e));
            return null;
        }
    }

}
