/*
 * Copyright (c) 2020.
 */
package com.project.utils;

import com.project.utils.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-05-19 16:24
 */
public class MailUtils {
    private final static Logger logger = LoggerFactory.getLogger(MailUtils.class);

    /**
     * 发送邮件
     *
     * @param mailBean 邮件对象
     * @return 是否发送成功
     */
    public static boolean sendMail(MailBean mailBean) {
        if (mailBean == null) {
            return false;
        }
        String host = mailBean.getHost();
        String user = mailBean.getUserName();
        String password = mailBean.getPassword();

        Properties prop = initProperties(host);

        //1.创建session
        Session session = Session.getInstance(prop);
        //开启session的debug模式，可以看到程序发送email的状态
        session.setDebug(true);

        //2.通过session得到transport对象
        Transport ts = null;
        try {
            ts = session.getTransport();
        } catch (NoSuchProviderException e) {
            logger.error("", e);
        }
        //未创建成功，则提返回失败
        if (ts == null) {
            return false;
        }

        try {
            //3.使用邮箱的用户名和密码连上邮件服务器
            ts.connect(host, user, password);
            //4.创建邮件
            Message message = createSimpleMail(session, mailBean);
            if (message == null) {
                return false;
            }
            //5.发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            return true;
        } catch (MessagingException e) {
            logger.error("", e);
        } finally {
            try {
                ts.close();
            } catch (MessagingException e) {
                logger.error("", e);
            }
        }
        return false;
    }


    /**
     * 创建简单邮件对象
     *
     * @param session  当前会话
     * @param mailBean 邮件基本对象
     *                 fromMail 发件人
     *                 toMails  收件人
     *                 ccMails  抄送人
     *                 subject  主题
     *                 content  内容
     * @return 返回邮件对象
     */
    private static MimeMessage createSimpleMail(Session session, MailBean mailBean) {
        MimeMessage message = new MimeMessage(session);
        String fromMail = mailBean.getFromMail();
        String[] toMails = mailBean.getToMails();
        String[] ccMails = mailBean.getCcMails();
        String subject = mailBean.getSubject();
        String content = mailBean.getContent();

        //收件人和发件人不能为空
        if (StringUtils.isEmpty(fromMail) || toMails == null) {
            return null;
        }

        try {
            //设置发件人
            message.setFrom(new InternetAddress(fromMail));
            //设置多收件人
            Address[] toAdresses = new InternetAddress[toMails.length];
            for (int i = 0; i < toMails.length; i++) {
                toAdresses[i] = new InternetAddress(toMails[i]);
            }
            message.setRecipients(Message.RecipientType.TO, toAdresses);

            //设置抄送人
            if (ccMails != null) {
                Address[] ccAdresses = new InternetAddress[ccMails.length];
                for (int i = 0; i < ccMails.length; i++) {
                    ccAdresses[i] = new InternetAddress(ccMails[i]);
                }
                message.setRecipients(Message.RecipientType.CC, ccAdresses);
            }

            //设置主题
            message.setSubject(subject);
            //设置内容
            message.setContent(content, "text/html;charset=UTF-8");
        } catch (MessagingException e) {
            logger.error("", e);
        }
        return message;
    }


    /**
     * 初始化邮件properties
     *
     * @param host
     * @return
     */
    private static Properties initProperties(String host) {
        if (StringUtils.isEmpty(host)) {
            return null;
        }
        Properties prop = new Properties();
        prop.setProperty("mail.host", host);
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        return prop;
    }
}
