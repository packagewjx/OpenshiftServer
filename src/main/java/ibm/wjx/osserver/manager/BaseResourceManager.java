package ibm.wjx.osserver.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.pojo.BasePojo;
import ibm.wjx.osserver.pojo.ListResult;
import ibm.wjx.osserver.shell.BaseShellCommand;
import ibm.wjx.osserver.shell.ShellCommandResult;
import ibm.wjx.osserver.shell.oc.ReplaceCommand;
import ibm.wjx.osserver.shell.oc.create.CreateFromJsonCommand;
import ibm.wjx.osserver.shell.oc.create.CreateResourceCommand;
import ibm.wjx.osserver.shell.oc.delete.DeleteResourceCommand;
import ibm.wjx.osserver.shell.oc.get.ApiTypeReference;
import ibm.wjx.osserver.shell.oc.get.GetResourceObjectCommand;
import ibm.wjx.osserver.shell.oc.get.GetResourceObjectsCommand;
import ibm.wjx.osserver.util.ExceptionLogUtil;
import ibm.wjx.osserver.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Create Date: 1/4/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description: Base class implementing ResourceManager interface, providing default implementations.
 */
public abstract class BaseResourceManager<T extends BasePojo> implements ResourceManager<T> {
    protected static Logger logger = LoggerFactory.getLogger(BaseResourceManager.class);
    private String resourceKind;
    private TypeReference<T> typeReference;
    private ApiTypeReference<T> apiTypeReference;

    public BaseResourceManager(String resourceKind, TypeReference<T> typeReference, ApiTypeReference<T> apiTypeReference) {
        this.resourceKind = resourceKind;
        this.typeReference = typeReference;
        this.apiTypeReference = apiTypeReference;
    }

    @Override
    public T add(T object, String project) {
        try {
            CreateFromJsonCommand command = new CreateFromJsonCommand(resourceKind, JsonUtil.convertToJson(object));
            command.setNamespace(project);
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
    public T add(String name, String project) {
        CreateResourceCommand command = new CreateResourceCommand(resourceKind, name);
        command.setNamespace(project);
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
    public boolean delete(String name, String project) {
        DeleteResourceCommand command = new DeleteResourceCommand(resourceKind, name);
        command.setNamespace(project);
        ShellCommandResult<String> result = command.execute();
        if (result.getReturnCode() == BaseShellCommand.PROCESS_OK) {
            logger.info("Successfully deleted {} {}", resourceKind, name);
            return true;
        } else {
            logger.error("Failed to delete {} {}, reason is {}", resourceKind, name, result.getRawResult());
            return false;
        }
    }

    @Override
    public T update(T object, String project) {
        try {
            ReplaceCommand command = new ReplaceCommand(resourceKind, JsonUtil.convertToJson(object));
            command.setNamespace(project);
            ShellCommandResult<String> result = command.execute();
            if (result.getReturnCode() == BaseShellCommand.PROCESS_OK) {
                logger.info("Successfully updated {} {}", resourceKind, object.getMetadata().getName());
                logger.info("Retrieving new {}", object.getMetadata().getName());
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
    public T get(String name, String project) {
        GetResourceObjectCommand<T> getCommand = new GetResourceObjectCommand<>(typeReference, resourceKind, name);
        getCommand.setNamespace(project);
        ShellCommandResult<T> getCommandResult = getCommand.execute();
        if (getCommandResult.getReturnCode() == BaseShellCommand.PROCESS_OK && getCommandResult.getData().isPresent()) {
            logger.info("Got the new {} {}, returning", resourceKind, name);
            return getCommandResult.getData().get();
        } else {
            logger.error("Failed to get {} {}, reason is {}", resourceKind, name, getCommandResult.getRawResult());
            return null;
        }
    }

    @Override
    public List<T> getAllInProject(String project) {
        GetResourceObjectsCommand<T> getResourceObjectsCommand = new GetResourceObjectsCommand<>(apiTypeReference, resourceKind);
        getResourceObjectsCommand.setNamespace(project);
        ShellCommandResult<ListResult<T>> result = getResourceObjectsCommand.execute();
        if (result.getReturnCode() == BaseShellCommand.PROCESS_OK && result.getData().isPresent()) {
            return result.getData().get().getItems();
        } else {
            logger.error("Error getting all {}", resourceKind);
            return new ArrayList<>(1);
        }
    }

    @Override
    public List<T> getAllInAllProjects() {
        GetResourceObjectsCommand<T> command = new GetResourceObjectsCommand<>(apiTypeReference, resourceKind, true);
        ShellCommandResult<ListResult<T>> result = command.execute();
        if (result.getReturnCode() == BaseShellCommand.PROCESS_OK && result.getData().isPresent()) {
            return result.getData().get().getItems();
        } else {
            logger.error("Error getting all {}", resourceKind);
            return new ArrayList<>(1);
        }
    }

    @Override
    public T add(T object) {
        return add(object, null);
    }

    @Override
    public T add(String name) {
        return add(name, null);
    }

    @Override
    public boolean delete(String name) {
        return delete(name, null);
    }

    @Override
    public T update(T object) {
        return update(object, null);
    }

    @Override
    public T get(String name) {
        return get(name, null);
    }

    @Override
    public List<T> getAll() {
        return getAllInProject(null);
    }
}
