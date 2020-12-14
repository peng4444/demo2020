package cn.pbj.demo2020.ssm.service;

import java.util.List;

/**
 * @ClassName: MailService
 * @Author: pbj
 * @Date: 2019/8/9 14:05
 * @Description: TODO 邮件发送接口
 */
public interface MailService {
    /**
     * 发送文本消息
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param to 收件人(多个收件人，可变参数，多个时用逗号隔开)
     */
    void sendTextMail(String subject, String content, String... to);

    /**
     * 发送带附件邮件
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件得本地路径
     * @param fileName 附件名
     * @param to 收件人
     */
    void sendAttachmentMail(String subject, String content, String filePath, String fileName, String... to);

    /**
     * 发送一个邮件并抄送
     * @param subject 主题
     * @param content 内容
     * @param ccList 抄送人(可以有多个，list集合)
     * @param to 收件人(可以有多个)
     */
    void sendTextMailPlus(String subject, String content, List<String> ccList, String... to);


    /* *
     * 功能描述: 发送带图片资源的邮件
     * @param: [subject 主题, content 内容, srcPath 图片资源路径字符串, resIds 图片资源对应的id, to接收人集合]
     * @return: void
     * @auther: pbj
     * @date: 2019/8/10 15:06
     */
    void sendTextMailWithImg(String subject, String content, String[] srcPath,String[] resIds, String... to);
}
