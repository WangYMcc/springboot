package com.project.wangyuming.wechat.model;

import javax.persistence.*;

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    @Column(name = "picUrl")
    private String picurl;

    private String url;

    private Integer textwithitemid;

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
     * @return title
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
     * @return description
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
     * @return picUrl
     */
    public String getPicurl() {
        return picurl;
    }

    /**
     * @param picurl
     */
    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    /**
     * @return url
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
     * @return textwithitemid
     */
    public Integer getTextwithitemid() {
        return textwithitemid;
    }

    /**
     * @param textwithitemid
     */
    public void setTextwithitemid(Integer textwithitemid) {
        this.textwithitemid = textwithitemid;
    }
}