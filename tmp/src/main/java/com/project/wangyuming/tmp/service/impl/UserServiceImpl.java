package com.project.wangyuming.tmp.service.impl;

import com.project.wangyuming.tmp.dao.UserMapper;
import com.project.wangyuming.tmp.model.User;
import com.project.wangyuming.tmp.service.UserService;
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
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
