package com.project.wangyuming.wechat.service.impl;

import com.project.wangyuming.wechat.dao.LineMessageMapper;
import com.project.wangyuming.wechat.model.LineMessage;
import com.project.wangyuming.wechat.service.LineMessageService;
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
public class LineMessageServiceImpl extends AbstractService<LineMessage> implements LineMessageService {
    @Resource
    private LineMessageMapper lineMessageMapper;

}
