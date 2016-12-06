package com.zlzkj.app.utils.AjaxResult;

/**
 * Created by Administrator on 2016/12/6 0006.
 */
public class Result {

    /**
     * 请求结果是否成功
     */
    private String ErrorCode = ResultCode.SUCCESS.getCode();

    /**
     * 请求返回信息
     */
    private String Message = ActionConstants.DEFAULT_SUCCESS_RETURNMSG;

    /**
     * 请求结果
     */
    private Object Data = null;


    /**
     * Instantiates a new Ajax result.
     */
    private Result(){}

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    /**
     * 获取正确结果模板
     *
     * @param message 请求返回信息
     * @param obj 请求结果
     * @return AjaxResult
     */
    public static Result getOK(String message, Object obj){
        Result result = new Result();
        result.setMessage(message);
        result.setData(obj);
        return result;
    }
    /**
     * 获取正确结果模板
     *
     * @param obj 请求结果
     * @return AjaxResult
     */
    public static Result getOK(Object obj){
        Result result = new Result();
        result.setMessage(ActionConstants.DEFAULT_SUCCESS_RETURNMSG);
        result.setData(obj);
        return result;
    }

    /**
     * 获取错误结果模板
     *
     * @param message 请求返回信息
     * @param obj 请求结果
     * @return AjaxResult
     */
    public static Result getError(ResultCode errorCode, String message, Object obj){
        Result result = new Result();
        result.setErrorCode(errorCode.getCode());
        result.setMessage(message);
        result.setData(obj);
        return result;
    }

    /**
     * 获取正确结果模板
     *
     * @return AjaxResult
     */
    public static Result getOK(){
        return getOK(ActionConstants.DEFAULT_SUCCESS_RETURNMSG,null);
    }


    /**
     * 获取错误结果模板
     *
     * @return AjaxResult
     */
    public static Result getError(ResultCode resultCode){
        return getError(resultCode, ActionConstants.DEFAULT_FAILED_RETURNMSG, null);
    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "ErrorCode='" + ErrorCode + '\'' +
                ", Message='" + Message + '\'' +
                ", Data=" + Data +
                '}';
    }
}
