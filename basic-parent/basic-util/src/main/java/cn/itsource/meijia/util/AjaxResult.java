package cn.itsource.meijia.util;

public class AjaxResult {

    private boolean success = true;
    private String message;
    private String errorCode; //用来设置出现错误时的错误码的
    private Object data; //封装数据的

    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置值后把当前对象作为方法的返回值   链式编程
     *
     * 失败：   添加失败
     * AjaxResult.me().setSuccess(false).setMessage("添加失败");
     *
     * @param success
     * @return
     */
    public AjaxResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public AjaxResult setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Object getData() {
        return data;
    }

    public AjaxResult setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 这是成功的返回，这样封装是为了方便连续调用
     * @return
     */
    public static AjaxResult me() {
        return new AjaxResult();
    }
}
