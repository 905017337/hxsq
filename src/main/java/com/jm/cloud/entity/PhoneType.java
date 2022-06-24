package com.jm.cloud.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PhoneType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 类型名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private String orderId;

    /**
     * 创建时间
     */
    private Date crateTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
