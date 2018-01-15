package ibm.wjx.osserver.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import ibm.wjx.osserver.constant.ResultCode;
import ibm.wjx.osserver.constant.ResultMessage;
import ibm.wjx.osserver.pojo.BasePojo;
import ibm.wjx.osserver.pojo.ListResult;
import ibm.wjx.osserver.pojo.Result;
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
    public Result<T> add(T object, String project) {
        try {
            CreateFromJsonCommand command = new CreateFromJsonCommand(resourceKind, JsonUtil.convertToJson(object));
            command.setNamespace(project);
            ShellCommandResult<String> result = command.execute();
            if (result.getReturnCode() == BaseShellCommand.PROCESS_OK) {
                logger.info("Successfully created {} {}", resourceKind, object.getMetadata().getName());
                return get(object.getMetadata().getName(), project);
            } else {
                logger.error("Create {} {} failed, reason is {}", resourceKind, object.getMetadata().getName(), result.getRawResult());
                return Result.newFailResult(null, result.getReturnCode(), result.getRawResult());
            }
        } catch (JsonProcessingException e) {
            logger.error("Convert {} into json failed", resourceKind);
            logger.error(ExceptionLogUtil.getStacktrace(e));
            return Result.newFailResult(null, ResultCode.JSON_CONVERT_ERROR, ResultMessage.CONVERT_FROM_JSON_ERROR);
        }
    }

    @Override
    public Result<T> add(String name, String project) {
        CreateResourceCommand command = new CreateResourceCommand(resourceKind, name);
        command.setNamespace(project);
        ShellCommandResult<String> result = command.execute();
        if (result.getReturnCode() == BaseShellCommand.PROCESS_OK) {
            logger.info("Successfully created {} {}", resourceKind, name);
            logger.info("Getting the new created {} {}", resourceKind, name);
            return get(name, project);
        } else {
            logger.error("Failed to create {} {}, reason is {}", resourceKind, name, result.getRawResult());
            return Result.newFailResult(null, result.getReturnCode(), result.getRawResult());
        }
    }

    @Override
    public Result<Boolean> delete(String name, String project) {
        DeleteResourceCommand command = new DeleteResourceCommand(resourceKind, name);
        command.setNamespace(project);
        ShellCommandResult<String> result = command.execute();
        if (result.getReturnCode() == BaseShellCommand.PROCESS_OK) {
            logger.info("Successfully deleted {} {}", resourceKind, name);
            return Result.newSuccessResult(true);
        } else {
            logger.error("Failed to delete {} {}, reason is {}", resourceKind, name, result.getRawResult());
            return Result.newFailResult(false, result.getReturnCode(), result.getRawResult());
        }
    }

    @Override
    public Result<T> update(T object, String project) {
        try {
            ReplaceCommand command = new ReplaceCommand(resourceKind, JsonUtil.convertToJson(object));
            command.setNamespace(project);
            ShellCommandResult<String> result = command.execute();
            if (result.getReturnCode() == BaseShellCommand.PROCESS_OK) {
                logger.info("Successfully updated {} {}", resourceKind, object.getMetadata().getName());
                logger.info("Retrieving new {}", object.getMetadata().getName());
                Result<T> getResult = get(object.getMetadata().getName());
                if (getResult.getResultCode() == ResultCode.SUCCESS) {
                    return Result.newSuccessResult(getResult.getData());
                } else {
                    return Result.newFailResult(object, ResultCode.OTHER, getResult.getMessage());
                }
            } else {
                logger.error("Failed to update {} {}, reason is {}", resourceKind, object.getMetadata().getName(), result.getRawResult());
                return Result.newFailResult(object, result.getReturnCode(), result.getRawResult());
            }
        } catch (JsonProcessingException e) {
            logger.error("Convert {} into json failed", resourceKind);
            logger.error(ExceptionLogUtil.getStacktrace(e));
            return Result.newFailResult(object, ResultCode.JSON_CONVERT_ERROR, ResultMessage.CONVERT_FROM_JSON_ERROR);
        }
    }

    @Override
    public Result<T> get(String name, String project) {
        GetResourceObjectCommand<T> getCommand = new GetResourceObjectCommand<>(typeReference, resourceKind, name);
        getCommand.setNamespace(project);
        ShellCommandResult<T> getCommandResult = getCommand.execute();
        if (getCommandResult.getReturnCode() == BaseShellCommand.PROCESS_OK && getCommandResult.getData().isPresent()) {
            logger.info("Got the new {} {}, returning", resourceKind, name);
            return Result.newSuccessResult(getCommandResult.getData().get());
        } else {
            logger.error("Failed to get {} {}, reason is {}", resourceKind, name, getCommandResult.getRawResult());
            return Result.newFailResult(null, getCommandResult.getReturnCode(), getCommandResult.getRawResult());
        }
    }

    @Override
    public Result<List<T>> getAllInProject(String project) {
        return getAllInProject(project, false);
    }

    @Override
    public Result<List<T>> getAllInAllProjects() {
        return getAllInProject(null, true);
    }

    private Result<List<T>> getAllInProject(String project, boolean isAllNamespace) {
        GetResourceObjectsCommand<T> command;
        if (isAllNamespace) {
            command = new GetResourceObjectsCommand<>(apiTypeReference, resourceKind, isAllNamespace);
        } else {
            command = new GetResourceObjectsCommand<>(apiTypeReference, resourceKind);
            command.setNamespace(project);
        }
        ShellCommandResult<ListResult<T>> result = command.execute();
        if (result.getReturnCode() == BaseShellCommand.PROCESS_OK && result.getData().isPresent()) {
            return Result.newSuccessResult(result.getData().get().getItems());
        } else {
            logger.error("Error getting all {}", resourceKind);
            Result<List<T>> errorResult = new Result<>();
            errorResult.setMessage(result.getRawResult());
            errorResult.setResultCode(result.getReturnCode());
            return errorResult;
        }
    }

    @Override
    public Result<T> add(T object) {
        return add(object, null);
    }

    @Override
    public Result<T> add(String name) {
        return add(name, null);
    }

    @Override
    public Result<Boolean> delete(String name) {
        return delete(name, null);
    }

    @Override
    public Result<T> update(T object) {
        return update(object, null);
    }

    @Override
    public Result<T> get(String name) {
        return get(name, null);
    }

    @Override
    public Result<List<T>> getAll() {
        return getAllInProject(null);
    }
}
