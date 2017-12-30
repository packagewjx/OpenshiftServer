package ibm.wjx.osserver.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.pojo.BaseResult;
import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.oc.ReplaceCommand;
import ibm.wjx.osserver.shell.oc.create.CreateFromJsonCommand;
import ibm.wjx.osserver.shell.oc.create.CreateResourceCommand;
import ibm.wjx.osserver.shell.oc.delete.DeleteResourceCommand;
import ibm.wjx.osserver.shell.oc.get.GetResourceObjectCommand;
import ibm.wjx.osserver.util.ExceptionLogUtil;
import ibm.wjx.osserver.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create Date: 12/29/17
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: A base class implementing Manager interface, providing default implementations for methods.
 */
public abstract class BaseManager<T extends BaseResult> implements Manager<T> {
    private static final Logger logger = LoggerFactory.getLogger(BaseManager.class);
    private String resourceKind;
    private TypeReference<T> typeReference;

    protected BaseManager(String resourceKind, TypeReference<T> typeReference) {
        this.resourceKind = resourceKind;
        this.typeReference = typeReference;
    }

    @Override
    public T add(T object) {
        try {
            CreateFromJsonCommand command = new CreateFromJsonCommand(resourceKind, JsonUtil.convertToJson(object));
            ShellCommandResult<String> result = command.execute();
            if (result.getReturnCode() == BaseShellCommand.PROCESS_OK) {
                logger.info("Successfully created {} {}", resourceKind, object.getMetadata().getName());
                return get(object.getMetadata().getName());
            } else {
                logger.error("Create {} {} failed, reason is {}", resourceKind, object.getMetadata().getName(), result.getRawResult());
                return null;
            }
        } catch (JsonProcessingException e) {
            logger.error("Convert {} into json failed", resourceKind);
            logger.error(ExceptionLogUtil.getStacktrace(e));
            return null;
        }
    }

    @Override
    public T add(String name) {
        CreateResourceCommand command = new CreateResourceCommand(resourceKind, name);
        ShellCommandResult<String> result = command.execute();
        if (result.getReturnCode() == BaseShellCommand.PROCESS_OK) {
            logger.info("Successfully created {} {}", resourceKind, name);
            return get(name);
        } else {
            logger.error("Failed to create {} {}, reason is {}", resourceKind, name, result.getRawResult());
            return null;
        }
    }

    @Override
    public boolean delete(String name) {
        DeleteResourceCommand command = new DeleteResourceCommand(resourceKind, name);
        ShellCommandResult<String> result = command.execute();
        if (result.getReturnCode() == BaseShellCommand.PROCESS_OK) {
            logger.info("Successfully deleted {} {}", resourceKind, name);
            return true;
        } else {
            logger.error("Failed to delete {} {}, reason is {}", resourceKind, name, result.getRawResult());
            return false;
        }
    }

    /**
     * update an object
     *
     * @param object updated object
     * @return if success, return the newest object. If failed, return the old object.
     */
    @Override
    public T update(T object) {
        try {
            ReplaceCommand command = new ReplaceCommand(resourceKind, JsonUtil.convertToJson(object));
            ShellCommandResult<String> result = command.execute();
            if (result.getReturnCode() == BaseShellCommand.PROCESS_OK) {
                logger.info("Successfully updated {} {}", resourceKind, object.getMetadata().getName());
                T newOb = get(object.getMetadata().getName());
                return newOb == null ? object : newOb;
            } else {
                logger.error("Failed to update {} {}, reason is {}", resourceKind, object.getMetadata().getName(), result.getRawResult());
                return object;
            }
        } catch (JsonProcessingException e) {
            logger.error("Convert {} into json failed", resourceKind);
            logger.error(ExceptionLogUtil.getStacktrace(e));
            return object;
        }
    }

    @Override
    public T get(String name) {
        GetResourceObjectCommand<T> getCommand = new GetResourceObjectCommand<>(typeReference, resourceKind, name);
        ShellCommandResult<T> getCommandResult = getCommand.execute();
        if (getCommandResult.getReturnCode() == BaseShellCommand.PROCESS_OK && getCommandResult.getData().isPresent()) {
            logger.info("Got the new {} {}, returning", resourceKind, name);
            return getCommandResult.getData().get();
        } else {
            logger.error("Failed to get {} {}, reason is {}", resourceKind, name, getCommandResult.getRawResult());
            return null;
        }
    }
}
