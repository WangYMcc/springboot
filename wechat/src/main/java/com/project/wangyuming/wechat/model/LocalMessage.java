package com.project.wangyuming.wechat.model;

import javax.persistence.*;

@Table(name = "local_message")
public class LocalMessage {
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

    @Column(name = "Location_X")
    private String locationX;

    @Column(name = "Location_Y")
    private String locationY;

    @Column(name = "Scale")
    private String scale;

    @Column(name = "Label")
    private String label;

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
     * @return Location_X
     */
    public String getLocationX() {
        return locationX;
    }

    /**
     * @param locationX
     */
    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    /**
     * @return Location_Y
     */
    public String getLocationY() {
        return locationY;
    }

    /**
     * @param locationY
     */
    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    /**
     * @return Scale
     */
    public String getScale() {
        return scale;
    }

    /**
     * @param scale
     */
    public void setScale(String scale) {
        this.scale = scale;
    }

    /**
     * @return Label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
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