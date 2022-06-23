package com.jm.cloud.service.impl;

import com.jm.cloud.entity.DictionaryItem;
import com.jm.cloud.mapper.DictionaryItemMapper;
import com.jm.cloud.service.IDictionaryItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典类型条目表 服务实现类
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-23
 */
@Service
public class DictionaryItemServiceImpl extends ServiceImpl<DictionaryItemMapper, DictionaryItem> implements IDictionaryItemService {

}
