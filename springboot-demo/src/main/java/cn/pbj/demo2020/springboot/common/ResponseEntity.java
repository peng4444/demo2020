package cn.pbj.demo2020.springboot.common;

/**
 * @pClassName: ResponseEntity
 * @author: pengbingjiang
 * @create: 2020/9/8 15:20
 * @description: TODO
 */
public class ResponseEntity <T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = 110;

    private Integer code;
    private String message;
    private String url;
    private T data;

    public ResponseEntity() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
