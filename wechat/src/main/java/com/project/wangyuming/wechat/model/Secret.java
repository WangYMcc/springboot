package com.project.wangyuming.wechat.model;

import javax.persistence.*;

public class Secret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer appid;

    private String secret;

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
     * @return appid
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * @param appid
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    /**
     * @return secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * @param secret
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }
}