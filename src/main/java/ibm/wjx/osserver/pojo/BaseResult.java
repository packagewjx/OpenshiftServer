package ibm.wjx.osserver.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Create Date: 12/20/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: All Openshift Object Contain these properties, using get command
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseResult {
    private String apiVersion;
    private String kind;
    private Metadata metadata;

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "apiVersion='" + apiVersion + '\'' +
                ", kind='" + kind + '\'' +
                ", metadata=" + metadata +
                '}';
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}