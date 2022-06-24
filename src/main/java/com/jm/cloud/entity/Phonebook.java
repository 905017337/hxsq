package com.jm.cloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

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

    private Integer id;

    private String name;

    private String phone;

    private String address;

    private String type;

    private Date createTime;

    private Date updateTime;

    private Integer status;


}
