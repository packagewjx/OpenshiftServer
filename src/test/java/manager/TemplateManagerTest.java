package manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import ibm.wjx.osserver.constant.ResultCode;
import ibm.wjx.osserver.manager.TemplateManager;
import ibm.wjx.osserver.pojo.Result;
import ibm.wjx.osserver.pojo.Template;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Create Date: 1/15/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class TemplateManagerTest {
    @Test
    public void normalTest() {
        TemplateManager manager = new TemplateManager();
        String templateName = "redis-template";
        //language=JSON
        String templateString = "{\n" +
                "  \"apiVersion\": \"v1\",\n" +
                "  \"kind\": \"Template\",\n" +
                "  \"metadata\": {\n" +
                "    \"name\": \"redis-template\",\n" +
                "    \"annotations\": {\n" +
                "      \"description\": \"Description\",\n" +
                "      \"iconClass\": \"icon-redis\",\n" +
                "      \"tags\": \"database,nosql\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"objects\": [\n" +
                "    {\n" +
                "      \"apiVersion\": \"v1\",\n" +
                "      \"kind\": \"Pod\",\n" +
                "      \"metadata\": {\n" +
                "        \"name\": \"redis-master\"\n" +
                "      },\n" +
                "      \"spec\": {\n" +
                "        \"containers\": [\n" +
                "          {\n" +
                "            \"env\": [\n" +
                "              {\n" +
                "                \"name\": \"REDIS_PASSWORD\",\n" +
                "                \"value\": \"${REDIS_PASSWORD}\"\n" +
                "              }\n" +
                "            ],\n" +
                "            \"image\": \"dockerfile/redis\",\n" +
                "            \"name\": \"master\",\n" +
                "            \"ports\": [\n" +
                "              {\n" +
                "                \"containerPort\": 6379,\n" +
                "                \"protocol\": \"TCP\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"parameters\": [\n" +
                "    {\n" +
                "      \"description\": \"Password used for Redis authentication\",\n" +
                "      \"from\": \"[A-Z0-9]{8}\",\n" +
                "      \"generate\": \"expression\",\n" +
                "      \"name\": \"REDIS_PASSWORD\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"labels\": {\n" +
                "    \"redis\": \"master\"\n" +
                "  }\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        Template template = null;
        try {
            template = mapper.readValue(templateString, Template.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //test add
        Result<Template> addResult = manager.add(template, "openshift");
        assertEquals(ResultCode.SUCCESS, addResult.getResultCode());
        assertNotNull(addResult.getData());
        System.out.println(addResult.getData());
        //test delete
        Result<Boolean> deleteResult = manager.delete(templateName, "openshift");
        assertEquals(ResultCode.SUCCESS, deleteResult.getResultCode());
    }
}
