package cn.pbj.demo2020.ssm.entity;

import lombok.Data;

/**
 * @pClassName: ErrorInfo
 * @author: pengbingjiang
 * @create: 2020/12/14 13:41
 * @description: TODO
 */
@Data
public class ErrorInfo<T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    private Integer code;
    private String message;
    private String url;
    private T data;
}
