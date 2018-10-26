package cn.com.gree.utils;

/**
 * @author Abin
 * @date 2018/8/7 14:20
 * 数据返回类，用于格式化返回数据
 */
public class Result {

    /** 标志 */
    private boolean success;

    /** 异常信息 */
    private String msg;

    /** 数据 */
    private Object data = null;


    public Result(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public Result(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
