package com.project.wangyuming.wechat.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandleMessage {

    public static String responseWechat(String echostr, String signature, String nonce, String openid, String timestamp, HttpServletRequest request, HttpServletResponse response) {
        //微信接入初始化响应配置
        if(echostr != null && signature != null && nonce != null && timestamp != null){
            return request.getParameter("echostr");

        }else {

        }

        return "";
    }
}
