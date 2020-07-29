package com.project.wangyuming.wechat.service.impl;

import com.project.wangyuming.wechat.dao.VoiceMessageMapper;
import com.project.wangyuming.wechat.model.VoiceMessage;
import com.project.wangyuming.wechat.service.VoiceMessageService;
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
public class VoiceMessageServiceImpl extends AbstractService<VoiceMessage> implements VoiceMessageService {
    @Resource
    private VoiceMessageMapper voiceMessageMapper;

}
