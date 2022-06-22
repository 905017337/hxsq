package com.jm.cloud.service.impl;

import com.jm.cloud.entity.Phonebook;
import com.jm.cloud.mapper.PhonebookMapper;
import com.jm.cloud.service.IPhonebookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-21
 */
@Service
public class PhonebookServiceImpl extends ServiceImpl<PhonebookMapper, Phonebook> implements IPhonebookService {

}
