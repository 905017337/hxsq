package com.jm.cloud.entity.Request;

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
 * @since 2022-06-23
 */
@Data
public class PhoneTypeRequest implements Serializable {


    @NonNull
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
    private String order;

    /**
     * 创建时间
     */
    private Date crateTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
