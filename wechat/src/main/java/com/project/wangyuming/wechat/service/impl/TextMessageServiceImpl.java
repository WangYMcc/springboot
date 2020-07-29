package com.project.wangyuming.wechat.service.impl;

import com.project.wangyuming.wechat.dao.TextMessageMapper;
import com.project.wangyuming.wechat.model.TextMessage;
import com.project.wangyuming.wechat.service.TextMessageService;
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
public class TextMessageServiceImpl extends AbstractService<TextMessage> implements TextMessageService {
    @Resource
    private TextMessageMapper textMessageMapper;

}
