package ibm.wjx.osserver.pojo;

import ibm.wjx.osserver.constant.Kind;

import java.util.List;

/**
 * Create Date: 12/20/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: get command api returned result contains.
 */
public class BaseApiResult<T> extends BaseResult {
    private String resourceVersion;
    private String selfLink;
    private List<T> items;

    public BaseApiResult() {
        this.setKind(Kind.LIST);
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
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

    @Override
    public String toString() {
        return "BaseApiResult{" +
                "resourceVersion='" + resourceVersion + '\'' +
                ", selfLink='" + selfLink + '\'' +
                ", items=" + items +
                '}';
    }
}
