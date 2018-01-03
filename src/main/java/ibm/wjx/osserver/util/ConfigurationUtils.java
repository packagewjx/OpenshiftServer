package ibm.wjx.osserver.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * Create Date: 12/20/17
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: A Utility class to read configurations.
 */
public class ConfigurationUtils {
    private static Logger logger = LoggerFactory.getLogger(ConfigurationUtils.class);
    private static Properties config = new Properties();

    static {
        try {
            config.load(new ClassPathResource("config.properties").getInputStream());
        } catch (IOException e) {
            //This Exception Should Not Happen!
            e.printStackTrace();
        }

        //TODO Load environment variables to override the configurations.
    }

    public static String getConfig(String key) {
        return config.getProperty(key);
    }

    public static final class Keys {
        public static final String OPENSHIFT_SERVER_ADDRESS = "address";
        public static final String KUBECONFIG_FILE = "kubeconfig.file";
    }
}
