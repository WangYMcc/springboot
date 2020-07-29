package com.project.wangyuming.wechat.model;

import javax.persistence.*;

@Table(name = "image_message")
public class ImageMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "toUser")
    private String touser;

    @Column(name = "fromUser")
    private String fromuser;

    @Column(name = "createTime")
    private String createtime;

    @Column(name = "msgType")
    private String msgtype;

    @Column(name = "mediaId")
    private String mediaid;

    @Column(name = "prcUrl")
    private String prcurl;

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
    public String getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(String createtime) {
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
     * @return mediaId
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
     * @return prcUrl
     */
    public String getPrcurl() {
        return prcurl;
    }

    /**
     * @param prcurl
     */
    public void setPrcurl(String prcurl) {
        this.prcurl = prcurl;
    }
}