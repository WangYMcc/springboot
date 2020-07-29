package com.project.wangyuming.wechat.controller;


import com.project.wangyuming.wechat.util.HandleMessage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class WechatController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @ApiOperation("wechat-Get")
    @GetMapping("/")
    public String wechatGet(@RequestParam(value="echostr", required = false)String echostr,
                         @RequestParam(value="signature", required = false)String signature,
                         @RequestParam(value="nonce", required = false)String nonce,
                         @RequestParam(value="openid", required = false)String openid,
                         @RequestParam(value="timestamp", required = false)String timestamp){

        System.out.println(echostr);
        System.out.println(signature);
        System.out.println(nonce);
        System.out.println(openid);
        System.out.println(timestamp);

        return HandleMessage.responseWechat(echostr, signature, nonce, openid, timestamp, request, response);
    }

    @ApiOperation("wechat-Post")
    @PostMapping("/")
    public String wechatPost(@RequestParam(value="echostr", required = false)String echostr,
                         @RequestParam(value="signature", required = false)String signature,
                         @RequestParam(value="nonce", required = false)String nonce,
                         @RequestParam(value="openid", required = false)String openid,
                         @RequestParam(value="timestamp", required = false)String timestamp){

        System.out.println(echostr);
        System.out.println(signature);
        System.out.println(nonce);
        System.out.println(openid);
        System.out.println(timestamp);

        return HandleMessage.responseWechat(echostr, signature, nonce, openid, timestamp, request, response);
    }
}
