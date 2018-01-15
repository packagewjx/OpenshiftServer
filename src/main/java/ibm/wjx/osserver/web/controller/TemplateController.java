package ibm.wjx.osserver.web.controller;

import ibm.wjx.osserver.manager.ResourceManager;
import ibm.wjx.osserver.pojo.Template;

/**
 * Create Date: 1/11/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class TemplateController extends BaseController<Template> {
    protected TemplateController(ResourceManager<Template> manager) {
        super(manager);
    }
}