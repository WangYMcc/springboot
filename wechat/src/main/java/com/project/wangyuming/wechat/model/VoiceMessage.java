package com.project.wangyuming.wechat.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "voice_message")
public class VoiceMessage {
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

    @Column(name = "MsgId")
    private String msgid;

    @Column(name = "MediaId")
    private String mediaid;

    private String format;

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

    /**
     * @return MediaId
     */
    public String getMediaid() {
        return mediaid;
    }

    /**
     * @param mediaid
     */
    public void setMediaid(String mediaid) {
        this.mediaid = mediaid;
    }

    /**
     * @return format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format
     */
    public void setFormat(String format) {
        this.format = format;
    }
}