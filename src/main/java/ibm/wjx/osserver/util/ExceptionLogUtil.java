package ibm.wjx.osserver.util;

import org.slf4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Create Date: 12/19/17
 * Author: <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: ${Description}
 */
public class ExceptionLogUtil {
    public static String getStacktrace(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        e.printStackTrace(writer);
        writer.flush();
        stringWriter.flush();
        try {
            stringWriter.close();
            writer.close();
            return stringWriter.toString();
        } catch (IOException e1) {
            e1.printStackTrace();
            return "";
        }
    }
}
