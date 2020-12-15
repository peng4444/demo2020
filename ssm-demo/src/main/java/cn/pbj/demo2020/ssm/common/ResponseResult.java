package cn.pbj.demo2020.ssm.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @pClassName: ResponseResult
 * @author: pengbingjiang
 * @create: 2020/12/15 09:47
 * @description: TODO
 */
@Data
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    private ResponseResult(StatusEnums responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    private ResponseResult(StatusEnums responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    private ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 返回成功信息
     * @param data      信息内容
     * @param <T>
     * @return
     */
    public static<T> ResponseResult success(T data) {
        return new ResponseResult<>(StatusEnums.SUCCESS, data);
    }

    /**
     * 返回成功信息
     * @return
     */
    public static ResponseResult success() {
        return new ResponseResult(StatusEnums.SUCCESS);
    }

    /**
     * 返回错误信息
     * @param statusEnums      响应码
     * @return
     */
    public static ResponseResult error(StatusEnums statusEnums) {
        return new ResponseResult(statusEnums);
    }
}
