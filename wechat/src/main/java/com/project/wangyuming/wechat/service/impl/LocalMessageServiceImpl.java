package com.project.wangyuming.wechat.service.impl;

import com.project.wangyuming.wechat.dao.LocalMessageMapper;
import com.project.wangyuming.wechat.model.LocalMessage;
import com.project.wangyuming.wechat.service.LocalMessageService;
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
public class LocalMessageServiceImpl extends AbstractService<LocalMessage> implements LocalMessageService {
    @Resource
    private LocalMessageMapper localMessageMapper;

}
