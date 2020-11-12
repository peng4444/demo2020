package cn.pbj.demo2020.myblog.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @pClassName: DataResult
 * @author: pengbingjiang
 * @create: 2020/11/12 15:11
 * @description: TODO
 */
@Data
public class DataResult implements Serializable {

    private int code; // 200是正常，非200表示异常
    private String msg;
    private Object data;

    public static DataResult succ(Object data) {
        return succ(200, "操作成功", data);
    }

    public static DataResult succ(int code, String msg, Object data) {
        DataResult r = new DataResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static DataResult fail(String msg) {
        return fail(400, msg, null);
    }

    public static DataResult fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static DataResult fail(int code, String msg, Object data) {
        DataResult r = new DataResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
