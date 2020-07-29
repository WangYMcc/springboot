package com.project.wangyuming.wechat.service.impl;

import com.project.wangyuming.wechat.dao.ImageWithItemMapper;
import com.project.wangyuming.wechat.model.ImageWithItem;
import com.project.wangyuming.wechat.service.ImageWithItemService;
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
public class ImageWithItemServiceImpl extends AbstractService<ImageWithItem> implements ImageWithItemService {
    @Resource
    private ImageWithItemMapper imageWithItemMapper;

}
