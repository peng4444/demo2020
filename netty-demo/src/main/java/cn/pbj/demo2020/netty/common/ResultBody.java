package cn.pbj.demo2020.netty.common;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: ResultBody
 * @Author: pbj
 * @Date: 2020/5/25 12:16
 * @Description: TODO
 */
public class ResultBody {
    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private String result;

    public ResultBody() {
    }

    public ResultBody(String code, String message, String result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
