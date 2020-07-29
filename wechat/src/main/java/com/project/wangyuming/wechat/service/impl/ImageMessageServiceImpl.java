package com.project.wangyuming.wechat.service.impl;

import com.project.wangyuming.wechat.dao.ImageMessageMapper;
import com.project.wangyuming.wechat.model.ImageMessage;
import com.project.wangyuming.wechat.service.ImageMessageService;
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
public class ImageMessageServiceImpl extends AbstractService<ImageMessage> implements ImageMessageService {
    @Resource
    private ImageMessageMapper imageMessageMapper;

}
