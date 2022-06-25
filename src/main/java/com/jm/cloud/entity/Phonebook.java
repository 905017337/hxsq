package com.jm.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("phone_book")
public class Phonebook implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    private String name;

    private String phone;

    private String address;

    private String type;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private String remark;


}
