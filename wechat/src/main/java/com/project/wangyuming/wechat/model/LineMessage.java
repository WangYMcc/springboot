package com.project.wangyuming.wechat.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "line_message")
public class LineMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "toUser")
    private String touser;

    @Column(name = "fromUser")
    private String fromuser;

    @Column(name = "createTime")
    private Date createtime;

    @Column(name = "msgType")
    private String msgtype;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Url")
    private String url;

    @Column(name = "MsgId")
    private String msgid;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return toUser
     */
    public String getTouser() {
        return touser;
    }

    /**
     * @param touser
     */
    public void setTouser(String touser) {
        this.touser = touser;
    }

    /**
     * @return fromUser
     */
    public String getFromuser() {
        return fromuser;
    }

    /**
     * @param fromuser
     */
    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }

    /**
     * @return createTime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return msgType
     */
    public String getMsgtype() {
        return msgtype;
    }

    /**
     * @param msgtype
     */
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    /**
     * @return Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return MsgId
     */
    public String getMsgid() {
        return msgid;
    }

    /**
     * @param msgid
     */
    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }
}