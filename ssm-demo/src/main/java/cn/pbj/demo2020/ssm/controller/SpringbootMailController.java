package cn.pbj.demo2020.ssm.controller;

import cn.pbj.demo2020.ssm.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SpringbootMailTest
 * @Author: pbj
 * @Date: 2019/8/8 23:44
 * @Description: TODO
 * [Spring Boot 邮件发送的 5 种姿势！](https://www.cnblogs.com/lenve/p/11198718.html)
 * [springboot+kafka+邮件发送（最佳实践）](https://www.cnblogs.com/729log/p/11283096.html)
 *  [Spring Boot2(十三)：整合定时任务发送邮件](https://www.cnblogs.com/niaobulashi/p/schedule-mail.html)
 *  [SpringBoot整合Mail发送邮件&发送模板邮件](https://www.cnblogs.com/ruiyeclub/p/13394493.html)
 */
public class SpringbootMailController {

    @Autowired
    MailService mailService;

    public void TestSendTextMail() {
        mailService.sendTextMail("测试邮件发送主题", "测试邮件发送内容",
                "1784675177@qq.com", "pengbingjiang4@163.com");
    }

    public void TestSendTextMailAndAttach() {
        mailService.sendAttachmentMail("测试邮件和附件发送主题", "测试邮件和附件发送内容",
                "d:/", "testemailattach.txt", "1234@qq.com", "12345@qq.com", "123456@qq.com");
    }

    public void TestSendTextMailAndManyCC() {
        List<String> cc = new ArrayList<>();
        cc.add("抄送人1");
        cc.add("抄送人2");
        cc.add("抄送人3");
        mailService.sendTextMailPlus("测试邮件发送和多抄送人主题", "测试邮件发送和多抄送人内容",
                cc, "1234@qq.com", "12345@qq.com", "123456@qq.com");
    }

    public void testSendMailWithImg() {
        mailService.sendTextMailWithImg("测试邮件发送和图片",
                "<div>hello,这是一封带图片的资源的邮件："+
                        "这个图片1：<div><img src='cid:p01'/></div>"+
                        "这是图片2：<div><img src='cid:p02'/></div>"+"</div>",
                new String[]{"C:\\test\\1.png","E:\\test\\2.png"},
                new String[]{"p01","p02"});
    }
}
