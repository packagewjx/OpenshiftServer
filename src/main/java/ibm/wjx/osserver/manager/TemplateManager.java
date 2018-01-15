package ibm.wjx.osserver.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.pojo.Result;
import ibm.wjx.osserver.pojo.Template;
import ibm.wjx.osserver.shell.constant.CmdKind;
import ibm.wjx.osserver.shell.oc.get.ApiTypeReference;

/**
 * Create Date: 1/15/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class TemplateManager extends BaseResourceManager<Template> {
    public TemplateManager() {
        super(CmdKind.TEMPLATE, new TypeReference<Template>() {
        }, new ApiTypeReference<>());
    }

    @Override
    public Result<Template> add(String name, String project) {
        throw new UnsupportedOperationException("Cannot create template using only name");
    }
}
