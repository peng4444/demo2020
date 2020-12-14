package cn.pbj.demo2020.ssm.exception;

/**
 * @pClassName: MyException
 * @author: pengbingjiang
 * @create: 2020/12/14 13:41
 * @description: TODO 自定义异常
 */
public class MyException extends Exception{
    public MyException(String message) {
        super(message);
    }
}
