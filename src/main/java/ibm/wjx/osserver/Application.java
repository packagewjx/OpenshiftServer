package ibm.wjx.osserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Create Date: 1/4/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
@SpringBootApplication(scanBasePackages = {"ibm.wjx.osserver.manager", "ibm.wjx.osserver.controller"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
