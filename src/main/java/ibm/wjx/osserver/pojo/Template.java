package ibm.wjx.osserver.pojo;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import ibm.wjx.osserver.constant.Kind;

/**
 * Create Date: 1/11/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: Template object. Due to its complexity and flexibility, just store its raw json definition. I don't want to deserilize so
 * much, so I use JsonRawValue to write some fields as json, and also deserilize them as raw json string.
 */
public class Template extends BasePojo {
    private String message;
    @JsonRawValue
    private String objects;
    @JsonRawValue
    private String parameters;
    @JsonRawValue
    private String labels;

    protected Template() {
        super(Kind.TEMPLATE);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getObjects() {
        return objects;
    }

    public void setObjects(String objects) {
        this.objects = objects;
    }

    @JsonSetter("objects")
    public void setObjects(JsonNode data) {
        this.objects = data.toString();
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    @JsonSetter("parameters")
    public void setParameters(JsonNode data) {
        this.parameters = data.toString();
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    @JsonSetter("labels")
    public void setLabels(JsonNode data) {
        this.labels = data.toString();
    }
}
