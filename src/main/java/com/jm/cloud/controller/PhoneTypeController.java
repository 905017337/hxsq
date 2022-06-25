package com.jm.cloud.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.util.StringUtil;
import com.jm.cloud.entity.PhoneType;
import com.jm.cloud.entity.Phonebook;
import com.jm.cloud.entity.Response.PhoneTypeResponse;
import com.jm.cloud.service.IPhoneTypeService;
import com.jm.cloud.service.IPhonebookService;
import com.jm.cloud.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-23
 */
@Slf4j
@RestController
@RequestMapping("/phone-type")
public class PhoneTypeController {

    @Autowired
    private IPhoneTypeService phoneTypeService;
    @Autowired
    IPhonebookService iphonebookService;
    @GetMapping("/list")
    public Result list(String id){
        QueryWrapper<PhoneType> queryWrapper = new QueryWrapper<>();
        if(StringUtil.isNotEmpty(id)){
            queryWrapper.lambda().like(PhoneType::getId,id);
        }
        List<PhoneType> phoneTypes = phoneTypeService.list(queryWrapper);
        return Result.success(phoneTypes);
    }

    @GetMapping("/phoneBootlist")
    public Result phoneBootlist(@RequestParam(value = "name",required = false) String name){
        QueryWrapper<PhoneType> queryWrapper = new QueryWrapper<>();
        if(StringUtil.isNotEmpty(name)){
            queryWrapper.lambda().eq(PhoneType::getName,name);
        }
        List<PhoneType> phoneTypes = phoneTypeService.list(queryWrapper);
        ArrayList<PhoneTypeResponse> responses = new ArrayList<>();
        phoneTypes.stream().forEach(item->{
            PhoneTypeResponse phoneTypeResponse = new PhoneTypeResponse();
            QueryWrapper<Phonebook> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(Phonebook::getType,item.getOrderId());
            List<Phonebook> list = iphonebookService.list(wrapper);
            BeanUtils.copyProperties(item,phoneTypeResponse);
            phoneTypeResponse.setPhonebookList(list);
            responses.add(phoneTypeResponse);
        });

        return Result.success(responses);
    }

    @PostMapping("/add")
    public Result add(@RequestBody PhoneType phoneType){
        return Result.success(phoneTypeService.save(phoneType));
    }

    @PostMapping("/update")
    public Result update(@RequestBody PhoneType phoneType){
        return Result.success(phoneTypeService.updateById(phoneType));
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam(value = "id") int id){
        return Result.success(phoneTypeService.removeById(id));
    }

    /**
     * 首页搜索
     * @param name
     * @return
     */
    @GetMapping("/search")
    public Result search(String name){
        QueryWrapper<Phonebook> wrapper = new QueryWrapper<>();
        wrapper.lambda().like(Phonebook::getName,name);
        List<Phonebook> list = iphonebookService.list(wrapper);
        ArrayList<PhoneTypeResponse> result =  new ArrayList<>();
        PhoneTypeResponse phoneTypeResponse = new PhoneTypeResponse();
        phoneTypeResponse.setName("搜索结果");
        phoneTypeResponse.setPhonebookList(list);
        phoneTypeResponse.setId(1);
        result.add(phoneTypeResponse);
        return Result.success(result);
    }
}
