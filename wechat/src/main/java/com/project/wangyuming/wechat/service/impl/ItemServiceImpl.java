package com.project.wangyuming.wechat.service.impl;

import com.project.wangyuming.wechat.dao.ItemMapper;
import com.project.wangyuming.wechat.model.Item;
import com.project.wangyuming.wechat.service.ItemService;
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
public class ItemServiceImpl extends AbstractService<Item> implements ItemService {
    @Resource
    private ItemMapper itemMapper;

}
