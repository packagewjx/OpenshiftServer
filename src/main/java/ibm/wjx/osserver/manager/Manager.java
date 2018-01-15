package ibm.wjx.osserver.manager;

import ibm.wjx.osserver.pojo.BasePojo;
import ibm.wjx.osserver.pojo.Result;

import java.util.List;

/**
 * Create Date: 12/29/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public interface Manager<T extends BasePojo> {
    /**
     * Create the object from a data object, use "oc create -f <file>" command to do this.
     *
     * @param object new object
     * @return return new object created
     */
    Result<T> add(T object);


    /**
     * create it by its name, you can update it afterwards.
     *
     * @param name object name, which is unique in the system.
     * @return new object created if success. If failed, return null.
     */
    Result<T> add(String name);

    /**
     * delete an object by its name
     *
     * @param name the object's name
     * @return true indicates success, otherwise false.
     */
    Result<Boolean> delete(String name);

    /**
     * Update the object, return the new object.
     *
     * @param object updated object
     * @return object updated and stored in the system.
     */
    Result<T> update(T object);

    /**
     * get one object
     *
     * @param name objects name
     * @return the desired object
     */
    Result<T> get(String name);

    /**
     * Get all objects.
     *
     * @return all objects
     */
    Result<List<T>> getAll();
}
