/*
 * Copyright (c) 2020.
 */
package com.project.utils;

/**
 * 邮件
 *
 * @author 汪强
 * 创建时间 2020-05-19 16:24
 */
public class MailBean {

    /**
     * 收件人
     */
    private String[] toMails;
    /**
     * 抄送人
     */
    private String[] ccMails;
    /**
     * 发件人
     */
    private String fromMail;
    /**
     * smtp主机
     */
    private String host;
    /**
     * 发件箱用户名
     */
    private String userName;
    /**
     * 发件箱密码
     */
    private String password;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 本地附件路径
     */
    private String[] attachFiles;
    /**
     * 附件名称
     */
    private String[] attachFileNames;

    public MailBean() {
    }

    public MailBean(String fromMail, String host, String userName, String password) {
        this.fromMail = fromMail;
        this.host = host;
        this.userName = userName;
        this.password = password;
    }

    public String[] getToMails() {
        if (toMails == null) {
            return null;
        }
        return toMails.clone();
    }

    public void setToMails(String[] toMails) {
        if (toMails == null) {
            this.toMails = null;
        } else {
            this.toMails = toMails.clone();
        }
    }

    public String[] getCcMails() {
        if (ccMails == null) {
            return null;
        }
        return ccMails.clone();
    }

    public void setCcMails(String[] ccMails) {
        if (ccMails == null) {
            this.ccMails = null;
        } else {
            this.ccMails = ccMails.clone();
        }
    }

    public String getFromMail() {
        return fromMail;
    }

    public void setFromMail(String fromMail) {
        if (fromMail == null) {
            this.fromMail = null;
        } else {
            this.fromMail = fromMail;
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getAttachFiles() {
        if (attachFiles == null) {
            return null;
        }
        return attachFiles.clone();
    }

    public void setAttachFiles(String[] attachFiles) {
        if (attachFiles == null) {
            this.attachFiles = null;
        } else {
            this.attachFiles = attachFiles.clone();
        }
    }

    public String[] getAttachFileNames() {
        if (attachFileNames == null) {
            return null;
        }
        return attachFileNames.clone();
    }

    public void setAttachFileNames(String[] attachFileNames) {
        if (attachFileNames == null) {
            this.attachFileNames = null;
        } else {
            this.attachFileNames = attachFileNames.clone();
        }
    }
}
