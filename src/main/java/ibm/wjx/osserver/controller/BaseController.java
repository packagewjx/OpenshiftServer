package ibm.wjx.osserver.controller;

import ibm.wjx.osserver.manager.ResourceManager;
import ibm.wjx.osserver.pojo.BasePojo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create Date: 1/4/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: Base controller, providing default methods. Subclass must add @RequestMapping to the class and set the
 * path argument.
 */

@RestController
public abstract class BaseController<T extends BasePojo> {
    protected ResourceManager<T> manager;

    protected BaseController(ResourceManager<T> manager) {
        this.manager = manager;
    }

    @RequestMapping(path = "/addByName", method = RequestMethod.PUT)
    public T add(@RequestParam(name = "name") String name, @RequestParam(name = "project", required = false, defaultValue = "") String project) {
        return manager.add(name, project);
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public T add(@RequestBody T object, @RequestParam(name = "project", required = false, defaultValue = "") String project) {
        return manager.add(object, project);
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable(name = "name") String name, @RequestParam(name = "project", required = false, defaultValue = "") String project) {
        return manager.delete(name, project);
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.POST)
    public T update(@RequestBody T object, @PathVariable(name = "name") String name, @RequestParam(name = "project", required = false, defaultValue = "") String project) {
        return manager.update(object, project);
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public T get(@PathVariable(name = "name") String name, @RequestParam(name = "project", required = false, defaultValue = "") String project) {
        return manager.get(name, project);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<T> getAll(@RequestParam(name = "project", required = false, defaultValue = "all") String project) {
        String allNamespace = "all";
        if (allNamespace.equals(project)) {
            return manager.getAllInAllProjects();
        } else {
            return manager.getAllInProject(project);
        }
    }


}
