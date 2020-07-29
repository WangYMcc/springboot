package com.project.wangyuming.wechat.service.impl;

import com.project.wangyuming.wechat.dao.TokenMapper;
import com.project.wangyuming.wechat.model.Token;
import com.project.wangyuming.wechat.service.TokenService;
import com.project.wangyuming.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author wangyuming
 * @date 2020/05/16
 */
@Service
@Transactional
public class TokenServiceImpl extends AbstractService<Token> implements TokenService {
    @Resource
    private TokenMapper tokenMapper;

}
