package util;

import ibm.wjx.osserver.util.ConfigurationUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Create Date: 12/20/17
 * Author: <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: ${Description}
 */
public class ConfigurationUtilsTest {
    @Test
    public void testGetConfig() {
        String address = ConfigurationUtils.getConfig(ConfigurationUtils.Keys.OPENSHIFT_SERVER_ADDRESS);
        System.out.println(address);
        Assert.assertTrue(address.matches("(\\d{1,3}\\.){3}\\d{1,3}"));
    }
}
