package com.project.wangyuming.wechat.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "image_with_item")
public class ImageWithItem {
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
}