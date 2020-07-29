package com.project.wangyuming.wechat.service.impl;

import com.project.wangyuming.wechat.dao.SecretMapper;
import com.project.wangyuming.wechat.model.Secret;
import com.project.wangyuming.wechat.service.SecretService;
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
public class SecretServiceImpl extends AbstractService<Secret> implements SecretService {
    @Resource
    private SecretMapper secretMapper;

}
