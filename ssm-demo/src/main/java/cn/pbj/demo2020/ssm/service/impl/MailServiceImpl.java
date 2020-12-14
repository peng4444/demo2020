package cn.pbj.demo2020.ssm.service.impl;

import cn.pbj.demo2020.ssm.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: MailService
 * @Author: pbj
 * @Date: 2019/8/8 23:27
 * @Description: TODO 封装邮件的发送
 * 【查看资料】(https://www.cnblogs.com/love-wzy/p/10345817.html)
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender javaMailSender;
    /* *
     * 功能描述: 测试邮件发送【关键是获取到邮件发送的授权码】
     * @param: [from 发送人, to 收件人, cc 抄送人, subject 邮件主题, content 邮件内容] 抄送人可以不需要
     * @return: void
     * @auther: pbj
     * @date: 2019/8/8 23:33
     */

    //发件人邮箱 在配置文件中已经配置
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendTextMail(String subject, String content, String... to) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setSentDate(new Date());
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendAttachmentMail(String subject, String content, String filePath, String fileName, String... to) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            //附件
            FileSystemResource file = new FileSystemResource(new File(filePath));
            helper.addAttachment(fileName, file);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendTextMailPlus(String subject, String content, List<String> ccList, String... to) {
        //收件人地址
        InternetAddress[] addressesTo = new InternetAddress[to.length];
        if(to != null && to.length>0){
            for(int i=0;i<to.length;i++){
                InternetAddress addressTo = null;
                try {
                    addressTo = new InternetAddress(to[i]);
                    addressesTo[i] = addressTo;
                } catch (AddressException e) {
                    e.printStackTrace();
                }
            }
        }
        //抄送人地址
        InternetAddress[] addressesCc = new InternetAddress[ccList.size()];
        if(ccList != null && ccList.size() > 0){
            for(int i=0;i<ccList.size();i++){
                String ccAdd = ccList.get(i);
                InternetAddress address = null;
                try {
                    address = new InternetAddress(ccAdd);
                    addressesCc[i] = address;
                } catch (AddressException e) {
                    e.printStackTrace();
                }
            }
        }

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom(from);
                mimeMessage.setSubject(subject);
                mimeMessage.setText(content);
                mimeMessage.setRecipients(Message.RecipientType.TO, addressesTo);
                mimeMessage.setRecipients(Message.RecipientType.CC, addressesCc);
            }
        };
        javaMailSender.send(preparator);
    }

    @Override
    public void sendTextMailWithImg(String subject, String content, String[] srcPath, String[] resIds, String... to) {
        if (srcPath.length != resIds.length) {
            System.out.println("发送失败");
            return;
        }

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);
            for (int i = 0; i < srcPath.length; i++) {
                FileSystemResource fileSystemResource = new FileSystemResource(new File(srcPath[i]));
                mimeMessageHelper.addInline(resIds[i],fileSystemResource);
            }
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSentDate(new Date());
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("发送失败");
        }

    }
}
