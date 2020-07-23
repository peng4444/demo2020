package cn.pbj.demo2020.manage.common;

import java.util.List;

/**
 * @pClassName: DataResult
 * @author: pengbingjiang
 * @create: 2020/7/23 19:51
 * @description: TODO
 */
public class DataResult<T> {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
