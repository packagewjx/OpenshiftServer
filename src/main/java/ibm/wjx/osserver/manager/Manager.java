package ibm.wjx.osserver.manager;

import ibm.wjx.osserver.pojo.BasePojo;

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
    T add(T object);


    /**
     * create it by its name, you can update it afterwards.
     *
     * @param name object name, which is unique in the system.
     * @return new object created
     */
    T add(String name);

    /**
     * delete an object by its name
     *
     * @param name the object's name
     * @return true indicates success, otherwise false.
     */
    boolean delete(String name);

    /**
     * Update the object, return the new object.
     *
     * @param object updated object
     * @return object updated and stored in the system.
     */
    T update(T object);

    /**
     * get one object
     *
     * @param name objects name
     * @return the desired object
     */
    T get(String name);
}
