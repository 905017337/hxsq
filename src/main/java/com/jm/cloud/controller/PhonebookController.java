package com.jm.cloud.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.util.StringUtil;
import com.jm.cloud.entity.Phonebook;
import com.jm.cloud.entity.Request.UserItem;
import com.jm.cloud.service.IPhonebookService;
import com.jm.cloud.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-21
 */
@Slf4j
@RestController
@RequestMapping("/phone-book")
public class PhonebookController {

    @Autowired
    IPhonebookService iphonebookService;

    /**
     * 查询列表
     * @param name
     * @return
     */
    @GetMapping("/list")
    public Result list(String name){
        QueryWrapper<Phonebook> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Phonebook::getStatus,1);
        if(!StringUtil.isEmpty(name)){
            wrapper.lambda()
                    .likeRight(Phonebook::getName,name)
                    .likeLeft(Phonebook::getName,name);
        }
        return Result.success(iphonebookService.list(wrapper));
    }

    /**
     * 添加用户
     * @param phonebook
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Phonebook phonebook){
        return Result.success(iphonebookService.save(phonebook));
    }

    /**
     * 查询指定用户
     * @param id
     * @return
     */
    @GetMapping("/item")
    public Result item(String id){
        QueryWrapper<Phonebook> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Phonebook::getId,id);
        return Result.success(iphonebookService.list(queryWrapper));

    }


    /**
     * 修改用户审核状态
     * @param id
     * @return
     */
    @PostMapping("/updateStats")
    public Result updateStats(Integer id){
        Phonebook phonebook = new Phonebook();
        phonebook.setId(id);
        phonebook.setStatus(1);
        return Result.success(iphonebookService.updateById(phonebook));
    }

    /**
     * 用户推荐未审核的联系人
     * @param userItem
     * @return
     */
    @PostMapping("/addPhone")
    public Result addPhone(@RequestBody UserItem userItem){
        QueryWrapper<Phonebook> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Phonebook::getPhone,userItem.getPhone());
        List<Phonebook> list = iphonebookService.list(wrapper);
        if(list.isEmpty()){
            Phonebook phonebook = new Phonebook();
            phonebook.setName(userItem.getName());
            phonebook.setPhone(userItem.getPhone());
            phonebook.setAddress(userItem.getAddress());
            phonebook.setRemark(userItem.getUserType());
            phonebook.setStatus(0);
            return Result.success(iphonebookService.save(phonebook));
        }
        return Result.failed("该用户已存在");
    }
}
