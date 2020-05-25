package cn.pbj.demo2020.springboot.aspect;

import cn.pbj.demo2020.springboot.common.ResultBody;
import cn.pbj.demo2020.springboot.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @ClassName: AspectTest
 * @Author: pbj
 * @Date: 2020/5/25 12:21
 * @Description: TODO SpringBoot切面Aop的demo简单讲解
 */
@Aspect
@Component
public class AspectTest {
    @Pointcut("execution(public * cn.pbj.demo2020.springboot.web.*.*(..))")
    public void doOperation() {
    }


    /**
     * @Title: before
     * @Description: 前置通知处理方法
     *    在处理之前调用，比如参数、权限校验
     * @param joinPoint
     */
    @Before("doOperation()")
    public void before(JoinPoint joinPoint) throws Throwable{
        Object[] objs = joinPoint.getArgs();
        for (Object obj : objs) {
            User user =(User) obj;
            System.out.println("前置通知接受的参数:"+user);
            String name =base64DeStr(user.getName());
            user.setName(name);
        }
    }




    @AfterReturning(returning = "object", pointcut = "doOperation()")
    public void doAfterReturning(Object object) {
        ResultBody resultBody = (ResultBody) object;
        String str =null;
        try {
            str=base64EnStr(resultBody.getResult());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        resultBody.setResult(str);
        System.out.println("后通知响应的参数:"+resultBody);
    }


    public  String base64EnStr(String str) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
    }


    public static String base64DeStr(String encodeStr) throws UnsupportedEncodingException {
        byte[] decodeStr = Base64.getDecoder().decode(encodeStr);
        return new String(decodeStr, "UTF-8");
    }

}
