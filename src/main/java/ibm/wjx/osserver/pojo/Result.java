package ibm.wjx.osserver.pojo;

import ibm.wjx.osserver.constant.ResultCode;
import ibm.wjx.osserver.constant.ResultMessage;

/**
 * Create Date: 1/15/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class Result<T> {
    private int resultCode;
    private String message;
    private T data;

    public Result() {
    }

    public static <ResultType> Result<ResultType> newSuccessResult(ResultType data) {
        Result<ResultType> result = new Result<>();
        result.setData(data);
        result.setResultCode(ResultCode.SUCCESS);
        result.setMessage(ResultMessage.SUCCESS);
        return result;
    }

    public static <ResultType> Result<ResultType> newFailResult(ResultType data, int resultCode, String message) {
        Result<ResultType> result = new Result<>();
        result.setResultCode(resultCode);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <ResultType> Result<ResultType> convertFromOther(ResultType data, Result otherResult) {
        Result<ResultType> result = new Result<>();
        result.setResultCode(otherResult.getResultCode());
        result.setMessage(otherResult.getMessage());
        result.setData(data);
        return result;
    }

    public int getResultCode() {

        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
