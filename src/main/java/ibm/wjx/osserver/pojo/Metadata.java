package ibm.wjx.osserver.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * Create Date: 12/20/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata {
    private String creationTimestamp;
    private String name;
    private String namespace;
    private String resourceVersion;
    private String selfLink;
    private String uid;
    private Map<String, String> annotations;
    private Map<String, String> labels;

    @Override
    public String toString() {
        return "Metadata{" +
                "creationTimestamp='" + creationTimestamp + '\'' +
                ", name='" + name + '\'' +
                ", namespace='" + namespace + '\'' +
                ", resourceVersion='" + resourceVersion + '\'' +
                ", selfLink='" + selfLink + '\'' +
                ", uid='" + uid + '\'' +
                ", annotations=" + annotations +
                ", labels=" + labels +
                '}';
    }

    public String getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(String creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Map<String, String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Map<String, String> annotations) {
        this.annotations = annotations;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public class Owner {

    }
}