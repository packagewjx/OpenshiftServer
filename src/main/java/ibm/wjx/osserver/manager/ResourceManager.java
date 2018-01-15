package ibm.wjx.osserver.manager;

import ibm.wjx.osserver.pojo.BasePojo;
import ibm.wjx.osserver.pojo.Result;

import java.util.List;

/**
 * Create Date: 1/4/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: Extending Manager interface with methods manupulating resources in a project.
 */
public interface ResourceManager<T extends BasePojo> extends Manager<T> {
    /**
     * Create the object from a data object, use "oc create -f <file> -n" command to do this.
     *
     * @param object  new object
     * @param project the target project to add to
     * @return return new object created
     */
    Result<T> add(T object, String project);


    /**
     * create it by its name, you can update it afterwards.
     *
     * @param name    object name, which is unique in the system.
     * @param project the target project to add to
     * @return new object created if success. If failed, return null.
     */
    Result<T> add(String name, String project);

    /**
     * delete an object by its name
     *
     * @param name    the object's name
     * @param project the target project to add to
     * @return true indicates success, otherwise false.
     */
    Result<Boolean> delete(String name, String project);

    /**
     * Update the object, return the new object.
     *
     * @param object  updated object
     * @param project the target project to add to
     * @return object updated and stored in the system. if fail, return old object.
     */
    Result<T> update(T object, String project);

    /**
     * get one object
     *
     * @param name    objects name
     * @param project the target project to add to
     * @return the desired object
     */
    Result<T> get(String name, String project);

    /**
     * get all resource object in a project.
     *
     * @param project project name
     * @return all resource object in a project.
     */
    Result<List<T>> getAllInProject(String project);

    /**
     * get all resource object in all namespaces.
     *
     * @return all resource object in all namespaces.
     */
    Result<List<T>> getAllInAllProjects();
}
