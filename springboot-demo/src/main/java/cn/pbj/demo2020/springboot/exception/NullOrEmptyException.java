package cn.pbj.demo2020.springboot.exception;

/**
 * @pClassName: NullOrEmptyException
 * @author: pengbingjiang
 * @create: 2020/9/8 15:26
 * @description: TODO
 */
public class NullOrEmptyException extends Exception{
    protected String messgae;

    public NullOrEmptyException(String message) {
        this.messgae = messgae;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }
}
